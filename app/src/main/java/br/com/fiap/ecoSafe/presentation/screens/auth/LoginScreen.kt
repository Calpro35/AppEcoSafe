package br.com.fiap.ecoSafe.presentation.screens.auth

import CustomTextField
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.ecoSafe.presentation.componets.GradientButton
import br.com.fiap.ecoSafe.presentation.componets.SocialButton
import br.com.fiap.ecoSafe.presentation.navigation.Screen
import br.com.fiap.ecoSafe.ui.theme.OpensSansFontFamily
import br.com.fiap.ecoSafe.ui.theme.RobotoFontFamily
import br.com.fiap.ecosafe.R

@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rememberMe by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título
        Text(
            text = "Bem vindo de volta!",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = RobotoFontFamily,
            color = Color.Black,
            modifier = Modifier.fillMaxWidth() // Ocupa toda a largura disponível
            .padding(start = 0.dp),
            letterSpacing = 1.sp,
            textAlign = TextAlign.Start // Alinha o texto à direita
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Entre com sua conta",
            fontFamily = RobotoFontFamily,
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.fillMaxWidth(),
            letterSpacing = 1.sp,// Ocupa toda a largura disponível
            textAlign = TextAlign.Start // Alinha o texto à direita
        )

        Spacer(modifier = Modifier.height(45.dp))

        // Campo de Email
        CustomTextField(
            value = email,
            onValueChange = { email = it },
            label = "E-mail",
            placeholder = "Seu email",
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.email_24),
                    contentDescription = "Email",
                    tint = Color.Gray
                )

            }

        )

       // Spacer(modifier = Modifier.height(10.dp))

        // Campo de Senha
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

       // Spacer(modifier = Modifier.height(16.dp))

        // Checkbox "Manter-me Conectado"
        Row(
            verticalAlignment = Alignment.CenterVertically, // Alinha os itens verticalmente no centro
            modifier = Modifier.fillMaxWidth()
        ) {
            Checkbox(
                checked = rememberMe,
                onCheckedChange = { rememberMe = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = Color.Blue,
                    uncheckedColor = Color.Gray
                ),
                modifier = Modifier.padding(0.dp) // Remove o padding interno do Checkbox
            )
            Text(
                text = "manter-me Conectado",
                fontSize = 13.sp,
                color = Color.Gray,
               // modifier = Modifier.padding(end = 5.dp) // Margem mínima entre o Checkbox e o Texto
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Botão de Login com Gradiente
        GradientButton (
            text = "Login",
            onClick = { navController.navigate("home_screen") }
        )

        Spacer(modifier = Modifier.height(5.dp))

        // Link "Esqueceu a senha?"
        TextButton(
            onClick = { navController.navigate(Screen.Forget.route)},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Esqueceu a senha?",
                fontSize = 15.sp,
                color = Color(0xFF54408C)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))


        Text(
            text = "Não tem uma conta? Sign Up",
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.clickable {
                navController.navigate("cadastro_screen")
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Ou",
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botão de Login com Google
        SocialButton (
            text = "Sign in with Google",
            icon = R.drawable.ic_google,
            onClick = { /* Ação para login com Google */ }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Botão de Login com Apple
        SocialButton(
            text = "Sign in with Apple",
            icon = R.drawable.ic_apple,
            onClick = { /* Ação para login com Apple */ }
        )
    }
}



