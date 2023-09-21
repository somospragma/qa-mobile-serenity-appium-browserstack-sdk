package com.browserstack.cucumber.ui;


import io.appium.java_client.AppiumBy;
import net.serenitybdd.screenplay.targets.Target;

public class PrincipalPage {

    public static final Target CARD_BUTTONS_DEMO = Target.the("Seleccionar demo botones").located(AppiumBy.accessibilityId("Buttons Demo"));


    private PrincipalPage() {
        throw new IllegalStateException("user interface class");
    }
}
