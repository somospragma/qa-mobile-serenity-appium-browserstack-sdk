package com.browserstack.cucumber.interactions.builders;


import com.browserstack.cucumber.interactions.Scroll;
import com.browserstack.cucumber.models.AreaType;
import com.browserstack.cucumber.models.ScrollDirection;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.targets.Target;


import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ScrollBuilder {

    private Target target;
    private ScrollDirection direction;
    private AreaType areaType;
    private Target referenceAreaElement;

    public ScrollBuilder untilVisibleTarget(Target target) {
        this.target=target;
        return this;
    }

    public ScrollBuilder withoutVisibleTargetReference() {
        this.target=null;
        return this;
    }

    public ScrollBuilder direction(ScrollDirection direction){
        this.direction = direction;
        return this;
    }

    public ScrollBuilder overAreaType(AreaType areaType){
        this.areaType = areaType;
        return this;
    }

    public ScrollBuilder ofReferenceAreaElement(Target referenceAreaElement){
        this.referenceAreaElement = referenceAreaElement;
        return this;
    }

    public Performable untilMaxAttempts(int attempts){
        return  instrumented (Scroll.class, target, direction, attempts,AreaType.FULL_SCREEN, null);
    }

    public Performable untilMaxAttemptsOverReferenceArea(int attempts){
        return  instrumented (Scroll.class, target, direction, attempts, areaType, referenceAreaElement);
    }

}
