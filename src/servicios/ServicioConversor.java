package servicios;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import utilidades.Configuracion;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServicioConversor {

    private final String API_KEY;
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/%s/latest/%s"; // URL de la API

    public ServicioConversor() {
        this.API_KEY = Configuracion.obtenerVariable("API_KEY");
    }

    /**
     * Convierte una cantidad de una moneda base a una moneda destino.
     * @param monedaBase:  La moneda de origen.
     * @param monedaDestino:  La moneda de destino.
     * @param cantidad: La cantidad a convertir, debe ser positiva.
     * @return La cantidad convertida en la moneda destino.
     * @throws ConversionException Si ocurre un error durante la conversión.
     */

    public double convertirMoneda(String monedaBase, String monedaDestino, double cantidad) throws ConversionException {
        if (cantidad < 0) {
            throw new ConversionException("La cantidad debe ser un número positivo.");
        }
        try {
            // Validar que las monedas estén en mayúsculas
            monedaBase = monedaBase.toUpperCase();
            monedaDestino = monedaDestino.toUpperCase();

            // Construir y realizar la solicitud HTTP GET
            JsonObject conversionRates = obtenerConversionRates(monedaBase);

            // Validar que las monedas estén disponibles
            if (!conversionRates.has(monedaDestino)) {
                throw new ConversionException("La moneda destino no es válida o no está disponible.");
            }

            // Obtener la tasa de cambio y realizar la conversión
            double tasaCambio = conversionRates.get(monedaDestino).getAsDouble();
            return cantidad * tasaCambio;

        } catch (Exception e) {
            throw new ConversionException("Error al realizar la conversión: " + e.getMessage(), e);
        }
    }

    public double obtenerTasaCambio(String monedaBase, String monedaDestino) throws ConversionException {
        try {
            // Validar que las monedas estén en mayúsculas
            monedaBase = monedaBase.toUpperCase();
            monedaDestino = monedaDestino.toUpperCase();

            // Construir y realizar la solicitud HTTP GET
            JsonObject conversionRates = obtenerConversionRates(monedaBase);

            // Validar que las monedas estén disponibles
            if (!conversionRates.has(monedaDestino)) {
                throw new ConversionException("La moneda destino no es válida o no está disponible.");
            }

            // Obtener la tasa de cambio entre las monedas
            return conversionRates.get(monedaDestino).getAsDouble();

        } catch (Exception e) {
            throw new ConversionException("Error al obtener la tasa de cambio: " + e.getMessage(), e);
        }
    }

    private JsonObject obtenerConversionRates(String monedaBase) throws Exception {
        // Construimos la URL de la API con la moneda base
        String urlStr = String.format(API_URL, API_KEY, monedaBase);
        URL url = new URL(urlStr);

        // Realizamos la solicitud HTTP GET
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.setRequestMethod("GET");
        conexion.setConnectTimeout(10000);
        conexion.setReadTimeout(10000);

        int respuestaCode = conexion.getResponseCode();
        if (respuestaCode != HttpURLConnection.HTTP_OK) {
            throw new Exception("Error al conectar con la API. Código de respuesta: " + respuestaCode);
        }

        // Leemos y procesamos la respuesta JSON
        InputStreamReader reader = new InputStreamReader(conexion.getInputStream());
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
        return jsonObject.getAsJsonObject("conversion_rates");
    }
}


