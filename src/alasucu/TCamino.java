/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alasucu;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author NicoPlaceres
 */
public class TCamino {
    

    private final TVertice origen;
    private Collection<Comparable> otrosVertices;
    private Double costoTotal = 0.0d;
    private String aerolinea;


    public void imprimirEtiquetasConsola() {
        System.out.println(imprimirEtiquetas());
    }

    public String imprimirEtiquetas() {
        StringBuilder sb = new StringBuilder();
        Aeropuerto a = (Aeropuerto) this.origen.getDatos();
        sb.append("Aerolinea : "+this.aerolinea+" ----> "+"Origen: " + a.getNombre()+" ("+ getOrigen().getEtiqueta()+") ");
        for (Comparable adyacente : getOtrosVertices()) {
            sb.append(" -> " + adyacente);
            
            sb.append(adyacente);
        }
        return sb.toString();
    }

    public TCamino(TVertice v,String aero) {
        this.origen = v;
        this.otrosVertices = new LinkedList();
        this.aerolinea=aero;

        
    }
 
        
    
    
    public boolean agregarAdyacencia (TAdyacencia adyacenciaActual){
        if (adyacenciaActual.getDestino() != null ){
            costoTotal = costoTotal + ((Number)adyacenciaActual.getCosto()).doubleValue();
            return otrosVertices.add(adyacenciaActual.getDestino().getEtiqueta());
        }
        return false;
  
    }
    public boolean eliminarAdyacencia(TAdyacencia adyacenciaActual) {
        if (getOtrosVertices().contains(adyacenciaActual.getDestino().getEtiqueta())) {
            setCostoTotal((Double) getCostoTotal() - ((Number) adyacenciaActual.getCosto()).doubleValue());
            return getOtrosVertices().remove(adyacenciaActual.getDestino().getEtiqueta());
        }
        return false;
    }

    public TVertice getOrigen() {
        return origen;
    }
   public String getAerolinea(){
       return this.aerolinea;
   }

    public Collection<Comparable> getOtrosVertices() {
        return otrosVertices;
    }

    public void setOtrosVertices(Collection<Comparable> otrosVertices) {
        this.otrosVertices = otrosVertices;
    }

    public Double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(Double costoTotal) {
        this.costoTotal = costoTotal;
    }

    public TCamino copiar() {
        Aeropuerto aero = (Aeropuerto) this.getOrigen().getDatos();
        TVertice origen = new TVertice((String) this.getOrigen().getEtiqueta(),aero.getNombre());
        String a = this.aerolinea;
        
        TCamino copia = new TCamino(origen,a);
        copia.setCostoTotal(this.getCostoTotal());
        origen.getAdyacentes().addAll(this.getOrigen().getAdyacentes());
        copia.getOtrosVertices().addAll(this.getOtrosVertices());

        return copia;
    }


    public String imprimirDesdeClave(Comparable clave) {
        StringBuilder sb = new StringBuilder();
        boolean start = false;
        Collection<Comparable> listaDefinitiva = new LinkedList<Comparable>();
        listaDefinitiva.add(this.getOrigen().getEtiqueta());
        listaDefinitiva.addAll(this.getOtrosVertices());

        for (Iterator<Comparable> iterator = listaDefinitiva.iterator(); iterator.hasNext();) {
            Comparable next = iterator.next();
            if (next.compareTo(clave) == 0) {
                start = true;
            }
            if (start) {
                sb.append(" " + next + " ");
            }
        }
        return sb.toString();
    }



}

    

