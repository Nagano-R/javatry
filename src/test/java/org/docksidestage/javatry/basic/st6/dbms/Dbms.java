package org.docksidestage.javatry.basic.st6.dbms;

/**
 * @author Ren
 * */

public abstract class Dbms {
    /*
     * テンプレートメソッドパターン
     * 処理の流れ(buildPagingQuery)と、その空欄の処理（getPagingQuery）に分ける
     * */
    public String buildPagingQuery(int pageSize, int pageNumber) {
        int offset = pageSize * (pageNumber - 1);
        return getPagingQuery(offset, pageSize);
    }

    protected abstract String getPagingQuery(int offset, int pageSize);
}