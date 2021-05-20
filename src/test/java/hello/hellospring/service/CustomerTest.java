package hello.hellospring.service;

import car_kiosk.CustomerDAO;
import org.junit.jupiter.api.Test;

public class CustomerTest {
    CustomerDAO customerDAO = CustomerDAO.getInstance();

    @Test
    void read(){
        customerDAO.GetLastRow();
    }

}
