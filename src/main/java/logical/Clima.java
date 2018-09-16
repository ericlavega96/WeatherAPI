package logical;

public class Clima {
    private String pais;
    private String ciudad;
    private Double tempActual;
    private Double tempMax;
    private Double tempMin;
    private Double humedad;
    private String tiempo;
    private String icono;

    public Clima() {

    }

    public Clima(String pais, String ciudad, Double tempActual, Double tempMax, Double tempMin, Double humedad,String tiempo,String icono) {
        this.pais = pais;
        this.ciudad = ciudad;
        this.tempActual = tempActual;
        this.tempMax = tempMax;
        this.tempMin = tempMin;
        this.humedad = humedad;
        this.tiempo = tiempo;
        this.icono = icono;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Double getTempActual() {
        return tempActual;
    }

    public void setTempActual(Double tempActual) {
        this.tempActual = tempActual;
    }

    public Double getTempMax() {
        return tempMax;
    }

    public void setTempMax(Double tempMax) {
        this.tempMax = tempMax;
    }

    public Double getTempMin() {
        return tempMin;
    }

    public void setTempMin(Double tempMin) {
        this.tempMin = tempMin;
    }

    public Double getHumedad() {
        return humedad;
    }

    public void setHumedad(Double humedad) {
        this.humedad = humedad;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }
}
