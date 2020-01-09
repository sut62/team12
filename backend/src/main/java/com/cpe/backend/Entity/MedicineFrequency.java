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
@Table(name = "MedicineFrequency")
public class MedicineFrequency{

  @Id
  @SequenceGenerator(name = "MEDICINEFREQUENCY_SEQ", sequenceName = "MEDICINEFREQUENCY_SEQ")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEDICINEFREQUENCY_SEQ")
  @Column(name = "MEDICINEFREQUENCY_ID", unique = true, nullable = true)

  private @NonNull Long id;
  private @NonNull String medicineFrequency;
  
}