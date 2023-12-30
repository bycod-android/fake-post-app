package by.wolfcup.fakepostapp.android

import android.app.Application
import by.wolfcup.fakepostapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FakePost: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@FakePost)
            modules(appModule)
        }
    }
}