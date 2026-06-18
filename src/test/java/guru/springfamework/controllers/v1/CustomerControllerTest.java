package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest {

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void testListCustomers() throws Exception {
        CustomerDTO customerDTO0 = new CustomerDTO();
        customerDTO0.setFirstname("FName0");
        customerDTO0.setLastname("LName0");

        CustomerDTO customerDTO1 = new CustomerDTO();
        customerDTO1.setFirstname("FName1");
        customerDTO1.setLastname("LName1");

        List<CustomerDTO> customerDTOs = Arrays.asList(customerDTO0, customerDTO1);

        when(customerService.getAllCustomers()).thenReturn(customerDTOs);

        mockMvc.perform(get("/api/v1/customers/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));
    }

    @Test
    public void testGetByNameCategories() throws Exception {
        CustomerDTO customerDTO0 = new CustomerDTO();
        customerDTO0.setId(1L);
        customerDTO0.setFirstname("FName0");
        customerDTO0.setLastname("LName0");

        when(customerService.getCustomerByLastname(anyString())).thenReturn(customerDTO0);

        mockMvc.perform(get("/api/v1/customers/LName0").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lastname", equalTo("LName0")));
    }
}
