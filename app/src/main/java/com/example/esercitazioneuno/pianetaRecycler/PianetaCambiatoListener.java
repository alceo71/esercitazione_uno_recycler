package com.example.esercitazioneuno.pianetaRecycler;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

public class PianetaCambiatoListener extends RecyclerView.OnScrollListener {

    // Lo snapHelper da monitorare
    public SnapHelper snapHelper;

    // Il listener da avvisare
    public PosizionePianetaCambiata posizionePianetaCambiata;

    public RecyclerView.LayoutManager layoutManager;

    public PianetaCambiatoListener(SnapHelper snapHelper, PosizionePianetaCambiata posizionePianetaCambiata, RecyclerView.LayoutManager layoutManager){
        this.snapHelper = snapHelper;
        this.posizionePianetaCambiata = posizionePianetaCambiata;
        this.layoutManager = layoutManager;
    }

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);

        if(newState == RecyclerView.SCROLL_STATE_IDLE){
            possibileNotifica(recyclerView);
        }
    }

    public void possibileNotifica(RecyclerView recyclerView){
        posizionePianetaCambiata.posizioneCambiata(posizionePianetaVisualizzato());
    }

    public int posizionePianetaVisualizzato(){
        View snapView = snapHelper.findSnapView(layoutManager);
        return layoutManager.getPosition(snapView);
    }

}
