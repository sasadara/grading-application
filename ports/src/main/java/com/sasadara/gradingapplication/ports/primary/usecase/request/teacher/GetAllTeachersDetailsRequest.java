package com.sasadara.gradingapplication.ports.primary.usecase.request.teacher;

import com.sasadara.gradingapplication.ports.primary.usecase.request.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetAllTeachersDetailsRequest implements Request {
    private Long teacherId;
}
