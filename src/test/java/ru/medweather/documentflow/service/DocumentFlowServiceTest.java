package ru.medweather.documentflow.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.medweather.documentflow.models.Document;
import ru.medweather.documentflow.models.Firm;
import ru.medweather.documentflow.repos.DocumentRepos;
import ru.medweather.documentflow.repos.FirmRepos;

import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DocumentFlowServiceTest {
    @Autowired
    private DocumentFlowService documentFlowService;

    @MockBean
    private FirmRepos firmRepos;

    @MockBean
    private DocumentRepos documentRepos;

    @Test
    public void createDocument() {
        Firm author = new Firm();
        Firm firm = new Firm();
        String otherName = "НПАО \"Рыбачок\"";
        firm.setName(otherName);
        Document document = new Document();

        Mockito.doReturn(firm)
                .when(firmRepos)
                .findByName(otherName);

        documentFlowService.createDocument(author, otherName, document);

        assertEquals(document.getAuthor(), author);
        assertEquals(document.getOtherSide(), firm);
        Mockito.verify(documentRepos, Mockito.times(1)).save(document);
    }

    @Test
    public void addDocument() {
        int id = 12;
        Document document = new Document();
        document.setId(id);
        Firm firm = new Firm();
        firm.setUsername("florida");
        Set<Document> documents = firm.getDocuments();

        Mockito.doReturn(document)
                .when(documentRepos)
                .findById(id);

        Mockito.doReturn(firm)
                .when(firmRepos)
                .findByUsername(firm.getUsername());

        documentFlowService.addDocument(id, firm);

        assertEquals(document.getFirm().getId(), firm.getId());
        assertTrue(documents.contains(document));

        Mockito.verify(documentRepos, Mockito.times(1)).save(document);
    }

    @Test
    public void deleteDocument() {
        int id = 123;
        Document document = new Document();
        document.setId(123);
        Firm firm = new Firm();
        firm.setUsername("gorod");
        Set<Document> documents = firm.getDocuments();

        Mockito.doReturn(document)
                .when(documentRepos)
                .findById(id);

        Mockito.doReturn(firm)
                .when(firmRepos)
                .findByUsername(firm.getUsername());

        documentFlowService.deleteDocument(id, firm);

        assertFalse(documents.contains(document));
        Mockito.verify(documentRepos, Mockito.times(1)).delete(document);
    }

    @Test
    public void editDocument() {
        int id = 234;
        Document document = new Document();
        document.setId(id);
        String title = "Договор №34";
        String about = "ООО «ТелекомРу», именуемое в дальнейшем «Исполнитель», " +
                "в лице генерального директора Юстинова Владимира Константиновича, " +
                "действующего на основании устава ООО от 25 марта 2005 г. с одной стороны, " +
                "и ООО «ЮрКонсульт», именуемое в дальнейшем «Заказчик», " +
                "в лице генерального директора Вирчишина Давида Львовича, " +
                "действующего на основании устава ООО от 12 июля 2004 г., " +
                "с другой стороны, совместно именуемые как «Стороны» или «Сторона», " +
                "заключили настоящий Договор о нижеследующем";
        Firm author = new Firm();
        author.setUsername("telecomru");
        Set<Document> documents = author.getDocuments();

        Mockito.doReturn(document)
                .when(documentRepos)
                .findById(id);

        Mockito.doReturn(author)
                .when(firmRepos)
                .findByUsername(author.getUsername());

        documentFlowService.editDocument(id, title, about, author);

        assertTrue(documents.contains(document));
        assertEquals(document.getTitle(), title);
        assertEquals(document.getAbout(), about);
        if(author.equals(document.getOtherSide())) {
            assertEquals(document.getOtherSide(), document.getAuthor());
            assertEquals(document.getAuthor(), author);
            assertFalse(document.isSign());
        }
        Mockito.verify(documentRepos, Mockito.times(1)).save(document);
    }

    @Test
    public void signDocument() {
        int id = 789;
        Document document = new Document();
        document.setId(id);
        Firm firm = new Firm();
        firm.setUsername("port");

        Mockito.doReturn(document)
                .when(documentRepos)
                .findById(id);

        Mockito.doReturn(firm)
                .when(firmRepos)
                .findByUsername(firm.getUsername());

        documentFlowService.signDocument(id, firm);

        if(firm.equals(document.getAuthor())) {
            assertTrue(document.isSign());
            assertEquals(document.getFirm(), document.getOtherSide());
        } else if(firm.equals(document.getOtherSide())) {
            assertTrue(document.isSignOtherSide());
            assertNull(document.getFirm());
        }
        Mockito.verify(documentRepos, Mockito.times(1)).save(document);
    }

    @Test
    public void getDocumentsCurrentUser() {
        Firm firm = new Firm();
        firm.setUsername("pochta");
        Set<Document> documents = firm.getDocuments();

        Mockito.doReturn(firm)
                .when(firmRepos)
                .findByUsername(firm.getUsername());

        Set<Document> documentsResult = documentFlowService.getDocumentsCurrentUser(firm);

        assertEquals(documentsResult, documents);
    }
}