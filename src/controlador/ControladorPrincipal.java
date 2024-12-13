package controlador;

import servicios.ServicioConversor;

import java.io.IOException;
import java.util.Scanner;

public class ControladorPrincipal {
    private final ServicioConversor servicioConversor;

    public ControladorPrincipal() {
        this.servicioConversor = new ServicioConversor();
    }

    public void iniciar() throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingresa la moneda base (Ejemplo: USD, EUR): ");
        String monedaBase = scanner.nextLine();

        // Solicitar moneda destino
        System.out.print("Ingresa la moneda destino (Ejemplo: MXN, JPY): ");
        String monedaDestino = scanner.nextLine();

        // Solicitar cantidad a convertir
        System.out.print("Ingresa la cantidad a convertir: ");
        double cantidad = scanner.nextDouble();

        // Realizar la conversión
        double resultado = servicioConversor.convertirMoneda(monedaBase, monedaDestino, cantidad);

        if (resultado != -1) {
            System.out.printf("Resultado: %.2f %s%n", resultado, monedaDestino);
        } else {
            System.out.println("Hubo un problema al realizar la conversión.");
        }
    }
}

