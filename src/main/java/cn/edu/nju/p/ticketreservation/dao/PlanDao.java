package cn.edu.nju.p.ticketreservation.dao;

import cn.edu.nju.p.ticketreservation.enums.PlanType;
import cn.edu.nju.p.ticketreservation.interact.input.PlanForm;
import cn.edu.nju.p.ticketreservation.interact.input.PlanSeatPriceForm;
import cn.edu.nju.p.ticketreservation.utils.mybatis.typeHandler.PlanTypeHandler;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PlanDao {

    @Insert("insert into site_plan (plan_type,site_id,introduction,start,end) values " +
            "(${plan.getDbPlanType()},#{plan.siteId},#{plan.introduction},#{plan.start},#{plan.end})")
    @Options(useGeneratedKeys = true, keyProperty = "plan.planId", keyColumn = "planid")
    void addPlan(@Param("plan") PlanForm planForm);

    @Select("select * from site_plan where planid=#{planId}")
    @Results({
            @Result(property = "planId", column = "planid"),
            @Result(property = "start", column = "start"),
            @Result(property = "end", column = "end"),
            @Result(property = "siteId", column = "site_id"),
            @Result(property = "introduction", column = "introduction"),
            @Result(property = "planType", column = "plan_type",javaType = PlanType.class,jdbcType = JdbcType.TINYINT,typeHandler = PlanTypeHandler.class),
            @Result(property = "priceList",column = "{planId = planid, siteId = site_id}"
                    ,many = @Many(select = "cn.edu.nju.p.ticketreservation.dao.SeatPriceDao.getPlanSeatPrice"))
    })
    PlanForm getPlanForm(int planId);

}
