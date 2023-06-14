/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package es.teis.model.servicio;

import es.teis.model.Partido;
import java.util.ArrayList;

/**
 *
 * @author maria
 */
public interface IPartidoServicio {
    void crearPartidos(ArrayList<Partido> partidos);
     boolean transferirVotos(String nombreOrigen, String nombreDestino, int cantidadVotos);
}
