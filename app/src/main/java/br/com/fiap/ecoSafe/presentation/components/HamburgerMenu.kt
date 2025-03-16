package br.com.fiap.ecoSafe.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.ecoSafe.presentation.navigation.Screen
import br.com.fiap.ecosafe.R

@Composable
fun HamburgerMenu(onCloseClick: () -> Unit, onItemClick: (String) -> Unit) {
    val menuItems = listOf(
        MenuItem("Home", Screen.Home.route, R.drawable.home_ham),
        MenuItem("Descobertas Recentes", Screen.RecentDiscoveries.route, R.drawable.search_ham),
        MenuItem("Explorar", Screen.Camera.route, R.drawable.camera_ham),
        MenuItem("Áreas Ameaçadas", Screen.ThreatenedAreas.route, R.drawable.map_ham),
        MenuItem("Espécies Ameaçadas", Screen.EndangeredSpecies.route, R.drawable.book_ham),
        MenuItem("Denúncias", Screen.Denounces.route, R.drawable.hand_ham),
        MenuItem("Perfil", Screen.Profile.route, R.drawable.person_ham),
        MenuItem("Configurações", Screen.Setting.route, R.drawable.settings_ham),
        MenuItem("Logout", Screen.Login.route, R.drawable.output_ham)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.7f))
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .fillMaxHeight()
                .background(Color.White)
                .padding(16.dp)
        ) {
            // Header do Menu
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_logo),
                    contentDescription = "Logo",
                    modifier = Modifier.size(75.dp)
                )
                IconButton(onClick = onCloseClick) {
                    Box(

                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.close_24),
                            contentDescription = "Fechar Menu",
                            tint = Color(0xFF121212) // Cor do ícone (vermelho)
                        )
                    }
                }
            }

            // Linha Separadora
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                color = Color.Gray
            )

            // Itens do Menu
            LazyColumn {
                items(menuItems) { item ->
                    MenuItemRow(item, onItemClick)
                }
            }
        }
    }
}


@Composable
fun MenuItemRow(item: MenuItem, onItemClick: (String) -> Unit) {
    val isConfig = item.route == Screen.Setting.route

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(item.route) }
            .padding(vertical = 13.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = item.iconRes),
            contentDescription = item.label,
            tint = Color(0xFF458018),
            modifier = Modifier.size(25.dp)
        )
        Spacer(modifier = Modifier.width(14.dp))
        Text(
            text = item.label,
            fontSize = 16.sp,
            color = Color.Black
        )
        if (isConfig) {
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .size(28.dp)

            )
        }
    }
}

data class MenuItem(val label: String, val route: String, val iconRes: Int)