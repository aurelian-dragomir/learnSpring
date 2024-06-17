package ing.hub.ingHub.entity.jpa.cpu;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Cpu {

    @Id
    private String id;

    private String brand;

    public Cpu(String id, String brand) {
        this.id = id;
        this.brand = brand;
    }
}
