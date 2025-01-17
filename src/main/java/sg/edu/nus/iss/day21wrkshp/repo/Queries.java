package sg.edu.nus.iss.day21wrkshp.repo;

public class Queries {

    public static final String SQL_GET_ALL_CUST =
        """
        select id, last_name, first_name, job_title, business_phone, fax_number, address, city,state_province, zip_postal_code, country_region from customers limit ? offset ?
        """;
    public static final String SQL_GET_CUST_DET =
        """
        select id, last_name, first_name, job_title, business_phone, fax_number, address, city,state_province, zip_postal_code, country_region from customers where id = ?
        """;
    public static final String SQL_GET_ALL_ORDERS =
        """
        select id, employee_id, customer_id, order_date, shipped_date, shipper_id, ship_name, ship_address, ship_city, ship_state_province, ship_zip_postal_code, ship_country_region, shipping_fee from orders where customer_id = ?
        """;
}
