package com.example.ExcelReader.repository;

import com.example.ExcelReader.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
}

