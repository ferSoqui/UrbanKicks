package com.example.urbankicks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.lifecycleScope
import com.example.urbankicks.navegacion.NavManager
import com.example.urbankicks.persistencia.Preferencias
import com.example.urbankicks.ui.theme.UrbanKicksTheme
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val preferencias = Preferencias(this)
        lifecycleScope.launch {
            val idioma = preferencias.idioma.first()
            val localeList = LocaleListCompat.forLanguageTags(idioma)
            AppCompatDelegate.setApplicationLocales(localeList)
        }

        enableEdgeToEdge()
        setContent {
            UrbanKicksTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavManager(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}