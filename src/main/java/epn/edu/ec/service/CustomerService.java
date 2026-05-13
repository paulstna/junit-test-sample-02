package epn.edu.ec.service;

import epn.edu.ec.model.customer.CustomerResponse;
import epn.edu.ec.model.customer.CustomersResponse;
import epn.edu.ec.repository.CustomerRepository;
import epn.edu.ec.repository.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomersResponse getCustomers() {
        return new CustomersResponse(
                customerRepository.findAll().stream()
                        .map(this::getCustomersResponse)
                        .sorted(Comparator.comparing(CustomerResponse::getName))
                        .collect(toList()));
    }

    private CustomerResponse getCustomersResponse(Customer customer) {
        return new CustomerResponse(customer.getId(), customer.getName(), customer.getPhone());
    }
}
