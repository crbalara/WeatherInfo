import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import static java.lang.System.out;
public class WeatherInfo {
    static String ct;

    //int cntcd;
    // int limt;
    Scanner sc = new Scanner(System.in);
    String lat_long() {
        System.out.println("Enter your CITY NAME:");
        ct = sc.nextLine();
        /*System.out.println("Enter your COUNTRY CODE:");
        cntcd = sc.nextInt();*/
        /*out.println("Enter your LIMIT:");
        limt = sc.nextInt();*/
        String dss = "";
        try {
            URL url = new URL("http://api.openweathermap.org/geo/1.0/direct?q=" + ct + ",91&limit=1&appid=c06001cc8670a7eaa42a881d74fec52c");
            HttpURLConnection urlcon = (HttpURLConnection) url.openConnection();
            urlcon.setRequestMethod("GET");
            InputStreamReader in = new InputStreamReader(urlcon.getInputStream());
            BufferedReader in1 = new BufferedReader(in);
            String dt;
            while ((dt = in1.readLine()) != null) {
                dss = dt;
            }
            urlcon.disconnect();
        }
        catch (Exception e) {
            System.out.println("city wala url nhi chla");
        }
        return dss;
    }
    String wther_info() {
        String ss = lat_long();
        if (!ss.contains("lat")) {
            System.out.println("Oops! please enter right city name.");
            System.exit(0);
        }
        String latstr = ss.substring(ss.indexOf("lat\":"));
        String longstr = ss.substring(ss.indexOf("lon\":"));
        String latitude = latstr.substring(5, latstr.indexOf(","));
        String logitude = longstr.substring(5, longstr.indexOf(","));
        String dt2 = null;
        System.out.println("latitude: "+logitude);
        System.out.println("logitude: "+logitude);
        try {
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?lat=" + latitude + "&lon=" + logitude + "&appid=c06001cc8670a7eaa42a881d74fec52c");
            HttpURLConnection urlcon = (HttpURLConnection) url.openConnection();
            urlcon.setRequestMethod("GET");
            InputStreamReader in = new InputStreamReader(urlcon.getInputStream());
            BufferedReader in1 = new BufferedReader(in);
            String dt, dt1 = null;
            while ((dt = in1.readLine()) != null) {
                dt1 = dt;
            }
            dt2 = dt1;
            urlcon.disconnect();
        } catch (Exception e) {
            System.out.println("error");
        }
        return dt2;
    }
    String temp (){
        String tempp=wther_info();
        String temp0=tempp.substring(tempp.indexOf("\"main\":{\"temp\""));
        String temp=temp0.substring(15,temp0.indexOf(","));
        double number = Double.parseDouble(temp);
        System.out.print("Temperature: "+number+"K");
        float v = (float) (number-273.15);
        System.out.printf(" & %.2f°C\n",v);
        return tempp;
    }
    String tempmin (){
        String tempp=temp();
        String temp0=tempp.substring(tempp.indexOf("\"temp_min\":"));
        String temp=temp0.substring(11,temp0.indexOf(","));
        double number = Double.parseDouble(temp);
        System.out.print("Temperature minimun: "+number+"K");
        float v = (float) (number-273.15);
        System.out.printf(" & %.2f°C\n",v);
        return tempp;
    }
    String tempmax (){
        String tempp=tempmin();
        String temp0=tempp.substring(tempp.indexOf("\"temp_max\":"));
        String temp=temp0.substring(11,temp0.indexOf(","));
        double number = Double.parseDouble(temp);
        System.out.print("Temperature maximum: "+number+"K");
        float v = (float) (number-273.15);
        System.out.printf(" & %.2f°C\n",v);
        return tempp;
    }
    String Humidity (){
        String tempp = tempmax();
        try {

            String sd=tempp;
            String temp0 = tempp.substring(tempp.indexOf("ity\":"));
            String temp = temp0.substring(5, temp0.indexOf(","));
            int number = Integer.parseInt(temp);
            out.println("Humidity: " + number);
            return tempp;
        }
        catch (Exception e)
        {
            String temp0 = tempp.substring(tempp.indexOf("ity\":"));
            String temp = temp0.substring(5, temp0.indexOf("}"));
            int number = Integer.parseInt(temp);
            out.println("Humidity: " + number);
            return tempp;

        }

    }
    String wind (){
        String tempp=Humidity();
        String temp0=tempp.substring(tempp.indexOf("\"speed\":"));
        String temp=temp0.substring(8,temp0.indexOf(","));
        Float number = Float.parseFloat(temp);
        System.out.printf("Wind: %.2f\n",number);
        return tempp;
    }
    String sky (){
        String tempp=wind();
        String temp0=tempp.substring(tempp.indexOf("\"description\":"));
        String temp=temp0.substring(14,temp0.indexOf(","));
        System.out.println("Sky: "+temp);
        return tempp;
    }
    String purresure (){
        String tempp=sky();
        String temp0=tempp.substring(tempp.indexOf("\"pressure\":"));
        String temp=temp0.substring(11,temp0.indexOf(","));
        Integer number = Integer.parseInt(temp);
        System.out.println("purresure: "+number);
        return tempp;
    }
    String visibility (){
        String tempp=purresure();
        String temp0=tempp.substring(tempp.indexOf("\"visibility\":"));
        String temp=temp0.substring(13,temp0.indexOf(","));
        Integer number = Integer.parseInt(temp);
        System.out.println("Visibility: "+number);
        return tempp;
    }
    String sunrise (){
        String tempp=visibility();
        String temp0=tempp.substring(tempp.indexOf("\"sunrise\":"));
        String temp=temp0.substring(10,temp0.indexOf(","));
        Long number = Long.parseLong(temp);
        SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String ldt = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new java.util.Date (number*1000));
        System.out.println("Sunrise time is: "+ldt);
        return tempp;
    }
    String sunset (){
        String tempp=sunrise();
        String temp0=tempp.substring(tempp.indexOf("\"sunset\":"));
        String temp=temp0.substring(9,temp0.indexOf("}"));
        Long number = Long.parseLong(temp);
        SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String ldt = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new java.util.Date (number*1000));
        System.out.println("Sunset time is: "+ldt);
        System.out.println(tempp);
        return tempp;
    }
   /* String sea_level (){
        String tempp=sunset();
        String temp0=tempp.substring(tempp.indexOf("\"sea_level\":"));
        String temp=temp0.substring(12,temp0.indexOf(","));
        Integer number = Integer.parseInt(temp);
        System.out.println("Sea level: "+number);
        return tempp;
    }
    String grnd_level (){
        String tempp=sea_level();
        try{
            String temp0=tempp.substring(tempp.indexOf("\"grnd_level\""));
            String temp=temp0.substring(13,temp0.indexOf("}"));
            Integer number = Integer.parseInt(temp);
            int true1 = Integer.parseInt(String.valueOf(number));
            out.println("Grand level: "+number);
            return tempp;
        }
        catch (Exception e){
            String temp0=tempp.substring(tempp.indexOf("\"grnd_level\""));
            String temp=temp0.substring(13,temp0.indexOf(","));
            Integer number = Integer.parseInt(temp);
            out.println("Grand level: "+number);
            return tempp;
        }
        }
    */

    String base () {
        String tempp = sunset();
        try {
            String temp0 = tempp.substring(tempp.indexOf("\"base\":\""));
            String temp = temp0.substring(7, temp0.indexOf(","));
            System.out.println(ct.toUpperCase() + " weather base on " + temp + ".");
            return tempp;
        } catch (Exception e) {
            String temp0 = tempp.substring(tempp.indexOf("\"base\":\""));
            String temp = temp0.substring(7, temp0.indexOf(","));
            System.out.println(ct.toUpperCase() + " weather base on " + temp + ".");
            return tempp;
        }
    }
    public static void main(String[] args) {
        WeatherInfo ob =new WeatherInfo();
        ob.base();
    }
}