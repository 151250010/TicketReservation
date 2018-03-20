package cn.edu.nju.p.ticketreservation.dao;

import cn.edu.nju.p.ticketreservation.dao.entity.SiteMoney;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MoneyDao {

    @Select("select balance from t_money where email=#{email}")
    double getBalance(String email);

    @Update("update t_money set balance=balance-#{money} where email=#{email}")
    void minusMoney(@Param("email") String email, @Param("money") double money);

    @Update("update t_money set balance=balance+#{money} where email=#{email}")
    void plusMoney(@Param("email") String email, @Param("money") double money);

    @Insert("insert into t_money (email,balance) values (#{email},0)")
    void addMoneyAccount(@Param("email") String email);

    @Select("select sum(totalMoney) as allMoney,orderStatus from t_order where siteId=#{siteId} and orderStatus in (2,3) group by orderStatus")
    List<SiteMoney> getSiteMoney(@Param("siteId") int siteId);
}
