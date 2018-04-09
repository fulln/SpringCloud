package com.fulln.pips.Common.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

@MappedTypes(value = Date.class)
public class  MyDateTypeHandler extends BaseTypeHandler<Date> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Date parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i,String.valueOf(parameter.getTime()));
    }

    public Date getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String sqlTimestamp = resultSet.getString(s);
        if (sqlTimestamp != null) {
            return new Date(Long.parseLong(sqlTimestamp));
        }
        return null;
    }

    public Date getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String sqlTimestamp = resultSet.getString(i);
        if (sqlTimestamp != null) {
            return new Date(Long.parseLong(sqlTimestamp));
        }
        return null;
    }

    public Date getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String sqlTimestamp = callableStatement.getString(i);
        if (sqlTimestamp != null) {
            return new Date(Long.parseLong(sqlTimestamp));
        }
        return null;
    }
}