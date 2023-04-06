import com.github.javafaker.Faker;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class DmoneyTestRunner extends Setup {
    LoginPage loginPage;
    DmoneyPage dmoneyPage;

    @BeforeTest
    public void doLogin() throws IOException, ParseException {
        loginPage = new LoginPage(driver);
        JSONObject userObject= Utils.loadJSONFile("./src/test/resources/Admin.json");

        String email= (String) userObject.get("Email");
        String password= (String) userObject.get("Password");

        loginPage.doLogin(email, password);

    }

    @Test(priority = 1, description = "Create customer")
    public void createCustomer() throws InterruptedException, IOException, ParseException {

        dmoneyPage = new DmoneyPage(driver);

        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String fullName=firstName+ " " +lastName;
        String email = "customer"+ Utils.generateRandomNumber(1000, 9999)+"@test.com";
        String password = "1234";
        String phoneNumber = "01700"+Utils.generateRandomNumber(100000, 999999);
        String nid = "02212345";
        String role = "customer";
        Thread.sleep(3000);

        dmoneyPage.createCustomer(fullName, email, password,phoneNumber,nid,role);

        Utils.addJsonArray(fullName, email, password, phoneNumber,nid,role);

        //Assertion
        Thread.sleep(3000);
        String DataActual = dmoneyPage.assertCreated.get(0).getText();
        String DataExpected = "User created";
        Thread.sleep(1000);
        Assert.assertTrue(DataActual.contains(DataExpected));
        Thread.sleep(1000);

    }

    @Test(priority = 2, description = "Create agent")
    public void createAgent() throws InterruptedException, IOException, ParseException {

        dmoneyPage = new DmoneyPage(driver);

        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String fullName=firstName+ " " +lastName;
        String email = "agent"+ Utils.generateRandomNumber(1000, 9999)+"@test.com";
        String password = "1234";
        String phoneNumber = "01800"+Utils.generateRandomNumber(100000, 999999);
        String nid = "02212345";
        String role = "agent";
        Thread.sleep(3000);

        dmoneyPage.txtSend.get(0).sendKeys(Keys.CONTROL + "a" + Keys.BACK_SPACE);
        dmoneyPage.txtSend.get(1).sendKeys(Keys.CONTROL + "a" + Keys.BACK_SPACE);
        dmoneyPage.txtSend.get(2).sendKeys(Keys.CONTROL + "a" + Keys.BACK_SPACE);
        dmoneyPage.txtSend.get(3).sendKeys(Keys.CONTROL + "a" + Keys.BACK_SPACE);
        dmoneyPage.txtSend.get(4).sendKeys(Keys.CONTROL + "a" + Keys.BACK_SPACE);

        dmoneyPage.createAgent(fullName, email, password,phoneNumber,nid,role);

        Utils.addJsonArray(fullName, email, password, phoneNumber,nid,role);

        //Assertion
        String DataActual = dmoneyPage.assertCreated.get(0).getText();
        String DataExpected = "User created";
        Thread.sleep(1000);
        Assert.assertTrue(DataActual.contains(DataExpected));
        Thread.sleep(1000);
    }

    @Test(priority = 3, description = "Search customer by phone number")
    public void userSearchByCustomerPhoneNumber() throws IOException, ParseException, InterruptedException {

        String file = "./src/test/resources/Users.json";

        List users = Utils.readJsondata(file);

        dmoneyPage = new DmoneyPage(driver);
        JSONObject customerObject = (JSONObject) users.get(users.size() - 2);
        String customerPhoneNumber = (String) customerObject.get("phoneNumber");
        Thread.sleep(3000);
        dmoneyPage.userSearchByCustomerPhoneNumber(customerPhoneNumber);
        Thread.sleep(2000);
    }

    @Test(priority = 5, description = "Update customer password" )
    public void updatePassword() throws InterruptedException, IOException, ParseException {

        String randomPassword = String.valueOf(Utils.generateRandomNumber(1000, 9999));
        String newPassword = randomPassword;
        Thread.sleep(2000);
        dmoneyPage.updatePassword(newPassword);
        Thread.sleep(1000);
//
        Utils.updateJSONList(1,newPassword,newPassword);
//
        //Assertion
        String DataActual = dmoneyPage.assertUpdate.get(0).getText();
        String DataExpected = "User Update Successfully!";
        Thread.sleep(1000);
        Assert.assertTrue(DataActual.contains(DataExpected));
        Thread.sleep(1000);
    }

//    @Test(priority = 6, description = "Logout from Admin")
//    public void logoutFromAdmin() throws InterruptedException {
//        dmoneyPage = new DmoneyPage(driver);
//        Thread.sleep(1000);
//        dmoneyPage.btnOk.get(2).click();
//        dmoneyPage.btnIcon.get(0).click();
//        Thread.sleep(5000);
//        dmoneyPage.btnSignOut.get(1).click();
//        Thread.sleep(5000);
//    }
}