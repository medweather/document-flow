package ru.medweather.documentflow.repos;

import org.springframework.data.repository.CrudRepository;
import ru.medweather.documentflow.models.Document;

public interface DocumentRepos extends CrudRepository<Document, Integer> {
    Document findById(int id);
}
