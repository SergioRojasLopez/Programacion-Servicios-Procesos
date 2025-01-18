package Echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketsTCP_ClienteEco {

  private final static String COD_TEXTO = "UTF-8";

  public static void main(String[] args) {

    if (args.length < 2) {
      System.err.println("ERROR, indicar: host_servidor puerto");
      System.exit(1);
    }

    String nomHost = args[0];
    int numPuerto = Integer.parseInt(args[1]);
    //Crear la conexión con el socker
    try (Socket socketComunicacion = new Socket(nomHost, numPuerto)) {
      System.out.println("Conectado a servidor.");

      try (
              //Captura el outStream del socket
              OutputStream osAServidor = socketComunicacion.getOutputStream();
              //Captura el inputStream del socket
              InputStream isDeServidor = socketComunicacion.getInputStream();
              InputStreamReader isrDeServidor = new InputStreamReader(isDeServidor, COD_TEXTO);
              //              PrintWriter out = new PrintWriter(osAServidor, true);
              OutputStreamWriter oswAServidor = new OutputStreamWriter(osAServidor, COD_TEXTO);
              BufferedWriter bwAServidor = new BufferedWriter(oswAServidor);
              BufferedReader brDeServidor = new BufferedReader(isrDeServidor);
              //Crea un flujo de entrada desde teclado
              InputStreamReader isrStdIn = new InputStreamReader(System.in);
              BufferedReader brStdIn = new BufferedReader(isrStdIn)) {
        String lineaLeida;
        System.out.println("Introducir líneas. Línea vacía para terminar.");
        System.out.print("Línea>");
        while ((lineaLeida = brStdIn.readLine()) != null && lineaLeida.length() > 0) {
          bwAServidor.write(lineaLeida);
          bwAServidor.newLine();
          bwAServidor.flush();
          System.out.println("respuesta: " + brDeServidor.readLine());
          System.out.print("Línea>");
        }
      }
    } catch (UnknownHostException e) {
      System.err.println("Host desconocido: " + nomHost);
      System.exit(1);
    } catch (IOException ex) {
      System.err.println("Excepción de E/S");
      ex.printStackTrace();
      System.exit(1);
    }

  }

}
