/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alasucu;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author NicoPlaceres
 */
public class UtilGrafos {
    

    public static Double[][] obtenerMatrizCostos(Map<Comparable, TVertice> vertices) {
        int cantidadVertices = vertices.size();
        Double[][] matrizCostos = new Double[cantidadVertices][cantidadVertices];

        for (int i = 0; i < matrizCostos.length; i++) {
            for (int j = 0; j < matrizCostos.length; j++) {
                if (i == j) {
                    matrizCostos[i][j] = -1d;
                } else {
                    matrizCostos[i][j] = Double.MAX_VALUE;
                }
            }
        }

        int i = 0;

        Set<Comparable> etiquetasVertices = vertices.keySet();
        Object[] VerticesIArr = etiquetasVertices.toArray();
        Object[] VerticesJArr = etiquetasVertices.toArray();

        while (i < cantidadVertices) {
            int j = 0;
            while (j < cantidadVertices) {
                TVertice elemVerticeI = vertices.get(VerticesIArr[i]);
                TVertice elemVerticeJ = vertices.get(VerticesJArr[j]);

                if (!elemVerticeI.getEtiqueta().equals(elemVerticeJ.getEtiqueta())) {
                    TVertice verticeI = (TVertice) elemVerticeI;
                    TVertice verticeJ = (TVertice) elemVerticeJ;
                    Double costoAdyacencia = verticeI.obtenerCostoAdyacencia(verticeJ);
                    matrizCostos[i][j] = costoAdyacencia;
                }
                j++;
            }
            i++;
        }
        return matrizCostos;
    }

    public static void imprimirMatriz(Comparable[][] matriz, Map<Comparable, TVertice> vertices) {
        Object[] etiquetas = vertices.keySet().toArray();
        System.out.print("  ");
        for (int i = 0; i < matriz.length; i++) {
            System.out.print(etiquetas[i] + "  ");
        }
        System.out.println();
        for (int i = 0; i < matriz.length; i++) {
            System.out.print(etiquetas[i] + " ");
            for (int j = 0; j < matriz.length; j++) {
                if (matriz[i][j].compareTo(Double.MAX_VALUE) == 0) {
                    System.out.print(" INF ");
                } else {
                    System.out.print(matriz[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public static void imprimirMatrizMejorado(Comparable[][] matriz, Map<Comparable, TVertice> vertices, String titulo) {
        if (vertices != null && matriz.length == vertices.keySet().size()) {

            Comparable[] etiquetas = vertices.keySet().toArray(new Comparable[vertices.keySet().size()]);
            int etiquetaMasLarga = stringMasLargo(etiquetas);
            int datoMasLargo = 0;
            String infinito = "Inf";
            String nulo = "Nulo";
            int separacionEntreColumnas = 3;

            Comparable[] datos = new Comparable[matriz.length];

            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz.length; j++) {
                    if (matriz[i][j] == null) {
                        datos[j] = nulo;
                    } else if (matriz[i][j].compareTo(Double.MAX_VALUE) == 0) {
                        datos[j] = infinito;
                    } else {
                        datos[j] = matriz[i][j];
                    }
                }
                if (stringMasLargo(datos) > datoMasLargo) {
                    datoMasLargo = stringMasLargo(datos);
                }
            }

            int largo = Math.max(etiquetaMasLarga, datoMasLargo) + separacionEntreColumnas;

            for (int i = 0; i < etiquetas.length; i++) {
                etiquetas[i] = rellenar(etiquetas[i].toString(), largo, ' ');
            }

            int tope = (largo) * (etiquetas.length + 1);

            String linea = rellenar("", tope, '-');
            String separador = rellenar("", largo, ' ');
            String sepTitulo = rellenar("", tope, '*');

            System.out.println(sepTitulo);
            System.out.println(devolverCentrado(titulo, tope));
            System.out.println(sepTitulo);
            System.out.println(linea);

            System.out.print(separador);
            for (int i = 0; i < matriz.length; i++) {
                System.out.print(etiquetas[i]);
            }

            System.out.println();
            System.out.println(linea);

            for (int i = 0; i < matriz.length; i++) {
                System.out.print(etiquetas[i]);
                for (int j = 0; j < matriz.length; j++) {

                    if (matriz[i][j] == null) {
                        System.out.print(rellenar(nulo, largo, ' '));
                    } else if (matriz[i][j].compareTo(Double.MAX_VALUE) == 0) {
                        System.out.print(rellenar(infinito, largo, ' '));
                    } else {
                        System.out.print(rellenar(matriz[i][j].toString(), largo, ' '));
                    }
                }
                System.out.println();
                System.out.println(linea);
            }
        }
        System.out.println();
    }

    public static String rellenar(String textoARellenar, int largoTotal, char relleno) {
        while (textoARellenar.length() < largoTotal) {
            textoARellenar += relleno;
        }
        return textoARellenar;
    }

    public static int stringMasLargo(Comparable[] etiquetas) {
        int mayor = etiquetas[0].toString().length();
        for (int i = 1; i < etiquetas.length; i++) {
            if (etiquetas[i].toString().length() > mayor) {
                mayor = etiquetas[i].toString().length();
            }
        }
        return mayor;
    }

    public static String devolverCentrado(String texto, int largo) {
        boolean pos = false;
        while (texto.length() < largo) {
            if (pos == false) {
                texto += " ";
                pos = true;
            } else {
                texto = " " + texto;
                pos = false;
            }
        }
        return texto;
    }
    public static Map<String,String>  cargarAerolinea(String archivo){
        boolean ignoreHeader;
        ignoreHeader = true;
        //LinkedList<Aerolinea> aerolineas ;
        Map<String,String> hasAerolineas = new HashMap<>();

        String[] aerolineasa = ManejadorArchivosGenerico.leerArchivo(archivo, ignoreHeader);
        for (int i = 0; i < aerolineasa.length; i++) {
            String aerolineasa2 = aerolineasa[i];
            String[] datos = aerolineasa2.split(",");
            
            String keyA = datos[0];
            String nombreA  = datos[1];
             if (!datos[0].isEmpty()){
            hasAerolineas.put(keyA, nombreA);
            System.out.println(keyA + "  " + nombreA);
             }
        }
        return hasAerolineas;
    }
    public static <T extends IGrafoDirigido> T cargarGrafo(String nomArchVert, String nomArchAdy,
            boolean ignoreHeader, Class t) throws ParseException {

        ignoreHeader = true;
        String[] vertices = ManejadorArchivosGenerico.leerArchivo(nomArchVert, ignoreHeader);
        String[] aristas = ManejadorArchivosGenerico.leerArchivo(nomArchAdy, ignoreHeader);
        //List<TVertice> verticesList = new ArrayList<TVertice>(vertices.length);
        //List<TArista> aristasList = new ArrayList<TArista>(aristas.length);

        List<TVertice> verticesList = new ArrayList<TVertice>();
        List<TArista> aristasList = new ArrayList<TArista>();

        for (int i = 0; i < vertices.length; i++) {
            String verticeString = vertices[i];
            if ((verticeString != null) && (verticeString.trim() != "")) {
                String[] datos = verticeString.split(",");
                if (!datos[0].isEmpty()) {
                    verticesList.add(new TVertice(datos[0], datos[1]));
                }
            }
        }
        for (int i = 0; i < aristas.length; i++) {
            String aristaString = aristas[i];
            if ((aristaString != null) && (aristaString.trim() != "")) {
                String[] datos = aristaString.split(",");
                aristasList.add(new TArista(datos[1], datos[2], Double.parseDouble(datos[3]), datos[0]));
            }
        }
        
        IGrafoDirigido g = new TGrafoDirigido(verticesList, aristasList);
        return (T) g;
    }
    

}

    

