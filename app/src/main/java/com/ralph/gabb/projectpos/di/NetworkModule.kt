package com.ralph.gabb.projectpos.di

import com.ralph.gabb.projectpos.http.AppNetworkService
import org.koin.dsl.module


/*
 * Created by Ralph Gabrielle Orden on 8/2/20.
 */
 
 val networkModule = module {

     single {  AppNetworkService() }

 }