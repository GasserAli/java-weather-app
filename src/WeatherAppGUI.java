import javax.swing.*;

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



     }
}
