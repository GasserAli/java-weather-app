<h1>Weather App GUI</h1>
<h2>Introduction</h2>
<p>

The Weather App, developed in Java, functions as a platform offering users up-to-date weather data for designated locations. Utilizing an external API, it retrieves weather information and showcases it through a 
user-friendly graphical interface (GUI). Users input their desired location, prompting the app to fetch and display weather details such as temperature, current weather conditions, humidity levels, and wind speed.
This documentation shows the project's architecture, employed technologies, and the roles and functionalities of each class constituting the application.
</p>

<p align="center">
    <img src="https://github.com/GasserAli/java-weather-app/blob/master/images%20for%20github%201.png" align="left">
     <img src="https://github.com/GasserAli/java-weather-app/blob/master/images%20for%20github%203.png" align="center">
</p>

<h2>Technologies Used</h2>
<p>
    The Weather App utilizes the following technologies and libraries:
</p>
<ul>
  <li>Java 18</li>
  <li><a href="https://code.google.com/archive/p/json-simple/downloads">JSON Simple</a> - Used to parse and read through JSON data</li>
  <li><a href="https://docs.oracle.com/en/java/javase/11/docs/api/java.net/java/net/HttpURLConnection.html">HTTPURLConnection</a>: Java's built-in library for making HTTP requests to fetch data from external APIs.</li>
  <li><a href="https://open-meteo.com/en/docs">OpenMeteo API</a>: Open source weather forecast API</li>
</ul>

<h2>Class Summaries</h2>

<h3>3.1. AppLauncher</h3>
<p>
    <strong>Description:</strong> 
The AppLauncher class acts as the starting point for the Weather App. Its primary function is to set up the graphical user interface (GUI) and present the main application window to the user.
</p>

<h3>3.2. WeatherAppGui</h3>
<p>
    <strong>Description:</strong> The WeatherAppGui class represents the graphical user interface (GUI) of the Weather App. It is responsible for displaying weather information for the prompted location.
</p>
<p>
    <strong>Summary:</strong> This class handles the layout and display of GUI components, including text fields, labels, buttons, and images. It also implements the user interface for entering a location and updating the weather information based on user input.
</p>

<h3>3.3. WeatherApp</h3>
<p>
    <strong>Description:</strong> The WeatherApp class contains the backend logic for fetching weather data from an external API. It retrieves geographic coordinates for a location, fetches weather data for that location, and provides methods to convert weather codes.
</p>
<p>
    <strong>Summary:</strong> This class serves as the central component of the Weather App, encompassing its fundamental operations. It contains functionalities to retrieve weather data and location coordinates, 
  translate weather codes into easily understandable weather conditions, and oversee API requests. Acting as the intermediary between the GUI and external weather data source, this class guarantees the accurate retrieval and presentation of weather information.
</p>
<h2>Key learnings</h2>

<h3>4.1 Making API Requests and Handling Responses</h3>
<p>The project involved integrating external APIs to fetch real-time weather data. Through this process, I gained valuable experience in making HTTP requests, parsing JSON responses, and handling various types of API responses. This included implementing error handling mechanisms to ensure robustness and reliability when interacting with external services.
</p>  
<h3>4.2 Creating User-Friendly GUI Using Java Swing Library</h3>
<p>A significant aspect of the project was designing and implementing a user-friendly graphical interface for the Weather App. Leveraging the Java Swing library, 
  I learned how to create intuitive and visually appealing GUI components such as buttons, text fields, and labels. 
  Additionally, I gained insights into structuring the GUI layout effectively to enhance user experience and readability.</p>
  <h3>4.3 Handling User Input and Utilizing It</h3>
  <p>The Weather App required robust mechanisms for handling user input and customizing weather displays based on user preferences and locations. Through implementing features 
    like location input fields and interactive elements, I learned how to capture and process user input effectively. This included validating user input, implementing autocomplete functionality,
    and dynamically updating weather information based on user actions.</p>
  
