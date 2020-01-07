package com.cpe.backend.entity;

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
import javax.persistence.FetchType;



@Data
@Entity //เป็นการประกาศว่า java class นี้เป็น entity class เพื่อให้ framework มาอ่านข้อมูลของ class นี้ไปสร้างเป็น table
@Getter // การส่งข้อมูล
@Setter   //การรับข่อมูล
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "MedicineLabel") //creat table 
public class MedicineLabel{


  @Id //ระบุว่า field นี้เป็น primary key
  //@TableGenerator เป็นการระบุว่าให้ database ทำการสร้าง id หรือ primary key ให้เองโดยอัตโนมัติ โดย ให้ squence id นั้นเก็บไว้ที่ table MedicineLabel_SEQ  ที่มีค่าของ column NAME เท่ากับ Schedule 
  @SequenceGenerator(name = "MEDICINELABEL_SEQ", sequenceName = "MEDICINELABEL_SEQ")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEDICINELABEL_SEQ")
  @Column(name = "MEDICINELABEL_ID", unique = true, nullable = true)  //กำหนดให้ column นี้มีชื่อว่า MedicineLabel_ID ถ้าไม่กำหนดมันจะเป็น ID ให้เองโดยอัตโนมัติ

  private @NonNull Long id;
  


  @ManyToOne(fetch = FetchType.EAGER, targetEntity = MedicineDuration.class)
  @JoinColumn(name = "MEDICINEDURATION_ID", insertable = true)
  private MedicineDuration medicineDuration;
  
  @ManyToOne(fetch = FetchType.EAGER, targetEntity = MedicineFrequency.class)
  @JoinColumn(name = "MEDICINEFREQUENCY_ID", insertable = true)
  private MedicineFrequency medicineFrequency ;
  
  @ManyToOne(fetch = FetchType.EAGER, targetEntity = MedicineQuantity.class) //เป็นความสัมพันธ์แบบ many to one ระหว่งคลาสนี้กับคลาส Room -Entity
  @JoinColumn(name = "MEDICINEQUANTITY_ID", insertable = true) //เป็นการ  join ตารางโดยมีตารางชื่อว่า Room_ID
  private MedicineQuantity medicineQuantity ; // private ชื่อ calss กับชื่อเส้น ที่จะส่งไป

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Prescription.class)
  @JoinColumn(name = "Prescription_ID", insertable = true)
  private Prescription prescription ;

}