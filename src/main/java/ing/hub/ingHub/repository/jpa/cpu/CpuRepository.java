package ing.hub.ingHub.repository.jpa.cpu;

import ing.hub.ingHub.entity.jpa.cpu.Cpu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CpuRepository extends JpaRepository<Cpu, String> {
}
