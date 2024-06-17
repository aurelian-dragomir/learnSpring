package ing.hub.ingHub.repository.jpa.cpu;

import ing.hub.ingHub.entity.jpa.cpu.CpuDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CpuDetailsRepository extends JpaRepository<CpuDetails, String> {
}
