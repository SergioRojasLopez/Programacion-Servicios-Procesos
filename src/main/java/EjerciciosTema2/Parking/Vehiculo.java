package EjerciciosTema2.Parking;

import java.util.Random;

public class Vehiculo implements Runnable {
    private final String id;
    private final int plazasNecesarias;
    private final ParkingManager parkingManager;

    public Vehiculo(String id, int plazasNecesarias, ParkingManager parkingManager) {
        this.id = id;
        this.plazasNecesarias = plazasNecesarias;
        this.parkingManager = parkingManager;
    }

    @Override
    public void run() {
        try {
            // Tiempo aleatorio circulando
            int tiempoCirculando = new Random().nextInt(10) + 1;
            System.out.println(id + " circulando por " + tiempoCirculando + " segundos.");
            Thread.sleep(tiempoCirculando * 1000L);

            // Intentar entrar al parking
            boolean estacionado = false;
            long startWaitTime = System.currentTimeMillis();

            while (!estacionado) {
                int plaza = parkingManager.buscarPlazasDisponibles(plazasNecesarias);
                if (plaza != -1) {
                    parkingManager.estacionar(id, plaza, plazasNecesarias);
                    estacionado = true;
                }

                if (!estacionado) {
                    if (System.currentTimeMillis() - startWaitTime > 20000) {
                        System.out.println(id + " no encontró plaza y se retira.");
                        return;
                    }
                    Thread.sleep(1000L); // Esperar antes de volver a intentar
                }
            }

            // Tiempo dentro del parking
            int tiempoEstacionado = new Random().nextInt(3) + 1;
            System.out.println(id + " estacionado por " + tiempoEstacionado + " segundos.");
            Thread.sleep(tiempoEstacionado * 1000L);

            // Salir del parking
            parkingManager.liberarPlazas(id, plazasNecesarias);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
