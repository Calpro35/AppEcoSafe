package br.com.fiap.ecoSafe.presentation.screens.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.ecoSafe.presentation.componets.TextField
import br.com.fiap.ecoSafe.presentation.navigation.Screen
import br.com.fiap.ecosafe.R

@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // exemplo de usar o componente
           TextField(
            value = email,
            onValueChange = { email = it },
            label = "E-mail",

            // coloca  o icone para o final mais e preciso importa ele no componente
           // trailingIcon = {
           //   Icon(painter = painterResource(R.drawable.email_24),
           //   contentDescription = "icone de email" )
           // },
            // coloca o icone no comeco do label
            placeholder = "Digite seu e-mail",
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.email_24),
                    contentDescription = "icone de email"
                )
            }

        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Senha") },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate(Screen.Home.route) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Entrar")
        }
    }
}

