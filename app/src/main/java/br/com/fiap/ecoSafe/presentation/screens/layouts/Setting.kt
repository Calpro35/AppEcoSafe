package br.com.fiap.ecoSafe.presentation.screens.layouts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
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
import br.com.fiap.ecoSafe.presentation.componets.HamburgerMenu
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

                Spacer(modifier = Modifier.height(24.dp).padding(10.dp))
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
    }
}



@Composable
fun HeaderSetting(
    onMenuClick: () -> Unit,
    BackClick: () -> Unit // Adicione um parâmetro para a ação de voltar
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Ícone de voltar à esquerda
        IconButton(
            onClick = BackClick, // Ação ao clicar na seta de voltar
            modifier = Modifier.size(48.dp) // Tamanho do ícone aumentado
        ) {
            Icon(
                imageVector = Icons.Default.Close, // Ícone de seta de voltar
                contentDescription = "fechar",
                tint = Color.Black // Cor do ícone
            )
        }

        // Spacer para empurrar o ícone do menu hambúrguer para a direita
        Spacer(modifier = Modifier.weight(1f))

        // Ícone do menu hambúrguer à direita
        IconButton(
            onClick = onMenuClick,
            modifier = Modifier.size(48.dp) // Tamanho do ícone aumentado
        ) {
            Icon(
                painter = painterResource(id = R.drawable.menu_ham),
                contentDescription = "Abrir Menu",
                modifier = Modifier.size(32.dp) // Tamanho do ícone interno
            )
        }
    }
}

