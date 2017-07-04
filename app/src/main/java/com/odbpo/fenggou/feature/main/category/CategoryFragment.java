package com.odbpo.fenggou.feature.main.category;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.StackView;

import com.core.op.Static;
import com.odbpo.fenggou.R;
import com.odbpo.fenggou.base.BaseFragment;
import com.odbpo.fenggou.databinding.FrgCategoryBinding;
import com.odbpo.fenggou.di.components.MainComponent;
import com.core.op.lib.utils.inject.AfterViews;
import com.core.op.lib.utils.inject.BeforeViews;
import com.core.op.lib.utils.inject.RootView;
import com.odbpo.fenggou.feature.search.SearchActivity;

import java.lang.reflect.Field;
import java.util.Observable;

import javax.inject.Inject;

import static com.odbpo.fenggou.R.id.serachview;

@RootView(R.layout.frg_category)
public final class CategoryFragment extends BaseFragment<CategoryViewModel, FrgCategoryBinding> {

    public static CategoryFragment instance() {
        return new CategoryFragment();
    }

    @BeforeViews
    void beforViews() {
        getComponent(MainComponent.class).inject(this);
    }


    @AfterViews
    void afterViews() {
        ((AppCompatActivity) activity).setSupportActionBar(binding.toolbar);
        SearchView searchView = binding.serachview;
        searchView.setIconifiedByDefault(false);
        SearchView.SearchAutoComplete textView = (SearchView.SearchAutoComplete) searchView.findViewById(R.id.search_src_text);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);//14sp
        textView.setTextColor(Static.CONTEXT.getResources().getColor(R.color.white_light));
        textView.setFocusable(false);
        textView.setFocusableInTouchMode(false);
        ImageView delButton = (ImageView) binding.serachview.findViewById(R.id.search_close_btn);
        delButton.setImageDrawable(Static.CONTEXT.getResources().getDrawable(R.mipmap.search_del_icon));
        try {
            Class<?> argClass = searchView.getClass();
            //指定某个私有属性
            Field ownField = argClass.getDeclaredField("mSearchPlate"); //注意mSearchPlate的背景是stateListDrawable(不同状态不同的图片)  所以不能用BitmapDrawable
            //setAccessible 它是用来设置是否有权限访问反射类中的私有属性的，只有设置为true时才可以访问，默认为false
            ownField.setAccessible(true);
            View mView = (View) ownField.get(searchView);
            mView.setBackground(null);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
