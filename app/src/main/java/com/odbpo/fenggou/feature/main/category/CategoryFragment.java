package com.odbpo.fenggou.feature.main.category;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;

import com.odbpo.fenggou.R;
import com.odbpo.fenggou.base.BaseFragment;
import com.odbpo.fenggou.databinding.FrgCategoryBinding;
import com.odbpo.fenggou.di.components.MainComponent;
import com.core.op.lib.utils.inject.AfterViews;
import com.core.op.lib.utils.inject.BeforeViews;
import com.core.op.lib.utils.inject.RootView;

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
        ((AppCompatActivity) getActivity()).setSupportActionBar(binding.toolbar);
        SearchView searchView = binding.serachview;
        searchView.setIconifiedByDefault(false);
    }
}
