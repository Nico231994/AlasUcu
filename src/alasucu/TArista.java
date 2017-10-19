/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alasucu;

/**
 *
 * @author NicoPlaceres
 */
public class TArista implements IArista {

    protected Comparable etiquetaOrigen;
    protected Comparable etiquetaDestino;
    protected double costo;
    private String aerolinea;

    public TArista(Comparable etiquetaOrigen, Comparable etiquetaDestino, double costo, String aerolinea){
        this.etiquetaOrigen = etiquetaOrigen;
        this.etiquetaDestino = etiquetaDestino;
        this.costo = costo;
        this.aerolinea = aerolinea;
        
    }

    public String getAerolinea() {
        return aerolinea;
    }
    
    @Override
    public Comparable getEtiquetaOrigen() {
        return etiquetaOrigen;
    }

    @Override
    public void setEtiquetaOrigen(Comparable etiquetaOrigen) {
        this.etiquetaOrigen = etiquetaOrigen;
    }

    @Override
    public Comparable getEtiquetaDestino() {
        return etiquetaDestino;
    }

    @Override
    public void setEtiquetaDestino(Comparable etiquetaDestino) {
        this.etiquetaDestino = etiquetaDestino;
    }

    @Override
    public double getCosto() {
        return costo;
    }

    @Override
    public void setCosto(double costo) {
        this.costo = costo;
    }
    public TArista aristaInversa (){
        return new TArista(this.getEtiquetaDestino(), this.getEtiquetaOrigen(),this.getCosto(),this.aerolinea);
    }

    
}

