package com.example.examen

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ResultFragment : Fragment(R.layout.fragment_result) {
    private lateinit var tvCurrentScore: TextView
    private lateinit var tvHighScore: TextView
    private lateinit var rvHistory: RecyclerView
    private lateinit var btnReplay: Button
    private lateinit var btnExit: Button

    // Historial en memoria
    companion object {
        val scoreHistory = mutableListOf<Int>()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Referencias
        tvCurrentScore = view.findViewById(R.id.tvCurrentScore)
        tvHighScore = view.findViewById(R.id.tvHighScore)
        rvHistory = view.findViewById(R.id.rvHistory)
        btnReplay = view.findViewById(R.id.btnReplay)
        btnExit = view.findViewById(R.id.btnExit)
        val score = arguments?.getInt("score", 0) ?: 0
        val currentScore = score
        tvCurrentScore.text = "Puntaje actual: $currentScore"

        // Guardar en historial de sesión
        scoreHistory.add(currentScore)

        // Mostrar mejor puntaje (SharedPreferences)
        val prefs = requireContext().getSharedPreferences("color_game", Context.MODE_PRIVATE)
        val highScore = prefs.getInt("high_score", 0)

        if (currentScore > highScore) {
            prefs.edit().putInt("high_score", currentScore).apply()
            tvHighScore.text = "Máximo histórico: $currentScore"
        } else {
            tvHighScore.text = "Máximo histórico: $highScore"
        }

        // Configurar RecyclerView
        rvHistory.layoutManager = LinearLayoutManager(requireContext())
        rvHistory.adapter = ScoreAdapter(scoreHistory)

        // Volver a jugar
        btnReplay.setOnClickListener {
            findNavController().navigate(R.id.action_result_to_game)
        }

        btnExit.setOnClickListener {
            requireActivity().finishAffinity()
        }
    }
}