package com.example.esercitazioneuno.pianetaRecycler;

import android.view.View;
import android.widget.TextView;



import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



public class PianetaViewHolder extends RecyclerView.ViewHolder {

    TextView distanzaValore;
    TextView massaValore;
    TextView rotazioneValore;
    TextView rivoluzioneValore;
    TextView diametroValore;
    TextView satellitiValore;

    public PianetaViewHolder(@NonNull View itemView) {
        super(itemView);
    }




}
