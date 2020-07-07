package com.sasadara.gradingapplication.ports.primary.usecase.request.student;

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
public class AddStudentDetailsRequest implements Request {

    @NotEmpty
    private String name;

    private List<Long> assignments;
}
