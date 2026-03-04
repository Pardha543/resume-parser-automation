package com.example.backend.service;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.example.backend.dto.ParsedResume;



@Service
public class ResumeParserService {
		public ParsedResume parse(String text){

        ParsedResume resume = new ParsedResume();

        resume.setEmail(extractEmail(text));
        resume.setPhone(extractPhone(text));
        resume.setSkills(extractSkills(text));
        resume.setName(extractName(text));
        resume.setEducation(extractEducation(text));
        resume.setExperience(extractExperience(text));

        return resume;
    }

    private String extractEmail(String text){
        Pattern pattern = Pattern.compile("[A-Za-z0-9+_.-]+@(.+)");
        Matcher matcher = pattern.matcher(text);
        return matcher.find() ? matcher.group() : "";
    }

    private String extractPhone(String text){
        Pattern pattern = Pattern.compile("\\+?\\d[\\d -]{8,}\\d");
        Matcher matcher = pattern.matcher(text);
        return matcher.find() ? matcher.group() : "";
    }

    private String extractName(String text){
        String[] lines = text.split("\n");
        return lines.length > 0 ? lines[0] : "";
    }

    private List<String> extractSkills(String text){

        List<String> knownSkills = Arrays.asList(
                "Java","Spring Boot","React","Python","AWS",
                "Docker","Kubernetes","Microservices","Flask",
                "Django","Hibernate","Spark","Hadoop"
        );

        return knownSkills.stream()
                .filter(skill -> text.toLowerCase().contains(skill.toLowerCase()))
                .toList();
    }

    private String extractEducation(String text){

        if(text.contains("University of East London"))
            return "MSc Computer Science - University of East London";

        return "";
    }

    private String extractExperience(String text){

        if(text.contains("House of Companies"))
            return "Software Developer Intern - House of Companies";

        return "";
    }
}
