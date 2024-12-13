package modelos;

import java.util.Map;

public class TasaCambio {
    private String result;
    private String baseCode;
    private Map<String, Double> conversionRates;

    // Constructor con parámetros
    public TasaCambio(String baseCode, Map<String, Double> conversionRates) {
        this.baseCode = baseCode;
        this.conversionRates = conversionRates;
    }

    // Getters y Setters
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getBaseCode() {
        return baseCode;
    }

    public void setBaseCode(String baseCode) {
        this.baseCode = baseCode;
    }

    public Map<String, Double> getConversionRates() {
        return conversionRates;
    }

    public void setConversionRates(Map<String, Double> conversionRates) {
        this.conversionRates = conversionRates;
    }
}



