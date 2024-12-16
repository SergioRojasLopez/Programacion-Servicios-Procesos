package EjerciciosTema2.Tarea3;

import java.util.Random;

class Puente {

    private static final int PESO_MAXIMO = 200;
    private static final int MAX_PERSONAS = 4;
    private static final int MAX_PERSONAS_DIRECCION = 3;

    private int peso = 0;
    private int numPersonas = 0;
    private int personasEnDireccion = 0;
    private String direccionActual = null; // Puede ser "NORTE" o "SUR"

    synchronized public int getPeso() {
        return peso;
    }

    synchronized public int getNumPersonas() {
        return numPersonas;
    }

    synchronized public String getDireccionActual() {
        return direccionActual;
    }

    synchronized public boolean autorizacionPaso(Persona persona) {
        String direccion = persona.getDireccion();
        boolean direccionCambiada = direccionActual != null && !direccionActual.equals(direccion);

        if (!direccionCambiada
                && this.peso + persona.getPeso() <= Puente.PESO_MAXIMO
                && this.numPersonas < Puente.MAX_PERSONAS
                && this.personasEnDireccion < Puente.MAX_PERSONAS_DIRECCION) {
            this.numPersonas++;
            this.personasEnDireccion++;
            this.peso += persona.getPeso();
            this.direccionActual = direccion;
            return true;
        }
        return false;
    }

    synchronized public void terminaPaso(Persona persona) {
        this.peso -= persona.getPeso();
        this.numPersonas--;
        this.personasEnDireccion--;

        // Si no quedan personas en la dirección actual, se libera el puente
        if (this.numPersonas == 0) {
            this.direccionActual = null;
        }
    }
}

class Persona implements Runnable {
    private final Puente puente;

    private final String idPersona;
    private final int peso;
    private final int tMinPaso, tMaxPaso;
    private final String direccion;

    public int getPeso() {
        return peso;
    }

    public String getDireccion() {
        return direccion;
    }

    Persona(Puente puente, int peso, int tMinPaso, int tMaxPaso, String idP, String direccion) {
        this.puente = puente;
        this.peso = peso;
        this.tMinPaso = tMinPaso;
        this.tMaxPaso = tMaxPaso;
        this.idPersona = idP;
        this.direccion = direccion;
    }

    @Override
    public void run() {
        System.out.printf("- %s (%s) de %d kg quiere cruzar, en puente %d kg, %d persona%s, dirección: %s.\n",
                this.idPersona, this.direccion, this.peso, puente.getPeso(), puente.getNumPersonas(),
                puente.getNumPersonas() != 1 ? "s" : "", puente.getDireccionActual());

        // Espera para conseguir autorización
        boolean autorizado = false;
        while (!autorizado) {
            synchronized (this.puente) {
                autorizado = this.puente.autorizacionPaso(this);
                if (!autorizado) {
                    try {
                        System.out.printf("# %s (%s) debe esperar.\n", this.idPersona, this.direccion);
                        this.puente.wait();
                    } catch (InterruptedException ex) {
                        System.out.printf("Interrupción mientras %s espera para cruzar.\n", this.idPersona);
                    }
                }
            }
        }

        System.out.printf("> %s (%s) con peso %d puede cruzar, puente soporta peso %d, con %d personas, dirección: %s.\n",
                this.idPersona, this.direccion, this.getPeso(), this.puente.getPeso(), puente.getNumPersonas(), puente.getDireccionActual());

        // Pasa al puente, y tarda un tiempo en cruzar
        Random r = new Random();
        int tiempoPaso = this.tMinPaso + r.nextInt(this.tMaxPaso - this.tMinPaso + 1);
        try {
            System.out.printf("%s (%s) va a tardar tiempo %d en cruzar.\n", this.idPersona, this.direccion, tiempoPaso);
            Thread.sleep(tiempoPaso * 100);
        } catch (InterruptedException ex) {
            System.out.printf("Interrupción mientras %s pasa.\n", this.idPersona);
        }

        // Sale del puente
        synchronized (this.puente) {
            this.puente.terminaPaso(this);
            System.out.printf("< %s (%s) sale del puente, puente soporta peso %d, %d persona%s, dirección: %s.\n",
                    this.idPersona, this.direccion, this.puente.getPeso(), this.puente.getNumPersonas(),
                    this.puente.getNumPersonas() != 1 ? "s" : "", puente.getDireccionActual());
            puente.notifyAll();
        }
    }
}

public class PasoPorPuente {

    public static void main(String[] args) {

        final Puente puente = new Puente();

        int tMinParaLlegadaPersona = 1;
        int tMaxParaLlegadaPersona = 5; // Simulación más rápida
        int tMinPaso = 1;
        int tMaxPaso = 5;
        int minPesoPersona = 40;
        int maxPesoPersona = 120;

        System.out.println(">>>>>>>>>>>> Comienza simulación.");
        Random r = new Random();
        int idPersona = 1;

        while (true) {
            int tParaLlegadaPersona = tMinParaLlegadaPersona + r.nextInt(
                    tMaxParaLlegadaPersona - tMinParaLlegadaPersona + 1);
            int pesoPersona = minPesoPersona + r.nextInt(
                    maxPesoPersona - minPesoPersona + 1);
            String direccion = r.nextBoolean() ? "NORTE" : "SUR";

            System.out.printf("Siguiente persona llega en %d segundos.\n", tParaLlegadaPersona);

            try {
                Thread.sleep(tParaLlegadaPersona * 100);
            } catch (InterruptedException ex) {
                System.out.printf("Interrumpido proceso principal");
            }

            Thread hiloPersona = new Thread(new Persona(puente, pesoPersona, tMinPaso, tMaxPaso, "P" + idPersona, direccion));
            hiloPersona.start();

            idPersona++;
        }
    }
}
