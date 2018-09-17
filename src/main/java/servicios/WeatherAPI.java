package servicios;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import com.google.gson.JsonObject;
import logical.Clima;

public class WeatherAPI {
    private static final String KEY = "b4063d867ac478a1b9803bafe6be5e74";
    private static WeatherAPI instancia;

    public static WeatherAPI getInstancia(){
        if(instancia == null){
            instancia = new WeatherAPI();
        }
        return instancia;
    }

    public String getWeatherData(String pais, String ciudad) {
        String weatherAPIURL = "http://api.openweathermap.org/data/2.5/weather?q=" + ciudad + "," + pais + "&units=metric&appid=" + KEY;
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(weatherAPIURL);
            URLConnection conn = url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String weatherData;
            while ((weatherData = br.readLine()) != null){
                result.append(weatherData);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public String getWeatherData(Double lat, Double lon) {
        String weatherAPIURL = "http://api.openweathermap.org/data/2.5/weather?lat=" + lat.toString() + "&lon=" + lon.toString() + "&units=metric&appid=" + KEY;
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(weatherAPIURL);
            URLConnection conn = url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String weatherData;
            while ((weatherData = br.readLine()) != null){
                result.append(weatherData);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public void printWeatherData(String json){
        //Map<String,Object> resultMap = JsonTransformer.jsonToMap(json);
        //Map<String,Object> mainMap = JsonTransformer.jsonToMap(resultMap.get("main").toString());
        //Map<String,Object> countryMap = JsonTransformer.jsonToMap(resultMap.get("sys").toString());
        //System.out.println(resultMap);

        //System.out.println("Localización: " + countryMap.get("country").toString() + ", " + resultMap.get("name"));
        //System.out.println("Temperatura actual: " + mainMap.get("temp") + " °C");
        //System.out.println("Temperatura mínima: " + mainMap.get("temp_min") + " °C");
        //System.out.println("Temperatura máxima: " + mainMap.get("temp_max") + " °C");
        //System.out.println("Humedad actual: " + mainMap.get("humidity") + " %");
        //System.out.println("Tiempo: " + weatherMap.get("main"));
        //System.out.println("Icono: " + weatherMap.get("icon"));

        JsonObject resultJson = JsonTransformer.stringToJson(json);
        JsonObject mainJson = resultJson.get("main").getAsJsonObject();
        JsonObject weatherJson = resultJson.getAsJsonArray("weather").get(0).getAsJsonObject();


        System.out.println("Localización: " + resultJson.get("sys").getAsJsonObject().get("country").getAsString() + ", " + resultJson.get("name").getAsString());
        System.out.println("Temperatura actual: " + mainJson.get("temp").getAsString() + " °C");
        System.out.println("Temperatura mínima: " + mainJson.get("temp_min").getAsString() + " °C");
        System.out.println("Temperatura máxima: " + mainJson.get("temp_max").getAsString() + " °C");
        System.out.println("Humedad actual: " + mainJson.get("humidity") + " %");
        System.out.println("Tiempo: " + weatherJson.get("main").getAsString());
        System.out.println("Icono: " + weatherJson.get("icon").getAsString());

    }

    public Clima getWeatherObject(String json){
        /*
        Map<String,Object> weatherAPIMap = JsonTransformer.jsonToMap(json);
        Map<String,Object> mainMap = JsonTransformer.jsonToMap(weatherAPIMap.get("main").toString());
        Map<String,Object> countryMap = JsonTransformer.jsonToMap(weatherAPIMap.get("sys").toString());
        Map<String,Object> weatherMap = JsonTransformer.jsonToMap(weatherAPIMap.get("weather").toString());

        String pais = countryMap.get("country").toString();
        String ciudad = mainMap.get("name").toString();
        Double tempActual = Double.valueOf(mainMap.get("temp").toString());
        Double tempMax = Double.valueOf(mainMap.get("temp_max").toString());
        Double tempMin = Double.valueOf(mainMap.get("temp_min").toString());
        Double humidity = Double.valueOf(mainMap.get("humidity").toString());
        String tiempo = weatherMap.get("main").toString();
        String icono = weatherMap.get("icon").toString();
        */

        JsonObject resultJson = JsonTransformer.stringToJson(json);
        JsonObject mainJson = resultJson.get("main").getAsJsonObject();
        JsonObject weatherJson = resultJson.getAsJsonArray("weather").get(0).getAsJsonObject();

        String pais = resultJson.get("sys").getAsJsonObject().get("country").getAsString();
        String ciudad = resultJson.get("name").getAsString();
        Double tempActual = mainJson.get("temp").getAsDouble();
        Double tempMax = mainJson.get("temp_max").getAsDouble();
        Double tempMin = mainJson.get("temp_min").getAsDouble();
        Double humidity = mainJson.get("humidity").getAsDouble();
        String tiempo = weatherJson.get("main").getAsString();
        String icono = weatherJson.get("icon").getAsString();


        Clima clima = new Clima(pais,ciudad,tempActual,tempMax,tempMin,humidity,tiempo,icono);
        return clima;
    }

}




