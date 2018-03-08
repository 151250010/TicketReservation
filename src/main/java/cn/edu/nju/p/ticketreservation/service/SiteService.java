package cn.edu.nju.p.ticketreservation.service;

import cn.edu.nju.p.ticketreservation.interact.display.SiteDisplay;
import cn.edu.nju.p.ticketreservation.interact.input.SiteReg;

public interface SiteService {

    SiteDisplay addSite(SiteReg siteReg);

    void updateSite(SiteReg siteReg);
}
