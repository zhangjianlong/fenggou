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
import com.core.op.lib.weight.progress.Progress;
import com.slash.youth.BR;
import com.slash.youth.R;
import com.slash.youth.databinding.FrgMlistBinding;
import com.slash.youth.domain.bean.ConversationBean;
import com.slash.youth.domain.interactor.message.ConversationsUseCase;
import com.slash.youth.domain.interactor.message.DelConversationsUseCase;
import com.slash.youth.engine.MsgManager;
import com.slash.youth.v2.util.MessageKey;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

import static android.R.attr.data;

@PerActivity
public class MListViewModel extends BFViewModel<FrgMlistBinding> {

    private boolean isComplate = false;

    private int pageSize = 0;
    private int limit = 20;

    public final ItemView itemView = ItemView.of(BR.viewModel, R.layout.item_message);
    public final List<MListItemViewModel> itemViewModels = new ArrayList<>();

    public final List<ConversationBean> conversationBeens = new ArrayList<>();

    ConversationBean aideConversation = new ConversationBean(1000);
    ConversationBean serviceConversation = new ConversationBean(Long.valueOf(MsgManager.customerServiceUid));

    ConversationsUseCase useCase;
    int index = 0;
    DelConversationsUseCase delConversationsUseCase;

    @Inject
    public MListViewModel(RxAppCompatActivity activity,
                          ConversationsUseCase useCase,
                          DelConversationsUseCase delConversationsUseCase) {
        super(activity);
        this.useCase = useCase;
        this.delConversationsUseCase = delConversationsUseCase;
    }

    Progress progress;

    @Override
    public void afterViews() {
        progress = Progress.create(activity).setStyle(Progress.Style.SPIN_INDETERMINATE);
        Messenger.getDefault().register(this, MessageKey.MESSAGE_PUSH, () -> {
            Observable.timer(300, TimeUnit.MILLISECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(d -> {
                        itemViewModels.clear();
                        conversationBeens.clear();
                        pageSize = 0;
                        loadData();
                    });
        });

        Messenger.getDefault().register(this, MessageKey.DELETE_MESSAGE, () -> {
            progress.show();
            Observable.timer(500, TimeUnit.MILLISECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(d -> {
                        itemViewModels.clear();
                        conversationBeens.clear();
                        pageSize = 0;
                        loadData();
                    });
        });
    }

    @Override
    public void onResume() {
        itemViewModels.clear();
        conversationBeens.clear();
        pageSize = 0;
        loadData();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        Messenger.getDefault().unregister(this);
        super.onDestroy();
    }

    private synchronized void loadData() {
        Map<String, String> map = new HashMap<>();
        map.put("offset", pageSize * limit + "");
        map.put("limit", limit + "");
        useCase.setParams(JsonUtil.mapToJson(map));
        useCase.execute().compose(activity.bindToLifecycle())
                .doOnNext(d -> {
                    if (d.getList() == null || (d.getList() != null && d.getList().size() < limit - 1)) {
                        isComplate = true;
                    }
                })
                .subscribe(data -> {
                    if (data.getList() != null)
                        conversationBeens.addAll(data.getList());
                }, error -> {
                }, () -> {
                    if (!isComplate) {
                        pageSize++;
                        loadData();
                    } else {
                        if (conversationBeens.size() == 0) {
                            conversationBeens.add(aideConversation);
                            conversationBeens.add(serviceConversation);
                            index = 0;
                            Observable.from(conversationBeens)
                                    .subscribe(data -> {
                                        itemViewModels.add(new MListItemViewModel(activity, data, delConversationsUseCase, index));
                                        index++;
                                    }, error -> {
                                        progress.dismiss();
                                    }, () -> {
                                        Collections.sort(itemViewModels, new SortComparator());
                                        binding.recyclerView.getAdapter().notifyDataSetChanged();
                                        progress.dismiss();
                                    });
                        } else
                            Observable.from(conversationBeens)
                                    .subscribe(data -> {
                                        if (data.getUid() == 1000) {
                                            aideConversation = data;
                                            conversationBeens.remove(data);
                                        } else if (data.getUid() == Long.valueOf(MsgManager.customerServiceUid)) {
                                            serviceConversation = data;
                                            conversationBeens.remove(data);
                                        }
                                    }, error -> {
                                        progress.dismiss();
                                    }, () -> {
                                        conversationBeens.add(aideConversation);
                                        conversationBeens.add(serviceConversation);
                                        index = 0;
                                        Observable.from(conversationBeens)
                                                .subscribe(data -> {
                                                    itemViewModels.add(new MListItemViewModel(activity, data, delConversationsUseCase, index));
                                                    index++;
                                                }, error -> {
                                                    progress.dismiss();
                                                }, () -> {
                                                    Collections.sort(itemViewModels, new SortComparator());
                                                    binding.recyclerView.getAdapter().notifyDataSetChanged();
                                                    progress.dismiss();
                                                });
                                    });

                    }
                });
    }

    public class SortComparator implements Comparator {
        @Override
        public int compare(Object lhs, Object rhs) {
            MListItemViewModel a = (MListItemViewModel) lhs;
            MListItemViewModel b = (MListItemViewModel) rhs;
            return (int) (b.data.getUts() - a.data.getUts());
        }
    }
}