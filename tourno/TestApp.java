import java.util.ArrayList;
import java.util.Scanner;
import Classes.Paricipant;
import Classes.SystemData;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class TestApp {
    public static void main(String[] args) {
        try{
            //API Sign in URL
            String urlString = " https://us-central1-swe206-221.cloudfunctions.net/app/UserSignIn";
            String username = "?username=Ahmed";
            String password = "&password=8377";

            urlString = urlString + username + password;
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
        
            // Make the request and get the response status code
            int statusCode = connection.getResponseCode();
        
            if (statusCode == 200) {
                // Success, User Found
                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                //Close the scanner
                scanner.close();

                System.out.println(informationString);

                //JSON to Object
                JSONParser parse = new JSONParser();
                JSONObject dataObject = (JSONObject) parse.parse(String.valueOf(informationString));

                
                System.out.println(dataObject.get("type"));

            } else if (statusCode == 403) {
                // Username or Password is wrong
                System.out.println("Username or Password is wrong");
            } else if (statusCode == 400) {
                // Missing parameters
                System.out.println(statusCode);
            }   
        }catch(Exception e){
        e.printStackTrace();
        }
    }
}