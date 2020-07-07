package com.sasadara.gradingapplication.dbadapterjpa.assignment;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sasadara.gradingapplication.dbadapterjpa.question.JPAQuestion;
import com.sasadara.gradingapplication.dbadapterjpa.student.JPAStudent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "assignment")
@DynamicUpdate
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JPAAssignment implements Serializable {

//    private static final long serialVersionUID = -9186052289159717453L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "jpaAssignment")
    @JsonBackReference
    private List<JPAQuestion> jpaQuestions;

    @ManyToOne
    @JoinColumn(name = "jpaAssignments")
    protected JPAStudent jpaStudent;
}
