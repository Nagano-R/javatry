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

import org.docksidestage.bizfw.basic.objanimal.loud.Loudable;

/**
 * The object for animal(動物).
 * @author jflute
 * @author Ren
 */
public abstract class Animal implements Loudable {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected int hitPoint;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Animal() {
        hitPoint = getInitialHitPoint();
    }

    protected int getInitialHitPoint() {
        return 10; // as default
    }

    // ===================================================================================
    //                                                                               Bark
    //                                                                              ======
    //    public BarkedSound bark() {
    //        breatheIn();
    //        prepareAbdominalMuscle();
    //        String barkWord = getBarkWord();
    //        BarkedSound barkedSound = doBark(barkWord);
    //        return barkedSound;
    //    }

    public BarkedSound bark() {
        /*4/11
         * もしここに追加の処理が入ったら？
         * OverrideしているZombieのbark()にもコピペをする羽目になる
         * それは避けたい
         * あとOverride元と先の両方に.barkProcess(getBarkWord())がくっついていて面倒
         * */

        return new BarkingProcess(this).barkProcess(getBarkWord());
    }

    /*
     * ?????
     * */
    protected BarkedSound getBarkProcess() {
        return new BarkingProcess(this).barkProcess(getBarkWord());
    }

    //    protected void prepareAbdominalMuscle() {
    //        logger.debug("...Using my abdominal muscle"); // dummy implementation
    //        downHitPoint();
    //    }

    //    protected void breatheIn() {
    //        logger.debug("...Breathing in"); // dummy implementation
    //        downHitPoint();
    //    }

    protected abstract String getBarkWord();

    //    protected BarkedSound doBark(String barkWord) {
    //        downHitPoint();
    //        return new BarkedSound(barkWord);
    //    }

    // ===================================================================================
    //                                                                           Hit Point
    //                                                                           =========
    protected void downHitPoint() {
        --hitPoint;
        if (hitPoint == 0) {
            throw new IllegalStateException("I'm very tired, so I want to sleep" + getBarkWord());
        }
    }

    // ===================================================================================
    //                                                                               Loud
    //                                                                              ======
    @Override
    public String soundLoudly() {
        return bark().getBarkWord();
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getHitPoint() {
        return hitPoint;
    }
}
