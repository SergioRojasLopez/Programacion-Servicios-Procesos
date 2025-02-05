package SC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Cliente {

    public static void main(String[] args) {
        int PUERTO = 5000;

        System.out.println("Cliente intentando conectarse...");

        try (Socket servidor = new Socket(InetAddress.getLocalHost(), PUERTO);
             BufferedReader bf = new BufferedReader(new InputStreamReader(servidor.getInputStream()));
             PrintWriter pw = new PrintWriter(servidor.getOutputStream(), true);
             BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Conexión exitosa con el servidor.");
            System.out.println(bf.readLine()); // Mensaje de bienvenida del servidor

            String entradaUsuario;
            while ((entradaUsuario = teclado.readLine()) != null) {
                pw.println(entradaUsuario);
                if (entradaUsuario.equalsIgnoreCase("salir")) {
                    break;
                }
                System.out.println(bf.readLine());
            }
        } catch (IOException e) {
            System.err.println("Error en el cliente: " + e.getMessage());
        }
    }
}
