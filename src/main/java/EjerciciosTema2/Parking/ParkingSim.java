package EjerciciosTema2.Parking;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParkingSim {
    private static final int TOTAL_PLAZAS = 10;
    private static final int TOTAL_COCHES = 40;
    private static final int TOTAL_FURGONETAS = 10;

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        ParkingManager parkingManager = new ParkingManager(TOTAL_PLAZAS);

        for (int i = 0; i < TOTAL_COCHES; i++) {
            executor.execute(new Vehiculo("Coche" + i, 1, parkingManager));
        }

        for (int i = 0; i < TOTAL_FURGONETAS; i++) {
            executor.execute(new Vehiculo("Furgoneta" + i, 2, parkingManager));
        }

        executor.shutdown();
    }
}
