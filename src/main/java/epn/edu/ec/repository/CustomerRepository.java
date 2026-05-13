package epn.edu.ec.repository;

import epn.edu.ec.repository.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
