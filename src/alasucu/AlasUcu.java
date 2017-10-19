/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alasucu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author NicoPlaceres
 */
public class AlasUcu {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        TGrafoDirigido gnd = (TGrafoDirigido) UtilGrafos.cargarGrafo(
                "src/alasucu/aeropuertos_1.csv",
                "src/alasucu/vuelos_test.csv",
                false, TGrafoDirigido.class);

        Map<String, String> hasAerolineas = UtilGrafos.cargarAerolinea("src/alasucu/Aerolineas.csv");
        System.out.println(hasAerolineas.get("AA"));

        String partida = "JFK";
        String destino = "LAX";

        int cantEscalas = 2;

        LinkedList<TCamino> caminoTotalTest = new LinkedList<>();
        Set<String> aerolineasKey = hasAerolineas.keySet();
        Object[] arr = aerolineasKey.toArray();
        

        
        for (int i = 0; i < arr.length; i++) {
            String aerolinea = arr[i].toString();
            TCaminos caminos = gnd.todosLosCaminos(partida, destino, aerolinea, cantEscalas);
            for (TCamino camino : caminos.getCaminos()) {
                caminoTotalTest.add(camino.copiar());
                //System.out.println(camino.imprimirEtiquetas()+" " + camino.getAerolinea() + " " + camino.getCostoTotal());
            }

            
        }
        
       // System.out.println(caminoTotalTest.element().imprimirEtiquetas() + " Costo total: " + caminoTotalTest.element().getCostoTotal());
        
         for (Iterator i = caminoTotalTest.iterator(); i.hasNext();) {
             TCamino camino = (TCamino) i.next();
            System.out.println(camino.imprimirEtiquetas() + " Costo total: " + camino.getCostoTotal());
         }
    
           
        /* TCaminos caminos = gnd.todosLosCaminos(partida, destino, "WN", cantEscalas);
        caminos.imprimirCaminosConsola();
        
        TCaminos caminos2 = gnd.todosLosCaminos(partida, destino, "AA", cantEscalas);
        caminos2.imprimirCaminosConsola();
        
        TCaminos caminos3 = gnd.todosLosCaminos(partida, destino, "7H", cantEscalas);
        caminos3.imprimirCaminosConsola();
        
        TCaminos caminos4 = gnd.todosLosCaminos(partida, destino, "AS", cantEscalas);
        caminos4.imprimirCaminosConsola();
        
        TCaminos caminos5 = gnd.todosLosCaminos(partida, destino, "US", cantEscalas);
        caminos5.imprimirCaminosConsola();
        
        TCaminos camino6 = gnd.todosLosCaminos(partida, destino, "UA", cantEscalas);
        camino6.imprimirCaminosConsola();
        
        TCaminos camino7 = gnd.todosLosCaminos(partida, destino, "KL", cantEscalas);
        camino7.imprimirCaminosConsola();
        
        TCaminos camino8 = gnd.todosLosCaminos(partida, destino, "DL", cantEscalas);
        camino8.imprimirCaminosConsola();
        
        TCaminos camino9 = gnd.todosLosCaminos(partida, destino, "AM", cantEscalas);
        camino9.imprimirCaminosConsola();
        
        
        LinkedList<TCamino> caminoTotal = new LinkedList<>();
        
        for (TCamino camino : caminos.getCaminos()) {
        caminoTotal.add(camino.copiar());
        }
        for (TCamino camino : caminos2.getCaminos()) {
        caminoTotal.add(camino.copiar());
        }
        for (TCamino camino : caminos3.getCaminos()) {
        caminoTotal.add(camino.copiar());
        }
        for (TCamino camino : caminos4.getCaminos()) {
        caminoTotal.add(camino.copiar());
        }
        for (TCamino camino : caminos5.getCaminos()) {
        caminoTotal.add(camino.copiar());
        }
        for (TCamino camino : camino6.getCaminos()) {
        caminoTotal.add(camino.copiar());
        }
        for (TCamino camino : camino7.getCaminos()) {
        caminoTotal.add(camino.copiar());
        }
        for (TCamino camino : camino8.getCaminos()) {
        caminoTotal.add(camino.copiar());
        }
        for (TCamino camino : camino9.getCaminos()) {
        caminoTotal.add(camino.copiar());
        }
        
        System.out.println(caminoTotal.element().imprimirEtiquetas() + " Costo total: " + caminoTotal.element().getCostoTotal());*/
        double minCosto = Double.MAX_VALUE;
        //long minTiempo = Long.MAX_VALUE;
        TCamino caminoMasBarato = null;
        TCamino caminoMasRapido = null;
        for (TCamino camino : caminoTotalTest) {
            if (camino.getCostoTotal() < minCosto) {
                minCosto = camino.getCostoTotal();

                caminoMasBarato = camino;
            }

        }
        try {
            System.out.println("La opcion mas economica es: " + caminoMasBarato.imprimirEtiquetas() + " con un costo total de: " + caminoMasBarato.getCostoTotal());

        } catch (Exception e) {
            System.out.println("No hay aerolineas dispoibles/vuelos ");
        }

        /*System.out.println("Bienvenido a AlasUcu");
        System.out.println("Ingrese la opcion deseada:");
        System.out.println("1-Para buscar vuelos");
        System.out.println("2-Para salir");
        String opcion = br.readLine();
        
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        
        while (!opcion.equals("2")){
            System.out.println("Ingrese Origen:");
            String origen = br.readLine();
            System.out.println("Ingrese Destino:");
            String destino = br.readLine();
            System.out.println("Ingrese Fecha:");
            String date = br.readLine()+" 00:00:00";
            Date fecha = format.parse(date);;
            TCaminos caminos = gnd.todosLosCaminos(origen, destino, fecha);
            caminos.imprimirCaminosConsola();
            double minCosto = Double.MAX_VALUE;
            long minTiempo = Long.MAX_VALUE;
            TCamino caminoMasBarato = null;
            TCamino caminoMasRapido = null;
            for(TCamino camino: caminos.getCaminos()){
                if (camino.getCostoTotal()<minCosto){
                    minCosto = camino.getCostoTotal();
                    caminoMasBarato = camino;
                }
                long tiempo = camino.getFecha().getTime()-camino.getComienzo().getTime();
                if (tiempo<minTiempo){
                    minTiempo = tiempo;
                    caminoMasRapido = camino;
                }
            }
            System.out.println("La opcion mas economica es: "+caminoMasBarato.imprimirEtiquetas()+" con un costo total de: "+caminoMasBarato.getCostoTotal());
            long mejorTiempo = (caminoMasRapido.getFecha().getTime()-caminoMasRapido.getComienzo().getTime())/(1000*60*60);
            System.out.println("La opcion mas rapida es: "+caminoMasRapido.imprimirEtiquetas()+" con un tiempo total de: "+mejorTiempo+" horas"+"\n");
            System.out.println("Ingrese la opcion deseada:");
            System.out.println("1-Para buscar vuelos");
            System.out.println("2-Para salir");
            opcion = br.readLine();
        }
        System.out.println("Gracias por utilizar AlasUcu");*/
    }
}
