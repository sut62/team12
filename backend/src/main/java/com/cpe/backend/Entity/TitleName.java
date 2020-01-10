package com.cpe.backend.Entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@Table(name = "TitleName")
public class TitleName {

    @Id
    @SequenceGenerator(name = "TitleName_SEQ", sequenceName = "TitleName_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TitleName_SEQ")
    @Column(name = "TitleName_ID", unique = true, nullable = true)
    private @NonNull Long id;

    private @NonNull String titlename;

}