package com.browserstack.cucumber.steps;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.thucydides.core.steps.StepEventBus;
import net.thucydides.model.domain.TestOutcome;
import net.thucydides.model.domain.TestResult;
import net.thucydides.model.environment.SystemEnvironmentVariables;
import net.thucydides.model.util.EnvironmentVariables;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Logger;


public class Hooks {

    /*
        for iOS use:
            public static IOSDriver driver;
    */
    public static AndroidDriver driver;
    public static Actor user;


    @Before
    public void setup() throws InterruptedException, MalformedURLException {
        OnStage.setTheStage(new OnlineCast());
        MutableCapabilities options = new UiAutomator2Options();
        /*
        for iOS use:
            MutableCapabilities options = new XCUITestOptions();
        */
        EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();
        String url = "https://"+variables.getProperty("browserstack.userName")+":"+variables.getProperty("browserstack.accessKey")+"@hub.browserstack.com/wd/hub";
        Logger.getAnonymousLogger().info("URL server: " + url);
        try {
            driver = new AndroidDriver(new URL(url),options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @After()
    public void tearDown() throws Exception {

        final JavascriptExecutor jse = (JavascriptExecutor) driver;

        int maxRetries = 3; //max retries attempts setup in pom.xml + 1
        File archivo = new File("attemptsTest.txt");
        int attempts = 0;

        if (archivo.exists()) {
                try {
                    // Leer el número del archivo
                    Scanner scanner = new Scanner(archivo);
                    if (scanner.hasNextInt()) {
                        attempts = scanner.nextInt();
                    }
                    scanner.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

        // Incrementar el número
        attempts++;

        // Guardar el nuevo número en el archivo
        try {
            FileWriter fw = new FileWriter(archivo);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(Integer.toString(attempts));
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Retry Count: " + attempts);

        TestOutcome testOutcome = StepEventBus.getEventBus().getBaseStepListener().latestTestOutcome().orElse(null);

        if (testOutcome != null) {
            TestResult resultado = testOutcome.getResult();
            String errorMessage = testOutcome.getConciseErrorMessage().replaceAll("Build info.*$", "").replaceAll("\"", "'");
            if (resultado == TestResult.SUCCESS) {
                Logger.getAnonymousLogger().info("La prueba pasó.");
                jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Execution without problems!\"}}");
                archivo.delete();
            } else if (resultado == TestResult.FAILURE && attempts >= maxRetries) {
                Logger.getAnonymousLogger().warning("La prueba falló. " + errorMessage);
                jse.executeScript(String.format("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Test Failure: '%s' \"}}", errorMessage));
                archivo.delete();
            } else if (resultado == TestResult.ERROR && attempts >= maxRetries) {
                Logger.getAnonymousLogger().warning("La prueba tuvo un error.");
                jse.executeScript(String.format("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Test with errors: '%s' \"}}", errorMessage));
                archivo.delete();
            } else if (resultado == TestResult.COMPROMISED && attempts >= maxRetries) {
                Logger.getAnonymousLogger().warning("La prueba está comprometida.");
                jse.executeScript(String.format("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Test with compromised: '%s' \"}}", errorMessage));
                archivo.delete();
            } else if (resultado == TestResult.IGNORED && attempts >= maxRetries) {
                Logger.getAnonymousLogger().warning("La prueba fue ignorada.");
                jse.executeScript(String.format("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Test ignored: '%s' \"}}", errorMessage));
                archivo.delete();
            } else if (resultado == TestResult.PENDING && attempts >= maxRetries) {
                Logger.getAnonymousLogger().warning("La prueba está pendiente.");
                jse.executeScript(String.format("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Test pending: '%s' \"}}", errorMessage));
                archivo.delete();
            } else if (resultado == TestResult.SKIPPED && attempts >= maxRetries) {
                Logger.getAnonymousLogger().warning("La prueba fue omitida.");
                jse.executeScript(String.format("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Test skipped: '%s' \"}}", errorMessage));
                archivo.delete();
            } else if (resultado == TestResult.UNDEFINED && attempts >= maxRetries) {
                Logger.getAnonymousLogger().warning("Resultado de la prueba no definido.");
                jse.executeScript(String.format("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Test undefined: '%s' \"}}", errorMessage));
                archivo.delete();
            }
        }

        driver.quit();

    }
}
