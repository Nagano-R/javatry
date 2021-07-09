package org.docksidestage.javatry.basic.st6.dbms;

/**
 * @author nagano
 * */

public interface Sql {
    public String buildPagingQuery(int pageSize, int pageNumber);
}
