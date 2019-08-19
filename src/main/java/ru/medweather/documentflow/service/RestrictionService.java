package ru.medweather.documentflow.service;

import org.springframework.stereotype.Service;
import ru.medweather.documentflow.models.Document;
import ru.medweather.documentflow.models.Firm;
import ru.medweather.documentflow.models.Restriction;
import ru.medweather.documentflow.repos.DocumentRepos;
import ru.medweather.documentflow.repos.FirmRepos;
import ru.medweather.documentflow.repos.RestrictionRepos;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

@Service
public class RestrictionService {

    private final RestrictionRepos restrictionRepos;

    private final FirmRepos firmRepos;

    private final DocumentRepos documentRepos;

    public RestrictionService(RestrictionRepos restrictionRepos, DocumentFlowService flowService, FirmRepos firmRepos, DocumentRepos documentRepos) {
        this.restrictionRepos = restrictionRepos;
        this.firmRepos = firmRepos;
        this.documentRepos = documentRepos;
    }

    /**
     * Период работы СЭД
     *
     * @param timeStart
     * @param timeFinish
     * @return
     */
    public boolean isPeriodDocumentFlow(int timeStart, int timeFinish) {
        Restriction restriction = restrictionRepos.findById(1);
        restriction.setTimeStart(timeStart);
        restriction.setTimeFinish(timeFinish);
        restrictionRepos.save(restriction);
        LocalTime ts = LocalTime.of(restriction.getTimeStart(), 0);
        LocalTime tf = LocalTime.of(restriction.getTimeFinish(), 0);
        return ts.isBefore(tf)
                ? LocalTime.now().isAfter(ts) && LocalTime.now().isBefore(tf)
                : LocalTime.now().isAfter(ts) || LocalTime.now().isBefore(tf);
    }

    /**
     * Количество документооборотов для одного пользователя
     *
     * @param firm
     * @param count
     * @return
     */
    public boolean isDocumentFlow(Firm firm, int count) {
        Restriction restriction = restrictionRepos.findById(1);
        restriction.setCountDocFlow(count);
        restrictionRepos.save(restriction);
        return firmRepos.findByUsername(firm.getUsername()).getDocuments().size() < restriction.getCountDocFlow();
    }

    /**
     * Количество созданных документов за определенный промежуток времени
     * (TODO пока заглушка)
     *
     * @param firm
     * @param count
     * @param hours
     * @return
     */
    public boolean isCreateDocument(Firm firm, int count, int hours) {
        Restriction restriction = restrictionRepos.findById(1);
        Iterable<Document> documents = documentRepos.findAll();
        LocalTime now = LocalTime.now();
        LocalTime after = now.plusHours(restriction.getPeriodCreateDoc());
        //TODO сделать промежуток времени в течение которого создается определенное количество документов
        return true;
    }

    /**
     * Количество документооборотов для двух компаний
     * (TODO пока заглушка)
     *
     * @param firm
     * @param otherName
     * @return
     */
    public boolean isDocFlowBetweenBothSides(Firm firm, String otherName) {
        Restriction restriction = restrictionRepos.findById(1);
        Firm other = firmRepos.findByName(otherName);
        List<Document> docs = new ArrayList<>();
        try {
            Iterator<Document> it = documentRepos.findAll().iterator();
            while (it.hasNext()) {
                Document doc = it.next();
                if((firm.equals(doc.getAuthor()) && other.equals(doc.getOtherSide()))
                        || (firm.equals(doc.getOtherSide()) && other.equals(doc.getAuthor()))) {
                    docs.add(doc);
                }
            }
        }
        catch (ConcurrentModificationException cme) {
            cme.getMessage();
        }
        //TODO доделать ограничение по ведению документооборота между двумя компаниями
        return true;
    }

    /**
     * Сохранение количества документооборотов для двух компаний в БД
     *
     * @param count
     */
    public void saveCountFlowBetBothSides(int count) {
        Restriction restriction = restrictionRepos.findById(1);
        restriction.setCountFlowBetBothSides(count);
        restrictionRepos.save(restriction);
    }

    /**
     * Сохранение количества созданных документов за определенный промежуток времени в БД
     *
     * @param count
     * @param hours
     */
    public void saveCreateDoc(int count, int hours) {
        Restriction restriction = restrictionRepos.findById(1);
        restriction.setCountCreateDoc(count);
        restriction.setPeriodCreateDoc(hours);
        restrictionRepos.save(restriction);
    }

    /**
     * Возвращает ограничение
     *
     * @return
     */
    public Restriction getRestriction() {
        return restrictionRepos.findById(1);
    }
}
