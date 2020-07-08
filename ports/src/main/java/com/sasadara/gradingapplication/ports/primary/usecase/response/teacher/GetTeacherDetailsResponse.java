package com.sasadara.gradingapplication.ports.primary.usecase.response.teacher;


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
public class GetTeacherDetailsResponse implements Response {
    private Long id;
    private String name;
    private List<StudentForResponse> students;
}
