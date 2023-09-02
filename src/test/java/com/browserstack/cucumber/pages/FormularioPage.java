package com.browserstack.cucumber.pages;


import io.appium.java_client.AppiumBy;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.Point;



public class FormularioPage {

    public static final Target CARD_INPUT = Target.the("Diligenciar campo").located(AppiumBy.accessibilityId("Inputs Screen "));

    private FormularioPage() {
        throw new IllegalStateException("user interface class");
    }

}
