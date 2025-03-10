package br.com.fiap.ecoSafe.presentation.screens.auth

import CustomTextField
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.ecoSafe.presentation.componets.GradientButton
import br.com.fiap.ecoSafe.ui.theme.InterFontFamily
import br.com.fiap.ecoSafe.ui.theme.OpensSansFontFamily
import br.com.fiap.ecoSafe.ui.theme.RobotoFontFamily
import br.com.fiap.ecosafe.R

@Composable
fun CadastroScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

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

        Spacer(modifier = Modifier.height(10.dp))

        // Título "Cadastro"
        Text(
            text = "Cadastro",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = RobotoFontFamily,
            color = Color(0xFF4A5237),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(25.dp))

        CustomTextField(
            value = name,
            onValueChange = { name = it },
            label = "Nome",
            placeholder = "Digite seu nome completo",

            )

        Spacer(modifier = Modifier.height(8.dp))


        CustomTextField(
            value = email,
            onValueChange = { email = it },
            label = "E-mail",
            placeholder = "email@email.com",
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.email_24),
                    contentDescription = "Email",
                    tint = Color.Gray
                )
            }

        )


        Spacer(modifier = Modifier.height(8.dp))

        CustomTextField(
            value = password,
            onValueChange = { password = it },
            label = "Senha",
            placeholder = "Digite sua senha",
            trailIcon = {
                Icon(
                    painter = painterResource(R.drawable.visibility_off),
                    contentDescription = "Senha",
                    tint = Color.Gray
                )
            },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(8.dp))

        CustomTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = "Confirma Senha",
            placeholder = "Digite Novamente",
            trailIcon = {
                Icon(
                    painter = painterResource(R.drawable.visibility_off),
                    contentDescription = "Senha",
                    tint = Color.Gray
                )
            },
            visualTransformation = PasswordVisualTransformation(),

            )


        Spacer(modifier = Modifier.height(35.dp))

        // Botão de Login com Gradiente
        GradientButton(
            text = "Cadastrar",
            onClick = {
                // Lógica para cadastrar o usuário
                if (password == confirmPassword) {
                    // Navegar para a próxima tela ou realizar o cadastro
                    navController.navigate("login_screen")
                } else {
                    // Mostrar erro de senha não coincidente
                    println("As senhas não coincidem!")
                }
            }
        )


        Spacer(modifier = Modifier.height(30.dp))

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

