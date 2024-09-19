package com.browserstack.cucumber.steps;


import com.browserstack.cucumber.interactions.Scroll;
import com.browserstack.cucumber.models.ScrollDirection;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.net.MalformedURLException;

import static com.browserstack.cucumber.steps.Hooks.driver;
import static com.browserstack.cucumber.ui.FormularioPage.*;
import static com.browserstack.cucumber.ui.PrincipalPage.CARD_BUTTONS_DEMO;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

import static com.browserstack.cucumber.steps.Hooks.user;


public class FormInputsScreenStepDefinitions {

    @Given("^the user is in Inputs Screen$")
    public void the_user_is_in_Inputs_Screen() throws InterruptedException, MalformedURLException {

        user = Actor.named("pragmatico");
        user.can(BrowseTheWeb.with(driver).asActor(user));
        user.attemptsTo(
                WaitUntil.the(CARD_BUTTONS_DEMO, isVisible()).forNoMoreThan(5).seconds(),
                Scroll.untilVisibleTarget(CARD_INPUT)
                        .direction(ScrollDirection.TO_BOTTOM)
                        .untilMaxAttempts(2),
                /*
                Scroll.untilVisibleTarget(CommonOnFinancePages.SEGUROS_SELECT)
                                .direction(ScrollDirection.TO_BOTTOM)
                                .overAreaType(AreaType.CONTAINER)
                                .ofReferenceAreaElement(CommonOnFinancePages.CATEGORIES_CONTAINER)
                                .untilMaxAttemptsOverReferenceArea(4),
                 */
                Click.on(CARD_INPUT)
        );
    }

    @When("fill out the name with the {word} suggest with {word}")
    public void fill_out_the_name_with_the_first_suggest_with_a(String option, String letter) {
//        user.attemptsTo(
//                Click.on(INPUT_ONE),
//                Enter.theValue("a").into(INPUT_ONE),
//                HideKeyboard.perform(),
//                TapOnCoordinates.onPoint(THIRD_OPTION_AUTOCOMPLETE)
//        );
    }

}
