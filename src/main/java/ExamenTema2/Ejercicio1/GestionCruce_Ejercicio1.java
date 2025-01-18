package ExamenTema2.Ejercicio1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GestionCruce_Ejercicio1 {
    private boolean semaforoNorteVerde = true;
    private boolean semaforoOesteVerde = false;

    private final Lock lock = new ReentrantLock();
    private final Condition norteCondition = lock.newCondition();
    private final Condition oesteCondition = lock.newCondition();

    public void llegaNorte() throws InterruptedException {
        lock.lock();
        try {
            while (!semaforoNorteVerde) {
                norteCondition.await();
            }
            System.out.println("Coche del norte pasa el cruce.");
        } finally {
            lock.unlock();
        }
    }

    public void llegaOeste() throws InterruptedException {
        lock.lock();
        try {
            while (!semaforoOesteVerde) {
                oesteCondition.await();
            }
            System.out.println("Coche del oeste pasa el cruce.");
        } finally {
            lock.unlock();
        }
    }

    public void sale() {
        lock.lock();
        try {
            System.out.println("Un coche ha salido del cruce.");
        } finally {
            lock.unlock();
        }
    }

    public void cambiaSemaforos() {
        lock.lock();
        try {
            semaforoNorteVerde = !semaforoNorteVerde;
            semaforoOesteVerde = !semaforoOesteVerde;
            if (semaforoNorteVerde) {
                norteCondition.signalAll();
                System.out.println("Semáforo norte en verde.");
            } else {
                oesteCondition.signalAll();
                System.out.println("Semáforo oeste en verde.");
            }
        } finally {
            lock.unlock();
        }
    }

}
