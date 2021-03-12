package org.docksidestage.bizfw.basic.buyticket;

/**
 * @author nagano
 */
public class OneDayTicket implements Ticket {

    private final int displayPrice;
    private final TimeType timetype;

    private boolean alreadyIn = false;

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
        return 1;
    }

    @Override
    public TimeType getTimeType() {
        return timetype;
    }

}
