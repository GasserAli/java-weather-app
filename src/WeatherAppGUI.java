import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WeatherAppGUI extends JFrame {
     public WeatherAppGUI(){
         //setup gui and add title
         super("Weather App");

         //configure GUI to end program once closed
         setDefaultCloseOperation(EXIT_ON_CLOSE);

         //set size of GUI
         setSize(450,650);

         //load GUI to center of the screen
         setLocationRelativeTo(null);

         //make layout manager null to manually position GUI elements
         setLayout(null);

         //block resizing
         setResizable(false);

         addGuiComponents();




     }

    private void addGuiComponents() {
         //search field
        JTextField searchTextField = new JTextField();

        //set location and size
        searchTextField.setBounds(15,15,351,45);

        //change font style and size
        searchTextField.setFont(new Font("Dialog",Font.PLAIN,24));

        add(searchTextField);

        //search button
        JButton searchButton = new JButton(loadImage("src/assets/search.png"));

        //change to hand cursor when hovering over a button
        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchButton.setBounds(375,13,47,45);
        add(searchButton);

        //weather image
        JLabel weatherConditionImage = new JLabel(loadImage("src/assets/cloudy.png"));
        weatherConditionImage.setBounds(0,125,450,217);
        add(weatherConditionImage);

        //temperature text
        JLabel temperatureText = new JLabel("10 C");
        temperatureText.setBounds(0,350,450,54);
        temperatureText.setFont(new Font("Dialog",Font.BOLD,48));
        temperatureText.setHorizontalAlignment(SwingConstants.CENTER);
        add(temperatureText);

        //add weather condition description
        JLabel weatherConditionDesc = new JLabel("Cloudy");
        weatherConditionDesc.setBounds(0,405,450,36);
        weatherConditionDesc.setFont(new Font("Dialog",Font.PLAIN,32));
        weatherConditionDesc.setHorizontalAlignment(SwingConstants.CENTER);
        add(weatherConditionDesc);

        //Humidity image
        JLabel humidityImage = new JLabel(loadImage("src/assets/humidity.png"));
        humidityImage.setBounds(15,500,74,66);
        add(humidityImage);

        //humidity text
        JLabel humidityText = new JLabel("<html><b>Humidity</b> 100%</html>"); //YOU CAN ADD HTML??
        humidityText.setBounds(90,500,85,55);
        humidityText.setFont(new Font("Dialog",Font.PLAIN ,16));
        add(humidityText);

        //windspeed image
        JLabel windspeedImage = new JLabel(loadImage("src/assets/windspeed.png"));
        windspeedImage.setBounds(220,500,74,66);
        add(windspeedImage);

        //windspeed text
        JLabel windspeedText = new JLabel("<html><b>Windspeed</b> 15km/h</html>");
        windspeedText.setBounds(310,500,85,55);
        windspeedText.setFont(new Font("Dialog",Font.PLAIN,16));
        add(windspeedText);


        





    }

    //used to add images to gui components
    private ImageIcon loadImage(String resourcePath) {
        try{
            //read the image file from the provided resourcePath
            BufferedImage image = ImageIO.read(new File(resourcePath));

            //return an image icon so that the component can render it
            return new ImageIcon(image);
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("Could not find resource");
        return null;

    }
}
