package POMCLASSES;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPageElements {

    public CartPageElements(WebDriver driver){PageFactory.initElements(driver,this);}

    @FindBy(xpath = "(//div[text()='Rebul Aqua Kolonya 50 ml'])[2]")
    public WebElement productItemName;

    @FindBy(css = "button[class='quantity-counter__button quantity-counter__button--inc']")
    public WebElement quantityIncreaseButton;

    @FindBy(xpath = "//e2-quantity-counter[@current-value]")
    public WebElement currentQuantity;

    @FindBy(xpath = "(//a[@class='remove-cart-entry'])[2]")
    public WebElement deleteCartButton;

    @FindBy(css = "button[class='button  button--primary button--uppercase button--full-width button--text-center']")
    public WebElement deleteConfirmButton;

    @FindBy(xpath = "//p[text()='Ürününüz başarıyla silindi!']")
    public WebElement deleteSuccessMessage;
}
