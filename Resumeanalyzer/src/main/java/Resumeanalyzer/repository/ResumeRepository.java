package Resumeanalyzer.repository;

import Resumeanalyzer.model.ResumeData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<ResumeData, Long> {

}