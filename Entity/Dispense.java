package com.okta.springbootvue.Entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;



import javax.persistence.Column;
import javax.persistence.Entity;


@Data
@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "Dispense")
public class Dispense {

    @Id
    @SequenceGenerator(name = "Dispense_SEQ", sequenceName = "Dispense_SEQ",initialValue = 10001)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Dispense_SEQ")
    @Column(name = "Dispense_ID", unique = true, nullable = true)
    private @NonNull Long id;  
   
    private @NonNull String medicine_name;
    private @NonNull Integer amount; 
  
}