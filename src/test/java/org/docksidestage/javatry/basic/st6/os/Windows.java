package org.docksidestage.javatry.basic.st6.os;

/**
 * @author Ren
 * */

public class Windows extends St6OperationSystem {

    public Windows(String loginId) {
        this.loginId = loginId;
    }

    @Override
    protected String getFileSeparator() {
        return "\\";
    }

    @Override
    protected String getUserDirectory() {
        return "/Users/" + loginId;
    }

}
