/*
 * Copyright 2019-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.bizfw.basic.objanimal;

import org.docksidestage.bizfw.basic.objanimal.barking.BarkingProcess;
import org.docksidestage.bizfw.basic.objanimal.barking.ZombieBarkingProcess;

/**
 * The object for zombie(ゾンビ).
 * @author jflute
 */
public class Zombie extends Animal {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected final ZombieDiary zombieDiary = new ZombieDiary();

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Zombie() {
    }

    @Override
    protected int getInitialHitPoint() {
        return -1; // magic number for infinity hit point
    }

    public static class ZombieDiary {

        private int breatheInCount;

        public void countBreatheIn() {
            ++breatheInCount;
        }

        public int getBreatheInCount() {
            return breatheInCount;
        }
    }

    // ===================================================================================
    //                                                                               Bark
    //                                                                              ======

    //    @Override
    //    protected void breatheIn() {
    //        super.breatheIn();
    //        zombieDiary.countBreatheIn();
    //    }

    //@Override 5/20にコメントアウト
    //public BarkedSound bark() {
    // zombieDiary.countBreatheIn(); おもいで ほかに移植した
    //return super.bark(); //いったんコメントアウト

    // return new ZombieBarkingProcess(this).barkProcess(getBarkWord());

    //}

    /* ?????? */
    /*
     * 4/8
     * BarkingProcess(this).barkProcess(getBarkWord()); だけ切り分ければとおもったけれど
     * 違う気がする
     * HINTはチャレンジのOSのあたり？？？
     * */
    @Override
    protected BarkingProcess getBarkProcess() {
        return new ZombieBarkingProcess(this);
    }

    /**
     * おもいで
     * この実装だとbark();プロセスの外側にzombieDiaryの記述がある
     * →bark();プロセス内でBreatheIn();が複数回入ってきてしまったら・・・？
     * →BreatheIn();一回につきzombieDiary一回がいい
     * →じゃあBreatheIn();内にzombieDiaryを書くしかない
     * →それかBreatheIn();直後にzombieDiaryを書くか
     *
     * どうにかして
     * 次回（2月）ここ
     * */

    @Override
    protected String getBarkWord() {
        return "uooo"; // what in English?
    }

    // ===================================================================================
    //                                                                           Hit Point
    //                                                                           =========
    @Override
    protected void downHitPoint() {
        // do nothing, infinity hit point
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public ZombieDiary getZombieDiary() {
        return zombieDiary;
    }
}
