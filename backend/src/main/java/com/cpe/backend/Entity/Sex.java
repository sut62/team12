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
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "Sex")
public class Sex {

    @Id 
    @SequenceGenerator(name = "Sex_SEQ", sequenceName = "Sex_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Sex_SEQ")
    @Column(name = "Sex_ID", unique = true, nullable = true)
    
   
    private @NonNull Long id;
    private @NonNull String sex;

   
}