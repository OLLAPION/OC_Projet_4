package com.example.mareu.events;

import com.example.mareu.model.Reunion;

public class DeleteReunionEvent {

    /**
     * Reunion à supprimer
     */
    public Reunion reunion;

    /**
     * Constructeur.
     * @param reunion
     */
    public DeleteReunionEvent(Reunion reunion) {
        this.reunion = reunion;
    }

}
