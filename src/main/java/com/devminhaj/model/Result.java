package com.devminhaj.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "result_sequence")
    @SequenceGenerator(
            name = "result_sequence",
            sequenceName = "result_sequence",
            allocationSize = 1
    )
    private Integer id;
    private String username;
    private int totalCorrect=0;
}
