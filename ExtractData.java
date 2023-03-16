import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ExtractData {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://api.thingspeak.com/channels/875453/feeds.json?api_key=1DSQ7R1XTT1OK0Z1&results=20");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            InputStreamReader stm = new InputStreamReader(con.getInputStream());
            BufferedReader bfr = new BufferedReader(stm);

            String st = bfr.readLine();
             System.out.println(st);
            bfr.close();

            JSONParser par = new JSONParser();
            JSONObject obj = (JSONObject) par.parse(st);
            JSONObject obj1 =(JSONObject) obj.get("channel");
            System.out.println("id :"+obj1.get("id"));
            System.out.println("name :"+obj1.get("name"));
            System.out.println(obj1.get("field1"));

            JSONArray array =(JSONArray) obj.get("feeds");
            //System.out.println(array.get(0));
            for(int i=0;i<array.size();i++)
            {
              JSONObject obj3=(JSONObject) array.get(i);
               System.out.println(obj3.get("field1"));
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
