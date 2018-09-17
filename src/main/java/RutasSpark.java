import freemarker.template.Configuration;
import servicios.WeatherAPI;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.HashMap;
import java.util.Map;
import logical.Clima;

import static spark.Spark.*;

public class RutasSpark {
    public void iniciarSpark(){
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_0);
        cfg.setClassForTemplateLoading(Main.class, "/templates");
        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine(cfg);


        get("/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            return new ModelAndView(attributes,"Opciones.html");
        }, freeMarkerEngine);

        post("/busqueda/ciudad", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            String pais = request.queryParams("pais");
            String ciudad = request.queryParams("ciudad");

            String weatherJSON = WeatherAPI.getInstancia().getWeatherData(pais,ciudad);
            WeatherAPI.getInstancia().printWeatherData(weatherJSON);
            attributes.put("clima", WeatherAPI.getInstancia().getWeatherObject(weatherJSON));
            return new ModelAndView(attributes,"WeatherAPI.html");
        }, freeMarkerEngine);

        post("busqueda/:lat/:lon", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            Double latitud = Double.valueOf(request.params("lat"));
            Double longitud = Double.valueOf(request.params("lon"));
            //String weatherJSON = WeatherAPI.getInstancia().getWeatherData(19.22,-70.53);
            System.out.println("Latitud:" +latitud + " Longitud: " + longitud );

            String weatherJSON = WeatherAPI.getInstancia().getWeatherData(latitud,longitud);
            //WeatherAPI.getInstancia().printWeatherData(weatherJSON);
            attributes.put("clima", WeatherAPI.getInstancia().getWeatherObject(weatherJSON));
            return new ModelAndView(attributes,"WeatherAPI.html");
        }, freeMarkerEngine);




    }
}
