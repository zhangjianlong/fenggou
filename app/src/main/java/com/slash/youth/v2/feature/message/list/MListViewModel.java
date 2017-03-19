package com.slash.youth.v2.feature.message.list;


import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.view.animation.CycleInterpolator;

import com.core.op.bindingadapter.common.ItemView;
import com.core.op.lib.base.BFViewModel;
import com.core.op.lib.command.ReplyCommand;
import com.core.op.lib.di.PerActivity;
import com.core.op.lib.messenger.Messenger;
import com.core.op.lib.utils.JsonUtil;
import com.slash.youth.BR;
import com.slash.youth.R;
import com.slash.youth.databinding.FrgMlistBinding;
import com.slash.youth.domain.bean.ConversationBean;
import com.slash.youth.domain.interactor.message.ConversationsUseCase;
import com.slash.youth.v2.util.MessageKey;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import rx.Observable;

import static android.R.attr.data;

@PerActivity
public class MListViewModel extends BFViewModel<FrgMlistBinding> {

    private boolean isComplate = false;

    private int pageSize = 0;
    private int limit = 20;

    public final ItemView itemView = ItemView.of(BR.viewModel, R.layout.item_message);
    public final ObservableList<MListItemViewModel> itemViewModels = new ObservableArrayList<>();

    public final List<ConversationBean> conversationBeens = new ArrayList<>();

    ConversationsUseCase useCase;

    public final ReplyCommand click = new ReplyCommand(() -> {

    });

    @Inject
    public MListViewModel(RxAppCompatActivity activity,
                          ConversationsUseCase useCase) {
        super(activity);
        this.useCase = useCase;
    }

    @Override
    public void afterViews() {
        conversationBeens.clear();
        loadData();
        Messenger.getDefault().register(this, MessageKey.MESSAGE_PUSH, () -> {

        });
    }

    @Override
    public void onDestroy() {
        Messenger.getDefault().unregister(this);
        super.onDestroy();
    }

    private void loadData() {
        Map<String, String> map = new HashMap<>();
        map.put("offset", pageSize * limit + "");
        map.put("limit", limit + "");
        useCase.setParams(JsonUtil.mapToJson(map));
        useCase.execute().compose(activity.bindToLifecycle())
                .doOnNext(d -> {
                    if (d.getList() != null && d.getList().size() < limit - 1) {
                        isComplate = true;
                    }
                })
                .subscribe(data -> {
                    conversationBeens.addAll(data.getList());
                }, error -> {
                }, () -> {
                    if (!isComplate) {
                        pageSize++;
                        loadData();
                    } else {
                        Collections.sort(conversationBeens, new SortComparator());
                        Observable.from(conversationBeens)
                                .subscribe(data -> {
                                    itemViewModels.add(new MListItemViewModel(activity, data));
                                });
                    }
                });
    }

    public class SortComparator implements Comparator {
        @Override
        public int compare(Object lhs, Object rhs) {
            ConversationBean a = (ConversationBean) lhs;
            ConversationBean b = (ConversationBean) rhs;
            return (int) (b.getUts() - a.getUts());
        }
    }
}