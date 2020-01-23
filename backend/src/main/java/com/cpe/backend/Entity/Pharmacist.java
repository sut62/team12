
package com.cpe.backend.Entity;

import lombok.*; //ช่วยให้เขียนโค๊ดได้ง่ายขึ้น

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import java.sql.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;


@Entity   //เป็นการประกาศว่า java class นี้เป็น entity class เพื่อให้ framework มาอ่านข้อมูลของ class นี้ไปสร้างเป็น table
@Data      //@Data annotation เป็นการรวม annotation @Setter, @Getter, @RequiredArgsConstructor, @ToString, @EqualsAndHashCode ดังนั้นจะสร้าง boilerplate code ให้เราแทบทั้งหมด (ครบจบใน annotation เดียว)
@Getter    //รับข้อมูล
@Setter    //ส่งข้อมูล
@NoArgsConstructor
@ToString   
@EqualsAndHashCode
@Table(name = "Pharmacist")  //creat table 
public class Pharmacist {

    @Id  //ระบุว่า field นี้เป็น primary key
    @SequenceGenerator(name = "Pharmacist_SEQ", sequenceName = "Pharmacist_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Pharmacist_SEQ")
    @Column(name = "Pharmacist_ID", unique = true, nullable = true)   //กำหนดให้ column นี้มีชื่อว่า Pharmacist_ID ถ้าไม่กำหนดมันจะเป็น ID ให้เองโดยอัตโนมัติ
    
    //กำหนดตัวแปร
    private @NonNull Long id;
    
    @NotNull
    @Pattern(regexp = "\\d{13}")
    @Column(name = "ID_CARD")
    private @NonNull Long id_card;

    @NotNull
    @Size(max=50,min=5)
    @Column(name = "NAME")
    private @NonNull String name;
    
    @Pattern(regexp = "\\d{10}")
    @NotNull
    @Column(name = "BIRTHDAY")
    private @NonNull Date birthday;

    @NotNull
    @Min(1)
    @Max(125)
    @Column(name = "AGE")
    private @NonNull Integer age;

    @NotNull
    @Size(max=100,min=5)
    @Column(name = "ADDRESS")
    private @NonNull String address;

    @NotNull
    @Pattern(regexp = "\\d{10}")
    @Column(name = "PHONE")
    private @NonNull String phone;

    @Size(max=50,min=5)
    @NotNull
    @Column(name = "MAIL")
    private @NonNull String mail;


    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Province.class)  //เป็นความสัมพันธ์แบบ many to one ระหว่งคลาสนี้กับคลาส Patient_type -Entity
    @JoinColumn(name = "Province_ID",insertable = true) //เป็นการ  join ตารางโดยมีตารางชื่อว่า Patient_type_ID
    private Province Province;  // private ชื่อ calss กับชื่อเส้น ที่จะส่ง

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Sex.class)
    @JoinColumn(name = "Sex_ID",insertable = true)
    private Sex sex ;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = TitlePharmacist.class)
    @JoinColumn(name = "TitlePharmacist_ID",insertable = true)
    private TitlePharmacist TitlePharmacist ;

    

}