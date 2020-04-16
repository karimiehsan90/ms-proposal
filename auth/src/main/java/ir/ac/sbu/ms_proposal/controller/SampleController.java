package ir.ac.sbu.ms_proposal.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sample")
public class SampleController {
    @RequestMapping("/get")
    public String getSampleString() {
        return "sample";
    }
}