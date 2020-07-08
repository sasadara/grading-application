package com.sasadara.gradingapplication.ports.primary.usecase.response.teacher;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentForResponse {
    private String name;
    private double avgTime;
    private double correctAnswers;
    private double incorrectAnswers;
}
