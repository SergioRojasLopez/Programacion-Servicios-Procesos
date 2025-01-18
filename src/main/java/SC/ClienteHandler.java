package SC;

import java.io.*;
import java.net.*;

public class ClienteHandler extends Thread {
    private Socket cliente;

    public ClienteHandler(Socket cliente) throws IOException {
        this.cliente = cliente;

    }
    @Override
    public void run() {
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter pw = new PrintWriter(cliente.getOutputStream(), true);

            String mensaje;
            pw.println("Conexión establecida con el servidor. Escriba 'salir' para terminar.");
            while ((mensaje = bf.readLine()) != null) {
                if (mensaje.equalsIgnoreCase("salir")) {
                    pw.println("Desconexión exitosa. ¡Hasta luego!");
                    break;
                }
                pw.println("Eco: " + mensaje);
            }
        } catch (IOException e) {
            System.err.println("Error al comunicar con el cliente: " + e.getMessage());
        } finally {
            try {
                cliente.close();
                System.out.println("Conexión con cliente cerrada.");
            } catch (IOException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}
