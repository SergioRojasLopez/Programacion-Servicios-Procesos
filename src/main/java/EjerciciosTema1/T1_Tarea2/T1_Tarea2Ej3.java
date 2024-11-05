package EjerciciosTema1.T1_Tarea2;

import java.io.File;
import java.io.IOException;

public class T1_Tarea2Ej3 {
    public static void main(String[] args) {
        String directorioPorDefecto = System.getProperty("user.dir");
        System.out.println("El directorio en ejecucion es " + directorioPorDefecto);
        ProcessBuilder pb = new ProcessBuilder("ls");
        String [] directorios = {directorioPorDefecto, "/home","/etc"};

        for (String directorio : directorios) {
            pb.directory(new File(directorio));
            try{
                Process process = pb.start();
                int result = process.waitFor();
                if (result == 0){
                    System.out.println("El comando -ls se ha ejecutado correctamente en " + directorio);
                }else {
                    System.out.println("Error al ejecutar el comando -ls en " + directorio);
                }
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


    }
}
