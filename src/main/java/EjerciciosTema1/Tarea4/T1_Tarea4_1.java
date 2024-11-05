package EjerciciosTema1.Tarea4;

import java.io.*;

public class T1_Tarea4_1 {
    public static void main(String[] args) {
        try {
            // Se crea un ProcessBuilder para iniciar PowerShell
            ProcessBuilder pb = new ProcessBuilder("pwsh");
            // Redirige los errores al flujo de salida estándar
            pb.redirectErrorStream(true);

            // Inicia el proceso de PowerShell
            Process process = pb.start();

            // Obtiene los flujos de entrada y salida del proceso
            OutputStream outputStream = process.getOutputStream();
            InputStream inputStream = process.getInputStream();

            // Crea un BufferedReader para leer la salida de PowerShell
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            // Crea un PrintWriter para enviar comandos a PowerShell
            PrintWriter writer = new PrintWriter(outputStream);

            // Crea un BufferedReader para leer la entrada del usuario desde la consola
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String entradaUsuario;

            // Bucle infinito para recibir comandos del usuario
            while (true) {
                System.out.print("Ingrese un comando de PowerShell (o 'exit' para salir): ");
                entradaUsuario = br.readLine(); // Lee el comando del usuario

                // Salimos si se escribe "exit" (sin importar mayúsculas o minúsculas)
                if (entradaUsuario.equalsIgnoreCase("exit")) {
                    break; // Sale del bucle
                }

                // Envía el comando al proceso de PowerShell
                writer.println(entradaUsuario);
                writer.flush(); // Asegura que el comando se envíe inmediatamente

                // Aquí se muestra la salida del comando de PowerShell
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line); // Imprime cada línea de la salida
                }
            }

            // Cierra los flujos de entrada/salida y destruye el proceso
            writer.close();
            reader.close();
            br.close();
            process.destroy();
        } catch (IOException e) {
            // Captura y muestra cualquier excepción que ocurra
            e.printStackTrace();
        }
    }
}

