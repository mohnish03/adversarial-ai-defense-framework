package com.defense.controller;

import org.springframework.web.bind.annotation.*;
import com.google.gson.*;
import java.io.*;
import java.util.*;

@RestController
@RequestMapping("/api/defense")
public class DefenseController {

    private static final List<String> SUSPICIOUS_PATTERNS = Arrays.asList(
        "ignore instructions",
        "ignore previous instructions",
        "disregard the above",
        "you are now",
        "system prompt",
        "reveal your prompt",
        "bypass",
        "jailbreak",
        "act as",
        "pretend you are"
    );

    @PostMapping("/analyze")
    public DefenseResponse analyze(@RequestBody AnalysisRequest request) {
        try {
            String input = request.getInput();
            if (input == null) {
                return new DefenseResponse(false, 1.0, "No input provided");
            }

            String lowerInput = input.toLowerCase();
            int matchCount = 0;
            for (String pattern : SUSPICIOUS_PATTERNS) {
                if (lowerInput.contains(pattern)) {
                    matchCount++;
                }
            }

            boolean isSafe = matchCount == 0;
            double threatLevel = Math.min(1.0, matchCount * 0.3);
            String message = isSafe ? "OK" : "Potential prompt injection detected";

            return new DefenseResponse(isSafe, threatLevel, message);
        } catch (Exception e) {
            return new DefenseResponse(false, 0.0, "Error");
        }
    }
}

class AnalysisRequest {
    private String input;
    public String getInput() { return input; }
}

class DefenseResponse {
    private boolean isSafe;
    private double threatLevel;
    private String message;

    public DefenseResponse(boolean isSafe, double threatLevel, String message) {
        this.isSafe = isSafe;
        this.threatLevel = threatLevel;
        this.message = message;
    }

    public boolean isIsSafe() { return isSafe; }
    public double getThreatLevel() { return threatLevel; }
    public String getMessage() { return message; }
}