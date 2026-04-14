package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

        private WebDriver driver;
        private LoginPage loginPage;

        @BeforeEach
        void setUp() {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                driver.manage().window().maximize();

                driver.get("https://www.saucedemo.com/");

                loginPage = new LoginPage(driver);
        }

        @AfterEach
        void tearDown() {
                if (driver != null) {
                        driver.quit();
                }
        }

    @Test
    void loginCorrecto() throws InterruptedException {

// introduce un usuario válido
        loginPage.ingresarUsuario("standard_user");
        Thread.sleep(2000);

// introduce una contraseña válida
        loginPage.ingresarPassword("secret_sauce");
        Thread.sleep(2000);

// pulsa el botón de login
        loginPage.clickLogin();
        Thread.sleep(2000);

// comprueba que la URL contiene la palabra "inventory"
        String urlActual = driver.getCurrentUrl();
        assertTrue(urlActual.contains("inventory"), "La URL no es la esperada tras el login");
        Thread.sleep(2000);
    }

    @Test
    void loginIncorrecto() throws InterruptedException {
            // introduce un usuario inválido
        loginPage.ingresarUsuario("usuario_falso");
        Thread.sleep(2000);

// introduce una contraseña válida
        loginPage.ingresarPassword("password_falso");
        Thread.sleep(2000);

// pulsa el botón de login
        loginPage.clickLogin();
        Thread.sleep(2000);

// comprueba que la URL contiene la palabra "inventory"
        String urlActual = driver.getCurrentUrl();
        assertFalse(urlActual.contains("inventory"), "El login fue exitoso a pesar de usar datos incorrectos");

    }
    //Test correcto de la nueva accion
    @Test
    void loginCorrect2() throws InterruptedException {
        loginPage.login("standard_user", "secret_sauce");
        assertTrue(driver.getCurrentUrl().contains("inventory"), "No se redirigió a la página de inventario");
    }
    //Test incorrecto de la nueva accion
    @Test
    void loginIncorrect2() throws InterruptedException {
            loginPage.login("usuario_incorrect", "password_incorrect");
        assertFalse(driver.getCurrentUrl().contains("inventory"));
    }


}