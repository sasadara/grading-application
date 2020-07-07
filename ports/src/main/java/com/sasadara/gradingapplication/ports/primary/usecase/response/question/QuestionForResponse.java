package com.sasadara.gradingapplication.ports.primary.usecase.response.question;

import com.sasadara.gradingapplication.entities.question.Results;
import com.sasadara.gradingapplication.ports.primary.usecase.response.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionForResponse implements Response {
    private long id;
    private String name;
    private Results result;
    private int timeSpentMints;
    private int numberOfAttempts;
    private String review;
}
