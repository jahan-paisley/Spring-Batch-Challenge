package digital.paisley.tmt.entities;

import lombok.*;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class StoreOrder implements java.io.Serializable {

    private static final long serialVersionUID = -4497997807305980954L;

    public Long id;

    @NotNull
    @NotEmpty
    @Size(min = 1,max = 20)
    public String orderId;

    @Temporal(value = TemporalType.TIMESTAMP)
    public Date orderDate;

    @Temporal(value = TemporalType.TIMESTAMP)
    public Date shipDate;

    @Size(max = 20)
    public String shipMode;

    @NotNull
    @NotEmpty
    public Integer quantity;

    @Digits(integer = 3, fraction = 2)
    public BigDecimal discount;

    @Digits(integer = 6, fraction = 4)
    public BigDecimal profit;

    @NotNull
    @NotEmpty
    @Size(min = 1,max = 20)
    public String productId;

    @NotNull
    @NotEmpty
    public String customerName;

    @NotNull
    @NotEmpty
    public String category;

    @NotNull
    @NotEmpty
    @Size(min = 1,max = 20)
    public String customerId;

    public String productName;

}