package sg.edu.nus.iss.day21wrkshp.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.edu.nus.iss.day21wrkshp.model.Customer;
import sg.edu.nus.iss.day21wrkshp.model.Orders;
import sg.edu.nus.iss.day21wrkshp.repo.NorthRepo;

@Service
public class NorthService {

    @Autowired
    private NorthRepo northRepo;

    public List<Customer> getAllCustomers(int limit, int offset) {
        return northRepo.getAllCust(limit, offset);
    }

    public Customer getCustomerById(Integer custId) {
        return northRepo.getCustDetails(custId);
    }

    public List<Orders> getCustOrders(Integer custId) {
        Customer customer = getCustomerById(custId);
        if (customer == null) {
            return null;
        }
        return northRepo.getCustOrders(custId);
    }
}
