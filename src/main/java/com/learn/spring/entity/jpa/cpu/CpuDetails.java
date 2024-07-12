package ing.hub.ingHub.entity.jpa.cpu;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class CpuDetails {

    @Id
    private String id;

    private Integer noOfCores;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Cpu cpu;

    public CpuDetails(Integer noOfCores, Cpu cpu) {
        this.id = cpu.getId();
        this.noOfCores = noOfCores;
        this.cpu = cpu;
    }
}
