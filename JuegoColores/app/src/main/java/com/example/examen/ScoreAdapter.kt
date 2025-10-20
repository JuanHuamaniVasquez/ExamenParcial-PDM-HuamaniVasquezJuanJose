package com.example.examen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Adapter para mostrar la lista de puntajes en el RecyclerView
class ScoreAdapter(private val scores: List<Int>) :
    RecyclerView.Adapter<ScoreAdapter.ScoreViewHolder>() {

    // Clase interna que representa cada fila del RecyclerView
    class ScoreViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvItem: TextView = view.findViewById(R.id.tvItemScore)// Referencia al TextView del ítem
    }
    // Crea el layout de cada ítem de la lista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_score, parent, false)// Usa el layout item_score.xml
        return ScoreViewHolder(view)
    }
    // Asigna los datos a cada elemento del RecyclerView
    override fun onBindViewHolder(holder: ScoreViewHolder, position: Int) {
        val score = scores[position] // Obtiene el puntaje según la posición
        holder.tvItem.text = "Partida ${position + 1}:  $score puntos"
    }
    // Devuelve la cantidad total de elementos en la lista
    override fun getItemCount() = scores.size
}
