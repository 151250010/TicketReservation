package cn.edu.nju.p.ticketreservation.service;

import cn.edu.nju.p.ticketreservation.interact.display.SiteDisplay;
import cn.edu.nju.p.ticketreservation.interact.input.SiteReg;

import java.util.List;

public interface SiteService {

    SiteDisplay addSite(SiteReg siteReg);

    void updateSite(SiteReg siteReg);

    SiteDisplay getSiteInfo(String siteId);

    List<SiteDisplay> getAllSites();

    List<SiteDisplay> getAllUncheckSites();

    void updateSiteStatus(String siteId, int status);
}
