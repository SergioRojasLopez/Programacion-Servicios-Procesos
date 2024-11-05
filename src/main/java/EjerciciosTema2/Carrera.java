package EjerciciosTema2;

public class Carrera {
    public static void main(String[] args) {
        System.out.println("¡Inicia la carrera!");

        // Crear los 5 corredores
        Corredor corredor1 = new Corredor("Corredor 1");
        Corredor corredor2 = new Corredor("Corredor 2");
        Corredor corredor3 = new Corredor("Corredor 3");
        Corredor corredor4 = new Corredor("Corredor 4");
        Corredor corredor5 = new Corredor("Corredor 5");

        // Iniciar los hilos de los corredores
        corredor1.start();
        corredor2.start();
        corredor3.start();
        corredor4.start();
        corredor5.start();

    }
}
