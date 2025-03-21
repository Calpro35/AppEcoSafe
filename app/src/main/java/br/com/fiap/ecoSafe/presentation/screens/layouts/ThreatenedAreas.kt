package br.com.fiap.ecoSafe.presentation.screens.layouts

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.room.util.getColumnIndex
import br.com.fiap.ecoSafe.data.model.User
import br.com.fiap.ecoSafe.presentation.components.BannerWithMap
import br.com.fiap.ecoSafe.presentation.components.Footer
import br.com.fiap.ecoSafe.presentation.components.GradientButton
import br.com.fiap.ecoSafe.presentation.components.HamburgerMenu
import br.com.fiap.ecoSafe.presentation.components.SearchBar
import br.com.fiap.ecosafe.R

@Composable
fun ThreatenedAreas(navController: NavController) {

    var isMenuOpen by remember { mutableStateOf(false) }


    Box(modifier = Modifier.fillMaxSize()) {
        // Conteúdo principal da tela
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp)
        ) {
            item {
                HeaderAreas(
                    onMenuClick = { isMenuOpen = true },
                    onBackClick = { navController.navigate("home_Screen") }
                )
                Spacer(
                    modifier = Modifier
                        .height(15.dp)
                        .padding(10.dp)
                )
                MainAreas()
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
fun HeaderAreas(
    onMenuClick: () -> Unit,
    onBackClick: () -> Unit // Adicione um parâmetro para a ação de voltar
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Ícone de voltar à esquerda
        IconButton(
            onClick = onBackClick, // Ação ao clicar na seta de voltar
            modifier = Modifier.size(48.dp) // Tamanho do ícone aumentado
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack, // Ícone de seta de voltar
                contentDescription = "Voltar",
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



@Composable
fun MainAreas(modifier: Modifier = Modifier) {

    Spacer(modifier = Modifier.height(15.dp))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 5.dp),
        horizontalAlignment = Alignment.Start
    ) {


        //

        Text(
            text = "Áreas Ameaçadas",
            modifier = Modifier.padding(start = 7.dp),
            fontSize = 18.sp,
            color = Color(0xFF35580C),
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 1.sp

        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Endereco: ",
            modifier = Modifier.padding(start = 7.dp),
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 1.sp,
            color = Color(0xFF696565)

        )
        Spacer(modifier = Modifier.height(2.dp))
        // Barra de Pesquisa
        SearchBar {}
        Spacer(modifier = Modifier.height(24.dp))


        // Títulos
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = "Animais",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp, // Tamanho da fonte muda ao clicar
                modifier = Modifier

                    .clickable { },
                color = Color(0xFF696565)
            )

            Text(
                text = "Plantas",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp, // Tamanho da fonte muda ao clicar
                modifier = Modifier
                    .clickable {},
                color = Color(0xFF696565) // Muda a cor ao clicar
            )

            Text(
                text = "Desmatadas",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp, // Tamanho da fonte muda ao clicar
                modifier = Modifier

                    .clickable { },
                color = Color(0xFF696565)// Muda a cor ao clicar
            )

        }

        Spacer(modifier = Modifier.height(24.dp))

    }

    Column(
             modifier = Modifier.fillMaxWidth().fillMaxHeight()

    ) {

        BannerWithMap() // Para exibir um mapa

        Spacer(modifier = Modifier.height(60.dp))




    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center

    ) {
        Text(
            text="Voltar",

            color = Color.Gray
        )

    }


}
}