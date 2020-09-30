package com.ralph.gabb.projectpos.di

import com.ralph.gabb.projectpos.ui.login.LoginViewModel
import com.ralph.gabb.projectpos.ui.main.shop.ShopViewModel
import com.ralph.gabb.projectpos.ui.setup.SetupViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


/*
 * Created by Ralph Gabrielle Orden on 8/2/20.
 */
 
val viewModelModule = module {

    viewModel { LoginViewModel( get() ) }

    viewModel { SetupViewModel( get() ) }

    viewModel { ShopViewModel() }

}