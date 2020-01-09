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
@Table(name = "MedicineQuantity")
public class MedicineQuantity {

  @Id
  @SequenceGenerator(name = "MEDICINEQUANTITY_SEQ", sequenceName = "MEDICINEQUANTITY_SEQ")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEDICINEQUANTITY_SEQ")
  @Column(name = "MEDICINEQUANTITY_ID", unique = true, nullable = true)

  private @NonNull Long id;
  private @NonNull String medicineQuantity;
  
}