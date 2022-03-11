package org.docksidestage.bizfw.basic.objanimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Ren
 * */
public class BarkingProcess {

    private static final Logger logger = LoggerFactory.getLogger(BarkingProcess.class);

    private final Animal animal;

    public BarkingProcess(Animal animal) {
        this.animal = animal;
    }

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
        if (animal instanceof Zombie)
            ((Zombie) animal).zombieDiary.countBreatheIn();

        /*
         * 2022/02/04 レビュー内容
         * HINT
         * ・上の気持ち悪い命令文（BarkingProcessの中にZombieの処理）を消すことはできて、
         * 　なおかつzombieDiaryにカウントさせることはできる。考えることは無駄ではない
         * ・Zombieのクラスに書くことをしなくても、Zombieの世界に書ければよい（ここはZombieの世界ではない）
         * 　→Zombie.javaの中以外でもZombieの世界が存在するということ？それを見つけたい
         *
         * （他の人のやつを聞いて）
         * この問題（呪い）って他の人には出現していない？？？
         * じゃあ他の人のコード見れば解決策が分かるかも？？？
         * */
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
