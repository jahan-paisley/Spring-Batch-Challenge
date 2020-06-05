package digital.paisley.tmt.entities;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "STORE_ORDER")
@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
@ToString
public class StoreOrder implements java.io.Serializable {

    private static final long serialVersionUID = -4497997807305980954L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Long id;

    @Column(name = "ORDER_ID", length = 20, nullable = false)
    @NotBlank
    @Length(max = 20)
    public String orderId;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "ORDER_DATE")
    public Date orderDate;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "SHIP_DATE")
    public Date shipDate;

    @Length(max = 20)
    @Column(name = "SHIP_MODE", length = 20)
    public String shipMode;

    @NotNull
    @Column(name = "QUANTITY", nullable = false)
    public Integer quantity;

    @Digits(integer = 3, fraction = 2)
    @Column(name = "DISCOUNT")
    public BigDecimal discount;

    @Digits(integer = 6, fraction = 4)
    @Column(name = "PROFIT")
    public BigDecimal profit;

    @NotBlank
    @Length(max = 20)
    @Column(name = "PRODUCT_ID", length = 20, nullable = false)
    public String productId;

    @NotBlank
    @Column(name = "CUSTOMER_NAME", nullable = false)
    public String customerName;

    @NotBlank
    @Column(name = "CATEGORY", nullable = false)
    public String category;

    @NotBlank
    @Length(max = 20)
    @Column(name = "CUSTOMER_ID", length = 20, nullable = false)
    public String customerId;

    @Column(name = "PRODUCT_NAME")
    public String productName;

}