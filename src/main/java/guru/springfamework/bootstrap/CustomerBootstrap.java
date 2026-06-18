package guru.springfamework.bootstrap;

import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CustomerBootstrap implements CommandLineRunner {

    private CustomerRepository customerRepository;

    public CustomerBootstrap(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Customer customer0 = new Customer();
        customer0.setFirstname("Asuman");
        customer0.setLastname("Morina");

        Customer customer1 = new Customer();
        customer1.setFirstname("Mines");
        customer1.setLastname("Dacamara");

        Customer customer2 = new Customer();
        customer2.setFirstname("Liza");
        customer2.setLastname("Kovalenko");

        Customer customer3 = new Customer();
        customer3.setFirstname("Anastasia");
        customer3.setLastname("Husarenko");

        Customer customer4 = new Customer();
        customer4.setFirstname("Siena");
        customer4.setLastname("Meira");


        customerRepository.save(customer0);
        customerRepository.save(customer1);
        customerRepository.save(customer2);
        customerRepository.save(customer3);
        customerRepository.save(customer4);
        System.out.println("Data Loaded = " + customerRepository.count());
    }
}
