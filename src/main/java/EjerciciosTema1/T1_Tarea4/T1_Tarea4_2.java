package EjerciciosTema1.T1_Tarea4;

import java.io.*;

public class T1_Tarea4_2 {
    public static void main(String[] args) {

        generarArchivoTexto();

        try {
            // Obtenemos el hash del archivo entrada.txt
            String hash = obtenerHashArchivo("entrada.txt");
            System.out.println("Hash del archivo entrada.txt: " + hash);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void generarArchivoTexto() {
        try {
            PrintWriter pw = new PrintWriter("entrada.txt");
            pw.println("Este es un archivo de texto de ejemplo.");
            pw.close();
            System.out.println("Se ha generado el archivo entrada.txt.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String obtenerHashArchivo(String archivo) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder("Certutil", "-hashfile", archivo);
        Process process = pb.start();

        // Obtenemos el flujo de entrada del proceso
        InputStream inputStream = process.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        StringBuilder hash = new StringBuilder();

        while ((line = br.readLine()) != null) {
            // Buscamos la línea que contiene el hash
            if (line.startsWith("Hash de archivo")) {
                int startIndex = line.indexOf(":") + 1;
                hash.append(line.substring(startIndex).trim());
            }
        }
        int exitCode = process.waitFor();

        if (exitCode == 0) {
            return hash.toString();
        } else {
            System.err.println("Error al obtener el hash del archivo.");
            return null;
        }
    }
}
