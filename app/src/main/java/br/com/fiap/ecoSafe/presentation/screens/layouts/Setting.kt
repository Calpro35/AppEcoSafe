package br.com.fiap.ecoSafe.presentation.screens.layouts

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.ecoSafe.presentation.components.Footer
import br.com.fiap.ecoSafe.presentation.components.HamburgerMenu
import br.com.fiap.ecoSafe.ui.theme.RobotoFontFamily
import br.com.fiap.ecosafe.R

@Composable
fun Setting(navController: NavController) {
    var isMenuOpen by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        // Conteúdo principal da tela
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp)
        ) {
            item {
                HeaderSetting(
                    onMenuClick = { isMenuOpen = true },
                    BackClick = { navController.navigate("home_Screen") }
                )

                Spacer(modifier = Modifier.height(24.dp))

                MainSetting()
            }
        }

        // Menu Hambúrguer
        if (isMenuOpen) {
            HamburgerMenu(
                onCloseClick = { isMenuOpen = false },
                onItemClick = { destination ->
                    navController.navigate(destination)
                    isMenuOpen = false
                }
            )
        }

        // Footer fixo na parte inferior
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
fun HeaderSetting(
    onMenuClick: () -> Unit,
    BackClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Ícone de voltar à esquerda
        IconButton(
            onClick = BackClick,
            modifier = Modifier.size(48.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "fechar",
                tint = Color.Black
            )
        }

        // Spacer para empurrar o ícone do menu hambúrguer para a direita
        Spacer(modifier = Modifier.weight(1f))

        // Ícone do menu hambúrguer à direita
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

@Composable
fun MainSetting() {
    val acessos = listOf(
        Acesso("Alterar Senha", R.drawable.circle), // Substitua pelo ícone correto
        Acesso("Dados de Acesso", R.drawable.circle), // Substitua pelo ícone correto
        Acesso("Temas", R.drawable.circle), // Substitua pelo ícone correto
        Acesso("Excluir Conta", R.drawable.circle) // Substitua pelo ícone correto
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.settings_ham),
                contentDescription = "engrenagem",
                modifier = Modifier.size(47.dp),
                tint = Color(0xFF314C1C)
            )

            Text(
                text = "Configurações",
                fontSize = 27.sp,
                fontFamily = RobotoFontFamily,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        AcessosList(acessos = acessos)
    }
}

@Composable
fun AcessosList(acessos: List<Acesso>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 5.dp)
    ) {
        Spacer(modifier = Modifier.height(15.dp)) // Espaço no topo da lista

        // Iterar sobre a lista de acessos
        acessos.forEach { acesso ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = acesso.icon),
                    contentDescription = "Ícone de ${acesso.title}",
                    modifier = Modifier.size(8.dp),
                    tint = Color(0xFF000000)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = acesso.title,
                    fontSize = 18.sp,
                    fontFamily = RobotoFontFamily,
                    color = Color(0xFF000000)
                )
            }

            Spacer(modifier = Modifier.height(8.dp)) // Espaço entre os itens
        }
    }
}

data class Acesso(
    val title: String,
    val icon: Int // Ícone associado ao acesso
)