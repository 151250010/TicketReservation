package cn.edu.nju.p.ticketreservation.controllers.site;

import cn.edu.nju.p.ticketreservation.base.BaseResult;
import cn.edu.nju.p.ticketreservation.base.ErrorCode;
import cn.edu.nju.p.ticketreservation.interact.display.SiteDisplay;
import cn.edu.nju.p.ticketreservation.interact.input.PlanForm;
import cn.edu.nju.p.ticketreservation.service.PlanService;
import cn.edu.nju.p.ticketreservation.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/siteplan")
public class SitePlanController {

    @Autowired
    private PlanService service;

    @Autowired
    private SiteService siteService;

    @PostMapping
    public BaseResult addSitePlan(@RequestBody @Validated PlanForm planForm) {
        String siteId = planForm.getSiteId();
        SiteDisplay siteDisplay = siteService.getSiteInfo(siteId);
        PlanForm planWithId =  service.addPlan(planForm,siteDisplay);
        return new BaseResult<>(planWithId, ErrorCode.SUCCESS);
    }

    @GetMapping
    public BaseResult getSitePlan(@RequestParam("planId") int planId) {
        return new BaseResult<>(service.getPlan(planId), ErrorCode.SUCCESS);
    }

    @GetMapping("/all")
    public BaseResult getSitePlans(@RequestParam("siteId") String siteId) {
        return new BaseResult<>(service.getAllPlan(siteId), ErrorCode.SUCCESS);
    }
}
