package com.sasadara.gradingapplication.ports.primary.usecase.response.student;

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
public class GetAllStudentsDetailsResponse implements Response {
    private List<GetStudentDetailsResponse> students;
}
