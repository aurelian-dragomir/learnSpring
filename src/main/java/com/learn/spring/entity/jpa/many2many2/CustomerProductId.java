package ing.hub.ingHub.entity.jpa.many2many2;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerProductId implements Serializable {
    private Long customerId;
    private Long productId;
}
