package com.example.examen

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import androidx.navigation.fragment.findNavController

class WelcomeFragment : Fragment(R.layout.fragment_welcome) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Referencia al botón
        val btnStart = view.findViewById<Button>(R.id.btnStart)

        // Mostrar las reglas del juego
        showRulesDialog()

        // Navegar al juego
        btnStart.setOnClickListener {
            findNavController().navigate(R.id.action_welcome_to_game)
        }
    }

    private fun showRulesDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Bienvenido al Juego de Colores")
            .setMessage(
                "Reglas del juego:\n"+
                "Aparecerá un color en pantalla.\n" +
                        "Presiona el botón que coincida con ese color.\n" +
                        "Cada acierto suma 1 punto.\n" +
                        "Cada fallo resta 1 punto.\n" +
                        "Tienes 30 segundos para lograr el mayor puntaje.\n\n" +
                        "¡Buena suerte!"
            )
            .setPositiveButton("Entendido") { dialog, _ ->
                dialog.dismiss()
            }
            .setCancelable(false)
            .show()
    }
}