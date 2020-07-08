package com.sasadara.gradingapplication.ports.primary.usecase.request.student;

import com.sasadara.gradingapplication.ports.primary.usecase.request.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStudentDetailsRequest implements Request {

    @NotNull
    private Long id;

    private StudentDetailsRequest studentDetailsRequest;
}
