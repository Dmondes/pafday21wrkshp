package sg.edu.nus.iss.day21wrkshp.repo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import sg.edu.nus.iss.day21wrkshp.model.Customer;
import sg.edu.nus.iss.day21wrkshp.model.Orders;

@Repository
public class NorthRepo {

    @Autowired
    private JdbcTemplate jTemplate;

    public List<Customer> getAllCust(int limit, int offset) {
        List<Customer> customers = new ArrayList<>();
        SqlRowSet rs = jTemplate.queryForRowSet(
            Queries.SQL_GET_ALL_CUST,
            limit,
            offset
        );
        while (rs.next()) { //@Builder in model, Customer customer = new CustomerRowMapper().mapRow(rs, rowNum);
            Customer customer = new Customer();
            customer.setId(rs.getInt("id"));
            customer.setLast_name(rs.getString("last_name"));
            customer.setFirst_name(rs.getString("first_name"));
            customer.setJob_title(rs.getString("job_title"));
            customer.setBusiness_phone(rs.getString("business_phone"));
            customer.setFax_number(rs.getString("fax_number"));
            customer.setAddress(rs.getString("address"));
            customer.setCity(rs.getString("city"));
            customer.setState_province(rs.getString("state_province"));
            customer.setZip_postal_code(rs.getString("zip_postal_code"));
            customer.setCountry_region(rs.getString("country_region"));
            customers.add(customer);
        }
        return customers;
    }

    public Customer getCustDetails(int id) {
        SqlRowSet rs = jTemplate.queryForRowSet(Queries.SQL_GET_CUST_DET, id);
        if (!rs.next()) {
            return null; // Return null if no customer found
        }
        Customer customer = new Customer();
        customer.setId(rs.getInt("id"));
        customer.setLast_name(rs.getString("last_name"));
        customer.setFirst_name(rs.getString("first_name"));
        customer.setJob_title(rs.getString("job_title"));
        customer.setBusiness_phone(rs.getString("business_phone"));
        customer.setFax_number(rs.getString("fax_number"));
        customer.setAddress(rs.getString("address"));
        customer.setCity(rs.getString("city"));
        customer.setState_province(rs.getString("state_province"));
        customer.setZip_postal_code(rs.getString("zip_postal_code"));
        customer.setCountry_region(rs.getString("country_region"));
        return customer;
    }

    public List<Orders> getCustOrders(int cust_id) {
        return jTemplate.query(
            Queries.SQL_GET_ALL_ORDERS,
            (rs, rowNum) -> {
                Orders order = new Orders();
                order.setId(rs.getInt("id"));
                order.setCustomer_id(rs.getInt("customer_id"));
                order.setEmployee_id(rs.getInt("employee_id"));

                java.sql.Timestamp orderTs = rs.getTimestamp("order_date");
                if (orderTs != null) {
                    order.setOrder_date(orderTs.toLocalDateTime());
                }

                java.sql.Timestamp shippedTs = rs.getTimestamp("shipped_date");
                if (shippedTs != null) {
                    order.setShipped_date(shippedTs.toLocalDateTime());
                }

                order.setShipper_id(rs.getInt("shipper_id"));
                order.setShip_name(rs.getString("ship_name"));
                order.setShip_address(rs.getString("ship_address"));
                order.setShip_city(rs.getString("ship_city"));
                order.setShip_state_province(
                    rs.getString("ship_state_province")
                );
                order.setShip_zip_postal_code(
                    rs.getString("ship_zip_postal_code")
                );
                order.setShip_country_region(
                    rs.getString("ship_country_region")
                );
                order.setShipping_fee(rs.getFloat("shipping_fee"));
                return order;
            },
            cust_id
        );
    }
}
