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

/**
 * @author jflute
 * @author Ren
 */

// done nagano [tips] なんとかInterfaceという名前のInterfaceを作る人はあまりいない (まあ暫定) by jflute (2020/10/16)
/*
 * インターフェースとは・・・
 * インタフェースでは定数とメソッドのみが定義できる。
 * インタフェースの変数は自動的にpublic static finalが付けられる。（定数になる）
 * 宣言されたメソッドは引き継ぎ先ですべて実装する必要がある
 * どんなクラスでも
 * 入場する、額面を見る、使用済みか確認する
 * という挙動の実装が約束されているのがメリット（？）
 *
 * Ticketクラスは残すために
 * TicketInterface（仮）を新規作成
 *
 * 必要なメソッド
 * doInPark()
 * getDisplayPrice()
 * isAlreadyIn()? メソッド名がよろしくない気がする 仮置き
 * */

public interface Ticket {
    /*
     * int displayPrice;
     * boolean alreadyIn;
     * int days;
     * TimeType timetype;
     * */

    public void doInPark();

    public int getDisplayPrice();

    public boolean isAlreadyIn();

    public int getDays();

    public TimeType getTimeType();

    public enum TimeType {
        NORMAL("通常版"), SHORT("短縮版"), NIGHT("夜間限定");

        private final String label;

        private TimeType(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }
}