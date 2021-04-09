/*
 * Copyright 2019-2019 the original author or authors.
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

import org.docksidestage.bizfw.basic.buyticket.MultiDayTicket;
import org.docksidestage.bizfw.basic.buyticket.OneDayTicket;
import org.docksidestage.bizfw.basic.buyticket.Ticket;
import org.docksidestage.bizfw.basic.buyticket.TicketBooth;
import org.docksidestage.bizfw.basic.buyticket.TicketBooth.TicketShortMoneyException;
import org.docksidestage.bizfw.basic.buyticket.TicketBuyResult;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of class. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author nagano_rentarou
 */
public class Step05ClassTest extends PlainTestCase {

    // ===================================================================================
    //                                                                          How to Use
    //                                                                          ==========
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_class_howToUse_basic() {
        TicketBooth booth = new TicketBooth();
        booth.buyOneDayPassport(7400);
        int sea = booth.getQuantity();
        log(sea); // your answer? => 9
        /*
         * Quantity = 量
         * 1枚かったので在庫10枚から1枚減って9枚になる
         * */
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_class_howToUse_overpay() {
        TicketBooth booth = new TicketBooth();
        booth.buyOneDayPassport(10000);
        Integer sea = booth.getSalesProceeds();
        log(sea); // your answer? => 10000
        /*
         * SalesProseed = 売上高
         * buyOneDayPassport内ではお釣りとか考えない模様
         * */
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_class_howToUse_nosales() {
        TicketBooth booth = new TicketBooth();
        Integer sea = booth.getSalesProceeds();
        log(sea); // your answer? => null
        /*
         * インスタンス化しただけだと売上高の初期値はない
         * buyOneDayPassportを呼ばない限りnull
         * */
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_class_howToUse_wrongQuantity() {
        Integer sea = doTest_class_ticket_wrongQuantity();
        log(sea); // your answer? => 9
        /*
         * 持ってるお金は7399円、1日パスポートは7400円  かなしい
         * buyOneDayPassportの処理順番が
         * ・在庫が足りてるかの確認 足りてなかったらSold Outエラーを投げる
         * ・在庫からチケットを一枚取る
         * ・お金が足りてるかの確認 足りなかったらShort moneyエラーを投げる
         * ・売上高にお金を追加
         *
         * という順番なので、在庫さえ足りてればお金が足りなくてもチケットの在庫は減る
         * */
    }

    private Integer doTest_class_ticket_wrongQuantity() {
        TicketBooth booth = new TicketBooth();
        int handedMoney = 7399;
        try {
            booth.buyOneDayPassport(handedMoney);
            fail("always exception but none");
        } catch (TicketShortMoneyException continued) {
            log("Failed to buy one-day passport: money=" + handedMoney, continued);
        }
        return booth.getQuantity();
    }

    // ===================================================================================
    //                                                                           Let's fix
    //                                                                           =========
    /**
     * Fix the problem of ticket quantity reduction when short money. (Don't forget to fix also previous exercise answers) <br>
     * (お金不足でもチケットが減る問題をクラスを修正して解決しましょう (以前のエクササイズのanswerの修正を忘れずに))
     */
    public void test_class_letsFix_ticketQuantityReduction() {
        Integer sea = doTest_class_ticket_wrongQuantity();
        log(sea); // should be max quantity, visual check here
    }

    /**
     * Fix the problem of sales proceeds increased by handed money. (Don't forget to fix also previous exercise answers) <br>
     * (受け取ったお金の分だけ売上が増えていく問題をクラスを修正して解決しましょう (以前のエクササイズのanswerの修正を忘れずに))
     */
    public void test_class_letsFix_salesProceedsIncrease() {
        TicketBooth booth = new TicketBooth();
        booth.buyOneDayPassport(10000);
        Integer sea = booth.getSalesProceeds();
        log(sea); // should be same as one-day price, visual check here
    }

    /**
     * Make method for buying two-day passport (price is 13200). (which can return change as method return value)
     * (TwoDayPassport (金額は13200) も買うメソッドを作りましょう (戻り値でお釣りをちゃんと返すように))
     */
    public void test_class_letsFix_makeMethod_twoday() {
        // comment out after making the method
        TicketBooth booth = new TicketBooth();
        int money = 14000;
        int change = booth.buyTwoDayPassport(money).getChange();
        Integer sea = booth.getSalesProceeds() + change;
        log(sea); // should be same as money

        // and show two-day passport quantity here
    }

    /**
     * Recycle duplicate logics between one-day and two-day by e.g. private method in class. (And confirm result of both before and after) <br>
     * (OneDayとTwoDayで冗長なロジックがあったら、クラス内のprivateメソッドなどで再利用しましょう (修正前と修正後の実行結果を確認))
     */
    public void test_class_letsFix_refactor_recycle() {
        TicketBooth booth = new TicketBooth();
        booth.buyOneDayPassport(10000);
        log(booth.getQuantity(), booth.getSalesProceeds()); // should be same as before-fix
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Now you cannot get ticket if you buy one-day passport, so return Ticket class and do in-park. <br>
     * (OneDayPassportを買ってもチケットをもらえませんでした。戻り値でTicketクラスを戻すようにしてインしましょう)
     */
    public void test_class_moreFix_return_ticket() {
        // comment out after modifying the method
        TicketBooth booth = new TicketBooth();
        Ticket oneDayPassport = booth.buyOneDayPassport(10000);
        log(oneDayPassport.getDisplayPrice()); // should be same as one-day price
        log(oneDayPassport.isAlreadyIn()); // should be false
        oneDayPassport.doInPark();
        log(oneDayPassport.isAlreadyIn()); // should be true
    }

    /**
     * Now also you cannot get ticket if two-day passport, so return class that has ticket and change. <br>
     * (TwoDayPassportもチケットをもらえませんでした。チケットとお釣りを戻すクラスを作って戻すようにしましょう)
     */
    public void test_class_moreFix_return_whole() {
        // comment out after modifying the method
        TicketBooth booth = new TicketBooth();
        int handedMoney = 20000;
        TicketBuyResult twoDayPassportResult = booth.buyTwoDayPassport(handedMoney);
        Ticket twoDayPassport = twoDayPassportResult.getTicket();
        int change = twoDayPassportResult.getChange();
        log(twoDayPassport.getDisplayPrice() + change); // should be same as money
    }

    /*
     * Ticketクラスが持つステータスは値段と使用済みかどうかのみ。種類ももっておいた方がいいかも？ -> もちました
     * */

    /**
     * Now you cannot judge ticket type "one-day or two-day?", so add method to judge it. <br>
     * (チケットをもらってもOneDayなのかTwoDayなのか区別が付きません。区別を付けられるメソッドを追加しましょう)
     */
    public void test_class_moreFix_type() {
        // your confirmation code here
        // done nagano (お客さんが)Ticket単体で判別ができるように (絶対に紛れないように) by jflute (2020/10/16)

        /*
         * チケットに何日分かの情報を持たせた。
         * */
        TicketBooth booth = new TicketBooth();
        TicketBuyResult twoDayPassport = booth.buyShortTwoDayPassport(20000);
        //System.out.println("チケットは" + twoDayPassport.getTicket().getDays() + "日分です。");

        Ticket ticket = twoDayPassport.getTicket();
        switch (ticket.getDays()) {
        case 1:
            System.out.println("チケットは1日分です");
            break;
        case 2:
            switch (ticket.getTimeType()) {
            case NORMAL:
                System.out.println("チケットは2日分です");
                break;
            case SHORT:
                System.out.println("チケットは2日分で、短縮版です");
                break;
            case NIGHT:
                System.out.println("チケットは2日分で、夜間限定です");
                break;
            }
            break;
        default:
            System.out.println("謎です");
        }

        //switch文を使わずに書いた場合
        System.out.println("チケットは" + ticket.getDays() + "日分で、" + ticket.getTimeType().getLabel() + "です");

        // done Ren TimeType等の変数をenumで扱ってみる (2021/01/15)

        /*
         * Ticketは日数(days)×時間帯(timetype)×入場範囲？(areatype)の分だけ種類を持てる
         * しかし実際にはTicketBoothで設定しているメソッドで定められたチケットしか作れない
         * ↓
         * チケットをコントロールするのはTicketBooth
         *
         * 材料的にはカレーも肉じゃがも作れるけど、
         * カレーを作るメソッドしか用意しないことで完成品を限定する、みたいな
         * */

        //おもいで
        //test_class_moreFix_type_ngn(new Ticket(7400));
    }

    /*
     * チケットが日数情報を持たなかった場合の処理。
     * 参考のため残
     * */
    //    private void test_class_moreFix_type_ngn(Ticket ticket) {
    //        /*
    //         * 額面だけだと何日分のチケットか分からない
    //         * かつ値段はTicketBoothの中にしかないため、TicketBoothの人に値段聞いて見比べるしかない？
    //         * TicketBoothの中のチケットの値段は公開してはダメ？
    //         *         ↓
    //         * 下にOneDayのTicketとTwoDayのTicketを作ることになっていた。種類を変数で持つ必要がなくなった。
    //         *
    //         * そもそもTicketBooth建設しないと何も見れなかった
    //         * 基本どこのTicketBoothでも値段は同じだし、わざわざ建てなくてもいい気がする
    //         * */
    //        TicketBooth booth = new TicketBooth();
    //        if (ticket.getDisplayPrice() == booth.getOneDayPrice()) {
    //            // チケットが1日分の時の処理
    //            System.out.println("Your ticket is for one day.");
    //        } else if (ticket.getDisplayPrice() == booth.getTwoDayPrice()) {
    //            // チケットが2日分の時の処理
    //            System.out.println("Your ticket is for two day.");
    //        } else {
    //            // チケットがどれにも当てはまらない時の処理
    //            System.out.println("Your ticket is invalid !!");
    //        }
    //    }

    /* ↓ 次回 ↓ */

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * Now only one use with two-day passport, so split ticket in one-day and two-day class and use interface. <br>
     * <pre>
     * o change Ticket class to interface, define doInPark(), getDisplayPrice() in it
     * o make class for one-day and class for plural days (called implementation class)
     * o make implementation classes implement Ticket interface
     * o doInPark() of plural days can execute defined times
     * </pre>
     * (TwoDayのチケットが一回しか利用できません。OneDayとTwoDayのクラスを分けてインターフェースを使うようにしましょう)
     * <pre>
     * o Ticket をインターフェース(interface)にして、doInPark(), getDisplayPrice() を定義
     * o OneDay用のクラスと複数日用のクラスを作成 (実装クラスと呼ぶ)
     * o 実装クラスが Ticket を implements するように
     * o 複数日用のクラスでは、決められた回数だけ doInPark() できるように
     * </pre>
     */
    public void test_class_moreFix_useInterface() {
        // your confirmation code here
        TicketBooth booth = new TicketBooth();
        OneDayTicket oneday = booth.buyOneDayPassport(15000);
        MultiDayTicket twoday = booth.buyTwoDayPassport(15000).getTicket();
        System.out.println(oneday.getDisplayPrice());
        oneday.doInPark(); //できる
        System.out.println(oneday.isAlreadyIn());
        //oneday.doInPark(); //エラー

        System.out.println(twoday.getDisplayPrice());
        twoday.doInPark();
        twoday.doInPark();
        System.out.println(twoday.isAlreadyIn());
        //twoday.doInPark(); //エラー

        // done Ren 次回このあたり (2021/01/15)
    }

    /**
     * Fix it to be able to buy four-day passport (price is 22400). <br>
     * (FourDayPassport (金額は22400) のチケットも買えるようにしましょう)
     */
    public void test_class_moreFix_wonder() {
        // your confirmation code here
        TicketBooth booth = new TicketBooth(); //売り場建設
        TicketBuyResult fourdayTicketResult = booth.buyFourDayPassport(22500); //4日チケットを買う
        MultiDayTicket fourday = fourdayTicketResult.getTicket(); //チケット本体
        int Oturi = fourdayTicketResult.getChange(); //おつり
        System.out.println("チケットの値段 :" + fourday.getDisplayPrice()); //チケットの金額
        System.out.println("おつり :" + Oturi);
        System.out.println("チケットの種類 :" + fourday.getTimeType());
        fourday.doInPark();
        fourday.doInPark();
        fourday.doInPark();
        fourday.doInPark();
        //fourday.doInPark(); //エラー
    }

    /**
     * Fix it to be able to buy night-only two-day passport (price is 7400). <br>
     * (NightOnlyTwoDayPassport (金額は7400) のチケットも買えるようにしましょう)
     */
    public void test_class_moreFix_wonder_night() {
        // your confirmation code here
        TicketBooth booth = new TicketBooth(); //売り場建設
        TicketBuyResult nightTwodayTicketResult = booth.buyNightOnlyTwoDayPassport(1000000); //2日(夜間)チケットを買う
        MultiDayTicket nightTwoday = nightTwodayTicketResult.getTicket(); //チケット本体
        int oturi = nightTwodayTicketResult.getChange(); //おつり
        System.out.println("チケットの値段 : " + nightTwoday.getDisplayPrice()); //チケットの金額
        System.out.println("おつり : " + oturi);
        System.out.println("チケットの種類 : " + nightTwoday.getTimeType().getLabel());
        nightTwoday.doInPark();
        nightTwoday.doInPark();
        // nightTwoday.doInPark(); //エラー
    }

    /**
     * Refactor if you want to fix (e.g. is it well-balanced name of method and variable?). <br>
     * (その他、気になるところがあったらリファクタリングしてみましょう (例えば、バランスの良いメソッド名や変数名になっていますか？))
     */
    public void test_class_moreFix_yourRefactoring() {
        // write confirmation code here
    }
}
