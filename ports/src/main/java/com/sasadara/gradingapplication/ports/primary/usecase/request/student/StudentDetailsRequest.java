package com.sasadara.gradingapplication.ports.primary.usecase.request.student;

import com.sasadara.gradingapplication.ports.primary.usecase.request.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDetailsRequest implements Request {
    private String name;

    private Long teacherId;
}
