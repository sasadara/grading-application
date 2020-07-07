package com.sasadara.gradingapplication.ports.primary.usecase.request.teacher;

import com.sasadara.gradingapplication.ports.primary.usecase.request.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTeacherDetailsRequest implements Request {
    @NotEmpty
    private long id;

    @NotEmpty
    private List<Long> students;
}
