package org.docksidestage.bizfw.basic.buyticket;

/**
 * @author Ren
 */
public class MultiDayTicket implements Ticket {

    //done コメント整理は後ほど（10/16）
    private final int displayPrice; //額面
    private final int days; //使用可能日数
    private final TimeType timetype; //時間帯
    private int useCount = 0; //使用回数

    public MultiDayTicket(int days, int displayPrice, TimeType timetype) {
        this.days = days;
        this.displayPrice = displayPrice;
        this.timetype = timetype;
    }

    public void doInPark() {
        if (this.isAlreadyIn()) {
            // 以前の実装のバックアップ
            //throw new IllegalStateException("Already in park by this ticket: displayedPrice=" + displayPrice);
            throw new IllegalStateException("This ticket does not allow you to enter the park anymore.");
        }
        useCount++;
    }

    public int getDisplayPrice() {
        return displayPrice;
    }

    public boolean isAlreadyIn() {
        return days <= useCount;
    }

    public int getDays() {
        return days;
    }

    public TimeType getTimeType() {
        return timetype;
    }

}
