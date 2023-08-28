package com.example.controller;

import com.example.dto.EmployeeDTO;
import com.example.service.EmployeeService;
import com.example.service.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees/generate-report")
    public ResponseEntity<Resource> generateReportAndDownload() throws JRException, IOException {

        List<EmployeeDTO> employeeDTOS = employeeService.findAllEmployees();
        String outputPath = reportService.generateReport(employeeDTOS);

        // Set the headers for downloading the file
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "employee-report.pdf");

        // Read the PDF file and return it as a ResponseEntity
        Path pdfPath = Paths.get(outputPath);
        byte[] pdfBytes = Files.readAllBytes(pdfPath);
        Resource resource = new ByteArrayResource(pdfBytes);

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(pdfBytes.length)
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }
}
