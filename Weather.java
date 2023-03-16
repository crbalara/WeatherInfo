import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Weather {



    public static void main(String[] args) {

        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter City Name");
            String city = sc.nextLine();
            String st = "";
            URL url = new URL("http://api.openweathermap.org/geo/1.0/direct?q="+city+",91&limit=1&appid=061cfe02dd0aa15848b80d1b7ba9af2d");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            InputStreamReader stm = new InputStreamReader(con.getInputStream());
            BufferedReader bfr = new BufferedReader(stm);

            st= bfr.readLine();
           // System.out.println(st);
            bfr.close();

            JSONParser par = new JSONParser();
            JSONArray array =(JSONArray) par.parse(st);
           JSONObject obj = (JSONObject) array.get(0);
            //System.out.println(obj);

          /*  for(int i=0;i<=array.size();i++)
            {
              System.out.println(array.get(i));
            }*/
             double lat = (double)obj.get("lat");
           // System.out.println(lat);
            double lon = (double)obj.get("lon");
          //  System.out.println(lon);

            File file = new File("Balara1.json");
            if(file.createNewFile())
            {
                System.out.println("File created Successfully!!!");
            }
            else
            {
                System.out.println("file already exists");
            }
            FileWriter fw = new FileWriter("Balara1.json",true);


            URL url1 = new URL("https://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lon+"&appid=061cfe02dd0aa15848b80d1b7ba9af2d");
            HttpURLConnection con1 = (HttpURLConnection) url1.openConnection();
            con1.setRequestMethod("GET");
            InputStreamReader stm1 = new InputStreamReader(con1.getInputStream());
            BufferedReader bfr1 = new BufferedReader(stm1);

            String st1= bfr1.readLine();
            // System.out.println(st1);
            bfr1.close();

            JSONParser parser = new JSONParser();
            JSONObject obj1 =(JSONObject) parser.parse(st1);
            JSONObject obj2 =(JSONObject) obj1.get("main");
            JSONObject obj3 =(JSONObject) obj1.get("wind");
            //System.out.println(obj1);
            fw.write(st1);
            fw.close();

            JSONArray array1 = (JSONArray) obj1.get("weather");

            System.out.println("City Name : "+obj1.get("name"));
           double temp = (double)obj2.get("temp");
            System.out.println("Temprature : "+(temp-273.15)+"C");
            System.out.println("Pressure : "+obj2.get("pressure")+"Ph");
            System.out.println("Humidity : "+obj2.get("humidity")+"%");
            System.out.println("Wind Speed : "+obj3.get("speed")+"m/s");





        } catch (Exception e) {

            System.out.println(e);
        }
    }
}
