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
fun Profile(navController: NavController) {

    var isMenuOpen by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        // Conteúdo principal da tela
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp)
        ) {
            item {
                HeaderProfile(onMenuClick = { isMenuOpen = true })
                Spacer(modifier = Modifier.height(24.dp).padding(10.dp))
                MainProfile()
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
fun HeaderProfile(
    onMenuClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Linha com o ícone do menu e o texto "Home"
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Texto "Home" centralizado
            Text(
                text = "Perfil",
                modifier = Modifier
                    .weight(1f) // Ocupa o espaço restante e centraliza o texto
                    .offset(x = 22.dp),
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                letterSpacing = 1.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2D2A2A),


                )

            // Ícone do menu hambúrguer (agora à direita)
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



}


@Composable
fun MainProfile(modifier: Modifier = Modifier) {

    Spacer(modifier = Modifier.height(15.dp))

    Column(
        modifier = Modifier.fillMaxSize()
    ){


        //Conteudo da pagina



    }


}


