package cn.edu.nju.p.ticketreservation.controllers.site;

import cn.edu.nju.p.ticketreservation.base.BaseResult;
import cn.edu.nju.p.ticketreservation.base.ErrorCode;
import cn.edu.nju.p.ticketreservation.interact.display.SiteDisplay;
import cn.edu.nju.p.ticketreservation.interact.input.SiteReg;
import cn.edu.nju.p.ticketreservation.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/site")
public class SiteController {

    @Autowired
    private SiteService siteService;

    @PostMapping
    public BaseResult registerSite(@RequestBody @Validated SiteReg siteReg) {
        SiteDisplay siteDisplay = siteService.addSite(siteReg);
        return new BaseResult<>(siteDisplay, ErrorCode.SUCCESS);
    }

    @PutMapping
    public BaseResult updateSite(@RequestBody @Validated SiteReg siteReg) {
        siteService.updateSite(siteReg);
        return new BaseResult<>("Update site successfully!", ErrorCode.SUCCESS);
    }

    @GetMapping("/all")
    public BaseResult getAllSites() {
        List<SiteDisplay> siteDisplays = siteService.getAllSites();
        return new BaseResult<>(siteDisplays, ErrorCode.SUCCESS);
    }
}
