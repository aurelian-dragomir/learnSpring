package ing.hub.ingHub.jpa.cpu;

import ing.hub.ingHub.entity.jpa.cpu.Cpu;
import ing.hub.ingHub.entity.jpa.cpu.CpuDetails;
import ing.hub.ingHub.jpa.BaseJpaTest;
import ing.hub.ingHub.repository.jpa.cpu.CpuDetailsRepository;
import ing.hub.ingHub.repository.jpa.cpu.CpuRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CpuTest extends BaseJpaTest {

    @Autowired
    private CpuRepository cpuRepository;

    @Autowired
    private CpuDetailsRepository cpuDetailsRepository;

    @Test
    public void saveCpuAndDetails() {
        executeInTransaction(() -> {

            Cpu cpu = new Cpu("123", "AMD");
            CpuDetails cpuDetails = new CpuDetails(2, cpu);

            cpuRepository.save(cpu);
            cpuDetailsRepository.save(cpuDetails);

            return null;
        });
    }
}
