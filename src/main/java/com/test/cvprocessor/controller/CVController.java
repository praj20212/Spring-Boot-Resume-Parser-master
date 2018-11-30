package com.test.cvprocessor.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.test.cvprocessor.constants.Constants;
import com.test.cvprocessor.model.Employee;
import com.test.cvprocessor.service.WordToPDFService;

/**
 * CV application controller
 *
 */
@Controller
public class CVController {

    @Autowired
    private WordToPDFService wordToPDFService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Employee employee) {
        return Constants.INDEXPAGE;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String processEmployeeData(@Valid Employee employee, @RequestParam("cvFile") MultipartFile cvFile,
            Model model) {
        try {
            String pdfFilePath = System.getProperty(Constants.TEMP_DIR) + File.separator + Constants.CVFILENAME;
            OutputStream out = wordToPDFService.convert(cvFile.getInputStream(), pdfFilePath);
            if (out == null) {
                throw new IllegalArgumentException("Could not convert word to pdf");
            }
            model.addAttribute("firstName", employee.getFirstName());
            model.addAttribute("lastName", employee.getLastName());
            model.addAttribute("country", employee.getCountry());
            model.addAttribute("date", employee.getDate());
            model.addAttribute("cv", Constants.CVFILENAME);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Constants.RESULTPAGE;
    }

}