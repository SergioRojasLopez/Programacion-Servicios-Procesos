package EjerciciosTema2.Parking;

public class ParkingManager {
    private final String[] parking;
    private final Object lock = new Object();

    public ParkingManager(int totalPlazas) {
        this.parking = new String[totalPlazas];
    }

    public int buscarPlazasDisponibles(int plazasNecesarias) {
        synchronized (lock) {
            if (plazasNecesarias == 1) {
                for (int i = 0; i < parking.length; i++) {
                    if (parking[i] == null) {
                        return i;
                    }
                }
            } else {
                for (int i = 0; i < parking.length - 1; i++) {
                    if (parking[i] == null && parking[i + 1] == null && i % 2 == 0) {
                        return i;
                    }
                }
            }
            return -1;
        }
    }

    public void estacionar(String id, int plaza, int plazasNecesarias) {
        synchronized (lock) {
            if (plazasNecesarias == 1) {
                parking[plaza] = id;
                System.out.println(id + " ocupó la plaza " + plaza + ".");
            } else {
                parking[plaza] = id;
                parking[plaza + 1] = id;
                System.out.println(id + " ocupó las plazas " + plaza + " y " + (plaza + 1) + ".");
            }
        }
    }

    public void liberarPlazas(String id, int plazasNecesarias) {
        synchronized (lock) {
            if (plazasNecesarias == 1) {
                for (int i = 0; i < parking.length; i++) {
                    if (id.equals(parking[i])) {
                        parking[i] = null;
                        System.out.println(id + " liberó la plaza " + i + ".");
                        break;
                    }
                }
            } else {
                for (int i = 0; i < parking.length - 1; i++) {
                    if (id.equals(parking[i]) && id.equals(parking[i + 1])) {
                        parking[i] = null;
                        parking[i + 1] = null;
                        System.out.println(id + " liberó las plazas " + i + " y " + (i + 1) + ".");
                        break;
                    }
                }
            }
        }
    }
}
