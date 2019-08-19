package ru.medweather.documentflow.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.medweather.documentflow.models.Firm;
import ru.medweather.documentflow.service.RestrictionService;

@Controller
public class RestrictionController {

    private final RestrictionService restrictionService;

    public RestrictionController(RestrictionService restrictionService) {
        this.restrictionService = restrictionService;
    }

    @PostMapping("/period")
    public String periodFlow(
            @RequestParam int timeStart,
            @RequestParam int timeFinish
    ) {
        if(restrictionService.isPeriodDocumentFlow(timeStart, timeFinish)) {
            return "redirect:/main";
        }
        return "redirect:/main";
    }

    @PostMapping("/count-flow")
    public String countFlow(
            @AuthenticationPrincipal Firm firm,
            @RequestParam int countDocFlow
    ) {
        if(restrictionService.isDocumentFlow(firm, countDocFlow)) {
            return "redirect:/main";
        }
        return "redirect:/main";
    }

    @PostMapping("/count-create-doc")
    public String countCreateDoc(
            @RequestParam int countCreateDoc,
            @RequestParam int periodCreateDoc
    ) {
        restrictionService.saveCreateDoc(countCreateDoc, periodCreateDoc);
        return "redirect:/main";
    }

    @PostMapping("/count-flow-both-sides")
    public String countFlowBothSides(
            @RequestParam int countFlowBetBothSides
    ) {
        restrictionService.saveCountFlowBetBothSides(countFlowBetBothSides);
        return "redirect:/main";
    }
}
