package ExamenTema2.Ejercicio2;

public class GestionRecursos_Ejercicio2 {
    public static void main(String[] args) {
        Monitor_Ejercicio2 monitor = new Monitor_Ejercicio2();

        new Thread(new Tarea_Ejercicio2(monitor, "T1", 1)).start();
        new Thread(new Tarea_Ejercicio2(monitor, "T2", 2)).start();
        new Thread(new Tarea_Ejercicio2(monitor, "T3", 3)).start();
    }
}
