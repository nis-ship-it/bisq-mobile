package network.bisq.mobile.domain.di

import network.bisq.mobile.domain.data.model.*
import network.bisq.mobile.domain.data.repository.*
import com.russhwolf.settings.Settings
import network.bisq.mobile.domain.data.persistance.KeyValuePersister
import org.koin.dsl.module

val domainModule = module {
    single<Settings> { Settings() }
    // wrapper for MP settings lib
    single<KeyValuePersister> { KeyValuePersister(get()) }

    single<BisqStatsRepository> { BisqStatsRepository() }
    single<BtcPriceRepository> { BtcPriceRepository() }
    single<UserProfileRepository> { UserProfileRepository() }
    single<SettingsRepository> { SettingsRepository() }

    // this example uses persistance with the above
    single<GreetingRepository<Greeting>> { GreetingRepository(get()) }
}
