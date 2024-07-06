package test.integration.swt_assignment_2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.List;

@SpringBootTest
class SwtAssignment2ApplicationTests {
    @Test
    void contextLoads() {
    }

    @Test
    public void checkTotalPrice() {

        // 1. Create an instance of ChromeDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // 2. Access the home page of tiki webiste
        driver.get("https://tiki.vn/");

        // 3. close popup if it's shown
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        try {
            WebElement closebnt = driver.findElement(By.xpath("//img[@alt='close-icon']"));
            closebnt.click();
        } catch (Exception e) {
        }

        // 4. Search for product "iphone 15 pro max.
        // 4.1 get html element by xpath.
        WebElement searchBox = driver.findElement(
                By.xpath("//input[@data-view-id='main_search_form_input']"));

        // 4.2 input search team into search box.
        searchBox.sendKeys("iphone 15 pro max");

        // 4.3. click search button.
        WebElement searchbnt = driver.findElement(
                By.xpath("//button[@data-view-id='main_search_form_button']"));
        searchbnt.click();

        // 5. Select the first Iphone item in result page.
        // 5.1 close popup if it's shown
        try {
            WebElement closebnt = driver.findElement(By.xpath("//img[@alt='close-icon']"));
            closebnt.click();
        } catch (Exception e) {
        }

        // 5.2. select first iteam in search result list.
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // get list of products items.
        List<WebElement> divs = driver.findElements(
                By.xpath("//a[@data-view-id='product_list_item']"));

        //get first elemennt
        divs.get(0).click();

        // 6. increase number of product to 2.
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement increasebnt = driver.findElement(By.xpath("//img[@alt='add-icon']"));

        // Wait for a few seconds (for demonstrationpurposes only)
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // click increase button.
        increasebnt.click();

        // 7. check total price

        // 7.1 get unit price by class name
        WebElement unitPriceElm = driver.findElement(By.className("product-price__current-price"));
        int unitPrice = Integer.parseInt(unitPriceElm.getText().replace(".", "").replace("₫", ""));


        // 7.2 Get total Price elemennt by css selector.
        WebElement totalPriceElm = driver.findElement(By.cssSelector("div[class='styles__PriceStyled-sc-1nhoeyy-1 HGbRm']"));
        int totalPrice = Integer.parseInt(totalPriceElm.getText().replace(".", "").replace("₫", ""));

        // 7.3 Get number of items by xpath.
        WebElement unitElm = driver.findElement(By.xpath("//input[@class='input']"));
        int unit = Integer.parseInt(unitElm.getAttribute("value"));

        // 7.4 Campare expected total price to the actual.
        int expectedPrice = unitPrice * unit;
        Assertions.assertEquals(expectedPrice, totalPrice);

        // Wait for a few seconds (for demonstrationpurposes only)
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 8. close browser
        driver.close();
    }

    @ParameterizedTest
    @CsvSource("REGULAR, STANDARD, 1000, NON_SALARIED, 0")
    public void checkCalculateCommission(String customerType, String itemType, double price, String employeeType, Double expected) {
        // 1. Create an instance of ChromeDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // 2. Access the home page of tiki webiste
        driver.get("http://localhost:8080/");

        // 3. close popup if it's shown
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // 4. input data into form
        WebElement customerTypeElm = driver.findElement(By.id("customerType"));
        customerTypeElm.sendKeys(customerType);

        WebElement itemTypeElm = driver.findElement(By.id("itemType"));
        itemTypeElm.sendKeys(itemType);

        WebElement priceElm = driver.findElement(By.id("itemPrice"));
        priceElm.sendKeys(String.valueOf(price));

        WebElement employeeTypeElm = driver.findElement(By.id("employeeType"));
        employeeTypeElm.sendKeys(employeeType);

        // 5. click submit button
        WebElement submitBnt = driver.findElement(By.id("submit"));
        submitBnt.click();

        // 6. get result
        WebElement resultElm = driver.findElement(By.id("result"));
        double result = Double.parseDouble(resultElm.getAttribute("value"));

        // 7. check result
        Assertions.assertEquals(expected, result);
    }
}
