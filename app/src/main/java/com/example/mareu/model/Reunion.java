package com.example.mareu.model;

import java.util.Date;
import java.util.List;

/**
 * L objet modele representant une reunion
 */
public class Reunion {


    /** Identifiant */
    private Integer id;

    /** Salle de reunion */
    private String reunionSalle;

    /** L heure de debut de la reunion */
    private String reunionDebut;

    /** L heure de fin de la reunion */
    private String reunionFin;

    /** Sujet de la reunion */
    private String reunionSujet;

    /** Participant a la reunion */
    private List<String> reunionParticipants;

    /** Jour de la reunion */
    private Date mReunionDate;



    public Reunion(String reunionSalle, String reunionDebut, String reunionFin, String reunionSujet, List<String> reunionParticipants, Date date) {
        this.reunionSalle = reunionSalle;
        this.reunionDebut = reunionDebut;
        this.reunionFin = reunionFin;
        this.reunionSujet = reunionSujet;
        this.reunionParticipants = reunionParticipants;
        this.mReunionDate = date;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReunionSalle() {
        return reunionSalle;
    }

    public void setReunionSalle(String reunionSalle) {
        this.reunionSalle = reunionSalle;
    }

    public Date getReunionDate() {
        return mReunionDate;
    }

    public void setReunionDate(Date reunionDate) {
        mReunionDate = reunionDate;
    }

    public String getReunionDebut() {
        return reunionDebut;
    }

    public void setReunionDebut(String reunionDebut) {
        this.reunionDebut = reunionDebut;
    }

    public String getReunionFin() {
        return reunionFin;
    }

    public void setId(String reunionFin) {
        this.reunionFin = reunionFin;
    }

    public String getReunionSujet() {
        return reunionSujet;
    }

    public void setReunionSujet(String reunionSujet) {
        this.reunionSujet = reunionSujet;
    }

    public List<String> getReunionParticipants() {
        return reunionParticipants;
    }

    public void setReunionParticipants(List<String> reunionParticipants) {
        this.reunionParticipants = reunionParticipants;
    }

    /**
     * Constructeur
     * @param id
     * @param reunionSalle
     * @param reunionDebut
     * @param reunionFin
     * @param reunionSujet
     * @param reunionParticipants
     * @param date
     */

    public Reunion(Integer id, String reunionSalle, String reunionDebut, String reunionFin, String reunionSujet, List<String> reunionParticipants, Date date) {
        this.id = id;
        this.reunionSalle = reunionSalle;
        this.reunionDebut = reunionDebut;
        this.reunionFin = reunionFin;
        this.reunionSujet = reunionSujet;
        this.reunionParticipants = reunionParticipants;
        this.mReunionDate = date;
    }

}
