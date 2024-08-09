package autoTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private final WebDriverWait wait;

    @FindBy(xpath = "//*[@id='user_email']")
    private WebElement emailInputField;

    @FindBy(xpath = "//*[@type='password']")
    private WebElement passwordInputField;

    @FindBy(xpath = "//*[@data-testid='login-submit']")
    private WebElement loginButton;

    @FindBy(xpath = "//*[@class='parsley-errors-list filled']")
    private WebElement emailFieldVerification;

    @FindBy(xpath = "//*[@id='parsley-id-7']")
    private WebElement passwordFieldRequiredText;


    public LoginPage(WebDriver driver, WebDriverWait wait) {
        PageFactory.initElements(driver, this);
        this.wait = wait;
    }

    public void authorizationNotEmailLogin(String email, String password) {
        typeEmailInField(email);
        typePasswordInField(password);
        clickLoginButton();
    }
    public void authorizationByEnteringOnlyEmail(String email) {
        typeEmailInField(email);
        clickLoginButton();
    }

    public void typeEmailInField(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailInputField)).sendKeys(email);
    }

    public void typePasswordInField(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordInputField)).sendKeys(password);
    }

    public void clickLoginButton() {
        wait.until(ExpectedConditions.visibilityOf(loginButton)).click();
    }


    public String getEmailFieldVerificationText() {
        return wait.until(ExpectedConditions.visibilityOf(emailFieldVerification))
                .getText();
    }
    public String getPasswordFieldRequiredText() {
        return wait.until(ExpectedConditions.visibilityOf(passwordFieldRequiredText))
                .getText();
    }

}
