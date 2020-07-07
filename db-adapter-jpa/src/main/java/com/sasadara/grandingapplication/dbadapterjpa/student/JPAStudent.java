package com.sasadara.grandingapplication.dbadapterjpa.student;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sasadara.grandingapplication.dbadapterjpa.assignment.JPAAssignment;
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

    private static final long serialVersionUID = -9186052289159717453L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "assignment")
    @JsonBackReference
    private List<JPAAssignment> assignments;
}
