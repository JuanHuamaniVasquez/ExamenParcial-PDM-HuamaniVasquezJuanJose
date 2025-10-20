package com.example.examen

import android.app.AlertDialog
import android.graphics.Color
import android.media.MediaPlayer
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
    // Referencias a los elementos visuales
    private lateinit var colorView: View
    private lateinit var tvScore: TextView
    private lateinit var tvTimer: TextView
    private lateinit var gridLayout: GridLayout
    // Variables del juego
    private var score = 0
    private var currentColorName = ""
    private var timer: CountDownTimer? = null

    // MediaPlayers
    private var correctSound: MediaPlayer? = null
    private var wrongSound: MediaPlayer? = null
    private var timeOverSound: MediaPlayer? = null

    // Lista de colores con su nombre y valor
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

        // Referencias a los botones de colores
        val btnRed = view.findViewById<Button>(R.id.btnRed)
        val btnGreen = view.findViewById<Button>(R.id.btnGreen)
        val btnBlue = view.findViewById<Button>(R.id.btnBlue)
        val btnYellow = view.findViewById<Button>(R.id.btnYellow)
        val btnBlack = view.findViewById<Button>(R.id.btnBlack)
        val btnWhite = view.findViewById<Button>(R.id.btnWhite)

        // Listeners de botones
        btnRed.setOnClickListener { checkAnswer("Rojo") }
        btnGreen.setOnClickListener { checkAnswer("Verde") }
        btnBlue.setOnClickListener { checkAnswer("Azul") }
        btnYellow.setOnClickListener { checkAnswer("Amarillo") }
        btnBlack.setOnClickListener { checkAnswer("Negro") }
        btnWhite.setOnClickListener { checkAnswer("Blanco") }

        // Inicializar sonidos
        correctSound = MediaPlayer.create(requireContext(), R.raw.correct)
        wrongSound = MediaPlayer.create(requireContext(), R.raw.wrong)
        timeOverSound = MediaPlayer.create(requireContext(), R.raw.time_over)

        // Iniciar juego
        showStartDialog()
    }

    // Muestra un cuadro de diálogo para comenzar
    private fun showStartDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("¿Estas Listo?")
            .setPositiveButton("Comenzar") { dialog, _ ->
                dialog.dismiss()
                startTimer()        // inicia el cronómetro
                showRandomColor()   // muestra un color al azar
            }
            .setCancelable(false)
            .show()
    }
    // Muestra un color aleatorio en el recuadro
    private fun showRandomColor() {
        val randomColor = colors.random()
        currentColorName = randomColor.first
        colorView.setBackgroundColor(randomColor.second)
    }

    // Verifica si la respuesta del jugador es correcta
    private fun checkAnswer(selectedColor: String) {
        if (selectedColor == currentColorName) {
            // Si acierta se suma un punto y reproduce sonido correcto
            score++
            correctSound?.start()
            tvScore.text = "$score"

        }
        else {
            // Si falla se resta un punto y reproduce sonido de error
            score--
            wrongSound?.start()
            tvScore.text = "$score"
        }
        shuffleButtons() // cambiar el orden de botones después de responder
        showRandomColor() // cambiar color después de responder
    }

    // Inicia el temporizador de 30 segundos
    private fun startTimer() {
        timer = object : CountDownTimer(30_000, 1_000) {
            override fun onTick(millisUntilFinished: Long) {
                val seconds = millisUntilFinished / 1000
                tvTimer.text = "${seconds}s" // muestra segundos restantes
            }

            // Cuando el tiempo se acaba:
            override fun onFinish() {
                timeOverSound?.start()
                AlertDialog.Builder(requireContext())
                    .setTitle("Se acabo el Tiempo :(")
                    .setPositiveButton("Continuar") { dialog, _ ->
                        dialog.dismiss()
                        goToResult() // va a la pantalla de resultados
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
    // Mezcla el orden de los botones del grid
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
    // Libera los recursos y detiene sonidos/timer al salir del fragmento
    override fun onDestroyView() {
        super.onDestroyView()
        timer?.cancel()
        correctSound?.release()
        wrongSound?.release()
        timeOverSound?.release()
    }

}