package org.example;/*
Aplicación que pasa al proceso el nslookup y el dominio al 
crear el proccessbuilder
Usa la Clase Scanner para leer de entrada estandar
*/

import java.util.Scanner;
import java.io.IOException;

public class LanzaProcesoEscribeAEntrada_bis {

    public static void main(String[] args) {


        Scanner sc = null;
        try {
            String linea;
            sc = new Scanner(System.in);
            System.out.print("Introducir nombre de dominio: ");

            if ((linea = sc.nextLine()) != null && linea.length() != 0) {
                ProcessBuilder pb = new ProcessBuilder("nslookup", linea);
                pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
                Process p = pb.start();  // Lanza el proceso
                try {
                    p.waitFor();
                } catch (InterruptedException ex) {
                }
                System.out.print("Fin del procesos: ");
            }

        } catch (IOException e) {
            System.out.println("ERROR: de E/S");
            e.printStackTrace();
        } finally {
            sc.close();
        }


    }

}
