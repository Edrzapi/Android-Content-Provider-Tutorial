package uk.co.devfoundry.contentprovidertutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import uk.co.devfoundry.contentprovidertutorial.viewmodel.CallHistoryViewModel

class CallHistoryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            CallLogContainer()
        }
    }


    @Composable
    fun CallLogContainer(viewModel: CallHistoryViewModel = viewModel()) {
        val callList by viewModel.callList.collectAsState()
        viewModel.loadCallHistory(LocalContext.current.contentResolver)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(50.dp)
        ) {
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(callList) { call ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        Text(text = "Number: ${call.number}")
                        Text(text = "Date: ${call.date}")
                        Text(text = "Duration: ${call.duration}")
                        HorizontalDivider()
                    }
                }
            }
            Button(
                onClick = { finish() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "Back")
            }
        }
    }
}

