package ExamenTema2.Ejercicio3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Sincronizador {
    private final int MAX_NUM_PATAS;
    private final int MAX_NUM_TABLEROS;
    private int patasDisponibles = 0;
    private int tablerosDisponibles = 0;
    private int mesasEnsambladas = 0;
    private final int LIMITE_MESAS;

    private final Lock lock = new ReentrantLock();
    private final Condition patasCondition = lock.newCondition();
    private final Condition tablerosCondition = lock.newCondition();
    private final Condition ensambladoresCondition = lock.newCondition();

    public Sincronizador(int maxNumPatas, int maxNumTableros, int limiteMesas) {
        this.MAX_NUM_PATAS = maxNumPatas;
        this.MAX_NUM_TABLEROS = maxNumTableros;
        this.LIMITE_MESAS = limiteMesas;
    }

    public void ponPata() throws InterruptedException {
        lock.lock();
        try {
            while (patasDisponibles >= MAX_NUM_PATAS) {
                patasCondition.await();
            }
            patasDisponibles++;
            System.out.println("Se ha producido una pata. Total patas disponibles: " + patasDisponibles);
            ensambladoresCondition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void ponTablero() throws InterruptedException {
        lock.lock();
        try {
            while (tablerosDisponibles >= MAX_NUM_TABLEROS) {
                tablerosCondition.await();
            }
            tablerosDisponibles++;
            System.out.println("Se ha producido un tablero. Total tableros disponibles: " + tablerosDisponibles);
            ensambladoresCondition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void cogePatasyTablero() throws InterruptedException {
        lock.lock();
        try {
            while (mesasEnsambladas < LIMITE_MESAS && (patasDisponibles < 4 || tablerosDisponibles < 1)) {
                ensambladoresCondition.await();
            }

            if (mesasEnsambladas >= LIMITE_MESAS) {
                return;
            }

            patasDisponibles -= 4;
            tablerosDisponibles -= 1;
            mesasEnsambladas++;
            System.out.println("Se ha ensamblado una mesa. Total mesas ensambladas: " + mesasEnsambladas);
            System.out.println("Patas restantes: " + patasDisponibles + ", Tableros restantes: " + tablerosDisponibles);

            patasCondition.signalAll();
            tablerosCondition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public boolean haAlcanzadoLimite() {
        lock.lock();
        try {
            return mesasEnsambladas >= LIMITE_MESAS;
        } finally {
            lock.unlock();
        }
    }
}
