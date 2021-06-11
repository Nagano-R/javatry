package org.docksidestage.javatry.basic.st6.os;

/**
 * @author Ren
 * */

public class Mac {

    private final String osType = "Mac";
    private final String loginId;

    public Mac(String loginId) {
        this.loginId = loginId;
    }

    public String buildUserResourcePath(String relativePath) {
        String fileSeparator = getFileSeparator();
        String userDirectory = getUserDirectory();
        String resourcePath = userDirectory + fileSeparator + relativePath;
        return resourcePath.replace("/", fileSeparator);
    }

    protected String getFileSeparator() {
        return "/";
    }

    protected String getUserDirectory() {
        return "/Users/" + loginId;
    }
}
