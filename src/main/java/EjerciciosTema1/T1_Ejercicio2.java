package EjerciciosTema1;

import java.io.IOException;

public class T1_Ejercicio2 {
    public class Main {
        public static void main(String[] args) {
            ProcessBuilder pb = new ProcessBuilder("bash" , "bucle.sh");
            /**
             * Lanzamos el proceso para despues comprobar si el estado sigue en ejecución, si
             * sigue en ejecución vamos a esperar 3 segundos hasta volver a comprobarlo, si el proceso
             * ya ha terminado lo cerramos.
             */
            try {
                Process proceso = pb.start();
                while (true) {
                    if (!proceso.isAlive()) {
                        System.out.println("El proceso ya ha finalizado.");
                        break;
                    } else {
                        System.out.println("El proceso sigue en ejecución...");
                        Thread.sleep(3000);
                    }
                }
            } catch (IOException e) {
                System.err.println("Error.");
                e.printStackTrace();
                System.err.println("---------------------");
                System.exit(2);
            } catch (InterruptedException e) {
                System.err.println("Proceso ha sido interrumpido");
                System.exit(3);
            }
        }
    }
}
