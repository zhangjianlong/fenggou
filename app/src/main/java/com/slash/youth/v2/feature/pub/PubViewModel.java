package com.slash.youth.v2.feature.pub;


import android.databinding.ObservableField;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import com.core.op.bindingadapter.common.ItemView;
import com.core.op.lib.base.BAViewModel;
import com.core.op.lib.command.ReplyCommand;
import com.core.op.lib.di.PerActivity;
import com.slash.youth.BR;
import com.slash.youth.R;
import com.slash.youth.databinding.ActPubBinding;
import com.slash.youth.ui.activity.test.TestActivity;
import com.slash.youth.v2.feature.local.LocalActivity;
import com.slash.youth.v2.feature.main.find.FindItemViewModel;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@PerActivity
public class PubViewModel extends BAViewModel<ActPubBinding> {

    public ObservableField<String> title = new ObservableField<>();
    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> nameNum = new ObservableField<>();
    public ObservableField<String> content = new ObservableField<>();
    public ObservableField<String> standard = new ObservableField<>();
    public ObservableField<String> price = new ObservableField<>();
    public ObservableField<String> unit = new ObservableField<>();


    public final ItemView itemView = ItemView.of(BR.viewModel, R.layout.item_pub_img);
    public final List<PubItemViewModel> itemViewModels = new ArrayList<>();

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

    @Inject
    public PubViewModel(RxAppCompatActivity activity) {
        super(activity);
        title.set(activity.getString(R.string.app_pub_title));
    }

    @Override
    public void afterViews() {

    }
}