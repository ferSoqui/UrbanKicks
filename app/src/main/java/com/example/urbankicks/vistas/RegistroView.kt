package com.example.urbankicks.vistas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.urbankicks.ui.theme.CafePrincipal
import com.example.urbankicks.ui.theme.FondoClaro
import com.example.urbankicks.ui.theme.TextoGris

@Composable
fun RegistroView(navController: NavHostController) {

    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var confirmarContrasena by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = FondoClaro
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Título principal
            Text(
                text = "Crea una",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "cuenta",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Campo de nombre
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre completo") },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Campo de correo
            OutlinedTextField(
                value = correo,
                onValueChange = { correo = it },
                label = { Text("Username o Email") },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Campo de contraseña
            OutlinedTextField(
                value = contrasena,
                onValueChange = { contrasena = it },
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Campo de confirmar contraseña
            OutlinedTextField(
                value = confirmarContrasena,
                onValueChange = { confirmarContrasena = it },
                label = { Text("Confirmar contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Texto de términos y condiciones
            Text(
                text = "Al registrarte, aceptas los términos y condiciones.",
                color = TextoGris,
                fontSize = 11.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            if (error.isNotEmpty()) {
                Text(error, color = MaterialTheme.colorScheme.error)
                Spacer(modifier = Modifier.height(8.dp))
            }

            // Botón principal con color café
            Button(
                onClick = {
                    if (nombre.trim().isEmpty()) {
                        error = "Ingresa tu nombre"
                        return@Button
                    }
                    if (correo.trim().isEmpty()) {
                        error = "Ingresa tu correo"
                        return@Button
                    }
                    if (contrasena.trim().isEmpty()) {
                        error = "Ingresa una contraseña"
                        return@Button
                    }
                    if (contrasena != confirmarContrasena) {
                        error = "Las contraseñas no coinciden"
                        return@Button
                    }
                    navController.navigate("login") {
                        popUpTo("registro") { inclusive = true }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = CafePrincipal)
            ) {
                Text(
                    text = "Registrarse",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Separador
            Text(
                text = "- O continúa con -",
                color = TextoGris,
                fontSize = 12.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botones sociales
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedButton(
                    onClick = { },
                    shape = RoundedCornerShape(50.dp)
                ) {
                    Text("G")
                }
                OutlinedButton(
                    onClick = { },
                    shape = RoundedCornerShape(50.dp)
                ) {
                    Text("")
                }
                OutlinedButton(
                    onClick = { },
                    shape = RoundedCornerShape(50.dp)
                ) {
                    Text("f")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Link a login
            TextButton(onClick = { navController.popBackStack() }) {
                Text(
                    text = "¿Ya tienes cuenta?  ",
                    color = TextoGris,
                    fontSize = 13.sp
                )
                Text(
                    text = "Inicia sesión",
                    color = CafePrincipal,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )
            }
        }
    }
}