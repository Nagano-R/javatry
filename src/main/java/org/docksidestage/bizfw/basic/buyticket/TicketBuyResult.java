package org.docksidestage.bizfw.basic.buyticket;

// done nagano javadocの中の不要な空行削除 by jflute (2020/10/16)
/**
 * @author Ren
 */
public class TicketBuyResult {

    // done nagano 書き換えないインスタンス変数なので、finalを付けると良いです by jflute (2020/10/16)
    private final MultiDayTicket myTicket;
    private final int change;

    /**
     * Ticketクラスとおつりを与えてください。入場者の手元に残るもののイメージです。
     * @param ticket 購入したTicketクラス
     * @param change 購入時のおつり
     * */
    public TicketBuyResult(MultiDayTicket ticket, int change) {
        this.myTicket = ticket;
        this.change = change;
    }

    public MultiDayTicket getTicket() {
        return this.myTicket;
    }

    public int getChange() {
        return this.change;
    }
}
