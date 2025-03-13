package br.com.fiap.ecoSafe.presentation.screens.layouts

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.ecoSafe.presentation.components.Footer
import br.com.fiap.ecoSafe.presentation.components.HamburgerMenu
import br.com.fiap.ecosafe.R
import androidx.compose.foundation.layout.Arrangement

@Composable
fun EndangeredSpecies(navController: NavController) {
    var isMenuOpen by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        // Main content
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp)
        ) {
            item {
                HeaderExtincion(onMenuClick = { isMenuOpen = true })
                Spacer(modifier = Modifier.height(24.dp))
                //MainExtincion()
            }
        }

        // Hamburger Menu
        if (isMenuOpen) {
            HamburgerMenu(
                onCloseClick = { isMenuOpen = false },
                onItemClick = { destination ->
                    navController.navigate(destination)
                    isMenuOpen = false
                }
            )
        }

        // Fixed Footer at the bottom
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        ) {
            Footer(navController = navController)
        }
    }
}

@Composable
fun HeaderExtincion(onMenuClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Extinção",
                modifier = Modifier
                    .weight(1f)
                    .offset(x = 22.dp),
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp,
                color = Color(0xFF2D2A2A),
            )

            IconButton(
                onClick = onMenuClick,
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.menu_ham),
                    contentDescription = "Abrir Menu",
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}

//@Composable
//fun MainExtincion() {
//    val items = listOf("Animais", "Plantas", "Insetos")
//    val searchItems = listOf(
//        "Arefirme azul" to "Pau-brasil",
//        "Borboleta-morama" to "Legnete azul",
//        "Tigre-de-surníria" to "Ongaldes-ferdazina"
//    )
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//    ) {
//        Text("Extinção", fontSize = 24.sp, fontWeight = FontWeight.Bold)
//        Spacer(modifier = Modifier.height(8.dp))
//            items(items) { item ->
//                Text(item, fontSize = 18.sp, modifier = Modifier.padding(vertical = 4.dp))
//
//        }
//        Spacer(modifier = Modifier.height(16.dp))
//        Text("Pesquisar......", fontSize = 20.sp, fontWeight = FontWeight.Bold)
//        Spacer(modifier = Modifier.height(8.dp))
//        searchItems.forEach { (first, second) ->
//            Text(first, fontSize = 16.sp, modifier = Modifier.padding(vertical = 4.dp))
//            Text(second, fontSize = 16.sp, modifier = Modifier.padding(vertical = 4.dp))
//            Spacer(modifier = Modifier.height(8.dp))
//        }
//        Spacer(modifier = Modifier.height(16.dp))
//        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
//            Text("Home", fontSize = 16.sp)
//            Text("Mapa", fontSize = 16.sp)
//            Text("Explorar", fontSize = 16.sp)
//            Text("Extinção", fontSize = 16.sp)
//            Text("Perfil", fontSize = 16.sp)
//        }
//    }
//
//
//}



//}
