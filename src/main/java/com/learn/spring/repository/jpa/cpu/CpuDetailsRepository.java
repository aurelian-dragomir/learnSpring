package com.learn.spring.repository.jpa.cpu;

import com.learn.spring.entity.jpa.cpu.CpuDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CpuDetailsRepository extends JpaRepository<CpuDetails, String> {
}
