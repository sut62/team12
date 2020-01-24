package com.cpe.backend.Entity;
import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;


@Data
@Entity
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "Drug")
public class Drug {

    @Id
    @SequenceGenerator(name="drug_seq",sequenceName="drug_seq", initialValue=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="drug_seq")
    @Column(name = "DRUG_ID", unique = true, nullable = true)
    private @NonNull Long id;

    @Size(max=30,min=3)
    private @NonNull String drugname;

    @Size(max=100,min=2)
    private @NonNull String how;

    @Size(max=200,min=2)
    private @NonNull String sef;
    
    @NotNull
    @Max(999999)
    @Min(1)
    private @NonNull Integer price;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Unit_of_medicine.class)
    @JoinColumn(name = "Unit_of_medicine_ID", insertable = true)
    private Unit_of_medicine unit;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = DrugCategory.class)
    @JoinColumn(name = "DrugCategory_ID", insertable = true)
    private DrugCategory category;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Pharmacist.class)
    @JoinColumn(name = "Pharmacist_ID", insertable = true)
    private Pharmacist pharmacist;


    public void setID(Long id){
        this.id = id;
    }

    public Long getID(){
        return id;
    }


    public void setDrugname(String drugname){
        this.drugname = drugname;
    }

    public String getDrugname(){
        return drugname;
    }


    public void setHow(String how){
        this.how = how;
    }

    public String getHow(){
        return how;
    }


    public void setSideEffect(String sef){
        this.sef = sef;
    }

    public String getSideEffect(){
        return sef;
    }


    public void setPrice(Integer price){
        this.price = price;
    }

    public Integer getPrice(){
        return price;
    }


    public void setCategory(DrugCategory category){
        this.category = category;
    }

    public DrugCategory getCategory(){
        return category;
    }


    public void setUnit_of_medicine(Unit_of_medicine unit){
        this.unit = unit;
    }

    public Unit_of_medicine getUnit_of_medicine(){
        return unit;
    }


    public void setPharmacist(Pharmacist pharmacist){
        this.pharmacist = pharmacist;
    }

    public Pharmacist getPharmacist(){
        return pharmacist;
    }
}



