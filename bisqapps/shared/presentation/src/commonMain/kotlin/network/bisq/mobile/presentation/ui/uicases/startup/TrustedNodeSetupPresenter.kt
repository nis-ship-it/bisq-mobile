package network.bisq.mobile.presentation.ui.uicases.startup

import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import network.bisq.mobile.domain.data.model.Settings
import network.bisq.mobile.domain.data.repository.SettingsRepository
import network.bisq.mobile.presentation.BasePresenter
import network.bisq.mobile.presentation.MainPresenter
import network.bisq.mobile.presentation.ui.navigation.Routes

class TrustedNodeSetupPresenter(
    mainPresenter: MainPresenter,
    private val navController: NavController,
    private val settingsRepository: SettingsRepository
) : BasePresenter(mainPresenter), ITrustedNodeSetupPresenter {

    private val _bisqUrl = MutableStateFlow("")
    override val bisqUrl: StateFlow<String> = _bisqUrl

    private val _isConnected = MutableStateFlow(false)
    override val isConnected: StateFlow<Boolean> = _isConnected

    override fun updateBisqUrl(newUrl: String) {
        _bisqUrl.value = newUrl
    }

    override fun testConnection(isTested: Boolean) {
        _isConnected.value = isTested

        CoroutineScope(Dispatchers.IO).launch {
            val updatedSettings = Settings().apply {
                bisqUrl = _bisqUrl.value
                isConnected = _isConnected.value
            }

            settingsRepository.update(updatedSettings)
        }
    }

    override fun navigateToNextScreen() {
        navController.navigate(Routes.TabContainer.name) {
            popUpTo(Routes.TrustedNodeSetup.name) { inclusive = true }
        }
    }
}