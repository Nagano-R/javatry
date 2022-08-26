package org.docksidestage.bizfw.basic.objanimal;

public class BarkingProcessDairinin {

    protected final Animal animal;

    public BarkingProcessDairinin(Animal animal) {
        this.animal = animal;
    }

    public void dhp() {
        animal.downHitPoint();
    }
}
