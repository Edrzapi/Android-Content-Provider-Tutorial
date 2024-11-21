package uk.co.devfoundry.contentprovidertutorial

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import uk.co.devfoundry.contentprovidertutorial.ui.theme.ContentProviderTutorialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            requestPermission()
            ContentProviderTutorialTheme {
                NavigateToCallHistory({
                    startActivity(
                        Intent(
                            this,
                            CallHistoryActivity::class.java
                        )
                    )
                })

            }
        }

    }


    private fun requestPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_CALL_LOG
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.READ_CALL_LOG),
                1
            )
        }
    }

    @Composable
    fun NavigateToCallHistory(navigate: () -> Unit) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(100.dp, 250.dp)
                .border(5.dp, color = Color.Blue),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(onClick = { navigate() }) {
                Text(text = "Check out call history!")

            }
        }
    }
}