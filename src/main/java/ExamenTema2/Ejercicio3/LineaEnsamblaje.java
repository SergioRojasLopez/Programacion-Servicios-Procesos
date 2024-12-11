package ExamenTema2.Ejercicio3;

public class LineaEnsamblaje {
    public static void main(String[] args) {
        final int MAX_NUM_PATAS = 20;
        final int MAX_NUM_TABLEROS = 10;
        final int LIMITE_MESAS = 5;

        final Sincronizador sincronizador = new Sincronizador(MAX_NUM_PATAS, MAX_NUM_TABLEROS, LIMITE_MESAS);

        // Crear y lanzar hilos de producción y ensamblaje
        Thread productorPatas1 = new Thread(new ProductorPatas(sincronizador));
        Thread productorPatas2 = new Thread(new ProductorPatas(sincronizador));
        Thread productorTableros = new Thread(new ProductorTableros(sincronizador));
        Thread ensamblador1 = new Thread(new Ensamblador(sincronizador));
        Thread ensamblador2 = new Thread(new Ensamblador(sincronizador));

        productorPatas1.start();
        productorPatas2.start();
        productorTableros.start();
        ensamblador1.start();
        ensamblador2.start();
    }
}

