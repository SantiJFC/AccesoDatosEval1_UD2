/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.teis.edu;

import es.teis.model.Partido;
import es.teis.model.dao.partido.IPartidoDao;
import es.teis.model.dao.partido.PartidoSQLServerDao;
import es.teis.model.servicio.IPartidoServicio;
import es.teis.model.servicio.PartidoServicio;
import java.util.ArrayList;

/**
 *
 * @author maria
 */
public class MainUD2 {
    
    public static void main(String[] args) {
        
    	
        ArrayList<Partido> partidos = crearPartidos();
        IPartidoDao partidoDao = new PartidoSQLServerDao();
        IPartidoServicio partidoServicio = new PartidoServicio(partidoDao);
        
        partidoServicio.crearPartidos(partidos);       
        
        partidoServicio.transferirVotos("Cs", "PP", 100); 
    }

    private static ArrayList<Partido> crearPartidos() {
        ArrayList<Partido> partidos = new ArrayList<Partido>();

        Partido p1 = new Partido("PP", 44064, 32.51f);
        Partido p2 = new Partido( "PSdeG", 43332, 31.97f);
        Partido p3 = new Partido( "BNG", 31628, 23.34f);
        Partido p4 = new Partido("PODEMOS-EU-ANOVA", 8245, 6.08f);
        Partido p5 = new Partido( "VOX", 2913, 2.15f);
        Partido p6 = new Partido( "Cs", 1671, 1.23f);

        partidos.add(p1);
        partidos.add(p2);
        partidos.add(p3);
        partidos.add(p4);
        partidos.add(p5);
        partidos.add(p6);
        return partidos;

    }

}
