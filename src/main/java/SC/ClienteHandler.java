package SC;

import java.io.*;
import java.net.*;

public class ClienteHandler extends Thread {
    private Socket cliente;
    private BufferedReader in;
    private PrintWriter out;

    public ClienteHandler(Socket cliente) throws IOException {
        this.cliente = cliente;
        this.in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
        this.out = new PrintWriter(cliente.getOutputStream(), true);
    }

    @Override
    public void run() {
        try {
            String mensaje;
            out.println("Conexión establecida con el servidor. Escriba 'salir' para terminar.");
            while ((mensaje = in.readLine()) != null) {
                if (mensaje.equalsIgnoreCase("salir")) {
                    out.println("Desconexión exitosa. ¡Hasta luego!");
                    break;
                }
                out.println("Eco: " + mensaje);
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
