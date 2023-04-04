import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTestRunner extends Setup{
    LoginPage loginPage;

    @Test(priority = 1, description = "Admin can do login successfully")
    public void doLogin() throws IOException, ParseException {
        loginPage = new LoginPage(driver);
        JSONObject userObject= Utils.loadJSONFile("./src/test/resources/Admin.json");


        String email= (String) userObject.get("Email");
        String password= (String) userObject.get("Password");

        loginPage.doLogin(email, password);

        //loginPage.doLogin("salman@roadtocareer.net","1234");
    }
}
