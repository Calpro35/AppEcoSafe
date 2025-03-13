package br.com.fiap.ecoSafe.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit // Callback para quando o usuário realizar a pesquisa
) {
    var searchQuery by remember { mutableStateOf("") } // Estado interno para o texto da pesquisa
    val focusManager = LocalFocusManager.current

    // Card para sombra e bordas arredondadas
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        shape = RoundedCornerShape(16.dp), // Bordas arredondadas
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp) // Sombra
    ) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF5F5F5)), // Fundo cinza claro
            placeholder = {
                Text(
                    text = "Pesquisar...",
                    color = Color(0xFFD3BEBE),// Cor do placeholder
                    letterSpacing = 1.sp
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Ícone de Pesquisa",
                    modifier = Modifier.padding(end = 5.dp), // Espaçamento entre ícone e texto
                    tint = Color.Gray // Cor do ícone
                )
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    focusManager.clearFocus() // Fecha o teclado ao pressionar "Search"
                    onSearch(searchQuery) // Executa a ação de pesquisa
                }
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Transparent, // Borda transparente quando focado
                unfocusedBorderColor = Color.Transparent, // Borda transparente quando não focado
                containerColor = Color(0xFFF5F5F5) // Fundo cinza claro
            ),
            shape = RoundedCornerShape(16.dp) // Bordas arredondadas
        )
    }
}