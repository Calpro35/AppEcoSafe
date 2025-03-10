package br.com.fiap.ecoSafe.presentation.screens.auth

import CustomTextField
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.ecoSafe.presentation.componets.GradientButton
import br.com.fiap.ecoSafe.ui.theme.RobotoFontFamily
import br.com.fiap.ecosafe.R

@Composable
fun ForgetScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Ícone de voltar no topo da tela
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.align(Alignment.Start)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Voltar",
                tint = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(15.dp))


        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "Logo",
            modifier = Modifier
                .size(110.dp)
                .aspectRatio(1f) // Mantém a proporção (quadrado)
                .align(Alignment.CenterHorizontally) // Centraliza horizontalmente
        )

        Spacer(modifier = Modifier.height(15.dp))
        // Título "Esqueceu a senha?"
        Text(
            text = "Esqueceu a senha?",
            letterSpacing = 2.sp,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF6F7688),
            modifier = Modifier.align(Alignment.CenterHorizontally)


        )

        Spacer(modifier = Modifier.height(15.dp))



        Text(
            text = "Para recuperar o seu acesso, preencha o campo com o e-mail cadastrado em sua conta.",
            fontSize = 18.sp,
            color = Color.Gray,
            modifier = Modifier
                .fillMaxWidth() // Ocupa toda a largura disponível
                .padding(horizontal = 10.dp, vertical = 5.dp),

            textAlign = TextAlign.Center, // Centraliza o texto dentro do Text
            maxLines = 3 // Define o número máximo de linhas
        )

        Spacer(modifier = Modifier.height(18.dp))

        // Campo de E-mail
        CustomTextField(
            value = email,
            onValueChange = { email = it },
            label = "E-mail",
            placeholder = "Digite seu e-mail",


            )

        Spacer(modifier = Modifier.height(40.dp))

        GradientButton(
            text = "Recuperar acesso",
            onClick = {

            }
        )

        Spacer(modifier = Modifier.height(50.dp))

        // Botão de Voltar
        TextButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Voltar",
                fontSize = 15.sp,
                color = Color.Gray
            )
        }





    }

}