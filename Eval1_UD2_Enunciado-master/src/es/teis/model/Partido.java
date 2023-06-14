/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.teis.model;

import java.io.Serializable;

/**
 *
 * @author maria
 */
public class Partido implements Serializable {
    
    private long id;
    private String nombre;
    private int votos;
    private float porcentaje;

    public Partido() {
    }
    
    

    public Partido(long id, String nombre, int votos, float porcentaje) {
        this.id = id;
        this.nombre = nombre;
        this.votos = votos;
        this.porcentaje = porcentaje;
    }
      public Partido( String nombre, int votos, float porcentaje) {
      
        this.nombre = nombre;
        this.votos = votos;
        this.porcentaje = porcentaje;
    }

    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVotos() {
        return votos;
    }

    public void setVotos(int votos) {
        this.votos = votos;
    }

    public float getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(float porcentaje) {
        this.porcentaje = porcentaje;
    }

    @Override
    public String toString() {
        return "Partido{" + "id=" + id + ", nombre=" + nombre + ", votos=" + votos + ", porcentaje=" + porcentaje + '}';
    }
    
    
    
}
