package com.odbpo.fenggou.feature.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import com.core.op.Static;
import com.odbpo.fenggou.R;
import com.odbpo.fenggou.base.BackActivity;
import com.odbpo.fenggou.base.BaseActivity;
import com.odbpo.fenggou.databinding.ActSearchBinding;
import com.odbpo.fenggou.di.components.DaggerSearchComponent;
import com.odbpo.fenggou.di.components.SearchComponent;
import com.odbpo.fenggou.di.modules.SearchModule;

import com.core.op.lib.utils.inject.AfterViews;
import com.core.op.lib.utils.inject.BeforeViews;
import com.core.op.lib.utils.inject.RootView;

import java.lang.reflect.Field;

import javax.inject.Inject;

@RootView(R.layout.act_search)
public final class SearchActivity extends BackActivity<SearchViewModel, ActSearchBinding> {

    SearchComponent component;

    public final static void instance(Context context) {
        instance(context, null);
    }

    public final static void instance(Context context, Bundle bundle) {
        Intent intent = new Intent(context, SearchActivity.class);
        if (bundle != null) {
            intent.putExtra("data", bundle);
        }
        context.startActivity(intent);
    }

    @BeforeViews
    void beforViews() {
        component = DaggerSearchComponent.builder()
                .appComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .searchModule(new SearchModule())
                .build();
        component.inject(this);
    }

    @AfterViews
    void afterViews() {
//        SearchView searchView = binding.serachview;
//        searchView.setIconifiedByDefault(false);
//        SearchView.SearchAutoComplete textView = (SearchView.SearchAutoComplete) searchView.findViewById(R.id.search_src_text);
//        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);//14sp
//        textView.setTextColor(Static.CONTEXT.getResources().getColor(R.color.black));
//        ImageView delButton = (ImageView) binding.serachview.findViewById(R.id.search_close_btn);
//        delButton.setImageDrawable(Static.CONTEXT.getResources().getDrawable(R.mipmap.search_del_icon));
//
//        try {
//            Class<?> argClass = searchView.getClass();
//            //指定某个私有属性
//            Field ownField = argClass.getDeclaredField("mSearchPlate"); //注意mSearchPlate的背景是stateListDrawable(不同状态不同的图片)  所以不能用BitmapDrawable
//            //setAccessible 它是用来设置是否有权限访问反射类中的私有属性的，只有设置为true时才可以访问，默认为false
//            ownField.setAccessible(true);
//            View mView = (View) ownField.get(searchView);
//            mView.setBackground(null);
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }

    }


    @Override
    protected Toolbar setToolBar() {
        return binding.toolbar;
    }
}
