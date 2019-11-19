package com.example.esercitazioneuno;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.Group;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.esercitazioneuno.common.util.LogUtil;
import com.example.esercitazioneuno.pianetaRecycler.PianetaCambiatoListener;
import com.example.esercitazioneuno.pianetaRecycler.PianetiAdapter;
import com.example.esercitazioneuno.pianetaRecycler.PosizionePianetaCambiata;
import com.example.esercitazioneuno.service.PianetaViewService;
import com.example.esercitazioneuno.view.PianetaView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements PosizionePianetaCambiata {

    // Elenco di tutti i pianeti
    List<PianetaView> pianeti;

    // Il titolo dell'activity
    TextView titolo;
    // Immagine grande in alto
    ImageView immagineGrande;

    // Gruppo che raccoglie le informazioni sul gruppo
    Group gruppoIntroduzione;

    // Recycler view con le informazioni del pianeta
    RecyclerView recyclerInfoPianeta;

    // Indica se si sta visualizzando l'intro o un pianeta
    private boolean visualizzazioneIntro;

    // Icon usata per la selezione
    ImageView iconaSelezione;

    // Il layout esterno necessario per muovere l'icona di selezione
    ConstraintLayout layoutEsterno;

    int posizionePianetaVisualizzato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Carica il layout
        setContentView(R.layout.activity_main);

        // Inizializza la lista dei pianeti
        pianeti = PianetaViewService.findAll();

        // Il layou esterno usato per muovere l'icona selezione
        layoutEsterno = (ConstraintLayout) findViewById(R.id.layoutEsterno);

        // Icona selezione
        iconaSelezione = findViewById(R.id.iconaSelezione);

        // il titolo
        titolo = findViewById(R.id.titolo);
        // l'immagine grande
        immagineGrande = findViewById(R.id.immagine_grande);

        // Il gruppo introduzione
        gruppoIntroduzione = findViewById(R.id.introduzione);

        // Il recycler pianeta
        recyclerInfoPianeta = (RecyclerView) findViewById(R.id.informazioni_pianeta);

        // Creiamo un manager che sia un layout orizzontale
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        RecyclerView.LayoutManager layoutManager = manager;
        recyclerInfoPianeta.setLayoutManager(layoutManager);

        // Creiamo un adapter che gestisca i pianeti
        RecyclerView.Adapter adapter = new PianetiAdapter(pianeti, this);
        recyclerInfoPianeta.setAdapter(adapter);

        // Classe usata per creare un effetto Snap nel momento in cui srolliamo
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerInfoPianeta);

        // Aggiungo il mio listener
        PianetaCambiatoListener pianetaCambiatoListener = new PianetaCambiatoListener(snapHelper,this, layoutManager);
        recyclerInfoPianeta.addOnScrollListener(pianetaCambiatoListener);

        visualizzazioneIntro = true;
    }


    /**
     * Carica i valori del pianeta
     *
     * @param pianeta
     */
    public void mostraContenutoPianeta(PianetaView pianeta){
        // Controlla se è presente l'gruppoIntroduzione
        if(visualizzazioneIntro){
            mostraLayoutPianeta();
            visualizzazioneIntro = false;
        }
        // Nome del pianeta
        titolo.setText( pianeta.getIdRisorsaNomePianeta());
        immagineGrande.setImageResource(pianeta.getIdRisorsaImmagine());
    }


    /**
     * Evento click su uno dei pianeti
     *
     * @param view
     */
    public void clickSuPianeta(View view){
        LogUtil.debug(view.getId() + "Click sul pianeta " + getResources().getResourceEntryName(view.getId()) );

        // Recupera il pianeta
        PianetaView pianeta = pianetaDaId(view.getId());

        // Mostra il contenuto del pianeta
        mostraContenutoPianeta(pianeta);

        // Recupera la posizione per questo pianeta
        posizionePianetaVisualizzato = pianeta.getPosizione();

        // Sposta il recycler view sulla giusta posizione
        recyclerInfoPianeta.smoothScrollToPosition(posizionePianetaVisualizzato);

        // Sposta l'icona della selezione
        spostaIconaSelezione(view.getId());

    }

    /**
     * Sposta l'icona selezione sull'icona indicata
     *
     * @param idPianeta
     */
    public void spostaIconaSelezione(int idPianeta){
        // Nuova posizione
         ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) iconaSelezione.getLayoutParams();

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(layoutEsterno);
        constraintSet.connect(iconaSelezione.getId(), ConstraintSet.TOP, idPianeta,ConstraintSet.TOP);
        constraintSet.connect(iconaSelezione.getId(), ConstraintSet.BOTTOM, idPianeta,ConstraintSet.BOTTOM);
        constraintSet.connect(iconaSelezione.getId(), ConstraintSet.START, idPianeta,ConstraintSet.START);
        constraintSet.connect(iconaSelezione.getId(), ConstraintSet.END, idPianeta,ConstraintSet.END);

        constraintSet.applyTo(layoutEsterno);
        if(iconaSelezione.getVisibility() != View.VISIBLE)
            iconaSelezione.setVisibility(View.VISIBLE);
    }


    /**
     * Ritorna l'oggetto Pianeta a partire dall'id della sua icona
     *
     * @param idIconaPianeta
     * @return
     */
    public PianetaView pianetaDaId(int idIconaPianeta){
        PianetaView result = null;
        for(PianetaView pianeta:pianeti){
            if(pianeta.getIdIconaPianeta() == idIconaPianeta){
                result = pianeta;
                // Interrompi il ciclo
                break;
            }
        }
        return result;
    }


    /**
     * Metodo che implementa l'interfaccia PosizionePianetaCambiata
     *
     * @param posizione
     */
    @Override
    public void posizioneCambiata(int posizione) {
        LogUtil.debug("Posizione cambiata !!! " + posizione);

        if(posizionePianetaVisualizzato != posizione){
            // Recupera il pianeta dalla posizione
            PianetaView pianeta = pianeti.get(posizione);

            // Mostra il conteuto del singolo pianetas
            mostraContenutoPianeta(pianeta);

            // Sposta l'icona che indica la selezione
            spostaIconaSelezione(pianeta.getIdIconaPianeta());

            posizionePianetaVisualizzato = posizione;
        }

    }

    /**
     * Mostra il layout con il dettaglio del pianeta
     *
     */
    public void mostraLayoutPianeta(){
        gruppoIntroduzione.setVisibility(View.GONE);
        recyclerInfoPianeta.setVisibility(View.VISIBLE);
        visualizzazioneIntro = false;
    }



}
