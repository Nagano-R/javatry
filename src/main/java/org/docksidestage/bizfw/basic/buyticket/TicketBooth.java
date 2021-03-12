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
package org.docksidestage.bizfw.basic.buyticket;

import org.docksidestage.bizfw.basic.buyticket.Ticket.TimeType;

/**
 * @author jflute
 * @author Ren
 */
public class TicketBooth {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final int MAX_QUANTITY = 10;
    private static final int ONE_DAY_PRICE = 7400; // when 2019/06/15
    private static final int TWO_DAY_PRICE = 13200;
    private static final int FOUR_DAY_PRICE = 22400;

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private int quantity = MAX_QUANTITY;
    private Integer salesProceeds;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public TicketBooth() {
    }

    // ===================================================================================
    //                                                                          Buy Ticket
    //                                                                          ==========

    //参考のために残しています
    /*public Ticket buyOneDayPassport(int handedMoney) {
        if (isStock(1)) {
            throw new TicketSoldOutException("Sold out");
        }
        if (handedMoney < ONE_DAY_PRICE) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }
        --quantity;
        if (salesProceeds != null) {
            salesProceeds = salesProceeds + ONE_DAY_PRICE;
        } else {
            salesProceeds = ONE_DAY_PRICE;
        }
    
        return new Ticket(1);
    }*/

    // done nagano 部品の呼び出しフローも再利用してください by jflute (2020/10/16)
    public OneDayTicket buyOneDayPassport(int handedMoney) {
        assertQuantityExists(1);
        assertFeeExists(ONE_DAY_PRICE, handedMoney);
        sellTicket(ONE_DAY_PRICE, 1);

        return new OneDayTicket(ONE_DAY_PRICE, TimeType.NORMAL);
    }

    //    public int buyTwoDayPassport(int handedMoney) {
    //        assertQuantityExists(2);
    //        assertFeeExists(TWO_DAY_PRICE, handedMoney);
    //        sellTicket(TWO_DAY_PRICE, 2);
    //
    //        return handedMoney - TWO_DAY_PRICE;
    //
    //        /*
    //         * 2日パスポートは1日パスポート2枚分とする 1ヶ月定期を2枚買えば2ヶ月定期理論
    //         * 1日パスと2日パスをべつにするなら、在庫インスタンス変数追加
    //         * */
    //    }

    public TicketBuyResult buyTwoDayPassport(int handedMoney) {
        MultiDayTicket ticket = doBuyMultiDayPassport(2, TWO_DAY_PRICE, handedMoney, TimeType.NORMAL);
        return new TicketBuyResult(ticket, handedMoney - TWO_DAY_PRICE);

        /*
         * 2日パスポートは1日パスポート2枚分とする 1ヶ月定期を2枚買えば2ヶ月定期理論
         * 1日パスと2日パスをべつにするなら、在庫インスタンス変数追加
         * */
    }

    public TicketBuyResult buyShortTwoDayPassport(int handedMoney) {
        MultiDayTicket ticket = doBuyMultiDayPassport(2, TWO_DAY_PRICE, handedMoney, TimeType.SHORT);
        return new TicketBuyResult(ticket, handedMoney - TWO_DAY_PRICE);

        /*
         * ミニ2日分チケットを変えるメソッド（追加課題）
         * 普通のチケットを買う時にもわざわざ種別指定するのはごちゃつきそうだけれど、
         * 種別を付加する時としない時でそれぞれ違うメソッドを作るのも違う気がする
         *
         * 現状の機能を破壊しないようにしたけれど、これはこれでごちゃついている
         * 在庫と値段を設定する場合、変数追加が必要
         * 現在は在庫値段ともにtwodayと同じ状態
         * */
    }

    public TicketBuyResult buyFourDayPassport(int handedMoney) {
        MultiDayTicket ticket = doBuyMultiDayPassport(4, FOUR_DAY_PRICE, handedMoney, TimeType.NORMAL);
        return new TicketBuyResult(ticket, handedMoney - FOUR_DAY_PRICE);
    }

    //    private Ticket doBuyPassport(int days, int price, int handedMoney) {
    //        return doBuyPassport(days, price, handedMoney, null);
    //        //
    //        //        assertQuantityExists(days);
    //        //        assertFeeExists(price, handedMoney);
    //        //        sellTicket(price, days);
    //        //
    //        //        return new Ticket(days, price);
    //    }

    /*
     * チケット製造メソッド（MultiDay）
     * */
    private MultiDayTicket doBuyMultiDayPassport(int days, int price, int handedMoney, TimeType type) {
        assertQuantityExists(days);
        assertFeeExists(price, handedMoney);
        sellTicket(price, days);

        return new MultiDayTicket(days, price, type);
    }

    /**
     * チケットの在庫を確認します
     * 在庫がなければ売り切れの例外を投げ処理を中断します、在庫があれば処理を続けます
     * @param ticketDays 買いたいチケット枚数 [枚日]
     * */
    private void assertQuantityExists(int ticketDays) {
        if (!(quantity >= ticketDays)) { //足りない場合
            throw new TicketSoldOutException("Sold out");
        }
    }

    /**
     * チケット代が足りているか確認します
     * ゼニが足りなければ料金不足の例外を投げ処理を中断します、足りていれば処理を続けます
     * @param price チケット料金 [枚日]
     * @param handedMoney 提示された料金
     */
    private void assertFeeExists(int price, int handedMoney) {
        if (handedMoney < price) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }
    }

    /**
     * チケットを売る挙動を行います
     * 在庫からチケットを減らし、売り上げに追加します
     * @param ticketPrice チケットの値段
     * @param ticketDays 買ったチケット枚数 [枚日]
     * */
    private void sellTicket(int ticketPrice, int ticketDays) {
        quantity -= ticketDays;
        if (salesProceeds != null) {
            salesProceeds = salesProceeds + ticketPrice;
        } else {
            salesProceeds = ticketPrice;
        }
    }

    public static class TicketSoldOutException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketSoldOutException(String msg) {
            super(msg);
        }
    }

    public static class TicketShortMoneyException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketShortMoneyException(String msg) {
            super(msg);
        }
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getQuantity() {
        return quantity;
    }

    public Integer getSalesProceeds() {
        return salesProceeds;
    }

    public Integer getOneDayPrice() {
        return ONE_DAY_PRICE;
    }

    public Integer getTwoDayPrice() {
        return TWO_DAY_PRICE;
    }
    // done nagano 不要な空行削除 by jflute (2020/10/16)
}