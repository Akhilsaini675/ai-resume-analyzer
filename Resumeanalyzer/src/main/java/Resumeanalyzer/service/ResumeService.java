package Resumeanalyzer.service;
import java.time.LocalDateTime;
import Resumeanalyzer.model.ResumeData;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResumeService {

    private final String[] SKILLS = {
            "java",
            "spring",
            "mysql",
            "html",
            "css",
            "javascript",
            "react",
            "python",
            "sql",
            "git"
    };

    public ResumeData analyzeResume(MultipartFile file) {

        try {

            PDDocument document = PDDocument.load(file.getInputStream());

            PDFTextStripper pdfTextStripper = new PDFTextStripper();

            String text = pdfTextStripper.getText(document);

            document.close();

            text = text.toLowerCase();

            List<String> foundSkills = new ArrayList<>();

            for (String skill : SKILLS) {

                if (text.contains(skill)) {

                    foundSkills.add(skill);
                }
            }

            int score = foundSkills.size() * 10;

            if(score > 100){
                score = 100;
            }

            ResumeData resumeData = new ResumeData();

            resumeData.setFileName(file.getOriginalFilename());

            resumeData.setAtsScore(score);

            resumeData.setSkills(foundSkills.toString());
            String suggestion = "";

            if(score < 30){

                suggestion =
                        "Add more technical skills and projects.";
            }

            else if(score < 60){

                suggestion =
                        "Good resume. Improve by adding certifications.";
            }

            else{

                suggestion =
                        "Excellent resume for ATS screening.";
            }
            resumeData.setSuggestions(suggestion);
            resumeData.setUploadTime(LocalDateTime.now());

            return resumeData;
        }

        catch (Exception e) {

            return null;
        }
    }
}
