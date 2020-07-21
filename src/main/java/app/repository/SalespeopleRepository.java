package app.repository;

import app.module.entities.Salespeople;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalespeopleRepository extends JpaRepository<Salespeople, Long> {
}
