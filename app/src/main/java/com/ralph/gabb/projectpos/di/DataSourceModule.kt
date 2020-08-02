package com.ralph.gabb.projectpos.di

import com.ralph.gabb.projectpos.data.data_source.item.ItemDataSource
import com.ralph.gabb.projectpos.data.data_source.item.ItemDataSourceImpl
import com.ralph.gabb.projectpos.data.data_source.user.UserDataSource
import com.ralph.gabb.projectpos.data.data_source.user.UserDataSourceImpl
import org.koin.dsl.module


/*
 * Created by Ralph Gabrielle Orden on 8/2/20.
 */
 
val dataSourceModule = module {

    factory<UserDataSource> { UserDataSourceImpl( get() ) }

    factory<ItemDataSource> { ItemDataSourceImpl( get() ) }

}