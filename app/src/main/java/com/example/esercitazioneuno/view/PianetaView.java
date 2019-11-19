package com.example.esercitazioneuno.view;

import com.example.esercitazioneuno.common.model.Pianeta;

public class PianetaView extends Pianeta {

    // Id dell'icona del pianeta
    private int idIconaPianeta;

    // Immagine di sfondo del pianeta
    private int idRisorsaImmagine;

    // Eticheta del pianeta
    private int idRisorsaNomePianeta;

    private int posizione;

    public PianetaView(Pianeta pianeta) {
        super(pianeta.getNome(),pianeta.getDistanzaUA(),pianeta.getRivoluzioneGiorni(),pianeta.getRotazioneOre(),pianeta.getMassa(),pianeta.getDiametro(),pianeta.getNumeroSatelliti());
    }

    public int getIdIconaPianeta() {
        return idIconaPianeta;
    }

    public void setIdIconaPianeta(int idIconaPianeta) {
        this.idIconaPianeta = idIconaPianeta;
    }

    public int getIdRisorsaImmagine() {
        return idRisorsaImmagine;
    }

    public void setIdRisorsaImmagine(int idRisorsaImmagine) {
        this.idRisorsaImmagine = idRisorsaImmagine;
    }

    public int getIdRisorsaNomePianeta() {
        return idRisorsaNomePianeta;
    }

    public void setIdRisorsaNomePianeta(int idRisorsaNomePianeta) {
        this.idRisorsaNomePianeta = idRisorsaNomePianeta;
    }

    public int getPosizione() {
        return posizione;
    }

    public void setPosizione(int posizione) {
        this.posizione = posizione;
    }
}
