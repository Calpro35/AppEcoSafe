package br.com.fiap.ecoSafe

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.ecoSafe.presentation.navigation.AppNavigation
import br.com.fiap.ecoSafe.ui.theme.EcoSafeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EcoSafeApp(this)
        }
    }
}

@Composable
fun EcoSafeApp(context: Context) {
    EcoSafeTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            val navController = rememberNavController()
            AppNavigation(navController, context)
        }
    }
}