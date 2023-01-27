package com.example.mareu.model;

import java.util.List;

/**
 * L objet modele representant une reunion
 */
public class Reunion {


    /** Identifiant */
    private Integer id;

    /** Salle de reunion */
    private String reunionSalle;

    /** Jour de la reunion */
    private String reunionDate;

    /** L heure de debut de la reunion */
    private String reunionDebut;

    /** L heure de fin de la reunion */
    private String reunionFin;

    /** Sujet de la reunion */
    private String reunionSujet;

    /** Participant a la reunion */
    private List<String> reunionParticipants;

    /**
     * Constructeur
     * @param id
     * @param reunionSalle
     * @param reunionDate
     * @param reunionDebut
     * @param reunionFin
     * @param reunionSujet
     * @param reunionParticipants
     */

    public Reunion(Integer id, String reunionSalle, String reunionDate, String reunionDebut, String reunionFin, String reunionSujet, List<String> reunionParticipants) {
        this.id = id;
        this.reunionSalle = reunionSalle;
        this.reunionDate = reunionDate;
        this.reunionDebut = reunionDebut;
        this.reunionFin = reunionFin;
        this.reunionSujet = reunionSujet;
        this.reunionParticipants = reunionParticipants;
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

    public String getReunionDate() {
        return reunionDate;
    }

    public void setReunionDate(String reunionDate) {
        this.reunionDate = reunionDate;
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

}
