# Conversor_Monedas_ONE

¿Alguna vez te has preguntado cuánto costaría tu café en yenes? ¿O cuánto podrías ahorrar al cambiar tus dólares a euros? Este conversor de monedas, desarrollado como parte del desafío ONE en la ruta Back-end, te permite realizar conversiones de divisas de manera rápida, sencilla y precisa utilizando la API de ExchangeRate.

## ✨ Características

- Selección de monedas base y destino (incluye soporte para monedas personalizadas).
- Conversión de cantidades con base en tasas de cambio actualizadas.
- Visualización de la tasa de cambio entre las monedas seleccionadas.
- Historial de conversiones.
- Manejo seguro de la API Key mediante un archivo `.env`.

## 📋 Requisitos

- [Java 8](https://www.oracle.com/java/technologies/javase-downloads.html) o superior.
- [IntelliJ IDEA](https://www.jetbrains.com/idea/) (opcional, pero recomendado).
- Librería [Gson](https://github.com/google/gson) para procesamiento de JSON.

## 🗂️ Estructura del Proyecto

```
src/
  controlador/
    - Main.java
    - ControladorPrincipal.java
  servicios/
    - ServicioConversor.java
  modelos/
    - [Futuro crecimiento del proyecto]
  utilidades/
    - Configuracion.java
recursos/
  - .env
```

- **controlador/**: Contiene la lógica principal del programa y la interacción con el usuario.
- **servicios/**: Maneja las peticiones a la API de ExchangeRate.
- **utilidades/**: Incluye configuraciones, como la lectura del archivo `.env`.

## ⚙️ Instalación y Configuración

1. **Clona este repositorio**:
   ```bash
   git clone https://github.com/NFabian-Parra/Conversor_Monedas_ONE.git
   cd conversor-monedas
   ```

2. **Agrega tu API Key**:
   - Crea un archivo `.env` en la carpeta `recursos/` con el siguiente contenido:
     ```env
     API_KEY=tu_api_key
     ```
   - Sustituye `tu_api_key` por tu clave obtenida en [ExchangeRate API](https://www.exchangerate-api.com/).

3. **Añade la librería Gson**:
   - Descarga el archivo `gson-<version>.jar` desde [Maven Repository](https://mvnrepository.com/artifact/com.google.code.gson/gson).
   - Inclúyelo como dependencia en tu proyecto (en IntelliJ IDEA: *File > Project Structure > Libraries*).

4. **Ejecuta el programa**:
   - Abre la clase `Main.java` y ejecuta el método `main`.

## 🛠️ Uso

1. Selecciona la moneda base y la moneda de destino.
2. Ingresa la cantidad a convertir.
3. Observa el resultado y la tasa de cambio utilizada.
4. Opcionalmente, revisa el historial de conversiones realizadas.

## 📝 Ejemplo de Salida

```
Bienvenido al conversor de monedas.
Por favor, elige una opción:
1. Peso colombiano (COP)
2. Dólar estadounidense (USD)
3. EURO (EUR)
4. Libra esterlina (GBP)
5. Yen japonés (JPY)
6. Peso mexicano (MXN)
7. Ingresa una moneda diferente.

Selecciona la moneda base:
2

Tu moneda base es: USD
Selecciona la moneda destino:
3

Ingresa la cantidad a convertir: 
100

¡Listo! Has convertido 100.00 USD a 92.34 EUR.
La tasa de cambio actual es 1 USD = 0.9234 EUR.

¿Deseas realizar otra conversión?
1. Sí
2. Ver historial de conversiones
3. Salir
```

## 🤝 Contribuciones

¡Las contribuciones son bienvenidas! Si deseas mejorar este proyecto, realiza un *fork*, crea una nueva rama para tus cambios, y envía un *pull request*.

1. Realiza un *fork* del repositorio.
2. Crea una nueva rama:
   ```bash
   git checkout -b feature/nueva-funcionalidad
   ```
3. Realiza tus cambios y haz un *commit*:
   ```bash
   git commit -m "Agrega nueva funcionalidad"
   ```
4. Sube tus cambios:
   ```bash
   git push origin feature/nueva-funcionalidad
   ```
5. Crea un *pull request*.

## 📜 Licencia

Este proyecto está bajo la licencia MIT. Consulta el archivo `LICENSE` para más información.

---

¡Gracias por usar el Conversor de Monedas! Si tienes preguntas o problemas, no dudes en crear un *issue* en el repositorio.

