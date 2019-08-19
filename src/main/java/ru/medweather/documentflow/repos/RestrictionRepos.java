package ru.medweather.documentflow.repos;

import org.springframework.data.repository.CrudRepository;
import ru.medweather.documentflow.models.Restriction;

public interface RestrictionRepos extends CrudRepository<Restriction, Integer> {
    Restriction findById(int id);
}
