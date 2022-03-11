package org.docksidestage.bizfw.basic.objanimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Ren
 * */
public class ZombieBarkingProcess extends BarkingProcess {

    private static final Logger logger = LoggerFactory.getLogger(ZombieBarkingProcess.class);

    public ZombieBarkingProcess(Zombie zombie) {
        super(zombie);
    }

    @Override
    protected void breatheIn() {
        System.out.print("Zombieのprocessだよ");
        super.breatheIn();
    }

}
