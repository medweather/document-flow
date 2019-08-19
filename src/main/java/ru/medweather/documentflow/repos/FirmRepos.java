package ru.medweather.documentflow.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.medweather.documentflow.models.Firm;

public interface FirmRepos extends JpaRepository<Firm, Integer> {
    Firm findByUsername(String username);
    Firm findByName(String name);
}
