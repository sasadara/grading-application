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
public class UpdateAssignmentRequest implements Request {

    @NotEmpty
    private long id;

    private AssignmentRequest assignmentRequest;
}
