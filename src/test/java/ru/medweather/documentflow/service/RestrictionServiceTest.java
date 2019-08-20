package ru.medweather.documentflow.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.medweather.documentflow.models.Firm;
import ru.medweather.documentflow.models.Restriction;
import ru.medweather.documentflow.repos.FirmRepos;
import ru.medweather.documentflow.repos.RestrictionRepos;

import java.time.LocalTime;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestrictionServiceTest {
    @Autowired
    private RestrictionService restrictionService;

    @MockBean
    private RestrictionRepos restrictionRepos;

    @MockBean
    private FirmRepos firmRepos;

    @Test
    public void isPeriodDocumentFlow() {
        int timeStart = 12;
        int timeFinish = 14;
        Restriction restriction = new Restriction();
        restriction.setId(1);

        Mockito.doReturn(restriction)
                .when(restrictionRepos)
                .findById(1);

        boolean pdf = restrictionService.isPeriodDocumentFlow(timeStart, timeFinish);

        if (LocalTime.now().isBefore(LocalTime.of(timeStart, 0))
                || LocalTime.now().isAfter(LocalTime.of(timeFinish, 0))) {
            assertFalse(pdf);
        } else {
            assertTrue(pdf);
        }
        assertEquals(restriction.getTimeFinish(), timeFinish);
        assertEquals(restriction.getTimeStart(), timeStart);
        Mockito.verify(restrictionRepos, Mockito.times(1)).save(restriction);
    }

    @Test
    public void isPeriodDocumentFlowSecondOption() {
        int timeStart = 22;
        int timeFinish = 7;
        Restriction restriction = new Restriction();
        restriction.setId(1);

        Mockito.doReturn(restriction)
                .when(restrictionRepos)
                .findById(1);

        boolean pdf = restrictionService.isPeriodDocumentFlow(timeStart, timeFinish);

        if (LocalTime.now().isBefore(LocalTime.of(timeStart, 0))
                || LocalTime.now().isAfter(LocalTime.of(timeFinish, 0))) {
            assertFalse(pdf);
        } else {
            assertTrue(pdf);
        }
        assertEquals(restriction.getTimeFinish(), timeFinish);
        assertEquals(restriction.getTimeStart(), timeStart);
        Mockito.verify(restrictionRepos, Mockito.times(1)).save(restriction);
    }

    @Test
    public void isDocumentFlow() {
        Firm firm = new Firm();
        firm.setUsername("fest");
        int count = 235565;
        Restriction restriction = new Restriction();
        restriction.setId(1);

        Mockito.doReturn(restriction)
                .when(restrictionRepos)
                .findById(1);

        Mockito.doReturn(firm)
                .when(firmRepos)
                .findByUsername(firm.getUsername());

        boolean df = restrictionService.isDocumentFlow(firm, count);

        assertTrue(df);
        assertEquals(restriction.getCountDocFlow(), count);
        Mockito.verify(restrictionRepos, Mockito.times(1)).save(restriction);
    }

    @Test
    public void saveCountFlowBetBothSides() {
        int count = 345345;
        Restriction restriction = new Restriction();
        restriction.setId(1);

        Mockito.doReturn(restriction)
                .when(restrictionRepos)
                .findById(1);

        restrictionService.saveCountFlowBetBothSides(count);

        assertEquals(restriction.getCountFlowBetBothSides(), count);
        Mockito.verify(restrictionRepos, Mockito.times(1)).save(restriction);
    }

    @Test
    public void saveCreateDoc() {
        int count = 3421;
        int hours = 12;
        Restriction restriction = new Restriction();
        restriction.setId(1);

        Mockito.doReturn(restriction)
                .when(restrictionRepos)
                .findById(1);

        restrictionService.saveCreateDoc(count, hours);

        assertEquals(restriction.getCountCreateDoc(), count);
        assertEquals(restriction.getPeriodCreateDoc(), hours);
        Mockito.verify(restrictionRepos, Mockito.times(1)).save(restriction);
    }
}