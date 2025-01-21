package TESTS;

import POMCLASSES.CartPageElements;
import POMCLASSES.HomePageElements;
import POMCLASSES.ProductPageElements;
import UTILS.BaseDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.rmi.NoSuchObjectException;
import java.time.Duration;
import java.util.List;
import java.util.Set;

public class SmokeTest extends BaseDriver {

    public HomePageElements homePageElements;
    public ProductPageElements productPageElements;
    public CartPageElements cartPageElements;


    // Fluent Wait nesnesi oluşturma
    FluentWait<WebDriver> wait = new FluentWait<>(driver)
            .withTimeout(Duration.ofSeconds(30)) // Maksimum bekleme süresi 30 saniye
            .pollingEvery(Duration.ofSeconds(2)) // Her 2 saniyede bir kontrol et
            .ignoring(StaleElementReferenceException.class, NoSuchObjectException.class); // NoSuchElementException hatasını görmezden gel



    @Test(priority=1)
    public void navigateToHomePageTest() throws InterruptedException {

        homePageElements = new HomePageElements(driver);

        //Home Page'e giderek doğru sayfa mı kontrol ediyorum
        driver.navigate().to("https://www.watsons.com.tr/");


        String actualHomePageUrl= driver.getCurrentUrl();
        //wait.until(ExpectedConditions.urlContains("watsons.com.tr/"));
        System.out.println("ACTUAL HOME PAGE URL = "+ actualHomePageUrl);
        String expectedHomePageUrl="https://www.watsons.com.tr/";

        Assert.assertTrue(actualHomePageUrl.contains(expectedHomePageUrl));

        Thread.sleep(Duration.ofSeconds(5));

        //Cookie Handle Etme (Accept)
        wait.until(ExpectedConditions.elementToBeClickable(homePageElements.cookiePrefAcceptButton));
        homePageElements.cookiePrefAcceptButton.click();

}

    @Test(priority = 2)
    public void searchBoxTest() throws InterruptedException {


        // Actions sınıfından bir nesne oluşturma
        Actions actions = new Actions(driver);

        homePageElements = new HomePageElements(driver);
        productPageElements= new ProductPageElements(driver);

        wait.until(ExpectedConditions.visibilityOf(homePageElements.searchBox));
        homePageElements.searchBox.sendKeys("rebul aqua");
        actions.sendKeys(Keys.ENTER).perform();


        System.out.println("ÜRÜN SAYFASI URL= "+ driver.getCurrentUrl());


        Thread.sleep(Duration.ofSeconds(5));

        List<WebElement> searchSonucuGelenTumUrunlerListesi = driver.findElements(By.xpath("//div[@class='product-tile__container']//div//a"));
        for (int i = 0; i < searchSonucuGelenTumUrunlerListesi.size(); i++) {
            System.out.println("SEARCH SONUCU GELEN TÜM ÜRÜNLERİN LİSTESİ= " + searchSonucuGelenTumUrunlerListesi.get(i).getText());
        }

        Assert.assertFalse(searchSonucuGelenTumUrunlerListesi.isEmpty());


}
    @Test(priority = 3)
    public void addToCartTest() throws InterruptedException {

        productPageElements = new ProductPageElements(driver);

        wait.until(ExpectedConditions.elementToBeClickable(productPageElements.rebul1AddToCartButton));
        productPageElements.rebul1AddToCartButton.click();

        Thread.sleep(Duration.ofSeconds(5));

        String actualDialogBoxTitle = productPageElements.dialogBoxTitle.getText();
        String expectedDialogBoxTitle= "ÜRÜNÜN SEPETE EKLENDİ";
        System.out.println("actualDialogBoxTitle ="+actualDialogBoxTitle);
        System.out.println("expectedDialogBoxTitle ="+expectedDialogBoxTitle);


        Assert.assertTrue(actualDialogBoxTitle.contains(expectedDialogBoxTitle));
        Assert.assertEquals(actualDialogBoxTitle,expectedDialogBoxTitle);

    }

    @Test(priority = 4)
    public void navigateToCartByDialogBox() throws InterruptedException {

        productPageElements = new ProductPageElements(driver);
        cartPageElements = new CartPageElements(driver);

        wait.until(ExpectedConditions.elementToBeClickable(productPageElements.dialogBoxGoToCartOption));
        productPageElements.dialogBoxGoToCartOption.click();

        wait.until(ExpectedConditions.visibilityOf(cartPageElements.productItemName));


        String actualProductItemNameInCartPage = cartPageElements.productItemName.getText();
        String expectedProductItemNameInCart="Rebul Aqua Kolonya 50 ml";
        System.out.println("expectedProductNameInCart ="+expectedProductItemNameInCart);
        System.out.println("actualProductItemNameInCartPage ="+ actualProductItemNameInCartPage);


        Assert.assertTrue(actualProductItemNameInCartPage.contains(expectedProductItemNameInCart));
        Assert.assertEquals(actualProductItemNameInCartPage,expectedProductItemNameInCart);

        }

    @Test(priority = 5)
    public void updateCartTest() throws InterruptedException {

        cartPageElements= new CartPageElements(driver);

        wait.until(ExpectedConditions.elementToBeClickable(cartPageElements.quantityIncreaseButton));
        cartPageElements.quantityIncreaseButton.click();

        Thread.sleep(Duration.ofSeconds(5));

        String lastQuantity= cartPageElements.currentQuantity.getAttribute("current-value");
        System.out.println("PRODUCT QUANTITY AFTER UPDATE ="+ lastQuantity);

        Assert.assertEquals(lastQuantity,"2");


    }

    @Test(priority = 6)
    public void deleteCartTest(){
        cartPageElements= new CartPageElements(driver);

        cartPageElements.deleteCartButton.click();

        wait.until(ExpectedConditions.elementToBeClickable(cartPageElements.deleteConfirmButton));
        cartPageElements.deleteConfirmButton.click();

        wait.until(ExpectedConditions.visibilityOf(cartPageElements.deleteSuccessMessage));
        String actualCartRemovalMessage = cartPageElements.deleteSuccessMessage.getText();
        System.out.println("AFTER DELETE ACTUAL MESSAGE= "+ actualCartRemovalMessage);



    }



    }

