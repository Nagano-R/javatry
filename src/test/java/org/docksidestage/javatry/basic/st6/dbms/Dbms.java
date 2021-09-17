package org.docksidestage.javatry.basic.st6.dbms;

/**
 * @author nagano
 * */

public abstract class Dbms {
    public int offset;

    protected String buildPagingQuery(int pageSize, int pageNumber) {
        int offset = pageSize * (pageNumber - 1);
        return "offset " + offset + " limit " + pageSize;
    }

    abstract String getPagingQuery(int offset, int pageSize);
}