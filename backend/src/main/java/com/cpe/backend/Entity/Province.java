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
@Table(name = "Province")
public class Province {

    @Id 
    @SequenceGenerator(name = "Province_SEQ", sequenceName = "Province_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Province_SEQ")
    @Column(name = "Province_ID", unique = true, nullable = true)
    
   
    private @NonNull Long id;
    private @NonNull String province;

    
}