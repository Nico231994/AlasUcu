/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alasucu;

import java.text.ParseException;

/**
 *
 * @author NicoPlaceres
 */



public class TAdyacencia implements IAdyacencia {
   

    private Comparable etiqueta;
    private double costo;
    private TVertice destino;
    private String aerolinea;

    @Override
    public String getAerolinea() {
        return aerolinea;
    }

    public void setAerolinea(String aerolinea) {
        this.aerolinea = aerolinea;
    }

   
    
    @Override
    public Comparable getEtiqueta() {
        return etiqueta;
    }
 
    @Override
    public double getCosto() {
        return costo;
    }
    

    @Override
    public TVertice getDestino() {
        return destino;
    }

    public TAdyacencia(double costo, TVertice destino, String aerolinea) throws ParseException {
        this.etiqueta = destino.getEtiqueta();
        this.costo = costo;
        this.destino = destino;
        this.aerolinea = aerolinea;
        
    }
}

    

