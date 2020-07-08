package com.sasadara.gradingapplication.ports.primary.usecase.request.question;


import com.sasadara.gradingapplication.ports.primary.usecase.request.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateQuestionReviewRequest implements Request {

    @NotNull
    private Long id;

    @NotEmpty
    private QuestionReviewRequest reviewRequest;
}
