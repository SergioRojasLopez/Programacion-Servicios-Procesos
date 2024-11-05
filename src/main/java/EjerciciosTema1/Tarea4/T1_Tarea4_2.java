package EjerciciosTema1.Tarea4;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.InputStreamReader;

public class T1_Tarea4_2 {
    public static void main(String[] args) {
        generarArchivoTexto();
        try {
            // Obtiene el hash del archivo "entrada.txt"
            String hash = obtenerHashArchivo("entrada.txt");
            System.out.println("Hash del archivo entrada.txt: " + hash);
        } catch (IOException | InterruptedException e) {
            // Captura y muestra cualquier excepción que ocurra
            e.printStackTrace();
        }
    }

    // Método para generar un archivo de texto de ejemplo
    private static void generarArchivoTexto() {
        try {
            // Crea un PrintWriter para escribir en "entrada.txt"
            PrintWriter pw = new PrintWriter("entrada.txt");
            // Escribe una línea de texto en el archivo
            pw.println("Este es un archivo de texto de ejemplo.");
            pw.close(); // Cierra el PrintWriter
            System.out.println("Se ha generado el archivo entrada.txt.");
        } catch (IOException e) {
            // Captura y muestra cualquier excepción de entrada/salida
            e.printStackTrace();
        }
    }

    // Método para obtener el hash del archivo especificado
    private static String obtenerHashArchivo(String archivo) throws IOException, InterruptedException {
        // Configura el ProcessBuilder para ejecutar el comando "sha256sum" para obtener el hash
        ProcessBuilder pb = new ProcessBuilder("sha256sum", archivo);
        Process process = pb.start(); // Inicia el proceso

        // Obtiene el flujo de entrada del proceso
        InputStream inputStream = process.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        StringBuilder hash = new StringBuilder(); // Usado para construir el hash

        // Lee la salida del proceso línea por línea
        if ((line = br.readLine()) != null) {
            // El hash es la primera parte de la línea
            hash.append(line.split(" ")[0]); // Extrae el hash
        }

        int exitCode = process.waitFor(); // Espera a que el proceso termine

        if (exitCode == 0) {
            return hash.toString(); // Retorna el hash si el proceso fue exitoso
        } else {
            System.err.println("Error al obtener el hash del archivo."); // Manejo de errores
            return null; // Retorna null en caso de error
        }
    }
}
