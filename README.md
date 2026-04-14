# Proyecto de Automatización de Pruebas: SauceDemo

Este proyecto utiliza **Selenium WebDriver**, **JUnit 5** y el patrón de diseño **Page Object Model (POM)** para automatizar y validar el proceso de autenticación en la web de pruebas [SauceDemo](https://www.saucedemo.com/).

## 🚀 Estructura del Proyecto

* **`LoginPage.java`**: Clase que representa la página de login. Contiene los localizadores (`By`) y los métodos de interacción como `ingresarUsuario`, `ingresarPassword` y el método simplificado `login`.
* **`LoginTests.java`**: Clase que contiene los casos de prueba automatizados para verificar flujos exitosos y fallidos.

## 🛠️ Tecnologías Utilizadas

* **Java**: Lenguaje de programación base.
* **Selenium WebDriver**: Herramienta para la automatización de acciones en el navegador.
* **JUnit 5**: Framework para la gestión y ejecución de las pruebas.

Este proyecto documenta el desarrollo y la evolución de un set de pruebas automatizadas utilizando **Selenium WebDriver**, **JUnit 5** y el patrón de diseño **Page Object Model (POM)**. El objetivo es validar el flujo de autenticación en la plataforma [SauceDemo](https://www.saucedemo.com/).

## 📋 Proceso de Desarrollo Paso a Paso

### 1. Fork Inicial y Configuración
El proyecto se inició mediante un **Fork** del repositorio base que contenía la estructura esencial de carpetas y dependencias de Maven. Se configuró el entorno para que el navegador se inicie y cierre correctamente entre pruebas.

### 2. Implementación de Acciones en `LoginPage`
Se rellenaron los métodos vacíos en la clase `pages.LoginPage`. En esta etapa, definimos las acciones atómicas necesarias para interactuar con los elementos del DOM:
* **`ingresarUsuario(String user)`**: Localiza el campo y envía el texto.
* **`ingresarPassword(String pass)`**: Localiza el campo de clave.
* **`clickLogin()`**: Ejecuta la acción de pulsado del botón.

### 3. Depuración Visual con `Thread.sleep`
Para poder supervisar visualmente el comportamiento de la automatización, añadimos pausas controladas. Debido a que `Thread.sleep` puede ser interrumpido, se actualizó la firma de los métodos incluyendo `throws InterruptedException`.

### 4. Creación del Método Consolidado `login`
Para optimizar el código y evitar la repetición en los tests, se creó una acción maestra en `LoginPage` que realiza todo el ciclo. Este método incluye la limpieza de los campos.

### 5. Creación de los Casos de Prueba
Finalmente, se implementaron los tests en la clase de pruebas utilizando el nuevo método login():

loginCorrecto(): Valida que un usuario válido (standard_user) accede correctamente y es redirigido a la página de inventario.

loginIncorrecto(): Valida que un usuario falso no puede acceder y que la URL no cambia.

📝 Cuestionario Técnico
#### 1. ¿Qué hace la anotación `@BeforeEach`?
La anotación de **JUnit** es una de las herramientas fundamentales de JUnit 5 (el framework estándar para pruebas unitarias en Java).
En términos sencillos: le dice a JUnit que el método marcado con esta anotación debe ejecutarse **antes de cada uno** de los métodos de prueba (@Test) de la clase.
* Su objetivo principal es la **inicialización del entorno de prueba** (setup). Se utiliza para garantizar que cada prueba comience desde un estado limpio y conocido, evitando que los cambios realizados por un test afecten
* **Utilidad**: Se usa para inicializar el driver, configurar el navegador y abrir la página, asegurando que cada prueba comience desde un estado limpio e independiente.

#### 2. ¿Para qué sirve `assertTrue`?
En el mundo de las pruebas unitarias con **JUnit**, la aserción assertTrue es una de las más utilizadas. Su función es extremadamente simple pero vital: **verifica que una condición booleana sea verdadera** (true).
Si la condición que le pasas es true, la prueba continúa con éxito. Si es false, la prueba falla inmediatamente.

* **Resultado**: Si la condición falla (es falsa), el test se detiene automáticamente y se marca como **fallido**, notificando que el resultado esperado no se cumplió.

#### 3. ¿Qué diferencia hay entre `findElement()` y `findElements()`?

| Método | Retorno | Comportamiento si no hay coincidencias |
| :--- | :--- | :--- |
| **`findElement()`** | El **primer** `WebElement` encontrado. | Lanza una excepción `NoSuchElementException` (detiene el test). |
| **`findElements()`** | Una **lista** (`List<WebElement>`). | Devuelve una lista vacía (permite que el test continúe). |

#### 4. ¿Por qué utilizamos una clase `LoginPage` en lugar de escribir todo en el test?
Patrón de Diseño: Page Object Model (POM)

El uso de clases específicas para cada página (como `LoginPage`) es la base de la automatización de pruebas profesional. Este patrón nos permite separar la interfaz de usuario de la lógica de los tests.

---

 ¿Por qué usamos Page Objects?

 1. Reutilización de código (DRY - Don't Repeat Yourself)
Imagina que tienes 20 pruebas diferentes que requieren iniciar sesión.

* **Sin LoginPage:** Tendrías que escribir el código para buscar el selector del usuario, la contraseña y el botón en los 20 tests. Si el ID del botón cambia en la aplicación, tendrías que editar **20 archivos**.
* **Con LoginPage:** Defines el selector y el método `login()` **una sola vez**. Si algo cambia en la interfaz, solo lo corriges en un lugar y todos los tests se actualizan automáticamente.

 2. Abstracción y Legibilidad
El test debe leerse como una serie de pasos de negocio, no como una lista de comandos técnicos.

* **❌ Código sucio (dentro del test):**
    ```java
    driver.findElement(By.id("user-123")).sendKeys("admin");
    ```
* **✅ Código limpio (usando POM):**
    ```java
    loginPage.ingresarCredenciales("admin", "1234");
    ```

Esto permite que cualquier persona (incluso sin conocimientos profundos de programación) entienda qué flujo está validando la prueba.

 3. Separación de Responsabilidades
El patrón POM separa el **QUÉ** del **CÓMO**:



* **La Clase de Prueba (Test):** Se encarga de la lógica de negocio y las verificaciones (`assertions`). Su función es decidir **qué** probar.
* **La Clase de Página (PageObject):** Se encarga de la interacción técnica con el navegador. Sabe **cómo** encontrar los elementos y manipularlos.

---

 Comparativa de Mantenimiento

| Característica | Sin POM (Scripts lineales) | Con POM (Estructurado) |
| :--- | :--- | :--- |
| **Mantenimiento** | Muy laborioso y propenso a errores. | Centralizado y rápido. |
| **Legibilidad** | Difícil de seguir (código espagueti). | Clara (lenguaje orientado a acciones). |
| **Escalabilidad** | El proyecto se vuelve inmanejable. | Permite crecer a cientos de tests. |
---
*Documentación de aprendizaje - Proyecto de Automatización 2026.*
