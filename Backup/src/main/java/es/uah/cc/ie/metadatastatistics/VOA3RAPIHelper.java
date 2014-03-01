package es.uah.cc.ie.metadatastatistics;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class VOA3RAPIHelper {
    
    public static List<String> getRecordIdsFromRepo(String repoid) {
        System.out.print("Request to voa3r for repository: "+ repoid + " ");
        ArrayList<String> idList = new ArrayList<String>();
        JSONObject response = toJSON(executePost("http://voa3r.cc.uah.es:8080/ContentItemsServlet/Content", "repositoryid="+repoid+"&limitstart=0&limit=8"));
        
        JSONArray resultset = (JSONArray) response.get("resultset");
        
        if (resultset != null && resultset.size() > 0) {
            for (Object record : resultset) {
                idList.add((String)((JSONObject) record).get("voa3rid"));
            }
        }
        System.out.println("[DONE] ["+idList.size() +"]");
        
        return idList;
    }
    
    public static HashMap<String, Object> getRecord(String voa3rid) {
        
        System.out.print("Request to voa3r for record: "+ voa3rid + " ");
        
        JSONObject response = toJSON(executePost("http://voa3r.cc.uah.es:8080/metadataViewer/MetadataViewer", "table=ContentId&voa3rid="+voa3rid));
        
        System.out.println("[DONE]");
        
        return (HashMap<String, Object>) response.get("metadata");
    }
    
    private static JSONObject toJSON(String str) {
        try {
            return (JSONObject) new JSONParser().parse(str);
        } catch (ParseException ex) {
            return null;
        }
    }
    
    private static String executePost(String targetURL, String urlParameters) {
        URL url;
        HttpURLConnection connection = null;
        try {
            //Create connection
            url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");

            connection.setRequestProperty("Content-Length", ""
                + Integer.toString(urlParameters.getBytes().length));
            connection.setRequestProperty("Content-Language", "en-US");

            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            //Send request
            DataOutputStream wr = new DataOutputStream(
                connection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            //Get Response	
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();

        } catch (Exception e) {

            e.printStackTrace();
            return null;

        } finally {

            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
