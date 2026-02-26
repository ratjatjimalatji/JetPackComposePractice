package com.example.beginnerapplication

import android.R.attr.content
import android.R.attr.horizontalDivider
import android.R.id.content
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import com.example.beginnerapplication.navigation.InsuranceNavigation
import com.example.beginnerapplication.ui.theme.BeginnerApplicationTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApp {

                InsuranceNavigation()
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    BeginnerApplicationTheme {
        content()
    }
}


