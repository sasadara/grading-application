package com.sasadara.grandingapplication.dbadapterjpa.question;

import com.sasadara.gradingapplication.entities.question.Results;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "question")
@DynamicUpdate
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JPAQuestion implements Serializable {

    private static final long serialVersionUID = -9186052289159717453L;

    @Id
    private long id;

    @Column
    private String name;

    @Column
    private Results result;

    @Column
    private int timeSpentMints;

    @Column
    private int numberOfAttempts;

    @Column
    private String review;
}
