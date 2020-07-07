package com.sasadara.gradingapplication.dbadapterjpa.teacher;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "teacher")
@DynamicUpdate
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JPATeacher implements Serializable {

    private static final long serialVersionUID = 4663944136223439772L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "jpaTeacher")
    @JsonBackReference
    private List<JPAStudent> jpaStudents;
}
