package uk.co.devfoundry.contentprovidertutorial.viewmodel

import android.content.ContentResolver
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import uk.co.devfoundry.contentprovidertutorial.contentprovider.CallHistoryProvider
import uk.co.devfoundry.contentprovidertutorial.contentprovider.CallLogEntry

class CallHistoryViewModel : ViewModel() {
    private val _callList = MutableStateFlow<List<CallLogEntry>>(emptyList())
    val callList: StateFlow<List<CallLogEntry>> = _callList.asStateFlow()

    fun loadCallHistory(contentResolver: ContentResolver) {
        // Initialize the CallHistoryProvider and fetch data
        val callHistoryProvider = CallHistoryProvider()
        _callList.value = callHistoryProvider.fetchCallHistory(contentResolver)
    }
}