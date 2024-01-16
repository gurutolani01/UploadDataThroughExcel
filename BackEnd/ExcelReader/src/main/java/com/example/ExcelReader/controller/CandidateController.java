package com.example.ExcelReader.controller;

import com.example.ExcelReader.entity.Candidate;
import com.example.ExcelReader.repository.CandidateRepository;
import com.example.ExcelReader.utility.ExcelFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {
    @Autowired
    private CandidateRepository candidateRepository;

    @PostMapping
    public Candidate addCandidate(@RequestBody Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    @GetMapping
    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }




  @PostMapping("/upload")
    public void uploadExcelFile(@RequestParam("file") MultipartFile file) throws IOException {
        List<Candidate> candidates = ExcelFileReader.readCandidatesFromExcel(file.getInputStream());
        candidateRepository.saveAll(candidates);
   }
}
