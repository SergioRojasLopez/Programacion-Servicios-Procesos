package EjerciciosTema1.Tarea3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class T1_Tarea3_1 {
    public static void main(String[] args) {
        // Validar el número de argumentos
        if (args.length < 3) {
            System.err.println("Se requieren tres argumentos: texto, ficheroEntrada y ficheroSalida.");
            return;
        }
        String texto = args[0];
        String ficheroEntrada = args[1];
        String ficheroSalida = args[2];

        try {
            ProcessBuilder processBuilder = new ProcessBuilder("grep", texto, ficheroEntrada);
            Process process = processBuilder.start();

            // Tenemos que guardar la salida del comando en un archivo de salida nuevo
            File fSalida = new File(ficheroSalida);
            FileOutputStream outputStream = new FileOutputStream(fSalida);
            InputStream inputStream = process.getInputStream();

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Comando ejecutado correctamente.");
            } else {
                System.err.println("Error. Código de salida: " + exitCode);
            }

            outputStream.close();
            inputStream.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
