package EjerciciosTema2.Tarea3;

class Contador {

    private int cuenta = 0;
    private final int maximo; // Valor máximo del contador

    Contador(int valorInicial, int maximo) {
        this.cuenta = valorInicial;
        this.maximo = maximo;
    }

    synchronized public int getCuenta() {
        return cuenta;
    }

    synchronized public void incrementa() {
        while (cuenta >= maximo) { // Espera si se alcanza el valor máximo
            try {
                System.out.println("!!! Incrementador en espera, contador alcanzó el máximo: " + cuenta);
                wait();
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        this.cuenta++;
        System.out.println("+++ Incrementado, nuevo valor: " + cuenta);
        notifyAll(); // Notifica a los decrementadores
    }

    synchronized public void decrementa() {
        while (cuenta <= 0) { // Espera si el contador está en su mínimo
            try {
                System.out.println("!!! Decrementador en espera, contador en 0");
                wait();
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        this.cuenta--;
        System.out.println("--- Decrementado, nuevo valor: " + cuenta);
        notifyAll(); // Notifica a los incrementadores
    }
}

class HiloIncr implements Runnable {

    private final String id;
    private final Contador cont;

    HiloIncr(String id, Contador c) {
        this.id = id;
        this.cont = c;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (this.cont) {
                cont.incrementa();
                System.out.printf("Hilo %s incrementa, valor contador: %d\n", this.id, cont.getCuenta());
            }
            try {
                Thread.sleep(100); // Retraso para mejor visualización
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

class HiloDecr implements Runnable {

    private final String id;
    private final Contador cont;

    HiloDecr(String id, Contador c) {
        this.id = id;
        this.cont = c;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (this.cont) {
                cont.decrementa();
                System.out.printf("Hilo %s decrementa, valor contador: %d\n", this.id, cont.getCuenta());
            }
            try {
                Thread.sleep(100); // Retraso para mejor visualización
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

class HilosIncDec {

    private static final int NUM_HILOS_INC = 5; // Número de hilos incrementadores
    private static final int NUM_HILOS_DEC = 5; // Número de hilos decrementadores
    private static final int VALOR_MAXIMO = 10; // Valor máximo del contador

    public static void main(String[] args) {
        Contador c = new Contador(0, VALOR_MAXIMO);

        Thread[] hilosInc = new Thread[NUM_HILOS_INC];
        for (int i = 0; i < NUM_HILOS_INC; i++) {
            Thread th = new Thread(new HiloIncr("INC" + i, c));
            hilosInc[i] = th;
        }
        for (int i = 0; i < NUM_HILOS_INC; i++) {
            hilosInc[i].start();
        }

        Thread[] hilosDec = new Thread[NUM_HILOS_DEC];
        for (int i = 0; i < NUM_HILOS_DEC; i++) {
            Thread th = new Thread(new HiloDecr("DEC" + i, c));
            hilosDec[i] = th;
        }
        for (int i = 0; i < NUM_HILOS_DEC; i++) {
            hilosDec[i].start();
        }
    }
}

