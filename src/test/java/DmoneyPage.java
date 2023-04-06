import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class DmoneyPage {

    @FindBy(linkText = "Create User")
    public List<WebElement>clickCreateUser;

    @FindBy(className = "form-control-lg")
    public List<WebElement> txtSend;
    @FindBy(css = "[type=submit]")
    WebElement btnSubmit;

    @FindBy(className = "swal2-html-container")
    public List<WebElement> assertCreated;

    @FindBy(className = "form-select-lg")
    public List<WebElement> btnSelect;
    @FindBy(css = "[type=button]")
    public List<WebElement> btnOk;

    @FindBy(linkText = "User List")
    WebElement clickUserList;

    @FindBy(css = "[type=text]")
    WebElement btnSerch;

    @FindBy(className = "fa-pencil")
    public List<WebElement> btnEdit;
    @FindBy(className = "swal2-html-container")
    public List<WebElement> assertUpdate;

    @FindBy(className = "fa-user-circle")
    public List<WebElement> btnIcon;

    @FindBy(className = "dropdown-item")
    public List<WebElement> btnSignOut;
    @FindBy(linkText = "Deposit")
    WebElement clickDeposite;
    @FindBy(className = "form-control")
    public List<WebElement> txtaccount;
    @FindBy(css = "[type=button]")
    public List<WebElement> btnYes;
    @FindBy(className = "swal2-title")
    public List<WebElement> assertDeposite;
    @FindBy(linkText = "Statement")
    WebElement clickStatement;
    @FindBy(linkText = "Send Money")
    WebElement clickSendMoney;

    @FindBy(linkText = "Withdraw")
    WebElement clickWithdraw;

    @FindBy(className = "table")
    WebElement table;
    @FindBy(className = "row")
    WebElement checkBalance;
    @FindBy(className = "swal2-html-container")
    public List<WebElement> assertBalanceafterwithdraw;
    @FindBy(linkText = "Payment")
    WebElement clickPayment;
    @FindBy(linkText = "Transaction")
    WebElement clickTransaction;
    @FindBy(css = "[type=password]")
    public List<WebElement> btnpassword;


    public DmoneyPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    public void createCustomer(String fullName,String email,String password,String phoneNumber,String nid,String role) throws InterruptedException {

        clickCreateUser.get(0).click();

        txtSend.get(0).sendKeys(fullName);
        txtSend.get(1).sendKeys(email);
        txtSend.get(2).sendKeys(password);
        txtSend.get(3).sendKeys(phoneNumber);
        txtSend.get(4).sendKeys(nid);

        btnSelect.get(0).click();
        Thread.sleep(2000);
        btnSelect.get(0).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        btnSelect.get(0).sendKeys(Keys.ENTER);
        btnSubmit.click();
        Thread.sleep(1000);

    }
    public void createAgent(String fullName,String email,String password,String phoneNumber,String nid,String role) throws InterruptedException {

        btnOk.get(2).click();
        Thread.sleep(1000);
        clickCreateUser.get(0).click();

        txtSend.get(0).sendKeys(fullName);
        txtSend.get(1).sendKeys(email);
        txtSend.get(2).sendKeys(password);
        txtSend.get(3).sendKeys(phoneNumber);
        txtSend.get(4).sendKeys(nid);

        btnSelect.get(0).click();
        Thread.sleep(2000);
        btnSelect.get(0).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        btnSelect.get(0).sendKeys(Keys.ENTER);
        btnSubmit.click();
    }

    public void userSearchByCustomerPhoneNumber(String phoneNumber) throws InterruptedException {
        btnOk.get(2).click();
        Thread.sleep(1000);
        clickUserList.click();
        Thread.sleep(1000);
        btnSerch.sendKeys(phoneNumber);
        Thread.sleep(1000);
    }

    public void updatePassword(String newPassword) throws InterruptedException {
        btnEdit.get(0).click();
        Thread.sleep(1000);
        btnpassword.get(0).sendKeys(Keys.CONTROL + "a" + Keys.BACK_SPACE);
        Thread.sleep(3000);
        btnpassword.get(0).sendKeys(newPassword);
        Thread.sleep(1000);
        btnSubmit.click();
        Thread.sleep(3000);
    }

    public void depositToAgent(String agentPhoneNumber) throws InterruptedException {
        clickDeposite.click();
        Thread.sleep(1000);
        txtaccount.get(0).sendKeys(agentPhoneNumber);
        Thread.sleep(1000);
        txtaccount.get(1).sendKeys("2000");
        Thread.sleep(1000);
        btnSubmit.click();
        Thread.sleep(1000);
        btnYes.get(2).click();
    }
    public void depositToCustomer(String customerPhoneNumber) throws InterruptedException {
        clickDeposite.click();
        Thread.sleep(1000);
        txtaccount.get(0).sendKeys(customerPhoneNumber);
        Thread.sleep(1000);
        txtaccount.get(1).sendKeys("1000");
        Thread.sleep(1000);
        btnSubmit.click();
        Thread.sleep(1000);
        btnYes.get(2).click();
    }
    public void sendMoney(String newCustomerPhoneNumber) throws InterruptedException {
        clickSendMoney.click();
        Thread.sleep(1000);
        txtaccount.get(0).sendKeys(newCustomerPhoneNumber);
        Thread.sleep(1000);
        txtaccount.get(1).sendKeys("100");
        Thread.sleep(1000);
        btnSubmit.click();
        Thread.sleep(1000);
        btnYes.get(2).click();
    }
    public void checkStatementForDeduct105andAssertBalance() throws InterruptedException {
        btnOk.get(2).click();
        Thread.sleep(1000);
        clickStatement.click();
        Thread.sleep(2000);

        WebElement row = table.findElements(By.tagName("tr")).get(2);
        Thread.sleep(5000);
        WebElement cell = row.findElements(By.tagName("td")).get(6);
        Thread.sleep(3000);
        String data = cell.getText();
        Thread.sleep(1000);

        Assert.assertEquals(data, "105 TK");

        Thread.sleep(1000);

        String balance=checkBalance.getText();
        Assert.assertEquals(balance, "Balance: 895 TK");

     }
    public void withdrawMoney(String agentPhoneNumber ) throws InterruptedException {
        clickWithdraw.click();
        Thread.sleep(1000);
        txtaccount.get(0).sendKeys(agentPhoneNumber);
        Thread.sleep(1000);
        txtaccount.get(1).sendKeys("500");
        Thread.sleep(1000);
        btnSubmit.click();
        Thread.sleep(1000);
        btnYes.get(2).click();
    }
    public void assertTrnxId() throws InterruptedException {
        btnOk.get(2).click();
        Thread.sleep(1000);
        clickStatement.click();
        Thread.sleep(1000);
    }
    public void paymentToMerchant(String merchantPhoneNumber) throws InterruptedException {
        clickPayment.click();
        Thread.sleep(1000);
        txtaccount.get(0).sendKeys(merchantPhoneNumber);
        Thread.sleep(1000);
        txtaccount.get(1).sendKeys("100");
        Thread.sleep(1000);
        btnSubmit.click();
        Thread.sleep(1000);
        btnYes.get(2).click();
    }
    public void checkTransactionList(String customerPhoneNumber) throws InterruptedException {
        clickTransaction.click();
        btnSerch.sendKeys(customerPhoneNumber);
        Thread.sleep(1000);
    }

}
