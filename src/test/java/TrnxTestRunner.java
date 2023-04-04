import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class TrnxTestRunner extends Setup{
    LoginPage loginPage;
    DmoneyPage dmoneyPage;

    @Test(priority = 1, description = "Login to System account")
    public void loginToSystemAccount() throws IOException, ParseException, InterruptedException {
        dmoneyPage = new DmoneyPage(driver);
        String systemAccount = "system@roadtocareer.net";
        String password = "1234";

        loginPage = new LoginPage(driver);
        loginPage.doLogin(systemAccount,password);
    }
    @Test(priority = 2, description = "Deposit to agent")
    public void depositToAgent() throws IOException, ParseException, InterruptedException {
        String file = "./src/test/resources/Users.json";

        List users = Utils.readJsondata(file);
        dmoneyPage = new DmoneyPage(driver);
        JSONObject agentObject = (JSONObject) users.get(users.size() - 1);
        String agentPhoneNumber = (String) agentObject.get("phoneNumber");
        Thread.sleep(3000);
        dmoneyPage.depositToAgent(agentPhoneNumber);
        Thread.sleep(2000);

        //Assertion
        String DataActual = dmoneyPage.assertDeposite.get(0).getText();
        String DataExpected = "Deposit successful";
        Thread.sleep(1000);
        Assert.assertTrue(DataActual.contains(DataExpected));
        Thread.sleep(1000);
    }
    @Test(priority = 3, description = "Logout from System")
    public void logoutFromSystem() throws InterruptedException {
        dmoneyPage = new DmoneyPage(driver);
        dmoneyPage.btnOk.get(2).click();
        dmoneyPage.btnIcon.get(0).click();
        Thread.sleep(5000);
        dmoneyPage.btnSignOut.get(1).click();
        Thread.sleep(5000);
    }
    @Test(priority = 4, description = "login to agent account")
    public void loginToagentAccount() throws IOException, ParseException {
        dmoneyPage = new DmoneyPage(driver);
        String file = "./src/test/resources/Users.json";

        List users = Utils.readJsondata(file);

        dmoneyPage = new DmoneyPage(driver);
        JSONObject agentObject = (JSONObject) users.get(users.size() - 1);
        String agentEmail = (String) agentObject.get("email");
        String agentPassword = (String) agentObject.get("password");

        loginPage = new LoginPage(driver);
        loginPage.doLogin(agentEmail,agentPassword);
    }

    @Test(priority = 5, description = "Deposit to customer")
    public void depositToCustomer() throws IOException, ParseException, InterruptedException {
        String file = "./src/test/resources/Users.json";

        List users = Utils.readJsondata(file);

        dmoneyPage = new DmoneyPage(driver);
        JSONObject customerObject = (JSONObject) users.get(users.size() - 2);
        String customerPhoneNumber = (String) customerObject.get("phoneNumber");

        dmoneyPage.depositToCustomer(customerPhoneNumber);
        Thread.sleep(2000);

        //Assertion
        String DataActual = dmoneyPage.assertDeposite.get(0).getText();
        String DataExpected = "Deposit successful";
        Thread.sleep(1000);
        Assert.assertTrue(DataActual.contains(DataExpected));
        Thread.sleep(1000);
    }
    @Test(priority = 6, description = "Logout from agent")
    public void logoutFromAgent() throws InterruptedException {
        dmoneyPage = new DmoneyPage(driver);
        dmoneyPage.btnOk.get(2).click();
        dmoneyPage.btnIcon.get(0).click();
        Thread.sleep(2000);
        dmoneyPage.btnSignOut.get(1).click();
        Thread.sleep(2000);
    }
    @Test(priority = 7, description = "Login to customer account")
    public void loginToCustomerAccount() throws IOException, ParseException, InterruptedException {
        dmoneyPage = new DmoneyPage(driver);
        String file = "./src/test/resources/Users.json";

        List users = Utils.readJsondata(file);

        JSONObject customerObject = (JSONObject) users.get(users.size() - 2);
        String customerEmail = (String) customerObject.get("email");
        String customerPassword = (String) customerObject.get("password");

        loginPage = new LoginPage(driver);
        loginPage.doLogin(customerEmail,customerPassword);
        Thread.sleep(1000);

        //Assertion
//        String DataActual = dmoneyPage.clickStatement.getText();
//        String DataExpected = "Statement";
//        Thread.sleep(1000);
//        Assert.assertTrue(DataActual.contains(DataExpected));
//        Thread.sleep(1000);
    }

    @Test(priority = 8, description = "Send money to another customer")
    public void sendMoney() throws IOException, ParseException, InterruptedException {
        dmoneyPage = new DmoneyPage(driver);
        String newCustomerPhoneNumber="01700535243";
        dmoneyPage.sendMoney(newCustomerPhoneNumber);
        Thread.sleep(1000);
    }
    @Test(priority = 9, description = "Check statement for deduct 105 and assert current balance")
    public void checkStatementForDeduct105andAssertBalance() throws InterruptedException {

        dmoneyPage = new DmoneyPage(driver);
        dmoneyPage.checkStatementForDeduct105andAssertBalance();
    }
    @Test(priority = 10, description = "Withdraw money")

    public void withdrawMoney() throws IOException, ParseException, InterruptedException {
        String file = "./src/test/resources/Users.json";

        List users = Utils.readJsondata(file);
        dmoneyPage = new DmoneyPage(driver);
        JSONObject agentObject = (JSONObject) users.get(users.size() - 1);
        String agentPhoneNumber = (String) agentObject.get("phoneNumber");
        Thread.sleep(2000);
        dmoneyPage.withdrawMoney(agentPhoneNumber);
        Thread.sleep(1000);

        //Assertion
        String DataActual = dmoneyPage.assertBalanceafterwithdraw.get(0).getText();
        String DataExpected = "Your current balance is 390 TK Fee 5 TK Trnx ID:";
        Thread.sleep(1000);
        Assert.assertTrue(DataActual.contains(DataExpected));
        Thread.sleep(1000);
    }
    @Test(priority = 11, description = "Assert transaction Id")

    public void assertTrnxId() throws InterruptedException {
        dmoneyPage.assertTrnxId();
        Thread.sleep(1000);
        WebElement row = dmoneyPage.table.findElements(By.tagName("tr")).get(3);
        Thread.sleep(5000);
        WebElement cell = row.findElements(By.tagName("td")).get(5);
        Thread.sleep(5000);
        String trnx = cell.getText();
        Thread.sleep(1000);

        //Assert.assertEquals(trnx, "TXN509972");
    }

    @Test(priority = 12, description = "Payment to Merchant")

    public void paymentToMerchant() throws InterruptedException {
        String merchantPhoneNumber = "01686606905";
        Thread.sleep(3000);
        dmoneyPage.paymentToMerchant(merchantPhoneNumber);
        Thread.sleep(2000);

        //Assertion
        String DataActual = dmoneyPage.assertDeposite.get(0).getText();
        String DataExpected = "Payment successful";
        Thread.sleep(1000);
        Assert.assertTrue(DataActual.contains(DataExpected));
        Thread.sleep(1000);
    }

    @Test(priority = 13, description = "Logout from Admin")
    public void checkTransactionList() throws InterruptedException, IOException, ParseException {
        dmoneyPage = new DmoneyPage(driver);
        dmoneyPage.btnOk.get(2).click();
        dmoneyPage.btnIcon.get(0).click();
        Thread.sleep(2000);
        dmoneyPage.btnSignOut.get(1).click();
        Thread.sleep(2000);

        loginPage = new LoginPage(driver);
        JSONObject userObject= Utils.loadJSONFile("./src/test/resources/Admin.json");
        String email= (String) userObject.get("Email");
        String password= (String) userObject.get("Password");
        loginPage.doLogin(email, password);

        String file = "./src/test/resources/Users.json";

        List users = Utils.readJsondata(file);

        dmoneyPage = new DmoneyPage(driver);
        JSONObject customerObject = (JSONObject) users.get(users.size() - 2);
        String customerPhoneNumber = (String) customerObject.get("phoneNumber");
        Thread.sleep(3000);
        dmoneyPage.checkTransactionList(customerPhoneNumber);
        Thread.sleep(2000);

        WebElement row = dmoneyPage.table.findElements(By.tagName("tr")).get(4);
        Thread.sleep(5000);
        WebElement cell = row.findElements(By.tagName("td")).get(4);
        Thread.sleep(3000);
        String data = cell.getText();
        Thread.sleep(1000);

        Assert.assertEquals(data, "Payment");
    }
}
