package com.slash.youth.v2.feature.pub;


import android.content.Intent;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import com.core.op.bindingadapter.common.ItemView;
import com.core.op.lib.base.BAViewModel;
import com.core.op.lib.command.ReplyCommand;
import com.core.op.lib.di.PerActivity;
import com.core.op.lib.messenger.Messenger;
import com.core.op.lib.weight.imgselector.MultiImageSelector;
import com.slash.youth.BR;
import com.slash.youth.R;
import com.slash.youth.databinding.ActPubBinding;
import com.slash.youth.ui.activity.test.TestActivity;
import com.slash.youth.v2.feature.local.LocalActivity;
import com.slash.youth.v2.feature.main.find.FindItemViewModel;
import com.slash.youth.v2.util.MessageKey;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;

import static android.R.attr.data;

@PerActivity
public class PubViewModel extends BAViewModel<ActPubBinding> {

    public ObservableField<String> title = new ObservableField<>();
    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> nameNum = new ObservableField<>("0/15");
    public ObservableField<String> content = new ObservableField<>();
    public ObservableField<String> standard = new ObservableField<>();
    public ObservableField<String> price = new ObservableField<>();
    public ObservableField<String> unit = new ObservableField<>();
    public ObservableField<String> local = new ObservableField<>();

    private ArrayList<String> mSelectPath;
    public final ItemView itemView = ItemView.of(BR.viewModel, R.layout.item_pub_img);
    public final ObservableList<PubItemViewModel> itemViewModels = new ObservableArrayList<>();

    public ReplyCommand localClick = new ReplyCommand(() -> {
        LocalActivity.instance(activity);
    });

    public TextWatcher textWatcher = new TextWatcher() {
        private CharSequence temp;

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            temp = s;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            nameNum.set(temp.length() + "/15");
        }
    };

    int index = 0;

    @Inject
    public PubViewModel(RxAppCompatActivity activity) {
        super(activity);
        title.set(activity.getString(R.string.app_pub_title));
        local.set(activity.getString(R.string.app_pub_local_select));
    }

    @Override
    public void afterViews() {
        Messenger.getDefault().register(this, MessageKey.PUB_CITY_SELECTED, String.class, data -> {
            local.set(data);
        });
        Messenger.getDefault().register(this, MessageKey.PUB_DEL_IMG, Integer.class, data -> {
            itemViewModels.remove(data);
        });

        itemViewModels.add(new PubItemViewModel(activity, 0, "", true));
    }

    public void uploadImage(Intent data) {
        mSelectPath = data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT);
        if (mSelectPath != null && mSelectPath.size() != 0) {
            itemViewModels.remove(itemViewModels.size() - 1);
            index = 0;
            Observable.from(mSelectPath)
                    .subscribe(d -> {
                        itemViewModels.add(new PubItemViewModel(activity, index, d, false));
                        index++;
                    }, error -> {
                    }, () -> {
                        if (itemViewModels.size() > 5) {
                            itemViewModels.add(new PubItemViewModel(activity, index, "", true));
                        }
                    });
        }
    }
}