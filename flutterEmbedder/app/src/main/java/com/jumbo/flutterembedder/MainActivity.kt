package com.jumbo.flutterembedder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.flutter.embedding.android.FlutterActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val context = this
        setContent {
            MaterialTheme {
                Column {
                    Text("Hello")
                    Button(
                        modifier = Modifier.padding(16.dp),
                        onClick = {
                            startActivity(
                                FlutterActivity.createDefaultIntent(context)
                            )
                        }
                    ) {
                        Text("Start Flutter")
                    }
                }
            }
        }
    }
}