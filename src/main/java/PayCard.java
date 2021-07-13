import com.google.common.annotations.VisibleForTesting;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class PayCard {

// 1. Определение UI элементов для взаимодействия

       //элементы для создания платежа

    By cardNumberDebit = By.xpath("//input[@data-qa-node='numberdebitSource']");
    By expiredDateDebit = By.xpath("//input[@data-qa-node='expiredebitSource']");
    By cvvDebitCard = By.xpath("//input[@data-qa-node='cvvdebitSource']");
    By firstNameDebit = By.xpath("//input[@data-qa-node='firstNamedebitSource']");
    By lastNameDebit = By.xpath("//input[@data-qa-node='lastNamedebitSource']");
    By cardNumberCredit = By.xpath("//input[@data-qa-node='numberreceiver']");
    By firstNameCredit = By.xpath("//input[@data-qa-node='firstNamereceiver']");
    By lastNameCredit = By.xpath("//input[@data-qa-node='lastNamereceiver']");
    By amount = By.xpath("//input[@data-qa-node='amount']");
    By currencyButton = By.xpath("//button[@data-qa-node='currency']");
    By currency = By.xpath("//button[@data-qa-value='USD']");
    By comment = By.xpath("//span[@data-qa-node='toggle-comment']");
    By commentNote = By.xpath("//textarea[@data-qa-node= 'comment']");
    By submitButton = By.xpath("//button[@type='submit']");

       //  элементы для корзины

    By Comment = By.xpath("//div[@data-qa-node='comment']");
    By CardNumberDebit = By.xpath("//span[@data-qa-node='payer-card']");
    By CardNumberCredit = By.xpath("//span[@data-qa-node='receiver-card']");
    By receiverName = By.xpath("//div[@data-qa-node='receiver-name']");
    By payerAmount = By.xpath("//div[@data-qa-node='payer-amount']");
    By receiverAmount = By.xpath("//div [@data-qa-node='receiver-amount']");
    By payerCommission = By.xpath("//div[@data-qa-node='payer-currency']");
    By receiverCommission = By.xpath("//div[@data-qa-node='receiver-currency']");
    By totalSum = By.xpath("//div[@data-qa-node='total']");


    @Test

    public void checkMinPaymentSum() {
        System.setProperty("webdriver.chrome.driver","src\\main\\resources\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);


        // 2. Действия с элементами

        driver.get("https://next.privat24.ua/money-transfer/card");
        driver.findElement(cardNumberDebit).sendKeys("4093206288191961");
        driver.findElement(expiredDateDebit).sendKeys("06/22");
        driver.findElement(cvvDebitCard).sendKeys("494");
        driver.findElement(firstNameDebit).sendKeys("Ronald");
        driver.findElement(lastNameDebit).sendKeys("Clark");
        driver.findElement(cardNumberCredit).sendKeys("4567739561253907");
        driver.findElement(firstNameCredit).sendKeys("Ruth");
        driver.findElement(lastNameCredit).sendKeys("Montaivo");
        driver.findElement(amount).sendKeys("10");
        driver.findElement(currencyButton).click();
        driver.findElement(currency).click();
        driver.findElement(comment).click();
       driver.findElement(commentNote).sendKeys("test test");
       driver.findElement(submitButton).submit();

       // 3. Проверки с ОР и ФР
        
        Assert.assertEquals("test test", driver.findElement(Comment).getText());
        Assert.assertEquals("4093 2062 8819 1961", driver.findElement(CardNumberDebit).getText());
        Assert.assertEquals("4567 7395 6125 3907", driver.findElement(CardNumberCredit).getText());
        Assert.assertEquals("R*** M*******", driver.findElement(receiverName).getText());
        Assert.assertEquals("10 USD", driver.findElement(payerAmount).getText());
        Assert.assertEquals("10 USD", driver.findElement(receiverAmount).getText());
        Assert.assertEquals("3.15 USD", driver.findElement(payerCommission).getText());
        Assert.assertEquals("0 USD", driver.findElement(receiverCommission).getText());
        Assert.assertEquals("Разом до списання 13.15 USD", driver.findElement(totalSum).getText());

    }
}
