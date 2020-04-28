package io.mycat.commands;

import io.mycat.MycatDataContext;
import io.mycat.api.collector.RowBaseIterator;
import io.mycat.beans.mycat.ResultSetBuilder;
import io.mycat.client.SQLRequest;
import io.mycat.util.Response;

import java.sql.JDBCType;

public class SelectLastInsertIdCommand implements MycatCommand{
   final String columnName = "last_insert_id()";

    @Override
    public boolean run(SQLRequest request, MycatDataContext context, Response response) {
        long lastInsertId = context.getLastInsertId();
        ResultSetBuilder resultSetBuilder = ResultSetBuilder.create();
        resultSetBuilder.addColumnInfo(columnName, JDBCType.BIGINT);
        resultSetBuilder.addObjectRowPayload(lastInsertId);
        RowBaseIterator rowBaseIterator = resultSetBuilder.build();
        response.sendResultSet(rowBaseIterator);
        return true;
    }

    @Override
    public boolean explain(SQLRequest request, MycatDataContext context, Response response) {
        response.sendExplain(SelectLastInsertIdCommand.class,columnName+":"+context.getLastInsertId());
        return true;
    }

    @Override
    public String getName() {
        return "selectLastInsertId";
    }
}