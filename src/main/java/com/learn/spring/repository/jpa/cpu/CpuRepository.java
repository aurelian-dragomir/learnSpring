package com.learn.spring.repository.jpa.cpu;

import com.learn.spring.entity.jpa.cpu.Cpu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CpuRepository extends JpaRepository<Cpu, String> {
}
