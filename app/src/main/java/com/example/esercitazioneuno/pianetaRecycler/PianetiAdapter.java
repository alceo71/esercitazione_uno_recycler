package com.example.esercitazioneuno.pianetaRecycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.esercitazioneuno.R;
import com.example.esercitazioneuno.common.model.Pianeta;
import com.example.esercitazioneuno.view.PianetaView;

import java.util.List;

public class PianetiAdapter extends RecyclerView.Adapter<PianetaViewHolder> {

    public List<PianetaView> pianeti;
    public Context context;

    public PianetiAdapter(List<PianetaView> pianeti, Context context){
        this.pianeti = pianeti;
        this.context = context;
    }


    @NonNull
    @Override
    public PianetaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View convertView = LayoutInflater.from(context).inflate(R.layout.pianeta, parent, false);

        PianetaViewHolder pianetaViewHolder = new PianetaViewHolder(convertView);

        pianetaViewHolder.distanzaValore = convertView.findViewById(R.id.distanza_valore);
        pianetaViewHolder.massaValore = convertView.findViewById(R.id.massa_valore);
        pianetaViewHolder.rotazioneValore = convertView.findViewById(R.id.rotazione_valore);
        pianetaViewHolder.rivoluzioneValore = convertView.findViewById(R.id.rivoluzione_valore);
        pianetaViewHolder.diametroValore = convertView.findViewById(R.id.diametro_valore);
        pianetaViewHolder.satellitiValore = convertView.findViewById(R.id.satelliti_valore);


        return pianetaViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PianetaViewHolder holder, int position) {
        Pianeta pianeta = pianeti.get(position);

        // Distanza
        holder.distanzaValore.setText(context.getString(R.string.valore_distanza,pianeta.getDistanzaUA()));

        // Massa
        holder.massaValore.setText(context.getString(R.string.valore_massa,pianeta.getMassa()));

        // Rotazione
        if(pianeta.getRotazioneOre() > 48){
            holder.rotazioneValore.setText(context.getString(R.string.valore_rotazione_giorni, pianeta.getRotazioneGiorni()));
        } else {
            holder.rotazioneValore.setText(context.getString(R.string.valore_rotazione_ore, pianeta.getRotazioneOre()));
        }

        // Rivoluzione
        if(pianeta.getRivoluzioneGiorni() > 700){
            holder.rivoluzioneValore.setText(context.getString(R.string.valore_rivoluzione_anni, pianeta.getRivoluzioneAnni()));
        } else{
            holder.rivoluzioneValore.setText(context.getString(R.string.valore_rivoluzione_giorni, pianeta.getRivoluzioneGiorni()));
        }

        // Diametro
        holder.diametroValore.setText(context.getString(R.string.valore_diametro,pianeta.getDiametro()));

        // Satelliti
        if(pianeta.getNumeroSatelliti() == 0){
            holder.satellitiValore.setText(R.string.valore_satellite_zero);
        } else {
            String etichetta = context.getResources().getQuantityString(R.plurals.valore_satellite, pianeta.getNumeroSatelliti(),pianeta.getNumeroSatelliti());
            holder.satellitiValore.setText(etichetta);
        }
    }

    @Override
    public int getItemCount() {
        if(pianeti == null)
            return 0;
        else
            return pianeti.size();
    }
}
