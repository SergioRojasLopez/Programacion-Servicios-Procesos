package EjerciciosTema2.hilosincdec;

class Contador {

    private int cuenta = 0;
    private final int valorMaximo;

    Contador(int valorInicial, int valorMaximo) {
        this.cuenta = valorInicial;
        this.valorMaximo = valorMaximo;
    }

    synchronized public int getCuenta() {
        return cuenta;
    }

    synchronized public int incrementa() {
        while (cuenta >= valorMaximo) {
            try {
                wait();  // Espera si cuenta ya alcanzó el valor máximo
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        cuenta++;
        notifyAll();  // Notifica a los hilos en espera de que se ha incrementado el valor
        return cuenta;
    }

    synchronized public int decrementa() {
        while (cuenta < 1) {
            try {
                wait();  // Espera si cuenta está en 0 o menos
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        cuenta--;
        notifyAll();  // Notifica a los hilos en espera de que se ha decrementado el valor
        return cuenta;
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
            synchronized (cont) {
                cont.incrementa();
                System.out.printf("Hilo %s incrementa, valor contador: %d\n", id, cont.getCuenta());
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
            synchronized (cont) {
                cont.decrementa();
                System.out.printf("Hilo %s decrementa, valor contador: %d\n", id, cont.getCuenta());
            }
        }
    }
}

class HilosIncDec {

    private static final int NUM_HILOS_INC = 10;
    private static final int NUM_HILOS_DEC = 10;
    private static final int VALOR_MAXIMO = 10;  // Valor máximo del contador

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
