package br.com.fiap.ecoSafe.presentation.screens.auth

import CustomTextField
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.ecoSafe.data.model.User
import br.com.fiap.ecoSafe.data.repository.UserRepository
import br.com.fiap.ecoSafe.presentation.components.GradientButton
import br.com.fiap.ecoSafe.ui.theme.RobotoFontFamily
import br.com.fiap.ecosafe.R

@Composable
fun CadastroScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isConfirmPasswordVisible by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val userRepository = UserRepository(context)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
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
            placeholder = "Digite seu nome completo"
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

        Spacer(modifier = Modifier.height(8.dp))

        CustomTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = "Confirma Senha",
            placeholder = "Digite Novamente",
            trailIcon = {
                IconButton(
                    onClick = { isConfirmPasswordVisible = !isConfirmPasswordVisible }
                ) {
                    Icon(
                        painter = painterResource(
                            if (isConfirmPasswordVisible) R.drawable.visibility_on else R.drawable.visibility_off
                        ),
                        contentDescription = if (isConfirmPasswordVisible) "Ocultar senha" else "Mostrar senha",
                        tint = Color.Gray
                    )
                }
            },
            visualTransformation = if (isConfirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(35.dp))

        GradientButton(
            text = "Cadastrar",
            onClick = {
                // Lógica para cadastrar o usuário
                if (name != "" && email != "" && email.contains("@") &&
                    email.contains(".com") && password != "" && confirmPassword != "" &&
                    password == confirmPassword
                ) {
                    val user = User(0L, name, email, password)
                    userRepository.salvar(user)
                    navController.navigate("login_screen")
                } else {
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