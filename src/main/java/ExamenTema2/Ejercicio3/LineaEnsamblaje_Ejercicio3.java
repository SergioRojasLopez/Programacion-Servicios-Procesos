package ExamenTema2.Ejercicio3;

public class LineaEnsamblaje_Ejercicio3 {
    public static void main(String[] args) {
        final int MAX_NUM_PATAS = 20;
        final int MAX_NUM_TABLEROS = 10;
        final int LIMITE_MESAS = 5;

        final Sincronizador_Ejercicio3 sincronizador = new Sincronizador_Ejercicio3(MAX_NUM_PATAS, MAX_NUM_TABLEROS, LIMITE_MESAS);

        // Crear y lanzar hilos de producción y ensamblaje
        Thread productorPatas1 = new Thread(new ProductorPatas_Ejercicio3(sincronizador));
        Thread productorPatas2 = new Thread(new ProductorPatas_Ejercicio3(sincronizador));
        Thread productorTableros = new Thread(new ProductorTableros_Ejercicio3(sincronizador));
        Thread ensamblador1 = new Thread(new Ensamblador_Ejercicio3(sincronizador));
        Thread ensamblador2 = new Thread(new Ensamblador_Ejercicio3(sincronizador));

        productorPatas1.start();
        productorPatas2.start();
        productorTableros.start();
        ensamblador1.start();
        ensamblador2.start();
    }
}

