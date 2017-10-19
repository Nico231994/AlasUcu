/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alasucu;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NicoPlaceres
 */
public class TGrafoDirigido implements IGrafoDirigido {

    final Map<Comparable, TVertice> vertices; //lista de vertices del grafo.-

    public TGrafoDirigido(Collection<TVertice> vertices, Collection<TArista> aristas) {
        this.vertices = new HashMap<>();
        for (TVertice vertice : vertices) {
            Aeropuerto a = (Aeropuerto) vertice.getDatos();
            insertarVertice(vertice.getEtiqueta(),a.getNombre());
        }
        for (TArista arista : aristas) {
            insertarArista(arista);
        }
    }

    /**
     * Metodo encargado de eliminar una arista dada por un origen y destino. En
     * caso de no existir la adyacencia, retorna falso. En caso de que las
     * etiquetas sean invalidas, retorna falso.
     *
     * @param nomVerticeOrigen
     * @param nomVerticeDestino
     * @return
     */
    @Override
    public boolean eliminarArista(Comparable nomVerticeOrigen, Comparable nomVerticeDestino) {
        if ((nomVerticeOrigen != null) && (nomVerticeDestino != null)) {
            TVertice vertOrigen = buscarVertice(nomVerticeOrigen);
            if (vertOrigen != null) {
                return vertOrigen.eliminarAdyacencia(nomVerticeDestino);
            }
        }
        return false;
    }

    /**
     * Metodo encargado de eliminar un vertice en el grafo. En caso de no
     * existir el v�rtice, retorna falso. En caso de que la etiqueta sea
     * inv�lida, retorna false.
     *
     * @param nombreVertice
     * @return
     */
    @Override
    public boolean eliminarVertice(Comparable nombreVertice) {
        if (nombreVertice != null) {
            getVertices().remove(nombreVertice);
            return getVertices().containsKey(nombreVertice);
        }
        return false;
    }

    /**
     * Metodo encargado de verificar la existencia de una arista. Las etiquetas
     * pasadas por par�metro deben ser v�lidas.
     *
     * @param etiquetaOrigen
     * @param etiquetaDestino
     * @return True si existe la adyacencia, false en caso contrario
     */
    @Override
    public boolean existeArista(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        TVertice vertOrigen = buscarVertice(etiquetaOrigen);
        TVertice vertDestino = buscarVertice(etiquetaDestino);
        if ((vertOrigen != null) && (vertDestino != null)) {
            return vertOrigen.buscarAdyacencia(vertDestino) != null;
        }
        return false;
    }

    /**
     * Metodo encargado de verificar la existencia de un vertice dentro del
     * grafo.-
     *
     * La etiqueta especificada como par�metro debe ser v�lida.
     *
     * @param unaEtiqueta Etiqueta del v�rtice a buscar.-
     * @return True si existe el vertice con la etiqueta indicada, false en caso
     * contrario
     */
    @Override
    public boolean existeVertice(Comparable unaEtiqueta) {
        return getVertices().get(unaEtiqueta) != null;
    }

    /**
     * Metodo encargado de verificar buscar un vertice dentro del grafo.-
     *
     * La etiqueta especificada como parametro debe ser valida.
     *
     * @param unaEtiqueta Etiqueta del v�rtice a buscar.-
     * @return El vertice encontrado. En caso de no existir, retorna nulo.
     */
    private TVertice buscarVertice(Comparable unaEtiqueta) {
        return getVertices().get(unaEtiqueta);
    }

    /**
     * Matodo encargado de insertar una arista en el grafo (con un cierto
     * costo), dado su vertice origen y destino.- Para que la arista sea valida,
     * se deben cumplir los siguientes casos: 1) Las etiquetas pasadas por
     * parametros son v�lidas.- 2) Los vertices (origen y destino) existen
     * dentro del grafo.- 3) No es posible ingresar una arista ya existente
     * (miso origen y mismo destino, aunque el costo sea diferente).- 4) El
     * costo debe ser mayor que 0.
     *
     * @param arista
     * @return True si se pudo insertar la adyacencia, false en caso contrario
     */
    @Override
    public boolean insertarArista(TArista arista) {
        boolean tempbool = false;
        if ((arista.getEtiquetaOrigen() != null) && (arista.getEtiquetaDestino() != null)) {
            TVertice vertOrigen = buscarVertice(arista.getEtiquetaOrigen());
            TVertice vertDestino = buscarVertice(arista.getEtiquetaDestino());
            tempbool = (vertOrigen != null) && (vertDestino != null);
            if (tempbool) {
                try {
                    //getLasAristas().add(arista);
                    return vertOrigen.insertarAdyacencia(arista.getCosto(), vertDestino, arista.getAerolinea());
                } catch (ParseException ex) {
                    Logger.getLogger(TGrafoDirigido.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        return false;
    }

    /**
     * Metodo encargado de insertar un vertice en el grafo.
     *
     * No pueden ingresarse v�rtices con la misma etiqueta. La etiqueta
     * especificada como par�metro debe ser v�lida.
     *
     * @param unaEtiqueta Etiqueta del v�rtice a ingresar.
     * @return True si se pudo insertar el vertice, false en caso contrario
     */
    public boolean insertarVertice(Comparable unaEtiqueta,String nombre) {
        if ((unaEtiqueta != null) && (!existeVertice(unaEtiqueta))) {
            TVertice vert = new TVertice((String) unaEtiqueta,nombre);
            getVertices().put(unaEtiqueta, vert);
            return getVertices().containsKey(unaEtiqueta);
        }
        return false;
    }

    @Override
    public boolean insertarVertice(TVertice vertice) {
        Comparable unaEtiqueta = vertice.getEtiqueta();
        if ((unaEtiqueta != null) && (!existeVertice(unaEtiqueta))) {
            getVertices().put(unaEtiqueta, vertice);
            return getVertices().containsKey(unaEtiqueta);
        }
        return false;
    }

    @Override
    public Double[][] floyd() {
        int cantidadVertices = vertices.size();
        Double[][] matriz = UtilGrafos.obtenerMatrizCostos(vertices);
        for (int k = 0; k < cantidadVertices; k++) {
            for (int i = 0; i < cantidadVertices; i++) {
                for (int j = 0; j < cantidadVertices; j++) {
                    matriz[i][i] = 0.0; // inicializo en 0 cuando el camino es i a i
                    if ((i != k) && (k != j) && (i != j)) {
                        if ((matriz[i][k] != -1) && (matriz[k][j] != -1)) {
                            Double costoIK = matriz[i][k];
                            Double costoKJ = matriz[k][j];
                            Double costoIJ = Double.MAX_VALUE;
                            if (matriz[i][j] != -1) {
                                costoIJ = matriz[i][j];
                            }
                            if ((costoIK + costoKJ) < costoIJ) {
                                matriz[i][j] = Math.min(costoIJ, costoIK + costoKJ);
                            }
                        }
                    }
                }

            }
        }
        return matriz;
    }

    @Override
    public boolean[][] warshall() {
        Double[][] adyacencias = UtilGrafos.obtenerMatrizCostos(vertices);
        boolean[][] warshall = new boolean[vertices.size()][vertices.size()];

        for(int i=0 ; i<adyacencias.length ; i++){
            for(int j=0 ; j<adyacencias.length ; j++){
                if(adyacencias[i][j] != Double.MAX_VALUE){
                    warshall[i][j]=true;
                }
                else{
                    warshall[i][j]= false;
                }
            }
        }
        for(int k =0 ; k<warshall.length ; k++){
            for(int i =0 ; i<warshall.length ; i++){
                    for(int j=0 ; j<warshall.length ; j++){
                            if( !warshall[i][j] ){
                                warshall[i][j] = warshall[i][k] && warshall[k][j];
                            }
                    }
            }
        }
        return warshall;
    }

    @Override
    public Double obtenerExcentricidad(Comparable etiquetaVertice) {
Object[] claves = vertices.keySet().toArray();
        Double[][] afterFloyd = floyd();
        int idx = -1;
        for (int i = 0; i < claves.length; i++) {
            if (claves[i].equals(etiquetaVertice)) {
                idx = i;
            }
        }
        Double max = -1.0;
        if (idx != -1) {
            for (int i = 0; i < claves.length; i++) {
                if (afterFloyd[i][idx] > max) {
                    max = afterFloyd[i][idx];
                }
            }
        }
        return max;             
    }

    /**
     * @return Etiqueta del centro del grafo
     */
    @Override
    public Comparable centroDelGrafo() {
        Object[] ver = this.vertices.keySet().toArray();
        Map<Object, Comparable> excentricidades = new HashMap<>();
        for (Object ver1 : ver) {
            Comparable vertice = (Comparable) ver1;
            excentricidades.put(vertice, obtenerExcentricidad(vertice));
        }
        Map.Entry<Object, Comparable> min = null;

        for (Map.Entry<Object, Comparable> entry : excentricidades.entrySet()) {
            Double value = (Double) entry.getValue();
            if (value != Double.MAX_VALUE) {
                if (min == null || entry.getValue().compareTo(min.getValue()) < 0) {
                    min = entry;
                }
            }

        }
        return (Comparable) min.getKey();
    }

    public Object[] getEtiquetasOrdenado() {
        TreeMap<Comparable, TVertice> mapOrdenado = new TreeMap<>(this.getVertices());
        return mapOrdenado.keySet().toArray();
    }

    @Override
    public void desvisitarVertices() {
        for (TVertice vertice : this.vertices.values()) {
            vertice.setVisitado(false);
        }
    }

    /**
     * @return the vertices
     */
    @Override
    public Map<Comparable, TVertice> getVertices() {
        return vertices;
    }

    @Override
    public Collection<Comparable> bpf(TVertice vertice) {
        TVertice ver = this.vertices.get(vertice);
        Collection<Comparable> lista = new ArrayList();
        ver.bpf(lista);
        return lista;
    }

    @Override

    public Collection<Comparable> bpf(Comparable etiquetaOrigen) {
        TVertice vertice = getVertices().get(etiquetaOrigen);
         Collection<Comparable> visitados = new ArrayList<>();
         vertice.bpf(visitados);
         return visitados;
    }

    @Override
    /**
     * BPF del grafo, a partir del primer vértice, para todos los vertices
     */

    public Collection<Comparable> bpf() {
        Collection<Comparable> visitados = new ArrayList<>();
        if (!this.vertices.isEmpty()) {
            for (Map.Entry<Comparable,TVertice> vertice : this.vertices.entrySet()) {
                vertice.getValue().bpf(visitados);
            }
        }
        return visitados;
    }

    /**
     * @param etiquetaOrigen
     * @param etiquetaDestino
     * @return Un array con las claves de los vertices que componen el mejor
     * camino, en caso de que exista
     */
    public TCaminos todosLosCaminos(Comparable etiquetaOrigen, Comparable etiquetaDestino,String a) {
        TCaminos todosLosCaminos = new TCaminos();
        TVertice v = buscarVertice(etiquetaOrigen);
  
        if (v != null) { 
            TCamino caminoPrevio = new TCamino(v,a);
            v.todosLosCaminos(etiquetaDestino, caminoPrevio, todosLosCaminos,a);
            return todosLosCaminos;
        }
        return null;
    }
    
    public TCaminos todosLosCaminos(Comparable unOrigen, Comparable unDestino, String unaAerolinea, int cantidadMaximaEscalas) {
        TCaminos todosLosCaminos = new TCaminos();
        TVertice v = this.buscarVertice(unOrigen); // problema con la Aerolinea 
        if (v != null) {
            TCamino caminoPrevio = new TCamino(v, unaAerolinea);
            v.todosLosCaminos(unDestino, caminoPrevio, todosLosCaminos,unaAerolinea ,cantidadMaximaEscalas);
            return todosLosCaminos;
        }
        return null;
    }

    @Override
    public boolean tieneCiclo() {
        boolean resultado = false;
        for (TVertice ver : vertices.values()){
            TCamino camino = new TCamino(ver,null);
            if (!ver.getVisitado()){
                resultado = ver.tieneCiclo(camino);
            }
            if (resultado)
                return true;
        }
        return false;
    }

    @Override
    public boolean tieneCiclo(Comparable etiquetaOrigen) {
        TVertice v = vertices.get(etiquetaOrigen);
        TCamino cPrevio = null;
        TCaminos todos = null;
        TCaminos caminos = v.todosLosCaminos(etiquetaOrigen, cPrevio, todos);
        if (!caminos.getCaminos().isEmpty()){
            return true;
        }
        return false;
    }

    @Override
    public boolean tieneCiclo(TCamino camino) {
        TVertice v = camino.getOrigen();
        if (v.tieneCiclo(camino)){
            return true;
        }
        return false;
    }

    @Override
    public TCaminos todosLosCaminos(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        TCaminos todosLosCaminos = new TCaminos();
        TVertice v = buscarVertice(etiquetaOrigen);
        if (v != null) {
            TCamino caminoPrevio = new TCamino(v,null);//deberia guardar aerolinea para diferenciar el grafo.
            v.todosLosCaminos(etiquetaDestino, caminoPrevio, todosLosCaminos);
            return todosLosCaminos;
        }
        return null;
    }

}

