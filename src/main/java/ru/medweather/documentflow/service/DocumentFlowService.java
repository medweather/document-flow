package ru.medweather.documentflow.service;

import org.springframework.stereotype.Service;
import ru.medweather.documentflow.models.Document;
import ru.medweather.documentflow.models.Firm;
import ru.medweather.documentflow.repos.DocumentRepos;
import ru.medweather.documentflow.repos.FirmRepos;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Set;

@Service
public class DocumentFlowService implements DocumentFlow {

    private final DocumentRepos documentRepos;

    private final FirmRepos firmRepos;

    private Set<Document> documents;

    public DocumentFlowService(DocumentRepos documentRepos, FirmRepos firmRepos) {
        this.documentRepos = documentRepos;
        this.firmRepos = firmRepos;
    }

    /**
     * Создание документа
     *
     * @param author
     * @param otherName
     * @param document
     */
    @Override
    public void createDocument(Firm author, String otherName, Document document) {
        document.setAuthor(author);
        document.setOtherSide(firmRepos.findByName(otherName));
        documentRepos.save(document);
    }

    /**
     * Добавление документа в СЭД
     *
     * @param id
     * @param author
     */
    @Override
    public void addDocument(int id, Firm author) {
        Document document = documentRepos.findById(id);
        documents = firmRepos.findByUsername(author.getUsername()).getDocuments();
        document.setFirm(author);
        documents.add(document);
        documentRepos.save(document);

    }

    /**
     * Удаление документа
     *
     * @param id
     * @param firm
     */
    @Override
    public void deleteDocument(int id, Firm firm) {
        Document document = documentRepos.findById(id);
        removeDoc(document, firm);
        documentRepos.delete(document);
    }

    /**
     * Редактирование документа
     *
     * @param id
     * @param title
     * @param about
     * @param author
     */
    @Override
    public void editDocument(int id, String title, String about, Firm author) {
        Document document = documentRepos.findById(id);
        removeDoc(document, author);
        if (author.equals(document.getOtherSide())) {
            document.setOtherSide(document.getAuthor());
            document.setAuthor(author);
            document.setSign(false);
        }
        document.setTitle(title);
        document.setAbout(about);
        documentRepos.save(document);
        getDocumentsCurrentUser(author).add(document);
    }

    /**
     * Подписание документа и отправка другой стороне
     *
     * @param id
     * @param firm
     */
    @Override
    public void signDocument(int id, Firm firm) {
        Document document = documentRepos.findById(id);
        removeDoc(document, firm);
        if (firm.equals(document.getAuthor())) {
            document.setSign(true);
            document.setFirm(document.getOtherSide());
        } else if (firm.equals(document.getOtherSide())) {
            document.setSignOtherSide(true);
            document.setFirm(null);
        }
        documentRepos.save(document);
    }

    /**
     * Список добавленных документов в СЭД
     *
     * @return
     */
    @Override
    public Set<Document> getDocuments() {
        return documents;
    }

    /**
     * Список добавленных документов текущего пользователя
     *
     * @param currentUser
     * @return
     */
    public Set<Document> getDocumentsCurrentUser(Firm currentUser) {
        return firmRepos.findByUsername(currentUser.getUsername()).getDocuments();
    }

    private void removeDoc(Document document, Firm firm)  {
        try {
            Iterator<Document> it = getDocumentsCurrentUser(firm).iterator();
            while (it.hasNext()) {
                Document doc = it.next();
                if(doc.equals(document)) {
                    getDocumentsCurrentUser(firm).remove(doc);
                }
            }
        }
        catch (ConcurrentModificationException cme) {
            cme.getMessage();
        }
    }
}
