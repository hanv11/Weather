package sample;

import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public Text txt_city_name;
    public Text txt_temp_min;
    public Text txt_temp_max;
    public Text txt_temp;
    public Text txt_desc;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            String url = "https://samples.openweathermap.org/data/2.5/weather?q=London&appid=b6907d289e10d714a6e88b30761fae22";

            URL objUrl = new URL(url);

            HttpURLConnection connection = (HttpURLConnection)objUrl.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String response = new String();
            String inputLine;
            while((inputLine = bufferedReader.readLine()) != null) {
                response += inputLine;
            }

            JSONObject root_object = (JSONObject) JSONValue.parse(response);
            JSONObject main_object = (JSONObject)root_object.get("main");
            double temp = (Double) main_object.get("temp");
            double temp_min = (Double) main_object.get("temp_min");
            double temp_max = (Double) main_object.get("temp_max");

            JSONArray weatherArr = (JSONArray) root_object.get("weather");
             JSONObject weatherObj = (JSONObject) weatherArr.get(0);
            String description = (String) weatherObj.get("description");
            String city_name = (String) root_object.get("name");
            txt_city_name.setText(city_name);
            txt_temp.setText(String.valueOf(temp ));
            txt_temp_max.setText(String.valueOf(temp_max));
            txt_temp_min.setText(String.valueOf(temp_min));
            txt_desc.setText(description);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
