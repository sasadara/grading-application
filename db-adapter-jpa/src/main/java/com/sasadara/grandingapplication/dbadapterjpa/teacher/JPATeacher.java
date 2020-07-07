package com.sasadara.grandingapplication.dbadapterjpa.teacher;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sasadara.grandingapplication.dbadapterjpa.student.JPAStudent;
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

    private static final long serialVersionUID = -9186052289159717453L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column
    private String name;

    @Column
    private String assetType;

    @Column
    private String assetGroup;

    @OneToMany(mappedBy = "binLocation")
    @JsonBackReference
    private List<JPAStudent> students;
}
