package com.odbpo.fenggou.di.components;

import dagger.Component;


import com.odbpo.fenggou.di.modules.ActivityModule;
import com.odbpo.fenggou.di.modules.MainModule;
import com.odbpo.fenggou.feature.main.MainActivity;
import com.core.op.lib.di.PerActivity;
import com.odbpo.fenggou.feature.main.category.CategoryFragment;
import com.odbpo.fenggou.feature.main.info.InfoFragment;
import com.odbpo.fenggou.feature.main.login.LoginFragment;
import com.odbpo.fenggou.feature.main.mine.MineFragment;
import com.odbpo.fenggou.feature.main.product.ProductFragment;
import com.odbpo.fenggou.feature.main.shopping.ShoppingFragment;
import com.odbpo.fenggou.feature.main.shopping.loginCart.LogincartFragment;
import com.odbpo.fenggou.feature.main.shopping.unloginCart.UnlonincartFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class, MainModule.class})
public interface MainComponent extends ActivityComponent {
    void inject(MainActivity activity);

    void inject(CategoryFragment fragment);

    void inject(ProductFragment fragment);

    void inject(ShoppingFragment fragment);

    void inject(MineFragment fragment);

    void inject(LoginFragment fragment);

    void inject(InfoFragment fragment);

    void inject(UnlonincartFragment fragment);

    void inject(LogincartFragment fragment);

}