import static spark.Spark.staticFiles;

public class Main {

    public static void main(String[] args) {
        staticFiles.location("/templates");
        new RutasSpark().iniciarSpark();
    }

}
