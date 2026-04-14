package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;

    private By userField = By.id("user-name");
    private By passField = By.id("password");
    private By loginBtn = By.id("login-button");

    private By errorMsg = By.cssSelector("h3[data-test='error']");

    public String obtenerMensajeError() {
        // Devuelve el texto del error para poder validarlo
        return driver.findElement(errorMsg).getText();
    }

    public boolean esVisibleElError() {
        // Devuelve true si el mensaje está desplegado
        return driver.findElement(errorMsg).isDisplayed();
    }

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void ingresarUsuario(String user) {
        driver.findElement(userField).clear();
        driver.findElement(userField).sendKeys(user);
    }

    public void ingresarPassword(String pass) {
        driver.findElement(passField).clear();
        driver.findElement(passField).sendKeys(pass);
    }

    public void clickLogin() {
        driver.findElement(loginBtn).click();
    }

    public void login(String usuario, String password) throws InterruptedException {
        driver.findElement(userField).clear();
        driver.findElement(userField).sendKeys(usuario);
        Thread.sleep(2000);
        driver.findElement(passField).clear();
        driver.findElement(passField).sendKeys(password);
        Thread.sleep(2000);
        driver.findElement(loginBtn).click();
        Thread.sleep(2000);
    }
}