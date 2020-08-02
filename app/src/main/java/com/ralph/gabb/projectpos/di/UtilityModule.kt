package com.ralph.gabb.projectpos.di

import com.ralph.gabb.projectpos.utils.PreferenceManager
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


/*
 * Created by Ralph Gabrielle Orden on 8/2/20.
 */
 
val utilityModule = module {

    single { PreferenceManager(androidApplication()) }

}