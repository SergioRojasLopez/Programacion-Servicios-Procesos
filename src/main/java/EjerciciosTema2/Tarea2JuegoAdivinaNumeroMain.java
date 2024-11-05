package EjerciciosTema2;

import java.util.Random;

class NumeroOculto {
    private int numeroSecreto;
    private boolean adivinado;

    public NumeroOculto() {
        // Genera un número al azar entre 0 y 100
        numeroSecreto = new Random().nextInt(101);
        adivinado = false;
    }

    public synchronized int propuestaNumero(int intento) {
        if (adivinado) {
            // Si el número ya fue adivinado, el juego termina para los demás
            return -1;
        }
        if (intento == numeroSecreto) {
            // Si el intento es correcto, marcar el juego como terminado
            adivinado = true;
            return 1;
        }
        // Si el intento no es correcto
        return 0;
    }
}

class Adivinador extends Thread {
    private NumeroOculto juego;

    public Adivinador(NumeroOculto juego) {
        this.juego = juego;
    }

    @Override
    public void run() {
        while (true) {
            // Genera un intento al azar entre 0 y 100
            int intento = new Random().nextInt(101);
            int resultado = juego.propuestaNumero(intento);

            if (resultado == 1) {
                System.out.println("¡Hilo adivinador ha acertado el número! Era: " + intento);
                break;
            } else if (resultado == -1) {
                System.out.println("Hilo adivinador se detiene, ya acertaron el número.");
                break;
            }
        }
    }
}

public class Tarea2JuegoAdivinaNumeroMain {
    public static void main(String[] args) {
        // El hilo principal genera el número oculto y crea a los adivinadores
        NumeroOculto juego = new NumeroOculto();
        Adivinador[] adivinadores = new Adivinador[10];

        for (int i = 0; i < 10; i++) {
            adivinadores[i] = new Adivinador(juego);
            adivinadores[i].start();
        }
    }
}
