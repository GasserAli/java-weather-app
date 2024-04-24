import netscape.javascript.JSObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

//retrieve data from API i.e fetch the latest weather data from the external API and return it
public class WeatherApp {
    //fetch weather data for given location
    //TODO: learn what is the JSONObject and JSONArray
    public static JSONObject getWeatherData(String locationName){
        //get the location coordinated using the geolocation API
        JSONArray locationData= getLocation(locationName);
    }

    private static JSONArray getLocation(String locationName){
        //replace any whitespace in locationName to '+' to adhere to API format
        locationName = locationName.replaceAll(" ","+");

        //build API URL with location parameter
        String urlString = "https://geocoding-api.open-meteo.com/v1/search?name="+locationName+"&count=10&language=en&format=json";

        try{
            //call api and get a response
            //TODO: learn what teh HttpURLConnection object is
            HttpURLConnection conn = fetchApiResponse(urlString);

            //check response status
            //200 means successful connection
            if(conn.getResponseCode() != 200){
                System.out.println("Error: could not connect to API");
                return null;
            }else{
                //store API results
                //TODO: learn what StringBuilder is
                StringBuilder resultJson = new StringBuilder();
                Scanner scanner = new Scanner(conn.getInputStream());

            }

        }catch(Exception e){
            e.printStackTrace();
        }


    }

   private static HttpURLConnection fetchApiResponse(String urlString){
        try {
            //attempt to create a connection
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //set request method to GET
            conn.setRequestMethod("GET");

            //connect to API
            conn.connect();

            return conn;

        }catch(IOException e){
            e.printStackTrace();
        }
        //couldnt make connection
        return null;

   }



}