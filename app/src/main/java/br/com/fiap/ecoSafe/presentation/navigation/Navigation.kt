package br.com.fiap.ecoSafe.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.fiap.ecoSafe.presentation.screens.auth.CadastroScreen
import br.com.fiap.ecoSafe.presentation.screens.auth.ForgetScreen
import br.com.fiap.ecoSafe.presentation.screens.home.HomeScreen
import br.com.fiap.ecoSafe.presentation.screens.auth.LoginScreen


import br.com.fiap.ecoSafe.presentation.splash.SplashActivity
import br.com.fiap.ecoSafe.ui.screens.TestApiScreen

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object Login : Screen("login_screen")
    object Cadastro : Screen("cadastro_screen")

    object Forget : Screen("Forget_screen")
    object Home : Screen("home_screen")
=======
    //Objeto usado para testes do Back-End
//>>>>>>> main
}

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(Screen.Splash.route) { SplashActivity(navController) }
        composable(Screen.Login.route) { LoginScreen(navController) }
        composable(Screen.Cadastro.route) { CadastroScreen(navController) }

        composable(Screen.Forget.route){ ForgetScreen(navController)}
        composable(Screen.Home.route) { HomeScreen(navController) }
=======
        //Composable apenas para Testes do Back End
//>>>>>>> main
    }
}


