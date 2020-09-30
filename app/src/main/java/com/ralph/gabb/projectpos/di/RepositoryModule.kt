package com.ralph.gabb.projectpos.di

import com.ralph.gabb.projectpos.data.repository.item.ItemRepository
import com.ralph.gabb.projectpos.data.repository.item.ItemRepositoryImpl
import com.ralph.gabb.projectpos.data.repository.user.UserRepository
import com.ralph.gabb.projectpos.data.repository.user.UserRepositoryImpl
import org.koin.dsl.module


/*
 * Created by Ralph Gabrielle Orden on 8/2/20.
 */
 
val repositoryModule = module {

    factory<UserRepository> { UserRepositoryImpl( get() ) }

    factory<ItemRepository> { ItemRepositoryImpl( get() ) }

}