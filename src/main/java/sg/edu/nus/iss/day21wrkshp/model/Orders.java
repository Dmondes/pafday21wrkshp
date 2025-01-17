package sg.edu.nus.iss.day21wrkshp.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {

    private int id;
    private int employee_id;
    private int customer_id;
    private LocalDateTime order_date;
    private LocalDateTime shipped_date;
    private int shipper_id;
    private String ship_name;
    private String ship_address;
    private String ship_city;
    private String ship_state_province;
    private String ship_zip_postal_code;
    private String ship_country_region;
    private float shipping_fee;
}
