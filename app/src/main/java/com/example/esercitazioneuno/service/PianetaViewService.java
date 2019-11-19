package com.example.esercitazioneuno.service;

import com.example.esercitazioneuno.R;
import com.example.esercitazioneuno.common.model.Pianeta;
import com.example.esercitazioneuno.view.PianetaView;

import java.util.ArrayList;
import java.util.List;

public class PianetaViewService {


    public static List<PianetaView> findAll(){
        List<PianetaView> result = new ArrayList<PianetaView>();
        List<Pianeta> pianeti = PianetaService.findAll();
        int position = 0;
        for(Pianeta pianeta:pianeti){
            PianetaView pianetaView = new PianetaView(pianeta);
            pianetaView.setIdIconaPianeta(idPianeta(pianeta.getNome()));
            pianetaView.setIdRisorsaImmagine(risorsaImmagine(pianeta.getNome()));
            pianetaView.setIdRisorsaNomePianeta(risorsaNomePianeta(pianeta.getNome()));
            pianetaView.setPosizione(position);
            position++;
            result.add(pianetaView);
        }
        return result;
    }


    public static int idPianeta(String nomePianeta){
        int idPianeta = -1;
        if(nomePianeta.equals("mercurio"))
            idPianeta = R.id.ic_mercurio;
        else if(nomePianeta.equals("venere"))
            idPianeta = R.id.ic_venere;
        else if(nomePianeta.equals("terra"))
            idPianeta = R.id.ic_terra;
        else if(nomePianeta.equals("marte"))
            idPianeta = R.id.ic_marte;
        else if(nomePianeta.equals("giove"))
            idPianeta = R.id.ic_giove;
        else if(nomePianeta.equals("saturno"))
            idPianeta = R.id.ic_saturno;
        else if(nomePianeta.equals("urano"))
            idPianeta = R.id.ic_urano;
        else if(nomePianeta.equals("nettuno"))
            idPianeta = R.id.ic_nettuno;
        return idPianeta;
    }

    public static int risorsaNomePianeta(String nomePianeta){
        int idPianeta = -1;
        if(nomePianeta.equals("mercurio"))
            idPianeta = R.string.mercurio;
        else if(nomePianeta.equals("venere"))
            idPianeta = R.string.venere;
        else if(nomePianeta.equals("terra"))
            idPianeta = R.string.terra;
        else if(nomePianeta.equals("marte"))
            idPianeta = R.string.marte;
        else if(nomePianeta.equals("giove"))
            idPianeta = R.string.giove;
        else if(nomePianeta.equals("saturno"))
            idPianeta = R.string.saturno;
        else if(nomePianeta.equals("urano"))
            idPianeta = R.string.urano;
        else if(nomePianeta.equals("nettuno"))
            idPianeta = R.string.nettuno;
        return idPianeta;
    }

    public static int risorsaImmagine(String nomePianeta){
        int idPianeta = -1;
        if(nomePianeta.equals("mercurio"))
            idPianeta = R.drawable.mercurio;
        else if(nomePianeta.equals("venere"))
            idPianeta = R.drawable.venere;
        else if(nomePianeta.equals("terra"))
            idPianeta = R.drawable.terra;
        else if(nomePianeta.equals("marte"))
            idPianeta = R.drawable.marte;
        else if(nomePianeta.equals("giove"))
            idPianeta = R.drawable.giove;
        else if(nomePianeta.equals("saturno"))
            idPianeta = R.drawable.saturno;
        else if(nomePianeta.equals("urano"))
            idPianeta = R.drawable.urano;
        else if(nomePianeta.equals("nettuno"))
            idPianeta = R.drawable.nettuno;
        return idPianeta;
    }
}
