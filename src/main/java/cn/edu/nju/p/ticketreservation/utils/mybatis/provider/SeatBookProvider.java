package cn.edu.nju.p.ticketreservation.utils.mybatis.provider;

import cn.edu.nju.p.ticketreservation.interact.input.SeatForm;

import java.util.List;
import java.util.Map;

public class SeatBookProvider {

    public String bookSeats(Map map) {

        System.out.println("go to book seats");

        List<SeatForm> seatForms = (List<SeatForm>) map.get("seats");
        assert seatForms.size() >= 1 && seatForms.size() <= 6 : "The number of seats booked should be not less than 1 and not greater than 6!";
        int planId = (int) map.get("planId");

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("update plan_seats set status=1 where planId=" + planId + " and (seat_x,seat_y,seat_z) in (");

        for (SeatForm seatForm : seatForms) {
            stringBuilder.append("(")
                    .append(seatForm.getX())
                    .append(",")
                    .append(seatForm.getY())
                    .append(",")
                    .append(seatForm.getZ())
                    .append(")")
                    .append(",");
        }

        stringBuilder.replace(stringBuilder.length() - 1, stringBuilder.length(), "");
        stringBuilder.append(")");
        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }
}
