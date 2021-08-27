package org.docksidestage.javatry.basic.st6.dbms;

/**
 * @author nagano
 * */

public abstract class Sql {
    public int offset;

    abstract public String buildPagingQuery(int pageSize, int pageNumber);

    protected int getOffset() {
        return pageSize * (pageNumber - 1); //???????????????????????
    }
}