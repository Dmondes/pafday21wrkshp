package sg.edu.nus.iss.day21wrkshp.restcontroller;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sg.edu.nus.iss.day21wrkshp.model.Customer;
import sg.edu.nus.iss.day21wrkshp.model.Orders;
import sg.edu.nus.iss.day21wrkshp.service.NorthService;

@RestController
@RequestMapping("/api")
public class GetController {

    @Autowired
    private NorthService northService;

    @GetMapping(
        path = "/customers",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Customer>> getAllCust(
        @RequestParam(defaultValue = "0") int offset,
        @RequestParam(defaultValue = "5") int limit
    ) {
        List<Customer> customers = northService.getAllCustomers(limit, offset);
        return ResponseEntity.ok(customers);
    }

    @GetMapping(
        path = "customer/{customer_id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getCustDetails(
        @PathVariable("customer_id") Integer custId
    ) {
        Customer customer = northService.getCustomerById(custId);

        if (customer == null) {
            JsonObject errorJ = Json.createObjectBuilder()
                .add("error", "Customer " + custId + " not found")
                .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                errorJ.toString()
            );
        }
        return ResponseEntity.ok(customer);
    }

    @GetMapping(
        path = "/customer/{customer_id}/orders",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getCustOrders(
        @PathVariable("customer_id") Integer custId
    ) {
        List<Orders> orders = northService.getCustOrders(custId);
        if (orders == null) {
            JsonObject errorJ = Json.createObjectBuilder()
                .add("error", "Customer " + custId + " not found")
                .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                errorJ.toString()
            );
        }
        return ResponseEntity.ok(orders);
    }
}
