/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alasucu;

import java.text.ParseException;
import java.util.Collection;
import java.util.LinkedList;

/**
 *
 * @author NicoPlaceres
 */
public class TVertice implements IVertice {

    private Comparable etiqueta;
    private LinkedList<TAdyacencia> adyacentes;
    private boolean visitado;
    private Aeropuerto datos;

    @Override
    public Comparable getEtiqueta() {
        return etiqueta;
    }

    @Override
    public LinkedList<TAdyacencia> getAdyacentes() {
        return adyacentes;
    }

    public TVertice(String etiqueta, String nombre) {
        this.etiqueta = etiqueta;
        adyacentes = new LinkedList();
        visitado = false;
        this.datos = new Aeropuerto(nombre);
    }

    @Override
    public void setVisitado(boolean valor) {
        this.visitado = valor;
    }

    @Override
    public boolean getVisitado() {
        return this.visitado;
    }

    @Override
    public TAdyacencia buscarAdyacencia(TVertice verticeDestino) {
        if (verticeDestino != null) {
            return buscarAdyacencia(verticeDestino.getEtiqueta());
        }
        return null;
    }


    @Override
    public Double obtenerCostoAdyacencia(TVertice verticeDestino) {
        TAdyacencia ady = buscarAdyacencia(verticeDestino);
        if (ady != null) {
            return ady.getCosto();
        }
        return Double.MAX_VALUE;
    }

    public boolean insertarAdyacencia(Double costo, TVertice verticeDestino, String aeroliea) throws ParseException {
        if (buscarAdyacencia(verticeDestino,aeroliea) == null) {
            TAdyacencia ady = new TAdyacencia(costo, verticeDestino, aeroliea);
            return adyacentes.add(ady);
        }
        return false;
    }

    @Override
    public boolean eliminarAdyacencia(Comparable nomVerticeDestino) {
        TAdyacencia ady = buscarAdyacencia(nomVerticeDestino);
        if (ady != null) {
            adyacentes.remove(ady);
            return true;
        }
        return false;
    }

    @Override
    public TVertice primerAdyacente() {
        if (this.adyacentes.getFirst() != null) {
            return this.adyacentes.getFirst().getDestino();

        }
        return null;
    }
     
    public TAdyacencia buscarAdyacencia(TVertice verticeDestino,String aerolinea) {
        if (verticeDestino != null) {
            return buscarAdyacencia(verticeDestino.getEtiqueta(),aerolinea);
        }
        return null;
    }

    @Override
    public TAdyacencia buscarAdyacencia(Comparable etiquetaDestino) {
        for (TAdyacencia adyacencia : adyacentes) {
            if (adyacencia.getDestino().getEtiqueta().compareTo(etiquetaDestino) == 0) {//aerolinea
                return adyacencia;
            }
        }
        return null;
    }
    public TAdyacencia buscarAdyacencia(Comparable etiquetaDestino,String aerolinea) {
        for (TAdyacencia adyacencia : adyacentes) {
            if (adyacencia.getDestino().getEtiqueta().compareTo(etiquetaDestino) == 0 && 
                    adyacencia.getAerolinea().compareTo(aerolinea)==0) {//aerolinea
                return adyacencia;
            }
        }
        return null;
    }

    @Override
    public Object getDatos() {
        return datos;
    }

    @Override
    public void bpf(Collection<Comparable> visitados) {
        setVisitado(true);
        visitados.add(this.getEtiqueta());
        for (TAdyacencia adyacente : adyacentes) {
            TVertice vertAdy = adyacente.getDestino();
            if (!vertAdy.getVisitado()) {
                vertAdy.bpf(visitados);
            }
        }
    }

    public TCaminos todosLosCaminos(Comparable etVertDest, TCamino caminoPrevio, TCaminos todosLosCaminos, String aerolinea) {
        this.setVisitado(true);
        for (TAdyacencia adyacencia : this.getAdyacentes()) {
            TVertice destino = adyacencia.getDestino();
            if (!destino.getVisitado()) {
               if (adyacencia.getAerolinea().equals(aerolinea) ) {//cambio 
                    if (destino.getEtiqueta().compareTo(etVertDest) == 0) {   
                        TCamino copia = caminoPrevio.copiar();
                        copia.agregarAdyacencia(adyacencia);
                        todosLosCaminos.getCaminos().add(copia);
                    } else {
                        caminoPrevio.agregarAdyacencia(adyacencia);
                        destino.todosLosCaminos(etVertDest, caminoPrevio, todosLosCaminos);
                        caminoPrevio.eliminarAdyacencia(adyacencia);
                    }
                    
                }
              
         
            }
        }
        this.setVisitado(false);
        return todosLosCaminos;
    }
    
public TCaminos todosLosCaminos(Comparable etVertDest, TCamino caminoPrevio, TCaminos todosLosCaminos, String aerolinea,int cantidadMaximaEscalas) {
        this.setVisitado(true);
        for (TAdyacencia adyacente : adyacentes) {
            TVertice destino = adyacente.getDestino();
            if (!destino.getVisitado()) {
                if (adyacente.getAerolinea().equals(aerolinea) ) {
                if (destino.getEtiqueta().compareTo(etVertDest) == 0) {
                    TCamino copia = caminoPrevio.copiar();
                    copia.agregarAdyacencia(adyacente);
                    todosLosCaminos.getCaminos().add(copia);
                } else {
                    if (cantidadMaximaEscalas >= 0) {
                        caminoPrevio.agregarAdyacencia(adyacente);
                        destino.todosLosCaminos(etVertDest, caminoPrevio, todosLosCaminos, aerolinea,cantidadMaximaEscalas - 1);
                        caminoPrevio.eliminarAdyacencia(adyacente);
                    } else {
                        break;
                    }
                }
            }
            }
        }
        this.setVisitado(false);
        return todosLosCaminos;
    }                       
       @Override
    public TCaminos todosLosCaminos(Comparable etVertDest, TCamino caminoPrevio, TCaminos todosLosCaminos) {
        this.setVisitado(true);
        for (TAdyacencia adyacencia : this.getAdyacentes()) {
            TVertice destino = adyacencia.getDestino();
            if (!destino.getVisitado()) {
                if (destino.getEtiqueta().compareTo(etVertDest) == 0) {
                    TCamino copia = caminoPrevio.copiar();
                    copia.agregarAdyacencia(adyacencia);
                    todosLosCaminos.getCaminos().add(copia);
                } else {
                    TCamino copia = caminoPrevio.copiar();
                    copia.agregarAdyacencia(adyacencia);
                    adyacencia.getDestino().todosLosCaminos(etVertDest, copia, todosLosCaminos);
                }
            }
        }
        this.setVisitado(false);
        return todosLosCaminos;
    }

    public boolean tieneCiclo(TCamino camino) {
        setVisitado(true);

        boolean ciclo = false;
        for (TAdyacencia adyacencia : this.getAdyacentes()) {
            if (ciclo) {
                break;
            }
            TVertice w = adyacencia.getDestino();
            if (!w.getVisitado()) {
                camino.agregarAdyacencia(adyacencia);
                ciclo = w.tieneCiclo(camino);
            } else {
                ciclo = true;
                System.out.println("hay ciclo : " + camino.imprimirDesdeClave(w.etiqueta));
            }
        }
        camino.getOtrosVertices().remove(this.getEtiqueta());
        return ciclo;

    }

 

    @Override
    public boolean insertarAdyacencia(Double costo, TVertice verticeDestino) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}