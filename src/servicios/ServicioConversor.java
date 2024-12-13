package servicios;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import modelos.TasaCambio;
import utilidades.Configuracion;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class ServicioConversor {

    private final String API_KEY;
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/%s/latest/%s"; // URL de la API

    public ServicioConversor() {
        this.API_KEY = Configuracion.obtenerVariable("API_KEY");
    }

    public double convertirMoneda(String monedaBase, String monedaDestino, double cantidad) {
        try {
            // Validar que las monedas estén en mayúsculas
            monedaBase = monedaBase.toUpperCase();
            monedaDestino = monedaDestino.toUpperCase();

            // Construimos la URL de la API con la moneda base
            String urlStr = String.format(API_URL, API_KEY, monedaBase);
            URL url = new URL(urlStr);

            // Realizamos la solicitud HTTP GET
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("GET");
            conexion.setConnectTimeout(10000);
            conexion.setReadTimeout(10000);

            int respuestaCode = conexion.getResponseCode();
            if (respuestaCode != 200) {
                System.out.println("Error al conectar con la API. Código de respuesta: " + respuestaCode);
                return -1;
            }

            // Leemos y procesamos la respuesta JSON
            InputStreamReader reader = new InputStreamReader(conexion.getInputStream());
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            JsonObject conversionRates = jsonObject.getAsJsonObject("conversion_rates");

            // Validar que las monedas estén disponibles
            if (!conversionRates.has(monedaDestino)) {
                System.out.println("Error: La moneda destino no es válida o no está disponible.");
                return -1;
            }

            // Obtener la tasa de cambio y realizar la conversión
            double tasaCambio = conversionRates.get(monedaDestino).getAsDouble();
            double resultado = cantidad * tasaCambio;

            System.out.printf("Convirtiendo %.2f %s a %s: %.2f %s%n",
                    cantidad, monedaBase, monedaDestino, resultado, monedaDestino);

            return resultado;

        } catch (Exception e) {
            System.err.println("Error al realizar la conversión: " + e.getMessage());
            return -1;
        }
    }

}

