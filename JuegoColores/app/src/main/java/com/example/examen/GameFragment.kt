package com.example.examen

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import androidx.core.view.children
import androidx.navigation.fragment.findNavController

class GameFragment : Fragment(R.layout.fragment_game) {
    private lateinit var colorView: View
    private lateinit var tvScore: TextView
    private lateinit var tvTimer: TextView
    private lateinit var gridLayout: GridLayout

    private var score = 0
    private var currentColorName = ""
    private var timer: CountDownTimer? = null

    private val colors = listOf(
        Pair("Rojo", Color.RED),
        Pair("Verde", Color.GREEN),
        Pair("Azul", Color.BLUE),
        Pair("Amarillo", Color.YELLOW),
        Pair("Negro", Color.BLACK),
        Pair("Blanco", Color.WHITE)

    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Referencias a vistas
        colorView = view.findViewById(R.id.colorView)
        tvScore = view.findViewById(R.id.tvScore)
        tvTimer = view.findViewById(R.id.tvTimer)
        gridLayout = view.findViewById(R.id.layoutButtons)

        val btnRed = view.findViewById<Button>(R.id.btnRed)
        val btnGreen = view.findViewById<Button>(R.id.btnGreen)
        val btnBlue = view.findViewById<Button>(R.id.btnBlue)
        val btnYellow = view.findViewById<Button>(R.id.btnYellow)
        val btnBlack = view.findViewById<Button>(R.id.btnBlack)
        val btnWhite = view.findViewById<Button>(R.id.btnWhite)

        // Iniciar juego
        showStartDialog()

        // Listeners de botones
        btnRed.setOnClickListener { checkAnswer("Rojo") }
        btnGreen.setOnClickListener { checkAnswer("Verde") }
        btnBlue.setOnClickListener { checkAnswer("Azul") }
        btnYellow.setOnClickListener { checkAnswer("Amarillo") }
        btnBlack.setOnClickListener { checkAnswer("Negro") }
        btnWhite.setOnClickListener { checkAnswer("Blanco") }
    }

    private fun showStartDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("¿Listo?")
            .setPositiveButton("Comenzar") { dialog, _ ->
                dialog.dismiss()
                startTimer()
                showRandomColor()
            }
            .setCancelable(false)
            .show()
    }
    private fun showRandomColor() {
        val randomColor = colors.random()
        currentColorName = randomColor.first
        colorView.setBackgroundColor(randomColor.second)
    }

    private fun checkAnswer(selectedColor: String) {
        if (selectedColor == currentColorName) {
            score++
            tvScore.text = "$score"
        }
        else {
            score--
            tvScore.text = "$score"
        }
        shuffleButtons() // cambiar el orden de botones después de responder
        showRandomColor() // cambiar color después de responder
    }

    private fun startTimer() {
        timer = object : CountDownTimer(30_000, 1_000) {
            override fun onTick(millisUntilFinished: Long) {
                val seconds = millisUntilFinished / 1000
                tvTimer.text = "${seconds}s"
            }

            override fun onFinish() {
                AlertDialog.Builder(requireContext())
                    .setTitle("Termino el Juego :(")
                    .setPositiveButton("Continuar") { dialog, _ ->
                        dialog.dismiss()
                        goToResult()
                    }
                    .setCancelable(false)
                    .show()
            }
        }.start()
    }

    private fun goToResult() {
        timer?.cancel()
        // Navegar pasando el puntaje
        val bundle = Bundle().apply {
            putInt("score", score)
        }
        findNavController().navigate(R.id.action_game_to_result, bundle)
    }
    private fun shuffleButtons() {
        val buttons = gridLayout.children.filterIsInstance<Button>().toList()
        // Mezclar el orden de los botones
        val shuffledButtons = buttons.shuffled()

        // Remover todos los botones
        gridLayout.removeAllViews()


        // Agregar los botones en orden mezclado
        shuffledButtons.forEach { button ->
            gridLayout.addView(button)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        timer?.cancel()
    }

}