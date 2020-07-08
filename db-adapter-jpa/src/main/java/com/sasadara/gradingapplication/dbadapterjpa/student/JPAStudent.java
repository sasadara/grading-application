package com.sasadara.gradingapplication.dbadapterjpa.student;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sasadara.gradingapplication.dbadapterjpa.assignment.JPAAssignment;
import com.sasadara.gradingapplication.dbadapterjpa.teacher.JPATeacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "student")
@DynamicUpdate
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JPAStudent implements Serializable {

    private static final long serialVersionUID = 4663944136223439770L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "jpaStudent")
    @JsonBackReference
    private List<JPAAssignment> jpaAssignments;

    @ManyToOne
    private JPATeacher jpaTeacher;
}
