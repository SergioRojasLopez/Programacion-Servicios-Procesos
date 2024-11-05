package EjerciciosTema2;

class Contadores {
    private long contador1 = 0;
    private long contador2 = 0;
    private final Object cerrojo1 = new Object();
    private final Object cerrojo2 = new Object();

    public void incrementarContador1() {
        synchronized (cerrojo1) {
            contador1++;
        }
    }

    public long obtenerContador1() {
        synchronized (cerrojo1) {
            return contador1;
        }
    }

    public void incrementarContador2() {
        synchronized (cerrojo2) {
            contador2++;
        }
    }

    public long obtenerContador2() {
        synchronized (cerrojo2) {
            return contador2;
        }
    }
}

class TareaIncremento extends Thread {
    private Contadores contadores;
    private int repeticiones;

    public TareaIncremento(Contadores contadores, int repeticiones) {
        this.contadores = contadores;
        this.repeticiones = repeticiones;
    }

    @Override
    public void run() {
        for (int i = 0; i < repeticiones; i++) {
            contadores.incrementarContador1();
        }

        for (int i = 0; i < repeticiones; i++) {
            contadores.incrementarContador2();
        }
    }
}

public class Tarea2Contadores {
    public static void main(String[] args) {
        Contadores contadores = new Contadores();
        int totalHilos = 10;
        int incrementosPorHilo = 10000000; // Cada hilo realiza 10,000,000 incrementos

        TareaIncremento[] hilosIncremento = new TareaIncremento[totalHilos];

        for (int i = 0; i < totalHilos; i++) {
            hilosIncremento[i] = new TareaIncremento(contadores, incrementosPorHilo);
            hilosIncremento[i].start();
        }

        // Esperar a que todos los hilos terminen
        for (int i = 0; i < totalHilos; i++) {
            try {
                hilosIncremento[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Verificar los valores finales de los contadores
        System.out.println("Valor de Contador 1: " + contadores.obtenerContador1());
        System.out.println("Valor de Contador 2: " + contadores.obtenerContador2());

        if (contadores.obtenerContador1() == 100000000 && contadores.obtenerContador2() == 100000000) {
            System.out.println("¡Éxito! Ambos contadores alcanzaron el valor esperado.");
        } else {
            System.out.println("Error: Los contadores no alcanzaron el valor esperado.");
        }
    }
}

