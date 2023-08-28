package com.example.service;

import com.example.dto.EmployeeDTO;
import com.example.entity.Employee;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    public String generateReport(List<EmployeeDTO> employeeDTOS) throws JRException, FileNotFoundException {

        File templateFile = ResourceUtils.getFile("classpath:employees-report.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(templateFile.getAbsolutePath());

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employeeDTOS);

        Map<String, Object> parameters = new HashMap<>();

        JasperPrint jasperPrint =
                JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // Define the file path where the PDF report will be saved
        String outputPath = "C:/Users/egomaa/Desktop/employee-report.pdf";

        JasperExportManager.exportReportToPdfFile(jasperPrint, outputPath);

        return outputPath;
    }


}
