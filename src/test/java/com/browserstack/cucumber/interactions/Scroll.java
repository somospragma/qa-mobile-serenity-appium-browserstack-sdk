package com.browserstack.cucumber.interactions;


import com.browserstack.cucumber.interactions.builders.ScrollBuilder;
import com.browserstack.cucumber.models.AreaType;
import com.browserstack.cucumber.models.ScrollDirection;
import io.appium.java_client.AppiumDriver;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.List;
import java.util.logging.Logger;


public class Scroll implements Interaction {
    private final Target target;
    private final int attempts;
    private final ScrollDirection direction;
    private final AreaType areaType;
    private final Target referenceAreaElement;

    public Scroll(Target target, ScrollDirection direction, int attempts, AreaType areaType, Target referenceAreaElement) {
        this.target = target;
        this.direction = direction;
        this.attempts = attempts;
        this.areaType = areaType;
        this.referenceAreaElement = referenceAreaElement;
    }

    public static ScrollBuilder untilVisibleTarget(Target target){
        return new ScrollBuilder().untilVisibleTarget(target);
    }

    public static ScrollBuilder withoutVisibleTargetReference(){
        return new ScrollBuilder().withoutVisibleTargetReference();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        AppiumDriver driver = (AppiumDriver) BrowseTheWeb.as(actor).getDriver();
        int centerX=0;
        int startY=0;
        int endY=0;

        if(areaType == AreaType.FULL_SCREEN){
            var dimension = driver.manage().window().getSize();
            centerX = dimension.width / 2;
            startY = dimension.height / 2;
            endY = (direction.equals(ScrollDirection.TO_BOTTOM)) ? (dimension.height / 4) : (dimension.height - (dimension.height / 4));
        }

        if(areaType == AreaType.CONTAINER){
            var dimension =referenceAreaElement.resolveFor(actor).getElement().getSize();
            var location =referenceAreaElement.resolveFor(actor).getElement().getLocation();
            centerX = location.x + (dimension.width / 2);
            startY = location.y + (dimension.height / 2);
            endY = (direction.equals(ScrollDirection.TO_BOTTOM)) ? location.y : (location.y + dimension.height);
        }

        var currentAttempt = 0;
        boolean elementFound = false;

        if (target!=null){
            while (currentAttempt <= attempts) {

                try {
                    if (target.resolveFor(actor).isVisible() && target.resolveFor(actor).isEnabled()) {
                        elementFound = true;
                        break;  // Salimos del bucle si encontramos el elemento
                    }
                } catch (org.openqa.selenium.NoSuchElementException e) {
                    Logger.getAnonymousLogger().info("Elemento no encontrado en el intento: " + currentAttempt);
                }

                doScroll(driver,centerX,startY,endY);

                currentAttempt++;
            }

            if (!elementFound) {
                Logger.getAnonymousLogger().info("El elemento no se encontró después de " + attempts + " intentos de scroll.");
            }
        }else {
            while (currentAttempt <= attempts) {
                doScroll(driver,centerX,startY,endY);
                currentAttempt++;
            }
        }
    }

    private void doScroll(AppiumDriver driver, int centerX, int startY, int endY ){

        //Type of Pointer Input
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH,"finger");
        //Creating Sequence object to add actions
        Sequence scroll = new Sequence(finger,1);
        //Move finger into starting position
        scroll.addAction(finger.createPointerMove(Duration.ofSeconds(0), PointerInput.Origin.viewport(),centerX,startY));
        //Finger comes down into contact with screen
        scroll.addAction(finger.createPointerDown(0));
        //Finger moves to end position
        scroll.addAction(finger.createPointerMove(Duration.ofMillis(700), PointerInput.Origin.viewport(),centerX, endY));
        //Get up Finger from Srceen
        scroll.addAction(finger.createPointerUp(0));

        //Perform the actions
        driver.perform(List.of(scroll));
    }
}
