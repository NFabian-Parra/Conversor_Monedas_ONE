package utilidades;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Configuracion {
    private static final String RUTA_ENV = "recursos/.env";
    private static final Map<String, String> variables = new HashMap<>();

    // Cargar el archivo .env una sola vez
    static {
        try {
            Files.lines(Paths.get(RUTA_ENV))
                    .filter(linea -> linea.contains("=") && !linea.startsWith("#")) // Ignorar comentarios
                    .forEach(linea -> {
                        String[] partes = linea.split("=", 2);
                        variables.put(partes[0].trim(), partes[1].trim());
                    });
        } catch (IOException e) {
            System.err.println("Error al cargar el archivo .env: " + e.getMessage());
        }
    }

    // Método para obtener una variable por su nombre
    public static String obtenerVariable(String nombre) {
        return variables.getOrDefault(nombre, "");
    }
}
