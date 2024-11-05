package EjerciciosTema1.Tarea2;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class T1_Tarea2_1 {
    public static void main(String[] args) {
        mostrarDirectorioPorDefecto();
        // Define los directorios en los que ejecutar el comando
        String[] directorios = {"/", "/home", "/tmp"};

        // Ejecuta el comando 'ls' en los distintos directorios
        for (String dir : directorios) {
            ejecutarComandoEnDirectorio(dir, "ls");
        }
    }
    // Método para mostrar el directorio por defecto de ejecución
    private static void mostrarDirectorioPorDefecto() {
        ProcessBuilder pb = new ProcessBuilder();
        File directorioPorDefecto = pb.directory();
        System.out.println("Directorio por defecto de ejecución: " + directorioPorDefecto);
    }

    // Método para ejecutar un comando en un directorio específico
    private static void ejecutarComandoEnDirectorio(String directorio, String comando) {
        ProcessBuilder pb = new ProcessBuilder(comando);
        pb.directory(new File(directorio)); // Asigna el directorio

        try {
            Process process = pb.start(); // Inicia el proceso

            // Lee la salida del comando
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            System.out.println("Ejecutando en: " + directorio);
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor(); // Espera a que el proceso termine
            if (exitCode != 0) {
                System.err.println("Error al ejecutar el comando en " + directorio);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace(); // Manejo de excepciones
        }
    }
}
