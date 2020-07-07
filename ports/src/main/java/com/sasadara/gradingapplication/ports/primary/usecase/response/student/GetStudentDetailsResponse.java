package com.sasadara.gradingapplication.ports.primary.usecase.response.student;

import com.sasadara.gradingapplication.ports.primary.usecase.response.Response;
import com.sasadara.gradingapplication.ports.primary.usecase.response.assignment.AssignmentForResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetStudentDetailsResponse implements Response {
    private List<AssignmentForResponse> assignments;
}
