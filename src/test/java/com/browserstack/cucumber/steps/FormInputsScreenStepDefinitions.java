package com.browserstack.cucumber.steps;

import com.browserstack.cucumber.interactions.HideKeyboard;
import com.browserstack.cucumber.interactions.Scroll;
import com.browserstack.cucumber.interactions.TapOnCoordinates;
import com.browserstack.cucumber.models.ScrollDirection;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.MutableCapabilities;


import java.net.MalformedURLException;
import java.net.URL;

import static com.browserstack.cucumber.ui.FormularioPage.*;
import static com.browserstack.cucumber.ui.PrincipalPage.CARD_BUTTONS_DEMO;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;


public class FormInputsScreenStepDefinitions {

    public AndroidDriver driver;
    public Actor pragma = Actor.named("pragma");

    @Given("^the user is in Inputs Screen$")
    public void the_user_is_in_Inputs_Screen() throws InterruptedException, MalformedURLException {
        OnStage.setTheStage(new OnlineCast());
        MutableCapabilities capabilities = new UiAutomator2Options();
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
        Actor pragma = Actor.named("pragma");
        pragma.can(BrowseTheWeb.with(driver).asActor(pragma));
        pragma.attemptsTo(
                WaitUntil.the(CARD_BUTTONS_DEMO, isVisible()).forNoMoreThan(5).seconds(),
                Scroll.untilVisibleTarget(CARD_INPUT).direction(ScrollDirection.TO_BOTTOM).untilMaxAttempts(2),
                Click.on(CARD_INPUT)
        );
    }

    @When("fill out the name with the {word} suggest with {word}")
    public void fill_out_the_name_with_the_first_suggest_with_a(String option, String letter) {
//        pragma.attemptsTo(
//                Click.on(INPUT_ONE),
//                Enter.theValue("a").into(INPUT_ONE),
//                HideKeyboard.perform(),
//                TapOnCoordinates.onPoint(THIRD_OPTION_AUTOCOMPLETE)
//        );
    }
    @After()
    public void tearDown() throws Exception {
        driver.quit();
    }

}
