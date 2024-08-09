package autoTest;

import org.junit.jupiter.api.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class UITest extends AbstractTest{

    static LoginPage loginPage;

    private static final String login = "login";
    private static final String loginEmail = "login@login.ru";
    private static final String password = "password";
    private static final String baseURL = "https://gb.ru/login";


    @BeforeEach
    void init() {
        driver.get(baseURL);
        loginPage = new LoginPage(driver, wait);
    }


    @Test
    void testGBNotEmailLogin() throws IOException {
        loginPage.authorizationNotEmailLogin(login, password);
        loginPage.clickLoginButton();
        Assertions.assertFalse((loginPage.getEmailFieldVerificationText()).isEmpty());
        Assertions.assertEquals("Введите адрес электронной почты.",
                loginPage.getEmailFieldVerificationText());
        getScreen();
    }
    

    @Test
    void testGBWithoutPassword() throws IOException {
        loginPage.authorizationByEnteringOnlyEmail(loginEmail);
        loginPage.clickLoginButton();
        Assertions.assertFalse(loginPage.getPasswordFieldRequiredText().isEmpty());
        Assertions.assertEquals("Обязательное поле.",
                loginPage.getPasswordFieldRequiredText());
        getScreen();
    }


    private void getScreen() throws IOException {
        byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        Files.write(Path.of(
                "src/test/resources/screenshot_"
                        + System.currentTimeMillis() + ".png"), screenshotBytes);
    }

}
