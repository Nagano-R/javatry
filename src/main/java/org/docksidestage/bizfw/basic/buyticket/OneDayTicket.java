package org.docksidestage.bizfw.basic.buyticket;

public class OneDayTicket implements Ticket {

    private final int displayPrice;
    private boolean alreadyIn = false;
    private TimeType timetype;

    public OneDayTicket(int displayPrice, TimeType timetype) {
        this.displayPrice = displayPrice;
        this.timetype = timetype;
    }

    public void doInPark() {
        if (alreadyIn) {
            throw new IllegalStateException("Already in park by this ticket: displayedPrice=" + displayPrice);
        }
        alreadyIn = true;
    }

    public int getDisplayPrice() {
        return displayPrice;
    }

    public boolean isAlreadyIn() {
        return alreadyIn;
    }

    @Override
    public int getDays() {
        // TODO 自動生成されたメソッド・スタブ
        return 1;
    }

    @Override
    public TimeType getTimeType() {
        // TODO 自動生成されたメソッド・スタブ
        return timetype;
    }

}
