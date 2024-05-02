import netscape.javascript.JSObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

//retrieve data from API i.e fetch the latest weather data from the external API and return it
public class WeatherApp {
    //fetch weather data for given location
    //TODO: learn what is the JSONObject and JSONArray
    public static JSONObject getWeatherData(String locationName){
        //get the location coordinated using the geolocation API
        JSONArray locationData= getLocation(locationName);

        //extract longitude and latitude
        JSONObject location = (JSONObject) locationData.get(0);
        double latitude = (double) location.get("latitude");
        double longitude = (double) location.get("longitude");

        //build API request URL with location coordinates
        String urlString = "https://api.open-meteo.com/v1/forecast?" +
                "latitude="+ latitude + "&longitude=" +longitude+
                "&hourly=temperature_2m,relative_humidity_2m,weather_code,wind_speed_10m&timezone=Africa%2FCairo";

        try{
            //call api and get response
            HttpURLConnection conn = fetchApiResponse(urlString);

            //check for response status
            if(conn.getResponseCode() != 200){
                System.out.println("Error: Couldn't connect to API");
                return null;

            }

            //store API result
            StringBuilder resultJson = new StringBuilder();
            Scanner scanner = new Scanner(conn.getInputStream());
            while(scanner.hasNext()){
                //read and store into string builder
                resultJson.append(scanner.nextLine());
            }

            //close scanner
            scanner.close();

            //close URL connection
            conn.disconnect();

            //parse the data
            JSONParser parser = new JSONParser();
            JSONObject resultJsonObj = (JSONObject) parser.parse(String.valueOf(resultJson));

            //retrieve hourly data
            JSONObject hourly = (JSONObject) resultJsonObj.get("hourly");

            //get the index of the current hour
            JSONArray time = (JSONArray) hourly.get("time");
            int index = findIndexOfCurrentTime(time);

            JSONArray temperatureData = (JSONArray) hourly.get("temperature_2m");
            double temperature = (double) temperatureData.get(index);

            //get weather code
            JSONArray weatherCode = (JSONArray) hourly.get("weather_code");
            String weatherCondition = convertWeatherCode((long) weatherCode.get(index));

            //get humidity
            JSONArray relativeHumidity = (JSONArray) hourly.get("relative_humidity_2m");
            long humidity = (long) relativeHumidity.get(index);

            //get windspeed
            JSONArray wind_Speed = (JSONArray) hourly.get("wind_speed_10m");
            double windspeed = (double) wind_Speed.get(index);

            //build the weather json data object that we are going to use in the frontend
            JSONObject weatherData = new JSONObject();
            weatherData.put("temperature: ",temperature);
            weatherData.put("weather_condition:", weatherCondition);
            weatherData.put("humidity:",humidity);
            weatherData.put("windspeed:",windspeed);

            return weatherData;

        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    private static int findIndexOfCurrentTime(JSONArray timeList) {
        String currentTime = getCurrentTime();

        //find the index that matches our current time from timelist
        for(int i = 0; i<timeList.size(); i++){
            String time = (String) timeList.get(i);
            if(time.equals(currentTime)){
                return i;
            }
        }
        return 0;
    }

     public static String getCurrentTime(){
        //gets the current time
        LocalDateTime currentDateTime = LocalDateTime.now();

        //format date to be YYYY-MM-DDTHH:MM to match the api result format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH':00'");

        //format and print current date and time
        String formattedDateTime = currentDateTime.format(formatter);

        return formattedDateTime;
    }

    public static JSONArray getLocation(String locationName){
        //replace any whitespace in locationName to '+' to adhere to API format
        locationName = locationName.replaceAll(" ","+");

        //build API URL with location parameter
        String urlString = "https://geocoding-api.open-meteo.com/v1/search?name="+locationName+"&count=10&language=en&format=json";

        try{
            //call api and get a response
            //TODO: learn what the HttpURLConnection object is
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
                while (scanner.hasNext()) {
                    resultJson.append(scanner.nextLine());
                }
                scanner.close();
                conn.disconnect();

                //parse the JSON string into a JSON obj
                JSONParser parser = new JSONParser();
                JSONObject resultsJSONobj = (JSONObject)  parser.parse(String.valueOf(resultJson));

                //get the list of location data the API generated from the location name
                JSONArray locationData = (JSONArray) resultsJSONobj.get("results");
                return locationData;

            }

        }catch(Exception e){
            e.printStackTrace();
        }

        //couldn't find location
        return null;

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

   //convert weather code to something readable
   private static String convertWeatherCode(long weatherCode){
        String weatherCondition = "";
        if( weatherCode == 0L ){
            weatherCondition = "Clear";
        } else if (weatherCode<=3L && weatherCode > 0l) {
            weatherCondition = "Cloudy";
        } else if ((weatherCode >= 51L && weatherCode <= 67L) || (weatherCode >= 80L && weatherCode <= 99L)){
            weatherCondition = "Rain";
        } else if(weatherCode >= 71L && weatherCode <= 77L){
            weatherCondition = "Snow";
        }
        return weatherCondition;
   }

}