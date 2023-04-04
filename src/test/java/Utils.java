import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Utils {

    public static int generateRandomNumber(int min,int max){
        return (int) Math.round(Math.random()*(max-min)+min);
    }

    public static JSONObject loadJSONFile(String fileLocation) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(new FileReader(fileLocation));
        JSONObject jsonObject = (JSONObject) obj;
        return jsonObject;
    }

    public static void addJsonArray(String fullName, String email,String password,String phoneNumber,String nid,String role) throws IOException, ParseException {
        String fileName="./src/test/resources/Users.json";
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(new FileReader(fileName));
        JSONObject userobj=new JSONObject();
        JSONArray jsonArray = (JSONArray) obj;

        userobj.put("fullName",fullName);
        userobj.put("email",email);
        userobj.put("password",password);
        userobj.put("phoneNumber",phoneNumber);
        userobj.put("nid ",nid);
        userobj.put("role ",role);
        jsonArray.add(userobj);

        FileWriter file = new FileWriter(fileName);
        file.write(jsonArray.toJSONString());
        file.flush();
        file.close();
    }

    public static List readJsondata(String fileName) throws IOException, ParseException {

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(fileName));
        JSONArray jsonArray = (JSONArray) obj;
        return jsonArray;
    }

    public static void updateJSONList(int index, String password,String newPassword) throws IOException, ParseException {
        String fileName="./src/test/resources/Users.json";
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(new FileReader(fileName));
        JSONArray jsonArray = (JSONArray) obj;

        JSONObject jsonObject = (JSONObject) jsonArray.get(3);
        jsonObject.put("password", newPassword);

        FileWriter file = new FileWriter(fileName);
        file.write(jsonArray.toJSONString());
        file.flush();
        file.close();

    }
}
