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
import com.example.urbankicks.persistencia.Preferencias
import com.example.urbankicks.ui.theme.CafePrincipal
import com.example.urbankicks.ui.theme.FondoClaro
import com.example.urbankicks.ui.theme.TextoGris
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.launch

@Composable
fun PerfilView(navController: NavHostController) {

    val context = LocalContext.current
    val preferencias = Preferencias(context)
    val scope = rememberCoroutineScope()

    val nombreGuardado by preferencias.nombre.collectAsState(initial = "")
    val correoGuardado by preferencias.correo.collectAsState(initial = "")

    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }

    LaunchedEffect(nombreGuardado, correoGuardado) {
        nombre = nombreGuardado
        correo = correoGuardado
    }

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

                        // Nombre
                        Text("Nombre completo", color = TextoGris, fontSize = 12.sp)
                        OutlinedTextField(
                            value = nombre,
                            onValueChange = { nombre = it },
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        // Correo
                        Text("Email", color = TextoGris, fontSize = 12.sp)
                        OutlinedTextField(
                            value = correo,
                            onValueChange = { correo = it },
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        // Contraseña
                        Text("Contraseña", color = TextoGris, fontSize = 12.sp)
                        OutlinedTextField(
                            value = contrasena,
                            onValueChange = { contrasena = it },
                            visualTransformation = PasswordVisualTransformation(),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        if (mensaje.isNotEmpty()) {
                            Text(
                                text = mensaje,
                                color = CafePrincipal,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }

                        // Botón guardar
                        Button(
                            onClick = {
                                if (nombre.trim().isEmpty()) {
                                    mensaje = "Ingresa tu nombre"
                                } else if (correo.trim().isEmpty()) {
                                    mensaje = "Ingresa tu correo"
                                } else {
                                    scope.launch {
                                        preferencias.guardarPerfil(
                                            nombre = nombre,
                                            correo = correo,
                                            idioma = "Español (México)"
                                        )
                                        mensaje = "Perfil guardado correctamente"
                                    }
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
