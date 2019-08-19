package ru.medweather.documentflow.service;

import ru.medweather.documentflow.models.Document;
import ru.medweather.documentflow.models.Firm;

import java.util.Set;

public interface DocumentFlow{
    void createDocument(Firm author, String otherName, Document document);
    void addDocument(int id, Firm author);
    void deleteDocument(int id, Firm firm);
    void editDocument(int id, String title, String about, Firm author);
    void signDocument(int id, Firm firm);
    Set<Document> getDocuments();
}
