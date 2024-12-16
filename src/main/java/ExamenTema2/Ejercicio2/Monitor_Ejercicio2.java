package ExamenTema2.Ejercicio2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Monitor_Ejercicio2 {
    private boolean r1Disponible = true;
    private boolean r2Disponible = true;
    private boolean r3Disponible = true;

    private final Lock lock = new ReentrantLock();

    public void requiereR1(String threadName) throws InterruptedException {
        lock.lock();
        try {
            while (!r1Disponible) {
                lock.newCondition().await();
            }
            r1Disponible = false;
            System.out.println(threadName + " adquirió R1.");
        } finally {
            lock.unlock();
        }
    }

    public void requiereR2_R3(String threadName) throws InterruptedException {
        lock.lock();
        try {
            while (!r2Disponible || !r3Disponible) {
                lock.newCondition().await();
            }
            r2Disponible = false;
            r3Disponible = false;
            System.out.println(threadName + " adquirió R2 y R3.");
        } finally {
            lock.unlock();
        }
    }

    public void requiereR1_R2_R3(String threadName) throws InterruptedException {
        lock.lock();
        try {
            while (!r1Disponible || !r2Disponible || !r3Disponible) {
                lock.newCondition().await();
            }
            r1Disponible = false;
            r2Disponible = false;
            r3Disponible = false;
            System.out.println(threadName + " adquirió R1, R2 y R3.");
        } finally {
            lock.unlock();
        }
    }

    public void liberaR1(String threadName) {
        lock.lock();
        try {
            r1Disponible = true;
            System.out.println(threadName + " liberó R1.");
            lock.newCondition().signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void liberaR2_R3(String threadName) {
        lock.lock();
        try {
            r2Disponible = true;
            r3Disponible = true;
            System.out.println(threadName + " liberó R2 y R3.");
            lock.newCondition().signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void liberaR1_R2_R3(String threadName) {
        lock.lock();
        try {
            r1Disponible = true;
            r2Disponible = true;
            r3Disponible = true;
            System.out.println(threadName + " liberó R1, R2 y R3.");
            lock.newCondition().signalAll();
        } finally {
            lock.unlock();
        }
    }
}
