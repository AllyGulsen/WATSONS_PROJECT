package POMCLASSES;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPageElements {

    public ProductPageElements(WebDriver driver){PageFactory.initElements(driver,this);}

    @FindBy(xpath = "(//span[text()=' Sepete Ekle'])[2]")
    public WebElement rebul1AddToCartButton;

    @FindBy(css = "div[class='minicart__quantity-badge minicart__quantity-badge--quantity-decades']")
    public WebElement cartQuantityBadge;

    @FindBy(xpath = "//div[@class='product-cart-popup__action-buttons']//a")
    public WebElement dialogBoxGoToCartOption;

    @FindBy(css = "h2[class='product-cart-popup__title']")
    public WebElement dialogBoxTitle;


}
