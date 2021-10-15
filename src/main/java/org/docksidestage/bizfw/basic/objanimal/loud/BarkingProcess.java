/**
 * @author Ren
 */

package org.docksidestage.bizfw.basic.objanimal.loud;

import org.docksidestage.bizfw.basic.objanimal.Animal;
import org.docksidestage.bizfw.basic.objanimal.BarkedSound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BarkingProcess {
    private static final Logger logger = LoggerFactory.getLogger(Animal.class);

    public BarkedSound bark() {
        breatheIn();
        prepareAbdominalMuscle();
        String barkWord = getBarkWord();
        BarkedSound barkedSound = doBark(barkWord);
        return barkedSound;
    }

    protected void breatheIn() {
        logger.debug("...Breathing in"); // dummy implementation
        downHitPoint();
    }

    protected void prepareAbdominalMuscle() {
        logger.debug("...Using my abdominal muscle"); // dummy implementation
        downHitPoint();
    }

    protected BarkedSound doBark(String barkWord) {
        downHitPoint();
        return new BarkedSound(barkWord);
    }

    protected abstract String downHitPoint();

    protected abstract String getBarkWord();
}
