package com.browserstack.cucumber.ui;


import io.appium.java_client.AppiumBy;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.Point;

import static com.browserstack.cucumber.models.Coordinates.withCoordinates;


public class FormularioPage {

    public static final Target CARD_INPUT = Target.the("Diligenciar campo").located(AppiumBy.accessibilityId("Inputs Screen "));
    public static final Target INPUT_ONE = Target.the("Escribir en input uno").located(AppiumBy.xpath("//*[@class='android.widget.EditText'][1]"));
    public static final Point THIRD_OPTION_AUTOCOMPLETE = withCoordinates(150,990);

    private FormularioPage() {
        throw new IllegalStateException("user interface class");
    }

}
