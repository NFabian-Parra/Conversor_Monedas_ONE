package controlador;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Bienvenido al Conversor de Monedas");
        ControladorPrincipal controlador = new ControladorPrincipal();
        controlador.iniciar();
    }
}
