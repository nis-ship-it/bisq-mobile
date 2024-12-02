package network.bisq.mobile.presentation

import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import network.bisq.mobile.android.node.BuildNodeConfig
import network.bisq.mobile.client.shared.BuildConfig
import network.bisq.mobile.domain.data.BackgroundDispatcher
import network.bisq.mobile.domain.getPlatform
import network.bisq.mobile.domain.service.controller.NotificationServiceController
import network.bisq.mobile.presentation.ui.AppPresenter


/**
 * Main Presenter as an example of implementation for now.
 */
open class MainPresenter(private val notificationServiceController: NotificationServiceController) :
    BasePresenter(null), AppPresenter {
    lateinit var navController: NavHostController
        private set

    override fun setNavController(controller: NavHostController) {
        navController = controller
    }

    // Observable state
    private val _isContentVisible = MutableStateFlow(false)
    override val isContentVisible: StateFlow<Boolean> = _isContentVisible


    // passthrough example
    //    private val _greetingText: StateFlow<String> = stateFlowFromRepository(
    //        repositoryFlow = greetingRepository.data,
    //        transform = { it?.greet() ?: "" },
    //        initialValue = "Welcome!"
    //    )
    //    override val greetingText: StateFlow<String> = _greetingText

    init {
        log.i { "Shared Version: ${BuildConfig.SHARED_LIBS_VERSION}" }
        log.i { "iOS Client Version: ${BuildConfig.IOS_APP_VERSION}" }
        log.i { "Android Client Version: ${BuildConfig.IOS_APP_VERSION}" }
        log.i { "Android Node Version: ${BuildNodeConfig.APP_VERSION}" }
        notificationServiceController.startService()
//        CoroutineScope(BackgroundDispatcher).launch {
//        }
    }

    // Toggle action
    override fun toggleContentVisibility() {
        _isContentVisible.value = !_isContentVisible.value
    }

    override fun isIOS(): Boolean {
        val platform = getPlatform()
        val isIOS = platform.name.lowercase().contains("ios")
        return isIOS
    }

}