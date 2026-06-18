package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CategoryMapper;
import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class CustomerServiceTest {

    public static final Long id = 1L;
    public static final String firstName = "FirstN";
    public static final String lastName = "LastN";

    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    public void getAllCustomers() throws Exception {
        List<Customer> customers = Arrays.asList(new Customer(), new Customer(), new Customer());
        when(customerRepository.findAll()).thenReturn(customers);
        List<CustomerDTO> customerDTOS = customerService.getAllCustomers();
        assertEquals(3, customerDTOS.size());
    }

    @Test
    public void getCustomerByLastname() throws Exception {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setFirstname(firstName);
        customer.setLastname(lastName);

        when(customerRepository.findByLastname(anyString())).thenReturn(customer);

        CustomerDTO customerDTO = customerService.getCustomerByLastname(lastName);

        assertEquals(id, customerDTO.getId());
        assertEquals(firstName, customerDTO.getFirstname());
        assertEquals(lastName, customerDTO.getLastname());
    }
}
