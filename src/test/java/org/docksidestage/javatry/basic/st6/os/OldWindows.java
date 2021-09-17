package org.docksidestage.javatry.basic.st6.os;

/**
 * @author Ren
 * */

public class OldWindows extends St6OperationSystem {

    public OldWindows(String loginId) {
        this.loginId = loginId;
    }

    @Override
    protected String getFileSeparator() {
        return "\\";
    }

    @Override
    protected String getUserDirectory() {
        return "/Documents and Settigs/" + loginId;
    }

}
