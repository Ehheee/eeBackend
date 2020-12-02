package kaur.ee.trial.data.db.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import kaur.ee.trial.data.db.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	

}
