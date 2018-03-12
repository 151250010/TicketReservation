package cn.edu.nju.p.ticketreservation.utils.mybatis.provider;

import cn.edu.nju.p.ticketreservation.interact.input.PlanSeatPriceForm;

import java.util.List;
import java.util.Map;

public class SeatPricesProvider {

    public String addPlanSeatPrice(Map map) {
        List<PlanSeatPriceForm> planSeatPriceForms = (List<PlanSeatPriceForm>) map.get("seatPrices");
            assert planSeatPriceForms.size() >= 1 : "The number of seat zones should be not less than 1";
            int siteId = (int) map.get("siteId");
            int planId = (int) map.get("planId");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("insert into plan_seatz_price (plan_id,site_id,seat_z,price) values ");
            for (int i = 0; i < planSeatPriceForms.size(); i++) {
                PlanSeatPriceForm seatPriceForm = planSeatPriceForms.get(i);
                stringBuilder.append("(")
                        .append(planId)
                        .append(",")
                        .append(siteId)
                        .append(',')
                        .append(seatPriceForm.getSeatZ())
                        .append(',')
                        .append(seatPriceForm.getPrice())
                    .append(")");
            if (i < planSeatPriceForms.size() - 1) {
                stringBuilder.append(',');
            }
        }
        return stringBuilder.toString();
    }
}
