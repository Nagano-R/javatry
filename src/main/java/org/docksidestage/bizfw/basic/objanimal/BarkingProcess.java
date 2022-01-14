/**
 * @author Ren
 * */

package org.docksidestage.bizfw.basic.objanimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BarkingProcess {

    public Animal animal;

    public BarkingProcess(Animal animal) {
        this.animal = animal;
    }

    private static final Logger logger = LoggerFactory.getLogger(Animal.class);

    /*
     * 元々BarkingProcessクラスはloudパッケージ配下に作っていたけれど、
     * そこだとBarkedSoundクラスを参照できなかった。
     * BarkedSoundはpublicだからパッケージの階層が違っても参照できるはずだけれど？？？
     * （解決済？）
     * */
    public BarkedSound barkProcess(String barkWord) {
        breatheIn();
        prepareAbdominalMuscle();
        // String barkWord = getBarkWord(); 直接引数でもらえばOKだと思う
        BarkedSound barkedSound = doBark(barkWord);
        return barkedSound;
    }

    /*
     * ここから下はprotectedではなくprivateでいいのでは？
     * どうせここ以外で呼ぶこともなさそう
     * */
    private void breatheIn() {
        logger.debug("...Breathing in"); // dummy implementation
        animal.downHitPoint();
    }

    private void prepareAbdominalMuscle() {
        logger.debug("...Using my abdominal muscle"); // dummy implementation
        animal.downHitPoint();
    }

    private BarkedSound doBark(String barkWord) {
        animal.downHitPoint();
        return new BarkedSound(barkWord);
    }

}
