package EjerciciosTema1.T1_Tarea3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class T1_Tarea3_1 {
    public static void main(String[] args) {
        String texto = args[0];
        String ficheroEntrada = args[1];
        String ficheroSalida = args[2];
//        Esta es una prueba exitosa del comando en IntelliJ en windows
//        String texto = "fichero";
//        String ficheroEntrada = "ficheroIn.txt";
//        String ficheroSalida = "ficheroOut.txt";

        try {
            ProcessBuilder processBuilder = new ProcessBuilder("grep", texto, ficheroEntrada);
            Process process = processBuilder.start();

            // Guardamos la salida del comando en el archivo de salida
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
