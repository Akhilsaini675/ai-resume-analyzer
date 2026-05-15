package Resumeanalyzer.Controller;
import Resumeanalyzer.model.ResumeData;
import Resumeanalyzer.repository.ResumeRepository;
import Resumeanalyzer.service.ResumeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ResumeController {

    @Autowired
    private ResumeService resumeService;
    @Autowired
    private ResumeRepository resumeRepository;

    @GetMapping("/")
    public String home() {

        return "index";
    }

    @PostMapping("/upload")
    public String uploadResume(@RequestParam("file") MultipartFile file,
                               Model model) {

        ResumeData result = resumeService.analyzeResume(file);
        resumeRepository.save(result);

        model.addAttribute("resume", result);

        return "result";

    }
    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        model.addAttribute("resumes",
                resumeRepository.findAll());

        return "dashboard";
    }
}