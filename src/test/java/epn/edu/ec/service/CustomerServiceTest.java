package epn.edu.ec.service;

import epn.edu.ec.model.customer.CustomersResponse;
import epn.edu.ec.repository.CustomerRepository;
import epn.edu.ec.repository.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    private Customer customerA;
    private Customer customerB;

    @BeforeEach
    public void setUp(){
        customerA = Customer
                .builder()
                .id(1)
                .name("Angelo")
                .phone("0231564895")
                .build();

        customerB = Customer
                .builder()
                .id(2)
                .name("Pedro")
                .phone("0231564895")
                .build();
    }


    @Test
    public void getCustomers_ShouldReturnAllCustomersSortedByName() {
        //ARRANGE
        List<Customer> customers = Arrays.asList(customerA, customerB);
        when(customerRepository.findAll()).thenReturn(customers);

        //ACT
        CustomersResponse customersResponse = customerService.getCustomers();

        //ASSERT
        assertNotNull(customersResponse);
        assertEquals(2, customersResponse.getCustomers().size());

        assertEquals("Angelo", customersResponse.getCustomers().get(0).getName());
        assertEquals("Pedro", customersResponse.getCustomers().get(1).getName());

        verify(customerRepository, times(1)).findAll();
    }


}
