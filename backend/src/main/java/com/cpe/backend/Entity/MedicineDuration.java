package com.cpe.backend.entity;

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
@Table(name = "MedicineDuration")
public class MedicineDuration{

  @Id
  @SequenceGenerator(name = "MEDICINEDURATION_SEQ", sequenceName = "MEDICINEDURATION_SEQ")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEDICINEDURATION_SEQ")
  @Column(name = "MEDICINEDURATION_ID", unique = true, nullable = true)

  private @NonNull Long id;
  private @NonNull String duration;
  
}