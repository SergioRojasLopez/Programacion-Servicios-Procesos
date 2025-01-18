package SC;

import java.io.*;
import java.net.*;

public class Servidor {

    public static void main(String[] args) {
        int PUERTO = 5000;

        System.out.println("Servidor escuchando en el puerto: " + PUERTO);

        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            while (true) {
                Socket cliente = servidor.accept();
                System.out.println("Nuevo cliente conectado: " + cliente.getInetAddress());
                // Crear un nuevo hilo para manejar al cliente
                new ClienteHandler(cliente).start();
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}