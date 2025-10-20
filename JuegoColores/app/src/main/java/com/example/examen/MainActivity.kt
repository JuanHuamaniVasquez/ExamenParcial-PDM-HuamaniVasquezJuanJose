package com.example.examen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.examen.R

// Clase principal que se ejecuta al iniciar la aplicación
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)  // Llama al metodo del ciclo de vida de la actividad
        setContentView(R.layout.activity_main) // Asigna el diseño (layout) que se mostrará en pantalla
    }
    }
}
