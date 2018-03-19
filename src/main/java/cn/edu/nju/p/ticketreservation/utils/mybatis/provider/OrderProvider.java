package cn.edu.nju.p.ticketreservation.utils.mybatis.provider;

import cn.edu.nju.p.ticketreservation.interact.display.SeatDisplay;
import cn.edu.nju.p.ticketreservation.interact.input.RandomSelectionOrder;
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
            if (i == (seatForms.size() - 1)) {
                seatSql.append(seatForm.toString());
            } else {
                seatSql.append(seatForm.toString()).append(';');
            }
        }
        String sql = "insert into t_order (planId,siteId,email,orderTime,totalMoney,seats,orderType,orderStatus) values " +
                "('" +
                order.getPlanId() +
                "','" +
                order.getSiteId() +
                "','" +
                order.getUserEmail() +
                "','" +
                order.getOrderTime() +
                "'," +
                order.getMoney() +
                ",'" +
                seatSql +
                "',0,0)";
        return sql;
    }

    public String insertRandomOrder(Map map) {

        RandomSelectionOrder order = (RandomSelectionOrder) map.get("order");
        List<SeatDisplay> seats = (List<SeatDisplay>) map.get("seats");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < seats.size(); i++) {
            SeatDisplay seatForm = seats.get(i);
            if (i == (seats.size() - 1)) {
                stringBuilder.append(seatForm.toString());
            } else {
                stringBuilder.append(seatForm.toString()).append(';');
            }
        }

        String sql = "insert into t_order (planId,siteId,email,orderTime,totalMoney,seats,orderType,orderStatus) values " +
                "('" +
                order.getPlanId() +
                "','" +
                order.getSiteId() +
                "','" +
                order.getEmail() +
                "','" +
                order.getOrderTime() +
                "'," +
                order.getMoney() +
                ",'" +
                stringBuilder +
                "',1,0)";

        return sql;
    }
}
