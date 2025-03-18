package br.com.fiap.ecoSafe.presentation.screens.layouts

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType.Companion.Uri
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.ecoSafe.presentation.components.Footer
import br.com.fiap.ecoSafe.presentation.components.HamburgerMenu
import br.com.fiap.ecosafe.R

import br.com.fiap.ecoSafe.ui.theme.OpensSansFontFamily
import java.net.URL


@Composable
fun Denounces(navController: NavController) {

    var isMenuOpen by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        // Conteúdo principal da tela
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp)
        ) {
            item {
                HeaderDenuncia(
                    onMenuClick = { isMenuOpen = true },
                    BackClick = { navController.navigate("home_Screen") }
                )
                Spacer(modifier = Modifier.height(24.dp))

                MainDenuncia()

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
fun HeaderDenuncia(
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
                contentDescription = "Fechar",
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
fun MainDenuncia() {
    val context = LocalContext.current

    // Lista de itens com texto e URL correspondente
    val denuncias = listOf(
        DenunciaItem("CAÇA-ilegal", "https://www.ibama.gov.br/denuncias"),
        DenunciaItem("Meio AMBIENTE", "https://www.gov.br/mma/pt-b"),
        DenunciaItem("MAUS-TRATOS", "https://www.polmil.sp.gov.br/unidades/especializadas/ambiental/"),
        DenunciaItem("ANIMAIS EM EXTINÇÃO", "https://www.exemplo.com/animais-extincao"),
        DenunciaItem("CUIDADOS ANIMAL FERIDO", "https://arcabrasil.org.br/")
    )

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        horizontalAlignment = Alignment.Start
    ) {
        // Título
        Text(
            text = "DENÚNCIAS",
            fontSize = 25.sp,
            color = Color(0xFFB51717),
            fontFamily = OpensSansFontFamily,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(45.dp))

        // Lista de denúncias
        denuncias.forEach { denuncia ->
            DenunciaItemView(denuncia, context)
            Spacer(modifier = Modifier.height(28.dp))
        }
    }
}

@Composable
fun DenunciaItemView(denuncia: DenunciaItem, context: android.content.Context) {
    Text(
        text = denuncia.text,
        fontSize = 18.sp,
        color = Color(0xFF2A8255),
        fontFamily = OpensSansFontFamily,
        modifier = Modifier
            .clickable {
                // Abrir o site no navegador
                openUrlInBrowser(denuncia.url, context)
            }
            .padding(8.dp) // Adicione padding para melhorar a área clicável
    )
}

// Função para abrir URL no navegador
fun openUrlInBrowser(url: String, context: android.content.Context) {
    try {
        // Verifica se o URL é válido
        URL(url) // Isso lançará uma exceção se o URL não for válido

        // Cria uma Intent para abrir o navegador
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = android.net.Uri.parse(url) // Usa Uri.parse internamente
        context.startActivity(intent)
    } catch (e: Exception) {
        // Trata erros (por exemplo, URL inválido)
        e.printStackTrace()
    }
}

// Data class para representar um item de denúncia
data class DenunciaItem(
    val text: String,
    val url: String
)