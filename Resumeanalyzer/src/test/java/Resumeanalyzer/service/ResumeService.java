package Resumeanalyzer.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ResumeService {

    public String analyzeResume(MultipartFile file) {

        return "Resume uploaded successfully: " + file.getOriginalFilename();
    }
}