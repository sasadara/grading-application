package com.sasadara.gradingapplication.ports.primary.usecase.response.assignment;

import com.sasadara.gradingapplication.ports.primary.usecase.response.Response;
import com.sasadara.gradingapplication.ports.primary.usecase.response.question.QuestionForResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentForResponse implements Response {
    private QuestionForResponse questions;
}
