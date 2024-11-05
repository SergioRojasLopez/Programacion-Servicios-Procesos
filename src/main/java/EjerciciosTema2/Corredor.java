package EjerciciosTema2;

public class Corredor extends Thread {
    private String nombre;

    public Corredor(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            try {
                // Tiempo aleatorio de espera entre tramos (100 a 500 ms)
                int tiempoEspera = (int) (Math.random() * 400) + 100;
                Thread.sleep(tiempoEspera);

                // Imprimir el avance del corredor
                System.out.println(nombre + " completó el tramo " + i);
            } catch (InterruptedException e) {
                System.out.println(nombre + " fue interrumpido.");
            }
        }
        System.out.println(nombre + " ha terminado la carrera!");
    }
}
