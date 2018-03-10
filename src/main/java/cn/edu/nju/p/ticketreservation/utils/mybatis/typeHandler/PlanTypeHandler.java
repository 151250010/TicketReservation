package cn.edu.nju.p.ticketreservation.utils.mybatis.typeHandler;

import cn.edu.nju.p.ticketreservation.enums.PlanType;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlanTypeHandler implements TypeHandler<PlanType> {

    @Override
    public void setParameter(PreparedStatement ps, int i, PlanType parameter, JdbcType jdbcType) throws SQLException {

    }

    @Override
    public PlanType getResult(ResultSet rs, String columnName) throws SQLException {
        int value = rs.getInt(columnName);
        return PlanType.convert2PlanType(value);
    }

    @Override
    public PlanType getResult(ResultSet rs, int columnIndex) throws SQLException {
        int value = rs.getInt(columnIndex);
        return PlanType.convert2PlanType(value);
    }

    @Override
    public PlanType getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return null;
    }
}
