package org.docksidestage.bizfw.basic.objanimal;

public class AnimalBarkingProcessDairinin {

    protected final Animal animal;

    public AnimalBarkingProcessDairinin(Animal animal) {
        this.animal = animal;
    }

    public void dhp() {
        animal.downHitPoint();
    }
}
