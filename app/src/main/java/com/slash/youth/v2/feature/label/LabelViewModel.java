package com.slash.youth.v2.feature.label;


import com.core.op.bindingadapter.common.ItemView;
import com.core.op.lib.base.BAViewModel;
import com.core.op.lib.di.PerActivity;
import com.core.op.lib.messenger.Messenger;
import com.slash.youth.BR;
import com.slash.youth.R;
import com.slash.youth.databinding.ActLabelBinding;
import com.slash.youth.domain.bean.LabelBean;
import com.slash.youth.domain.interactor.task.LableUseCase;
import com.slash.youth.v2.util.MessageKey;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;

import static android.R.attr.data;

@PerActivity
public class LabelViewModel extends BAViewModel<ActLabelBinding> {

    public String title;

    public final ItemView secondItemView = ItemView.of(BR.viewModel, R.layout.item_label_hot);
    public final List<LabelSItemViewModel> secondItemViewModels = new ArrayList<>();

    public final ItemView itemView = ItemView.of(BR.viewModel, R.layout.item_label);
    public final List<LabelItemViewModel> itemViewModels = new ArrayList<>();

    LableUseCase lableUseCase;

    List<LabelBean> lableBeens = new ArrayList<>();

    LabelItemViewModel selectedModel;

    int index = 0;

    @Inject
    public LabelViewModel(RxAppCompatActivity activity,
                          LableUseCase lableUseCase) {
        super(activity);
        this.lableUseCase = lableUseCase;
        title = activity.getString(R.string.app_label_title);
    }

    @Override
    public void afterViews() {
        Messenger.getDefault().register(MessageKey.LABEL_SELECT_STAIR, Integer.class, data -> {
            index = 0;
            secondItemViewModels.clear();
            getLableBeens(itemViewModels.get((int)data).data.getId())
                    .subscribe(d -> {
                        secondItemViewModels.add(new LabelSItemViewModel(activity, d, index, false));
                        index++;
                    }, error -> {
                    }, () -> {
                        binding.secondRecyclerView.getAdapter().notifyDataSetChanged();
                    });
        });
        loadData();
    }

    private void loadData() {
        lableUseCase.execute().compose(activity.bindToLifecycle())
                .subscribe(data -> {
                    lableBeens.addAll(data);
                }, error -> {

                }, () -> {
                    index = 0;
                    getLableBeens(0)
                            .subscribe(data -> {
                                if (index == 0) {
                                    selectedModel = new LabelItemViewModel(activity, data, index, true);
                                    itemViewModels.add(selectedModel);
                                } else {
                                    itemViewModels.add(new LabelItemViewModel(activity, data, index, false));
                                }
                                index++;
                            }, error -> {
                            }, () -> {
                                index = 0;
                                getLableBeens(selectedModel.data.getId())
                                        .subscribe(data -> {
                                            secondItemViewModels.add(new LabelSItemViewModel(activity, data, index, false));
                                            index++;
                                        });
                            });
                });
    }

    private Observable<LabelBean> getLableBeens(int id) {
        return Observable.from(lableBeens)
                .filter(data -> {
                    return data.getF1() == id;
                });
    }
}