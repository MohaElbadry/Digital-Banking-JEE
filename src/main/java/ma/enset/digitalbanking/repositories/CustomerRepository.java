package ma.enset.digitalbanking.repositories;

import ma.enset.digitalbanking.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	Customer findByName(String name);
	Customer findByEmail(String email);
	Customer findByIdAndName(Long id, String name);
	Customer findByIdAndEmail(Long id, String email);
}
