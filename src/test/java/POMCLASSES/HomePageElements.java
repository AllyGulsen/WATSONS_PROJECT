package POMCLASSES;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageElements {

    //Sınıf adıyla bir Constructor oluşturdum
    public HomePageElements(WebDriver driver){PageFactory.initElements(driver,this);}

    @FindBy(id = "onetrust-accept-btn-handler")
    public WebElement cookiePrefAcceptButton;

    @FindBy(css = "input[placeholder='Arama Yap']")
    public WebElement searchBox;







}
