package org.docksidestage.javatry.basic.st6.os;

/**
 * @author Ren
 * */

public class OldWindows {

    private final String osType = "OldWindows";
    private final String loginId;

    public OldWindows(String loginId) {
        this.loginId = loginId;
    }

    public String buildUserResourcePath(String relativePath) {
        String fileSeparator = getFileSeparator();
        String userDirectory = getUserDirectory();
        String resourcePath = userDirectory + fileSeparator + relativePath;
        return resourcePath.replace("/", fileSeparator);
    }

    protected String getFileSeparator() {
        return "\\";
    }

    protected String getUserDirectory() {
        return "/Documents and Settigs/" + loginId;
    }
}
