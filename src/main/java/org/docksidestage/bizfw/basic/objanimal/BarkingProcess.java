package org.docksidestage.bizfw.basic.objanimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author nagano
 * */

public class BarkingProcess {

    private static final Logger logger = LoggerFactory.getLogger(Animal.class);
    protected String barkWord;
    protected int hp;

    public BarkingProcess(String barkWord) {
        this.barkWord = barkWord;
    }

    public int bark(int hitpoint) {
        hp = hitpoint;
        return hp;
    }

    protected void breatheIn() {
        logger.debug("...Breathing in"); // dummy implementation
        downHitPoint();
    }

    protected void downHitPoint() {
        --hp;
        if (hp == 0) {
            throw new IllegalStateException("I'm very tired, so I want to sleep" + barkWord);
        }
    }

}
