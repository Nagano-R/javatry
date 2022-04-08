package org.docksidestage.bizfw.basic.objanimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Ren
 * */
public class ZombieBarkingProcess extends BarkingProcess {

    private static final Logger logger = LoggerFactory.getLogger(ZombieBarkingProcess.class);

    private final Zombie zombie;

    /*
     * きた 3/11
     * */
    public ZombieBarkingProcess(Zombie zombie) {
        super(zombie);
        this.zombie = zombie;
    }
    /*
     * 3/11
     * コンストラクタの引数はZombie
     * すなわちここはZombieの世界
     * */

    /*
     * 4/8
     * BarkingProcessのオーバーライド
     * 元々のプロセスにcountBreatheIn()を追加
     *
     * super.animalで継承元の変数を引っ張ってきて(Zombie)でキャストしても
     * 一応動いたからZombieの変数宣言と代入を無くしても大丈夫そうだけど、
     * 毎回キャストするのも面倒なのでこっちが良いかも
     * */
    @Override
    protected void breatheIn() {
        zombie.zombieDiary.countBreatheIn();
        super.breatheIn();
    }

}
