package digital.paisley.tmt.entities;

import lombok.*;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class StoreOrder implements java.io.Serializable {

    private static final long serialVersionUID = -4497997807305980954L;

    public Long id;

    public String orderId;

    public Date orderDate;

    public Date shipDate;

    public String shipMode;

    public Integer quantity;

    public BigDecimal discount;

    public BigDecimal profit;

    public String productId;

    public String customerName;

    public String category;

    public String customerId;

    public String productName;

}
