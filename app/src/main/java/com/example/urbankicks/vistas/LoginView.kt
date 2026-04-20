package com.example.urbankicks.vistas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.urbankicks.R
import com.example.urbankicks.ui.theme.CafePrincipal
import com.example.urbankicks.ui.theme.FondoClaro
import com.example.urbankicks.ui.theme.TextoGris

@Composable
fun LoginView(navController: NavHostController) {

    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
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

            // Título alineado a la izquierda
            Text(
                text = "Bienvenido",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )

            Text(
                text = "de nuevo!",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Campo correo
            OutlinedTextField(
                value = correo,
                onValueChange = { correo = it },
                label = { Text("Username o Email") },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Campo contraseña
            OutlinedTextField(
                value = contrasena,
                onValueChange = { contrasena = it },
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Olvidaste contraseña
            Box(modifier = Modifier.fillMaxWidth()) {
                TextButton(
                    onClick = { },
                    modifier = Modifier.align(Alignment.CenterEnd)
                ) {
                    Text(
                        text = "Olvidaste la contraseña?",
                        color = TextoGris,
                        fontSize = 12.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            if (error.isNotEmpty()) {
                Text(error, color = MaterialTheme.colorScheme.error)
                Spacer(modifier = Modifier.height(8.dp))
            }

            // Botón login
            Button(
                onClick = {
                    if (correo.trim().isEmpty() || contrasena.trim().isEmpty()) {
                        error = "Ingresa tu correo y contraseña"
                    } else {
                        navController.navigate("home") {
                            popUpTo("login") { inclusive = true }
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = CafePrincipal)
            ) {
                Text(
                    text = "Iniciar sesión",
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

            // 🔥 BOTONES CON TUS SVG
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                // Google
                OutlinedButton(
                    onClick = { },
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier.size(60.dp),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.google_icon_logo_svgrepo_com),
                        contentDescription = "Google",
                        modifier = Modifier.size(28.dp),
                        tint = Color.Unspecified // 👈 mantiene colores reales
                    )
                }

                // Apple
                OutlinedButton(
                    onClick = { },
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier.size(60.dp),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.apple_black_logo_svgrepo_com),
                        contentDescription = "Apple",
                        modifier = Modifier.size(28.dp),
                        tint = Color.Unspecified
                    )
                }

                // Facebook
                OutlinedButton(
                    onClick = { },
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier.size(60.dp),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.facebook_3_logo_svgrepo_com),
                        contentDescription = "Facebook",
                        modifier = Modifier.size(28.dp),
                        tint = Color.Unspecified
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Registro
            TextButton(onClick = { navController.navigate("registro") }) {
                Text(
                    text = "Crear una cuenta  ",
                    color = TextoGris,
                    fontSize = 13.sp
                )
                Text(
                    text = "Regístrate",
                    color = CafePrincipal,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )
            }
        }
    }
}