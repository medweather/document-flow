package ru.medweather.documentflow.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.medweather.documentflow.models.Document;
import ru.medweather.documentflow.models.Firm;
import ru.medweather.documentflow.repos.DocumentRepos;
import ru.medweather.documentflow.repos.FirmRepos;
import ru.medweather.documentflow.service.DocumentFlowService;
import ru.medweather.documentflow.service.RestrictionService;

@Controller
public class DocumentFlowController {

    private final DocumentRepos documentRepos;

    private final DocumentFlowService flowService;

    private final RestrictionService restrictionService;

    private final FirmRepos firmRepos;

    public DocumentFlowController(DocumentRepos documentRepos, DocumentFlowService flowService, RestrictionService restrictionService, FirmRepos firmRepos) {
        this.documentRepos = documentRepos;
        this.flowService = flowService;
        this.restrictionService = restrictionService;
        this.firmRepos = firmRepos;
    }

    /**
     * Приветствие
     *
     * @return
     */
    @GetMapping("/")
    public String greeting() {
        return "greeting";
    }

    /**
     * Загрузка главной страницы
     *
     * @param model
     * @return
     */
    @GetMapping("/main")
    public String main(Model model) {
        Iterable<Document> documents = documentRepos.findAll();
        model.addAttribute("documents", documents);
        model.addAttribute("restriction", restrictionService.getRestriction());
        return "main";
    }

    /**
     * Создание документа
     *
     * @param author
     * @param otherName
     * @param document
     * @param model
     * @return
     */
    @PostMapping("/main")
    public String create(
            @AuthenticationPrincipal Firm author,
            @RequestParam(required = false) String otherName,
            Document document,
            Model model
    ) {
        flowService.createDocument(author, otherName, document);
        Iterable<Document> documents = documentRepos.findAll();
        Iterable<Firm> firms = firmRepos.findAll();
        model.addAttribute("firms", firms);
        model.addAttribute("documents", documents);
        model.addAttribute("restriction", restrictionService.getRestriction());
        return "main";
    }

    /**
     * Загрузка страницы СЭД (системы электронного документооборота)
     *
     * @param firm
     * @param model
     * @return
     */
    @GetMapping("/documentFlow")
    public String getAdding(
            @AuthenticationPrincipal Firm firm,
            Model model
    ) {
        model.addAttribute("documentsFlow", flowService.getDocumentsCurrentUser(firm));
        return "showDocument";
    }

    /**
     * Добавление документа в СЭД
     *
     * @param author
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/documentFlow/{id}")
    public String add(
            @AuthenticationPrincipal Firm author,
            @PathVariable("id") int id,
            Model model
    ) {
        if(restrictionService.isPeriodDocumentFlow(restrictionService.getRestriction().getTimeStart(),
                restrictionService.getRestriction().getTimeFinish())
                && restrictionService.isDocumentFlow(author, restrictionService.getRestriction().getCountDocFlow())) {
            flowService.addDocument(id, author);
            model.addAttribute("documentsFlow", flowService.getDocuments());
            return "showDocument";
        }
        return "redirect:/main";
    }

    /**
     * Удаление документа
     *
     * @param firm
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/delete/{id}")
    public String delete(
            @AuthenticationPrincipal Firm firm,
            @PathVariable("id") int id,
            Model model
    ) {
        if(restrictionService.isPeriodDocumentFlow(restrictionService.getRestriction().getTimeStart(),
                restrictionService.getRestriction().getTimeFinish()))
            flowService.deleteDocument(id, firm);
        model.addAttribute("documentsFlow", flowService.getDocuments());
        return "redirect:/documentFlow";
    }

    /**
     * Загрузка страницы редактирования
     *
     * @param id
     * @param document
     * @param model
     * @return
     */
    @GetMapping("/edit/{id}")
    public String getEdit(
            @PathVariable("id") int id,
            Document document,
            Model model
    ) {
        model.addAttribute("document", document);
        model.addAttribute("documentsFlow", flowService.getDocuments());
        return "edit";
    }

    /**
     * Редактирование документа
     *
     * @param author
     * @param id
     * @param title
     * @param about
     * @param model
     * @return
     */
    @PostMapping("/edit/{id}")
    public String edit(
            @AuthenticationPrincipal Firm author,
            @PathVariable("id") int id,
            @RequestParam String title,
            @RequestParam String about,
            Model model
    ) {
        if(restrictionService.isPeriodDocumentFlow(restrictionService.getRestriction().getTimeStart(),
                restrictionService.getRestriction().getTimeFinish()))
            flowService.editDocument(id, title, about, author);
        model.addAttribute("documentsFlow", flowService.getDocuments());
        return "redirect:/documentFlow";
    }

    /**
     * Подписание документа и отправка другой стороне
     *
     * @param firm
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/sign/{id}")
    public String signature(
            @AuthenticationPrincipal Firm firm,
            @PathVariable("id") int id,
            Model model
    ) {
        if(restrictionService.isPeriodDocumentFlow(restrictionService.getRestriction().getTimeStart(),
                restrictionService.getRestriction().getTimeFinish()))
            flowService.signDocument(id, firm);
        model.addAttribute("documentsFlow", flowService.getDocuments());
        return "redirect:/documentFlow";
    }
}
