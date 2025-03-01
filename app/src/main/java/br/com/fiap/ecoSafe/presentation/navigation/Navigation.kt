package br.com.fiap.ecoSafe.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.fiap.ecoSafe.presentation.screens.cadastro.CadastroScreen
import br.com.fiap.ecoSafe.presentation.screens.home.HomeScreen
import br.com.fiap.ecoSafe.presentation.screens.login.LoginScreen
import br.com.fiap.ecoSafe.presentation.splash.SplashActivity

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object Login : Screen("login_screen")
    object Home : Screen("home_screen")
    object Cadastro : Screen("cadastro_screen")
}

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(Screen.Splash.route) { SplashActivity(navController) }
        composable(Screen.Login.route) { LoginScreen(navController) }
        composable(Screen.Home.route) { HomeScreen(navController) }
        composable(Screen.Cadastro.route) { CadastroScreen(navController) }
    }
}