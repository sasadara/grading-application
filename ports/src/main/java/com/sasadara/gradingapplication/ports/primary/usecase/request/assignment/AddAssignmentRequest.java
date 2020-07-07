package com.sasadara.gradingapplication.ports.primary.usecase.request.assignment;


import com.sasadara.gradingapplication.ports.primary.usecase.request.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddAssignmentRequest implements Request {

    @NotEmpty
    private String name;

    private Long student;
}
