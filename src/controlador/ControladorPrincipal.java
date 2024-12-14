package controlador;

import servicios.ServicioConversor;
import servicios.ConversionException;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.util.Scanner;

public class ControladorPrincipal {
    private final ServicioConversor servicioConversor;
    private final List<String> historialConversiones;

    public ControladorPrincipal() {
        this.servicioConversor = new ServicioConversor();
        this.historialConversiones = new ArrayList<>();
    }

    public void iniciar() throws IOException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Bienvenido al conversor de monedas.");
            System.out.println("Por favor, elige una opción:");
            mostrarMenuMonedas();

            // Elegir moneda base
            String monedaBase = obtenerMoneda(scanner, "base");
            if (monedaBase == null) continue; // Si la opción es incorrecta, reiniciar

            // Mostrar la moneda base seleccionada antes de elegir la moneda destino
            System.out.println("Tu moneda base es: " + monedaBase);

            // Elegir moneda destino
            String monedaDestino = obtenerMoneda(scanner, "destino");
            if (monedaDestino == null) continue; // Si la opción es incorrecta, reiniciar

            // Pedir la cantidad a convertir
            System.out.print("Ingresa la cantidad a convertir: ");
            double cantidad = scanner.nextDouble();

            try {
                // Realizar la conversión
                double resultado = servicioConversor.convertirMoneda(monedaBase, monedaDestino, cantidad);

                // Presentación de la conversión
                String conversion = String.format("¡Listo! Has convertido %.2f %s a %.2f %s.\n" +
                                "La tasa de cambio actual es 1 %s = %.2f %s.",
                        cantidad, monedaBase, resultado, monedaDestino,
                        monedaBase, servicioConversor.obtenerTasaCambio(monedaBase, monedaDestino), monedaDestino);
                historialConversiones.add(conversion);
                System.out.println(conversion);
            } catch (ConversionException e) {
                System.err.println("Hubo un problema al realizar la conversión: " + e.getMessage());
            }

            // Preguntar si desea hacer otra conversión o ver historial
            while (true) {
                System.out.println("¿Deseas realizar otra conversión?");
                System.out.println("1. Sí");
                System.out.println("2. Ver historial de conversiones");
                System.out.println("3. Salir");

                int opcion = scanner.nextInt();

                if (opcion == 1) {
                    break; // Continuar con una nueva conversión
                } else if (opcion == 2) {
                    mostrarHistorial();
                } else if (opcion == 3) {
                    System.out.println("Gracias por usar el conversor de monedas. ¡Hasta luego!");
                    return; // Salir del programa
                } else {
                    System.out.println("Opción incorrecta, por favor intente nuevamente.");
                }
            }
        }
    }

    // Mostrar menú de monedas
    private void mostrarMenuMonedas() {
        System.out.println("1. Peso colombiano (COP)");
        System.out.println("2. Dólar estadounidense (USD)");
        System.out.println("3. EURO (EUR)");
        System.out.println("4. Libra esterlina (GBP)");
        System.out.println("5. Yen japonés (JPY)");
        System.out.println("6. Peso mexicano (MXN)");
        System.out.println("7. Ingresa una moneda diferente.");
    }

    // Obtener moneda base o destino
    private String obtenerMoneda(Scanner scanner, String tipoMoneda) {
        System.out.printf("Selecciona la moneda %s:\n", tipoMoneda);

        // Repetir hasta que la opción sea válida
        while (true) {
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1: return "COP"; // Peso colombiano
                case 2: return "USD"; // Dólar estadounidense
                case 3: return "EUR"; // EURO
                case 4: return "GBP"; // Libra esterlina
                case 5: return "JPY"; // Yen japonés
                case 6: return "MXN"; // Peso mexicano
                case 7:
                    scanner.nextLine();  // Limpiar el buffer
                    System.out.print("Ingresa la moneda que deseas utilizar: ");
                    return scanner.nextLine().toUpperCase();
                default:
                    System.out.println("Carácter erróneo, por favor vuelva a escoger una opción.");
            }
        }
    }

    // Mostrar el historial de conversiones
    private void mostrarHistorial() {
        if (historialConversiones.isEmpty()) {
            System.out.println("No se han realizado conversiones.");
        } else {
            System.out.println("Historial de conversiones:");
            for (String conversion : historialConversiones) {
                System.out.println(conversion);
            }
        }
    }
}


