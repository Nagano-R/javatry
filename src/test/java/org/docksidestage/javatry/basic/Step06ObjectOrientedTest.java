/*
 * Copyright 2019-2020 the original author or authors.
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
package org.docksidestage.javatry.basic;

import org.docksidestage.bizfw.basic.buyticket.Ticket;
import org.docksidestage.bizfw.basic.buyticket.TicketBooth;
import org.docksidestage.bizfw.basic.objanimal.Animal;
import org.docksidestage.bizfw.basic.objanimal.BarkedSound;
import org.docksidestage.bizfw.basic.objanimal.Bird;
import org.docksidestage.bizfw.basic.objanimal.Cat;
import org.docksidestage.bizfw.basic.objanimal.Dog;
import org.docksidestage.bizfw.basic.objanimal.Zombie;
import org.docksidestage.bizfw.basic.objanimal.fly.Flyer;
import org.docksidestage.bizfw.basic.objanimal.loud.AlarmClock;
import org.docksidestage.bizfw.basic.objanimal.loud.Loudable;
import org.docksidestage.bizfw.basic.objanimal.runner.FastRunner;
import org.docksidestage.javatry.basic.st6.dbms.Dbms;
import org.docksidestage.javatry.basic.st6.dbms.St6MySql;
import org.docksidestage.javatry.basic.st6.dbms.St6PostgreSql;
import org.docksidestage.javatry.basic.st6.os.Mac;
import org.docksidestage.javatry.basic.st6.os.OldWindows;
import org.docksidestage.javatry.basic.st6.os.Windows;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of object-oriented. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author nagano
 */
public class Step06ObjectOrientedTest extends PlainTestCase {

    // ===================================================================================
    //                                                                        About Object
    //                                                                        ============
    // -----------------------------------------------------
    //                                        Against Object
    //                                        --------------
    /**
     * Fix 5 mistakes in buying one-day passport and in-park process. <br>
     * (OneDayPassportを買って InPark する処理の中で、間違いが5つあるので修正しましょう)
     */
    public void test_objectOriented_aboutObject_againstObject() {
        //
        // [ticket booth info]
        //
        int oneDayPrice = 7400;
        int quantity = 10;
        // Integer salesProceeds = null; // 売上高は0スタート nullに加算処理するとエラー
        Integer salesProceeds = 0;

        //
        // [buy one-day passport]
        //
        int handedMoney = 10000;
        if (quantity <= 0) {
            throw new IllegalStateException("Sold out");
        }
        if (handedMoney < oneDayPrice) {
            throw new IllegalStateException("Short money: handedMoney=" + handedMoney);
        }
        --quantity;
        // salesProceeds = handedMoney; // 売上高には代入ではなく加算 & 出されたお金を全部売り上げに加算するのはズル
        salesProceeds += oneDayPrice;

        //
        // [ticket info]
        //
        // int displayPrice = quantity; // チケットに刻むのは在庫でなく値段
        int displayPrice = oneDayPrice;
        boolean alreadyIn = false;

        // other processes here...
        // ...
        // ...

        //
        // [do in park now!!!]
        //
        if (alreadyIn) {
            // throw new IllegalStateException("Already in park by this ticket: displayPrice=" + quantity);
            throw new IllegalStateException("Already in park by this ticket: displayPrice=" + displayPrice);
        }
        /*
         * ↑この位置にalready判定は不適切？
         * [do in park now!!!]部分でalreadyIn = true;を行った場合、確定でエラーになる
         * 行わなかった場合、必ずスルーされる
         * */
        alreadyIn = true;

        //
        // [final process]
        //
        // saveBuyingHistory(quantity, displayPrice, salesProceeds, alreadyIn); // 引数の順番がおかしい
        saveBuyingHistory(quantity, salesProceeds, displayPrice, alreadyIn);
        /*
         * メソッド名はsaveBuyingHistoryではなくshowBuyingHistoryが正しい？（メソッド内でsaveする挙動は行っていないため）
         * */
    }

    private void saveBuyingHistory(int quantity, Integer salesProceeds, int displayPrice, boolean alreadyIn) {
        if (alreadyIn) {
            // only logging here (normally e.g. DB insert)
            // showTicketBooth(displayPrice, salesProceeds);
            // showYourTicket(quantity, alreadyIn); // quantity と displayPrice が逆
            showTicketBooth(quantity, salesProceeds);
            showYourTicket(displayPrice, alreadyIn);
        }
    }

    private void showTicketBooth(int quantity, Integer salesProceeds) {
        log("Ticket Booth: quantity={}, salesProceeds={}", quantity, salesProceeds);
    }

    private void showYourTicket(int displayPrice, boolean alreadyIn) {
        log("Ticket: displayPrice={}, alreadyIn={}", displayPrice, alreadyIn);
    }

    // -----------------------------------------------------
    //                                          Using Object
    //                                          ------------
    /**
     * Read (analyze) this code and compare with the previous test method, and think "what is object?". <br>
     * (このコードを読んで(分析して)、一つ前のテストメソッドと比べて、「オブジェクトとは何か？」を考えてみましょう)
     */
    public void test_objectOriented_aboutObject_usingObject() {
        //
        // [ticket booth info]
        //
        TicketBooth booth = new TicketBooth();

        // *booth has these properties: （訳:boothには次のような特徴があります）
        //int oneDayPrice = 7400;
        //int quantity = 10;
        //Integer salesProceeds = null;

        //
        // [buy one-day passport]
        //
        // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
        // #fixme you if step05 has been finished, you can use this code by jflute (2019/06/15)
        // _/_/_/_/_/_/_/_/_/_/
        Ticket ticket = booth.buyOneDayPassport(10000);
        // booth.buyOneDayPassport(10000); // as temporary, remove if you finished steo05 （訳:一時的なものとして、step05が終了したら削除してください）
        //Ticket ticket = new Ticket(7400); // also here （訳:こちらも）

        // *buyOneDayPassport() has this process:
        //if (quantity <= 0) {
        //    throw new TicketSoldOutException("Sold out");
        //}
        //if (handedMoney < oneDayPrice) {
        //    throw new TicketShortMoneyException("Short money: handedMoney=" + handedMoney);
        //}
        //--quantity;
        //salesProceeds = handedMoney;

        // *ticket has these properties:
        //int displayPrice = oneDayPrice;
        //boolean alreadyIn = false;

        // other processes here...
        // ...
        // ...

        //
        // [do in park now!!!]
        //
        ticket.doInPark();

        // *doInPark() has this process:
        //if (alreadyIn) {
        //    throw new IllegalStateException("Already in park by this ticket: displayPrice=" + displayPrice);
        //}
        //alreadyIn = true;

        //
        // [final process]
        //
        saveBuyingHistory(booth, ticket);
    }

    private void saveBuyingHistory(TicketBooth booth, Ticket ticket) {
        if (ticket.isAlreadyIn()) {
            // only logging here (normally e.g. DB insert)
            doShowTicketBooth(booth);
            doShowYourTicket(ticket);
        }
    }

    private void doShowTicketBooth(TicketBooth booth) {
        log("Ticket Booth: quantity={}, salesProceeds={}", booth.getQuantity(), booth.getSalesProceeds());
    }

    private void doShowYourTicket(Ticket ticket) {
        log("Your Ticket: displayPrice={}, alreadyIn={}", ticket.getDisplayPrice(), ticket.isAlreadyIn());
    }

    /* 「オブジェクトとは何か？」
     *
     * 現実世界において実体のあるもの？チケットとか売り場とか
     * 「モノ」をそれが持つ情報（変数）と役割（メソッド）で表現したもの、のような感じ
     * オブジェクトを使わない場合と比べて、
     * doShowTicketBooth の引数がTicketBooth になっていたりしてスッキリ
     * 処理が人間にわかりやすい
     *
     * 管理しやすい、大量生産しやすい、やっていることがメソッド名やオブジェクト名で説明しやすい
     *
     * */

    // ===================================================================================
    //                                                              Polymorphism Beginning
    //                                                              ======================
    /**
     * What string is sea and land variable at the method end? <br>
     * (メソッド終了時の変数 sea, land の中身は？)
     */
    public void test_objectOriented_polymorphism_1st_concreteOnly() {
        Dog dog = new Dog();
        BarkedSound sound = dog.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => wan ○
        int land = dog.getHitPoint();
        log(land); // your answer? => 10 × => 7
        /*
         * Q.なんでHP減ってるの？
         * A.ほえる指令を出した時に息吸い込み、腹筋チャージ、ほえる動作でそれぞれHPが1ずつ減っている
         * HPの概念やほえる動作はAnimalからの継承
         * */
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_2nd_asAbstract() {
        Animal animal = new Dog();
        BarkedSound sound = animal.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => wan ○
        int land = animal.getHitPoint();
        log(land); // your answer? => 7 ○
        /*
         * Abstractは抽象クラス
         * 宣言のみ行い、処理自体は記述しない
         * interfaceに書かれたメソッドっぽい？
         * Animal型にDogインスタンスぶちこんでも問題ないようだ
         * */
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_3rd_fromMethod() {
        Animal animal = createAnyAnimal();
        BarkedSound sound = animal.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => wan ○
        int land = animal.getHitPoint();
        log(land); // your answer? => 7 ○
        /*
         * 上の問題とやっていることは変わらないように見える
         * */
    }

    private Animal createAnyAnimal() {
        return new Dog();
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_4th_toMethod() {
        Dog dog = new Dog();
        doAnimalSeaLand_for_4th(dog);
    }

    private void doAnimalSeaLand_for_4th(Animal animal) {
        BarkedSound sound = animal.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => wan ○
        int land = animal.getHitPoint();
        log(land); // your answer? => 7 ○
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_5rd_overrideWithSuper() {
        Animal animal = new Cat();
        BarkedSound sound = animal.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => nya- ○
        int land = animal.getHitPoint();
        log(land); // your answer? => 7 × => 5
        /*
         * Override: 継承した親クラスのメソッドを上書き
         * super: 一個上の親クラスを参照するときに使う
         * downHP();→子クラスのメソッド super.downHP();→親クラスのメソッド
         * みたいな
         * HPdown時、HPが偶数なら更にHPが減るようになっている(10→9→7→5)
         * */
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_6th_overriddenWithoutSuper() {
        Animal animal = new Zombie();
        BarkedSound sound = animal.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => uooo ○
        int land = animal.getHitPoint();
        log(land); // your answer? => -1 ○
        /*
         * Overrideにより初期HPが-1(∞扱い？)になっていて、HPが減らなくなっている（HP減少処理を消している）（ﾑﾃｷ）
         * */
    }

    // ===================================================================================
    //                                                              Polymorphism Interface
    //                                                              ======================
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_interface_dispatch() {
        Loudable loudable = new Zombie();
        String sea = loudable.soundLoudly();
        log(sea); // your answer? => uooo ○
        String land = ((Zombie) loudable).bark().getBarkWord();
        log(land); // your answer? => uooo ○
        /*
         * DownCast
         * AnimalからLoudableに抽象化
         * 型はLoudableだけど一応Zombie
         * */
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_interface_hierarchy() {
        Loudable loudable = new AlarmClock();
        String sea = loudable.soundLoudly();
        log(sea); // your answer? => jiri jiri jiri--- ○
        boolean land = loudable instanceof Animal;
        log(land); // your answer? => false ○
        /*
         * 何か音が出るからといってAnimalとは限らない
         * */
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_interface_partImpl() {
        Animal seaAnimal = new Cat();
        Animal landAnimal = new Zombie();
        boolean sea = seaAnimal instanceof FastRunner;
        log(sea); // your answer? => true ○
        boolean land = landAnimal instanceof FastRunner;
        log(land); // your answer? => false ○
        /*
         * CatはFastRunnerインターフェースを実装
         * Cat->Animal & FastRunner & Loudable & ...
         * */
    }

    /**
     * Make Dog class implement FastRunner interface. (the implementation is same as Cat class) <br>
     * (DogもFastRunnerインターフェースをimplementsしてみましょう (実装はCatと同じで))
     */
    public void test_objectOriented_polymorphism_interface_runnerImpl() {
        // your confirmation code here
        Dog dog = new Dog();
        boolean tf = dog instanceof FastRunner;
        log(tf);
        /*
         * FastRunnerを実装するとrunメソッドの追加が必要
         * run()の中身は未決定
         * */
    }

    // ===================================================================================
    //                                                                 Polymorphism Making
    //                                                                 ===================
    /**
     * Make concrete class of Animal, which is not FastRunner, in "objanimal" package. (implementation is as you like) <br>
     * (FastRunnerではないAnimalクラスのコンクリートクラスをobjanimalパッケージに作成しましょう (実装はお好きなように))
     */
    public void test_objectOriented_polymorphism_makeConcrete() {
        // your confirmation code here
        Bird bird = new Bird();
        boolean tf = bird instanceof FastRunner;
        log(tf);
    }

    /**
     * Make interface implemented by part of Animal concrete class in new package under "objanimal" package. (implementation is as you like) <br>
     * (Animalクラスの一部のコンクリートクラスだけがimplementsするインターフェースをobjanimal配下の新しいパッケージに作成しましょう (実装はお好きなように))
     */
    public void test_objectOriented_polymorphism_makeInterface() {
        // your confirmation code here
        Bird piyo = new Bird();
        boolean tf = piyo instanceof Flyer;
        log(tf);
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Extract St6MySql, St6PostgreSql (basic.st6.dbms)'s process to abstract class (as super class and sub-class) <br>
     * (St6MySql, St6PostgreSql (basic.st6.dbms) から抽象クラスを抽出してみましょう (スーパークラスとサブクラスの関係に))
     */
    public void test_objectOriented_writing_generalization_extractToAbstract() {
        // your confirmation code here
        St6MySql mySql = new St6MySql();
        St6PostgreSql postgreSql = new St6PostgreSql();
        log(mySql instanceof Dbms);
        log(postgreSql instanceof Dbms);
        log(mySql.buildPagingQuery(3, 5));
    }

    /**
     * Extract St6OperationSystem (basic.st6.os)'s process to concrete classes (as super class and sub-class) <br>
     * (St6OperationSystem (basic.st6.os) からコンクリートクラスを抽出してみましょう (スーパークラスとサブクラスの関係に))
     */
    public void test_objectOriented_writing_specialization_extractToConcrete() {
        // your confirmation code here
        Windows win = new Windows("nagano");
        Mac mac = new Mac("nagano");
        OldWindows oldwin = new OldWindows("nagano");
        log(win.buildUserResourcePath("folder"));
        log(mac.buildUserResourcePath("folder"));
        log(oldwin.buildUserResourcePath("folder"));
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * Extract Animal's bark() process as BarkingProcess class to also avoid large abstract class. <br>
     * (抽象クラス肥大化を抑制するためにも、Animalのbark()のプロセス(処理)をBarkingProcessクラスとして切り出しましょう)
     */
    public void test_objectOriented_writing_withDelegation() {
        // your confirmation code here
        //        Dog dog = new Dog();
        //        dog.bark();
        //        log(dog.getHitPoint());

        Zombie zonzon = new Zombie();
        zonzon.bark();
        log(zonzon.getZombieDiary().getBreatheInCount());
        //何のエラーだ？？？ 11/12
        /*
         * ZombieクラスがbreatheIn()をOverrideしておる
         * 困る
         * 処理自体は完了する
         * breatheIn()はAnimalから切り離しときたいが･･･？
         * */
    }

    /**
     * Is Zombie correct as sub-class of Animal? Analyze it in thirty seconds. (thinking only) <br>
     * (ゾンビは動物クラスのサブクラスとして適切でしょうか？30秒だけ考えてみましょう (考えるだけでOK))
     */
    public void test_objectOriented_zoo() {
        // do nothing here
    }
}
