package com.odbpo.fenggou.feature.detail;


import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ObservableList;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import com.core.op.Static;
import com.core.op.bindingadapter.common.ItemView;
import com.core.op.lib.base.BAViewModel;
import com.core.op.lib.command.ReplyCommand;
import com.core.op.lib.di.PerActivity;
import com.core.op.lib.utils.MyStateBarUtil;
import com.core.op.lib.weight.imgselector.utils.ScreenUtils;
import com.core.op.lib.weight.scrollview.GradationScrollView;
import com.odbpo.fenggou.BR;
import com.odbpo.fenggou.R;
import com.odbpo.fenggou.databinding.ActDetailBinding;
import com.odbpo.fenggou.feature.dialog.cart.CartDialog;
import com.odbpo.fenggou.feature.main.MainActivity;
import com.odbpo.fenggou.util.CommonUtils;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;

import static android.R.attr.data;
import static com.odbpo.fenggou.R.id.iv;
import static com.odbpo.fenggou.R.id.tabLayout;

@PerActivity
public class DetailViewModel extends BAViewModel<ActDetailBinding> {
    private CartDialog cartDialog;


    @Inject
    public DetailViewModel(RxAppCompatActivity activity, CartDialog cartDialog) {
        super(activity);
        this.cartDialog = cartDialog;
    }

    @Override
    public void afterViews() {
        initToolBar();
        initBanner();
        initTabs();


    }

    @Override
    public void onResume() {
        super.onResume();
        binding.topView.scrollview.setScrollViewListener(new GradationScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(GradationScrollView scrollView, int x, int y, int oldx, int oldy) {
                bannerMeasureHeight = binding.topView.bannerll.getMeasuredHeight();
                bannerHeight = binding.topView.scrollview.getHeight();
                if (y <= 0) {   //设置标题的背景颜色
                    System.out.println("model0_" + "  bannerHeight" + bannerHeight + "y: " + y);
                    binding.toolbar.toolbar.setBackgroundColor(Color.argb((int) 0, 254, 74, 98));
                } else if (y > 0 && y <= bannerHeight) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
                    float scale = (float) y / (bannerMeasureHeight - (MyStateBarUtil.getScreenHeight() - (MyStateBarUtil.getScreenHeight() - bannerHeight)));
                    float alpha = (255 * scale);
                    binding.toolbar.toolbarTitle.setTextColor(Color.argb((int) alpha, 255, 255, 255));
                    binding.toolbar.toolbar.setBackgroundColor(Color.argb((int) alpha, 254, 74, 98));
                    System.out.println("model1_" + "  bannerHeight" + bannerHeight + "bannerMeasureHeight:" + bannerMeasureHeight + "alpha: " + alpha + "scale: " + scale + "y: " + y);
                } else {    //滑动到banner下面设置普通颜色
                    System.out.println("model2_" + "  bannerHeight" + bannerHeight + "y: " + y);
                    binding.toolbar.toolbar.setBackgroundColor(Color.argb((int) 255, 254, 74, 98));
                }
            }
        });

    }


    private void initToolBar() {
        binding.toolbar.toolbar.setBackgroundResource(R.color.transparent);
    }

    private static final String TAG_DETAIL = "TAG_DETAIL";
    private static final String TAG_PARAMETERS = "TAG_PARAMETERS";
    private int bannerMeasureHeight;
    private int bannerHeight;
    private List<String> images = new ArrayList<>();
    public final ItemView bItemView = ItemView.of(BR.viewModel, R.layout.item_main_find_banner_item);
    public final ObservableList<FindBannerItemViewModel> bViewModels = new ObservableArrayList<>();
    public ObservableField<String> title = new ObservableField<>(Static.CONTEXT.getString(R.string.app_product_detail_title));
    public ObservableInt height = new ObservableInt(MyStateBarUtil.getScreenHalfHeight(Static.CONTEXT));

    private void initBanner() {
        images.add("http://pic2.ooopic.com/12/19/25/59bOOOPIC45_1024.jpg");
        images.add("http://www.asiadancing.com/images/aHR0cDovL3BpYzQ5Lm5pcGljLmNvbS9maWxlLzIwMTQxMDA4LzE5NjY4NTQ1XzEyMjMwMzE4NDUwNl8yLmpwZw==.jpg");
        images.add("http://img02.tooopen.com/images/20140305/sy_56173791696.jpg");

        Observable.from(images).subscribe(data -> {
            bViewModels.add(new FindBannerItemViewModel(activity, data));
        }, error -> {

        }, () -> {
            if (binding.topView.banner.getViewPager().getAdapter() != null) {
                binding.topView.banner.getViewPager().notify();
            }
            initIndicator();

        });


        binding.topView.banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                setSelectedVpPointer(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private void initIndicator() {
        binding.topView.llIndicator.removeAllViews();
        for (int i = 0; i < bViewModels.size(); i++) {
            LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(CommonUtils.dip2px(6), CommonUtils.dip2px(6));
            if (i > 0) {
                llParams.leftMargin = CommonUtils.dip2px(10);
            }
            View vPoint = new View(CommonUtils.getContext());
            vPoint.setLayoutParams(llParams);
            binding.topView.llIndicator.addView(vPoint);
        }
        int currentItem = binding.topView.banner.getViewPager().getCurrentItem();
        setSelectedVpPointer(currentItem);
    }

    private void setSelectedVpPointer(int index) {
        //这里需要做判断，因为可能执行这段代码的时候，bannerList中的数据还没有从网络加载完毕
        if (bViewModels != null && bViewModels.size() > 0) {
            index = index % bViewModels.size();
            int childCount = binding.topView.llIndicator.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View vPoint = binding.topView.llIndicator.getChildAt(i);
                if (i == index) {//选中
                    vPoint.setBackgroundResource(R.drawable.shape_vpindicator_selected);
                } else {//未选中
                    vPoint.setBackgroundResource(R.drawable.shape_vpindicator_unselected);
                }
            }
        }
    }

    private void initTabs() {
        TabLayout.Tab tab = binding.bottomView.tabLayout.newTab();
        tab.setText(Static.CONTEXT.getText(R.string.app_product_detail_title));
        tab.setTag(TAG_DETAIL);
        binding.bottomView.tabLayout.addTab(tab);
        tab = binding.bottomView.tabLayout.newTab();
        tab.setText(Static.CONTEXT.getText(R.string.app_product_detail_parameters));
        tab.setTag(TAG_PARAMETERS);
        binding.bottomView.tabLayout.addTab(tab);
        binding.bottomView.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getTag().equals(TAG_DETAIL)) {

                } else if (tab.getTag().equals(TAG_PARAMETERS)) {
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


    public final ReplyCommand addCart = new ReplyCommand(() -> {
        if (!cartDialog.isShowing()) {
            cartDialog.show();
        }
    });

    public final ReplyCommand purchase = new ReplyCommand(() -> {
        if (!cartDialog.isShowing()) {
            cartDialog.show();
        }
    });


}

