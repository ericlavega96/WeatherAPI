package servicios;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;

public class JsonTransformer {
    private static Gson gson = new GsonBuilder().serializeNulls().create();

    public static String toJson(Object object) {
        return gson.toJson(object);
    }

    public static Map<String, Object> jsonToMap(String json) {
        Map<String, Object> map = gson.fromJson(json, new TypeToken<HashMap<String, Object>>() {
        }.getType());

        return map;
    }

    public static JsonObject stringToJson(String jsonString) {
        return new JsonParser().parse(jsonString).getAsJsonObject();
    }


}