package com.example.urbankicks.vistas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.urbankicks.ui.theme.CafePrincipal
import com.example.urbankicks.ui.theme.FondoClaro
import com.example.urbankicks.ui.theme.TextoGris

@Composable
fun PerfilView(navController: NavHostController) {

    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var idiomaSeleccionado by remember { mutableStateOf("Español (México)") }
    var mensaje by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = FondoClaro
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            // Encabezado
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Regresar",
                        tint = CafePrincipal
                    )
                }

                Text(
                    text = "Mi Perfil",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.width(48.dp))
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // Ícono de perfil
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "",
                    tint = CafePrincipal,
                    modifier = Modifier.size(64.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {

                        // Campo nombre
                        Text(
                            text = "Nombre completo",
                            color = TextoGris,
                            fontSize = 12.sp
                        )
                        OutlinedTextField(
                            value = nombre,
                            onValueChange = { nombre = it },
                            placeholder = { Text("Tu nombre") },
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        // Campo correo
                        Text(
                            text = "Email",
                            color = TextoGris,
                            fontSize = 12.sp
                        )
                        OutlinedTextField(
                            value = correo,
                            onValueChange = { correo = it },
                            placeholder = { Text("tucorreo@email.com") },
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        // Campo contraseña
                        Text(
                            text = "Contraseña",
                            color = TextoGris,
                            fontSize = 12.sp
                        )
                        OutlinedTextField(
                            value = contrasena,
                            onValueChange = { contrasena = it },
                            placeholder = { Text("••••••••••") },
                            visualTransformation = PasswordVisualTransformation(),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        TextButton(onClick = { }) {
                            Text(
                                text = "Cambiar Contraseña",
                                color = TextoGris,
                                fontSize = 12.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        // Selector de idioma
                        Text(
                            text = "Idioma:",
                            color = TextoGris,
                            fontSize = 12.sp
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Button(
                                onClick = { idiomaSeleccionado = "Español (México)" },
                                shape = RoundedCornerShape(8.dp),
                                colors = if (idiomaSeleccionado == "Español (México)")
                                    ButtonDefaults.buttonColors(containerColor = CafePrincipal)
                                else
                                    ButtonDefaults.buttonColors(containerColor = Color.White)
                            ) {
                                Text(
                                    text = "Español (México)",
                                    color = if (idiomaSeleccionado == "Español (México)")
                                        Color.White else CafePrincipal,
                                    fontSize = 12.sp
                                )
                            }

                            Button(
                                onClick = { idiomaSeleccionado = "English" },
                                shape = RoundedCornerShape(8.dp),
                                colors = if (idiomaSeleccionado == "English")
                                    ButtonDefaults.buttonColors(containerColor = CafePrincipal)
                                else
                                    ButtonDefaults.buttonColors(containerColor = Color.White)
                            ) {
                                Text(
                                    text = "English (US)",
                                    color = if (idiomaSeleccionado == "English")
                                        Color.White else CafePrincipal,
                                    fontSize = 12.sp
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        if (mensaje.isNotEmpty()) {
                            Text(
                                text = mensaje,
                                color = CafePrincipal,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }

                        // Botón guardar con ícono
                        Button(
                            onClick = {
                                if (nombre.trim().isEmpty()) {
                                    mensaje = "Ingresa tu nombre"
                                } else {
                                    mensaje = "Perfil guardado"
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = CafePrincipal)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Save,
                                contentDescription = ""
                            )
                            Text(
                                text = "   Guardar",
                                color = Color.White,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}