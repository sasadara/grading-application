package com.sasadara.gradingapplication.ports.primary.usecase.response.assignment;

import com.sasadara.gradingapplication.ports.primary.usecase.response.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllAssignmentsResponse implements Response {
    List<AssignmentForResponse> assignments;
}
