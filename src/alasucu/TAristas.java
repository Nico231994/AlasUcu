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
public class TAristas extends LinkedList<TArista> {

    private final static String SEPARADOR_ELEMENTOS_IMPRESOS = "-";
    //private Collection<TArista> aristas  = new LinkedList<TArista>();

    /**
     * Busca dentro de la lista de aristas una arista que conecte a etOrigen con
     * etDestino
     *
     * @param etOrigen
     * @param etDestino
     * @return
     */
    public TArista buscar(Comparable etOrigen, Comparable etDestino) {
        TArista tempArista;
        for (TArista laa : this) {
            if ((laa.getEtiquetaOrigen().equals(etOrigen)) && laa.getEtiquetaDestino().equals(etDestino)) {
                return laa;
            }
        }

        return null;
    }

    /**
     * Busca la arista de menor costo que conecte a cualquier nodo de VerticesU
     * con cualquier otro de VerticesV y cuyo costo sea el minimo
     *
     * @param VerticesU - Lista de vertices U
     * @param VerticesV - Lista de vertices V
     * @return
     */
    public TArista buscarMin(Collection<Comparable> VerticesU, Collection<Comparable> VerticesV) {
        TArista tempArista = null;
        TArista tAMin = null;
        Double costoMin = Double.POSITIVE_INFINITY;
        for(Comparable ver1 : VerticesU){
            for(Comparable ver2 : VerticesV){
                 tempArista = buscar(ver1,ver2);
                if(tempArista!=null && tempArista.getCosto()<costoMin){
                    costoMin = tempArista.getCosto();
                    tAMin = tempArista;
                }
            }
        }
       
        return tAMin;
    }

    public String imprimirEtiquetas() {
        if (this.isEmpty()) {
            return null;
        }
        StringBuilder salida = new StringBuilder();
        //TODO: Completar codigo que imprime todas las aristas de la lista en el siguiente formato:
        //ORIGEN - DESTINO - COSTO
        for (TArista arista : this) {
            salida.append(arista.getEtiquetaOrigen() + ":" + arista.getEtiquetaDestino() + ":" + arista.getCosto() + SEPARADOR_ELEMENTOS_IMPRESOS);
        }
//            salida += arista.getEtiquetaOrigen() + ":" + arista.getEtiquetaDestino() + ":" + arista.getCosto() + SEPARADOR_ELEMENTOS_IMPRESOS;

        return salida.toString();
    }

    void insertarAmbosSentidos(Collection<TArista> aristas) throws ParseException {
        TArista tempArista;
        for (TArista ta : aristas) {
            this.add(ta);
            this.add(ta.aristaInversa());
        }
    }

    TArista buscarMin(LinkedList<TVertice> VerticesU, LinkedList<TVertice> VerticesV) {
        TArista tempArista = null;
        TArista tAMin = null;
        Double costoMin = Double.POSITIVE_INFINITY;
        for(TVertice ver1:VerticesU){
            for(TVertice ver2:VerticesV){
                 tempArista = buscar(ver1.getEtiqueta(),ver2.getEtiqueta());
                if(tempArista!=null && tempArista.getCosto()<costoMin){
                    costoMin = tempArista.getCosto();
                    tAMin = tempArista;
                }
            }
        }   
        return tAMin;
    }
}

