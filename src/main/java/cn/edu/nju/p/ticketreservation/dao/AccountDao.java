package cn.edu.nju.p.ticketreservation.dao;

import cn.edu.nju.p.ticketreservation.dao.entity.Account;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AccountDao {

    @Insert("insert into account (id,password) values(#{account.id},#{account.password})")
    void addAccount(@Param("account") Account account);

    @Delete("delete from account where id=#{id}")
    void deleteAccount(String id);

    @Select("select password from account where id=#{id}")
    String getPassword(String id);

}
