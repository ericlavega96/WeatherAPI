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
            String weatherJSON = WeatherAPI.getInstancia().getWeatherData(19.22,-70.53);
            WeatherAPI.getInstancia().printWeatherData(weatherJSON);
            attributes.put("clima", WeatherAPI.getInstancia().getWeatherObject(weatherJSON));
            return new ModelAndView(attributes,"weatherAPI.html");
        }, freeMarkerEngine);

    }
}
