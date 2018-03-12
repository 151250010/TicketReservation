package cn.edu.nju.p.ticketreservation.utils.mybatis.provider;

import cn.edu.nju.p.ticketreservation.interact.input.SeatForm;
import cn.edu.nju.p.ticketreservation.interact.input.SeatSelectionOrder;

import java.util.List;
import java.util.Map;

public class OrderProvider {

    public String insertOrder(Map map) {

        SeatSelectionOrder order = (SeatSelectionOrder) map.get("order");
        List<SeatForm> seatForms = order.getSeatForms();
        StringBuilder seatSql = new StringBuilder();
        for (int i = 0; i < seatForms.size(); i++) {
            SeatForm seatForm = seatForms.get(i);
            if (i != seatForms.size() - 1) {
                seatSql.append(seatForm.toString()).append(';');
            } else {
                seatSql.append(seatForm.toString());
            }
        }
        String sql = "insert into t_order (planId,email,orderTime,seats,orderType,orderStatus) values " +
                "('" +
                order.getPlanId() +
                "','" +
                order.getUserEmail() +
                "','" +
                order.getOrderTime() +
                "','" +
                seatSql +
                "',0,0)";
        System.out.println(sql);
        return sql;
    }
}
