package org.docksidestage.bizfw.basic.objanimal;

import org.docksidestage.bizfw.basic.objanimal.fly.Flyer;

/**
 * @author nagano
 * */

public class Bird extends Animal implements Flyer {

    @Override
    protected String getBarkWord() {
        return "piyo piyo";
    }

}
