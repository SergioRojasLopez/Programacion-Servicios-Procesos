package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CrearProcesoSalida {
    public static void main(String[] args) throws IOException {
        // -- Creo un array con los argumentos del proceso que quiero crear, en mi caso,
        // pretendo hacer un ls -l ( solo vale en un entorno Unix u OSx)
        String argumentos[] = new String[2];
        argumentos[0] = "ls";
        argumentos[1] = "-l";
        // -- Creo un proceso nuevo, en dos pasos:
        // 1.- Creo un "ConstructorDeProcesos" (ProcessBuilder) con los argumentos del proceso
        // que quiero crear, que es el array -argumentos- antes construido
        ProcessBuilder obProBuil = new ProcessBuilder(argumentos);
        // 2.- Creo el proceso, arrancando el constructor de procesos
        Process obProceso = obProBuil.start();
        // -- Creo un stream (canal) que se engancha a la salida estandar del objeto del proceso hijo
        // para poder ver la salida del proceso, esto es, lo que éste escriba
        InputStream obInpStr = obProceso.getInputStream();
        // -- Con el objeto stream, para leer lo que emita el proceso,
        // creo un iniciador y un filtro para poder usar sus metodos y leer datos.
        // Normalmente, donde esta obInpStrRea, la "puerta" donde leer, deberia ir System.in o el
        // nombre de un fichero donde leer, pero aqui colocamos la salida del proceso hijo.
        InputStreamReader obInpStrRea = new InputStreamReader(obInpStr);
        BufferedReader obBufRea = new BufferedReader(obInpStrRea);
        System.out.println("La ejecucion del proceso devuelve: ");
        // Hago un bucle que lea linea a linea lo que emite (escriba) el proceso,
        // hasta que no emita nada mas, esto es, hasta que la linea que lea sea null.
        // Ver al final un ejemplo de lo que muestra
        String line;
        while ((line = obBufRea.readLine()) != null) {
            // Imprimo por pantalla lo que voy leyendo
            System.out.println(line);
        }
    }
}
