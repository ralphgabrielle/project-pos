package com.ralph.gabb.projectpos

import android.app.Application
import com.ralph.gabb.projectpos.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber


/*
 * Created by Ralph Gabrielle Orden on 8/2/20.
 */

class ProjectApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        plantDebugTree()
        configureDependencyInjection()
    }

    private fun configureDependencyInjection() {
        startKoin {
            androidContext(this@ProjectApplication)
            modules(appModule)
        }
    }

    private fun plantDebugTree() {
        Timber.plant(Timber.DebugTree())
    }
}