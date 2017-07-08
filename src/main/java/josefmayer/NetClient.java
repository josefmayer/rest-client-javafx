package josefmayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Josef Mayer on 06.07.2017.
 */
public class NetClient {

    public void mainNetClient(String[] args) {
        setGETConnection();
        //setPOSTConnection();
    }

    //public static void setGETConnection() {
    public StringBuffer setGETConnection() {
        StringBuffer sb = new StringBuffer();

        try {

            URL url = new URL("http://localhost:8080/");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));



            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                sb.append(output);
                sb.append("\n");
                //System.out.println(output);
            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(sb);
        return sb;

    }

    //public static void setPOSTConnection() {
    public void setPOSTConnection(String inData) {
        try {

            URL url = new URL("http://localhost:8080");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //String postData = "{\"a\":[2.0,4.0,5.0,-6.0],\"b\":[8.0,4.0]}";
            String postData = inData;


            byte[] postDataBytes = postData.toString().getBytes("UTF-8");
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            conn.setDoOutput(true);
            conn.getOutputStream().write(postDataBytes);

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
