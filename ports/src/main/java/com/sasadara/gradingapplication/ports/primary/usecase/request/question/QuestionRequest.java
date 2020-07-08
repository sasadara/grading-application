package com.sasadara.gradingapplication.ports.primary.usecase.request.question;


import com.sasadara.gradingapplication.ports.primary.usecase.request.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionRequest implements Request {

    private Integer numberOfAttempts;

    private String name;

    private Integer timeSpentMints;

    private Long assignmentId;
}
