package EjerciciosTema1.T1_Tarea4;

import java.io.*;

public class T1_Tarea4_1 {
    public static void main(String[] args) {
        try {
            ProcessBuilder pb = new ProcessBuilder("pwsh");
            pb.redirectErrorStream(true);

            Process process = pb.start();

            // Sacamos el flujo de entrada y salida del proceso
            OutputStream outputStream = process.getOutputStream();
            InputStream inputStream = process.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            PrintWriter writer = new PrintWriter(outputStream);

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String entradaUsuario;

            while (true) {
                System.out.print("Ingrese un comando de PowerShell (o 'exit' para salir): ");
                entradaUsuario = br.readLine();

                //salimos si se escribe Exit tanto en mayus como en minus.
                if (entradaUsuario.equalsIgnoreCase("exit")) {
                    break;
                }

                // Enviamos el comando al PowerShell
                writer.println(entradaUsuario);
                writer.flush();

                // mostramos la salida del comando de PowerShell
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }

            writer.close();
            reader.close();
            br.close();
            process.destroy();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
