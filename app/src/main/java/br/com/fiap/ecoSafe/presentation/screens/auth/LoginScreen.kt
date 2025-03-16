package br.com.fiap.ecoSafe.presentation.screens.auth

import CustomTextField
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.ecoSafe.data.repository.UserRepository
import br.com.fiap.ecoSafe.presentation.components.GradientButton
import br.com.fiap.ecoSafe.presentation.components.SocialButton
import br.com.fiap.ecoSafe.presentation.navigation.Screen
import br.com.fiap.ecoSafe.ui.theme.RobotoFontFamily
import br.com.fiap.ecosafe.R

@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rememberMe by remember { mutableStateOf(false) }
    var isPasswordVisible by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val userRepository = UserRepository(context)

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
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 0.dp),
            letterSpacing = 1.sp,
            textAlign = TextAlign.Start
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Entre com sua conta",
            fontFamily = RobotoFontFamily,
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.fillMaxWidth(),
            letterSpacing = 1.sp,
            textAlign = TextAlign.Start
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

        // Campo de Senha
        CustomTextField(
            value = password,
            onValueChange = { password = it },
            label = "Senha",
            placeholder = "Digite sua senha",
            trailIcon = {
                IconButton(
                    onClick = { isPasswordVisible = !isPasswordVisible }
                ) {
                    Icon(
                        painter = painterResource(
                            if (isPasswordVisible) R.drawable.visibility_on else R.drawable.visibility_off
                        ),
                        contentDescription = if (isPasswordVisible) "Ocultar senha" else "Mostrar senha",
                        tint = Color.Gray
                    )
                }
            },
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
        )

        // Checkbox "Manter-me Conectado"
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Checkbox(
                checked = rememberMe,
                onCheckedChange = { rememberMe = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = Color.Blue,
                    uncheckedColor = Color.Gray,

                ),
                modifier = Modifier.
                offset(x = (-7).dp)
            )
            Text(
                text = "manter-me Conectado",
                fontSize = 13.sp,
                color = Color.Gray,
                modifier = Modifier.offset(x = (-14).dp)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Botão de Login com Gradiente
        GradientButton(
            text = "Login",
            onClick = {
                if (!email.equals("") && email.contains("@") && email.contains(".com")
                    && !password.equals("")) {
                    if (userRepository.verificarLogin(email, password)) {
                        navController.navigate("home_screen")
                    }
                }
            }
        )

        Spacer(modifier = Modifier.height(5.dp))

        // Link "Esqueceu a senha?"
        TextButton(
            onClick = { navController.navigate(Screen.Forget.route) },
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

          Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 16.dp), // Ajuste o padding conforme necessário
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Traço à esquerda
            HorizontalDivider(
                modifier = Modifier
                    .weight(1f) // Ocupa o espaço disponível
                    .height(1.dp), // Altura do traço
                color = Color(0xFFE8E8E8) // Cor do traço
            )

            // Texto "Ou"
            Text(
                text = "Ou",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.padding(horizontal = 8.dp) // Espaço entre o texto e os traços
            )

            // Traço à direita
            HorizontalDivider(
                modifier = Modifier
                    .weight(1f) // Ocupa o espaço disponível
                    .height(1.dp), // Altura do traço
                color = Color(0xffE8E8E8)// Cor do traço
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botão de Login com Google
        SocialButton(
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