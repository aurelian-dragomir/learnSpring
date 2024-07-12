package com.learn.spring.jpa.cpu;

import com.learn.spring.entity.jpa.cpu.Cpu;
import com.learn.spring.entity.jpa.cpu.CpuDetails;
import com.learn.spring.jpa.BaseJpaTest;
import com.learn.spring.repository.jpa.cpu.CpuDetailsRepository;
import com.learn.spring.repository.jpa.cpu.CpuRepository;
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
