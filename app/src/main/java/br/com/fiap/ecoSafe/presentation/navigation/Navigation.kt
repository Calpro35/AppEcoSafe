package br.com.fiap.ecoSafe.presentation.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import br.com.fiap.ecoSafe.presentation.screens.auth.CadastroScreen
import br.com.fiap.ecoSafe.presentation.screens.auth.ForgetScreen
import br.com.fiap.ecoSafe.presentation.screens.home.HomeScreen
import br.com.fiap.ecoSafe.presentation.screens.auth.LoginScreen
import br.com.fiap.ecoSafe.presentation.screens.layouts.Denounces
import br.com.fiap.ecoSafe.presentation.screens.layouts.EndangeredSpecies
import br.com.fiap.ecoSafe.presentation.screens.layouts.Extinction
import br.com.fiap.ecoSafe.presentation.screens.layouts.Mapa
import br.com.fiap.ecoSafe.presentation.screens.layouts.Profile
import br.com.fiap.ecoSafe.presentation.screens.layouts.RecentDiscoveries
import br.com.fiap.ecoSafe.presentation.screens.layouts.Setting
import br.com.fiap.ecoSafe.presentation.screens.layouts.ThreatenedAreas
import br.com.fiap.ecoSafe.presentation.screens.test.TestApiScreen
import br.com.fiap.ecoSafe.presentation.splash.SplashActivity
import br.com.fiap.ecoSafe.presentation.screens.layouts.CameraScreen
import br.com.fiap.ecoSafe.presentation.screens.layouts.PhotoScreen

sealed class Screen(val route: String) {
    object SplashActivity : Screen("splash_screen")
    object Login : Screen("login_screen")
    object Cadastro : Screen("cadastro_screen")
    object Forget : Screen("Forget_screen")
    object Home : Screen("home_screen")
    object RecentDiscoveries : Screen("descobertas_recentes")
    object EndangeredSpecies : Screen("Especies_ameacadas")
    object Extinction : Screen("Extincao")
    object Denounces : Screen("denuncia")
    object Profile : Screen("perfil")
    object Setting : Screen("configuracao")
    object ThreatenedAreas : Screen("areas_ameacadas")
    object Mapa : Screen("mapa")
    object CameraScreen : Screen("camera_screen") // Nova rota para a tela de câmera
    object PhotoScreen : Screen("photo_details/{imageBase64}") // Rota com argumento

    // Objeto usado para testes do Back-End
    object TestApi : Screen("teste")
}

@Composable
fun AppNavigation(navController: NavHostController, context: Context) {
    NavHost(navController = navController, startDestination = Screen.SplashActivity.route) {
        composable(Screen.SplashActivity.route) { SplashActivity(navController) }
        composable(Screen.Login.route) { LoginScreen(navController) }
        composable(Screen.Cadastro.route) { CadastroScreen(navController) }
        composable(Screen.Forget.route) { ForgetScreen(navController) }
        composable(Screen.Home.route) { HomeScreen(navController) }
        composable(Screen.RecentDiscoveries.route) { RecentDiscoveries(navController) }
        composable(Screen.EndangeredSpecies.route) { EndangeredSpecies(navController, context) }
        composable(Screen.Denounces.route) { Denounces(navController) }
        composable(Screen.Profile.route) { Profile(navController) }
        composable(Screen.Setting.route) { Setting(navController) }
        composable(Screen.ThreatenedAreas.route) { ThreatenedAreas(navController) }
        composable(Screen.Mapa.route) { Mapa(navController) }
        composable(Screen.Extinction.route) { Extinction(navController) }
        composable(Screen.CameraScreen.route) {
            CameraScreen(
                onBackClick = { navController.popBackStack() },
                navController = navController
            )
        }
        composable(
            route = Screen.PhotoScreen.route, // Usa a rota definida no objeto
            arguments = listOf(
                navArgument("imageBase64") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val imageBase64 = backStackEntry.arguments?.getString("imageBase64")
            PhotoScreen(imageBase64 = imageBase64)
        }
        composable(Screen.TestApi.route) { TestApiScreen(navController, context) }
    }
}