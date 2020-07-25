package app.repository;

import app.module.entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellersRepository extends JpaRepository<Seller, Long> {
}
