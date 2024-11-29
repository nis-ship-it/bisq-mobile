package network.bisq.mobile.domain.di

import com.russhwolf.settings.Settings
import network.bisq.mobile.domain.service.controller.NotificationServiceController
import org.koin.dsl.module
import org.koin.android.ext.koin.androidContext

actual fun provideSettings(): Settings {
     return Settings()
}

val serviceModule = module {
    single<NotificationServiceController> { NotificationServiceController(androidContext()) }
}