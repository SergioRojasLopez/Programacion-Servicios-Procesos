package EjerciciosTema1.Tarea3;

import java.io.*;

public class T1_Tarea3_2 {
    public static void main(String[] args) {
        String rutaDirectorio = args[0];
        File directorio = new File(rutaDirectorio);

        if (!directorio.exists()) {
            System.err.println("El directorio no existe.");
            System.exit(1);
        }

        if (!directorio.isDirectory()) {
            System.err.println("La ruta que has especificado no es un directorio.");
            System.exit(1);
        }

        try {
            ProcessBuilder pb = new ProcessBuilder("ls", "-lF", rutaDirectorio);
            Process process = pb.start();

            // Aqui obtenemos el stream de salida del proceso
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String linea;
            int numeroLinea = 1;
            while ((linea = reader.readLine()) != null) {
                System.out.println(numeroLinea + ": " + linea);
                numeroLinea++;
            }

            // Esperamos a que el proceso termine
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Comando ejecutado correctamente.");
            } else {
                System.err.println("Error. Código de salida: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
