<template>
<v-container>                             
    <div v-if="saveUSC">
        <v-alert outlined dense text type="warning" prominent border="left">
            <strong>ไม่สามารถบันทึกได้</strong> โปรดตรวจสอบข้อมูลอีกครั้ง
        </v-alert>
    </div>

    <div v-if="saveSC">
        <v-alert dense outlined text prominent type="success">บันทึกข้อมูลสำเร็จ
        </v-alert>
    </div>                   

     <div v-if="orderNotFound">
        <v-alert dense outlined type="error">
            <strong>ไม่พบข้อมูลใบสั่งยา</strong>กรุณากรอกข้อมูลอีกครั้ง
        </v-alert>
    </div>

    <div class="text-center">
        <h1 class="display-2" text--left>ระบบจ่ายยา</h1>
    </div><br><br>

    <v-row>
        <v-col md="6">
            <v-card max-width="500">
                <v-row>
                    &ensp;
                    <v-col cols="10" md="4">
                        <v-text-field v-model="DrugDispense.prescriptionId"
                        label="ID ใบสั่งยา" outlined>
                        </v-text-field>
                    </v-col>

                    <v-col cols="2">
                        <div class="my-2">
                            <v-btn absolute height="40px" width="100px" 
                            color="#58D68D" dark 
                            @click="findPrescription">ค้นหา</v-btn>
                        </div>
                    </v-col>
                </v-row>

                <!-- combo box ช่องจ่ายยา -->
                <v-col cols="12" md="6">
                    <v-select label="ช่องจ่ายยา" 
                    v-model="DrugDispense.drugdispensechannelId" 
                    :items="drugdispensechannels" 
                    item-text="drugdispensechannel" 
                    item-value="id">
                    </v-select>
                </v-col>
                <!------------------------->

                <!-- combo box คำนำหน้าชื่อ -->
                <v-col cols="12" md="7">
                    <v-select label="คำนำหน้าชื่อ(เภสัชกร)" 
                    v-model="DrugDispense.titlepharmacistId" 
                    :items="titlepharmacists" 
                    item-text="titlepharmacist" 
                    item-value="id">
                    </v-select>
                </v-col>
                <!------------------------->

                <!-- combobox ชื่อ-สกุล -->
                <v-col cols="12" md="9">
                    <v-select label="ชื่อ-สกุล(เภสัชกร)" 
                    v-model="DrugDispense.pharmacistId" 
                    :items="pharmacists" 
                    item-text="name" 
                    item-value="id">
                    </v-select>
                </v-col>
                <!---------------------->

                <!-- field ชื่อผู้รับยา -->
                <v-col cols="12" md="9">
                    <v-text-field 
                        v-model="DrugDispense.reciever_name" label="ชื่อ-สกุล(ผู้รับยา)"
                    ></v-text-field>
                </v-col>
                <!------------------>

            </v-card>
            <br />

            
            <div class="text-center">                
                <v-bottom-sheet v-model="sheet">
                    
                    <!-- ปุ่ม แสดงรายละเอียด -->
                    <template v-slot:activator="{ on }">
                        <v-btn rounded @click="saveDrugDispenses" dark>บันทึก</v-btn>
                        <v-btn rounded v-on="on" dark>แสดงรายละเอียด</v-btn>
                    </template>
                    <!------------->
                    <v-sheet class="text-center" height="750px">
                        <flash-message transitionIn="animated swing" class="myCustomClass"></flash-message>                        
                        <!-- รายละเอียดใบจ่ายยา -->
                        <div id="printMe">
                            <v-container>
                                <v-row justify="center">
                                <v-col justify="center" cols="6">
                                <v-form v-model="valid" ref="form" >
                                
                                <h1>รายละเอียดใบจ่ายยา</h1>

                                <v-row>
                                    <v-col cols="4">
                                        <p class="text-left"><strong>รหัสผู้ป่วย :</strong> {{patientId}} </p> 
                                    </v-col>
                                </v-row>

                                <v-row>    
                                    <v-col cols="8">
                                        <p class="text-left"><strong>ชื่อ-สกุล(ผู้ป่วย) :</strong> {{titlename}}{{patientName}} </p> 
                                    </v-col>
                                </v-row>                                

                                <v-row>
                                    <v-col cols="4">
                                        <p class="text-left"><strong>รหัสยา :</strong> {{iDdrug}} </p> 
                                    </v-col>
                                    <v-col cols="3">
                                        <p class="text-left"><strong>ชื่อยา :</strong> {{N_drug}} </p> 
                                    </v-col>
                                    <v-col cols="3">
                                        <p class="text-left"><strong>จำนวนยา :</strong> {{drugamount}} {{Unit}} </p> 
                                    </v-col>
                                </v-row> 

                                <v-row>
                                    <v-col cols="5">
                                        <p class="text-left"><strong>ช่องรับยา :</strong> {{DrugDispense.channels}} </p>
                                    </v-col>
                                </v-row>

                                <v-row>
                                    <v-col cols="10">
                                        <p class="text-left"><strong>ชื่อ-สกุล(เภสัชกร) :</strong> {{DrugDispense.n_titlepharmacist}} {{DrugDispense.n_pharmacist}} </p>
                                    </v-col>
                                </v-row>

                                <v-row>
                                    <v-col cols="10">
                                        <p class="text-left"><strong>ชื่อ-สกุล(ผู้รับยา) :</strong> {{DrugDispense.reciever_name}} </p>
                                    </v-col>
                                </v-row>                            
                                <!------------------------------------------>

                                <!-- ปุ่ม พิมพ์ -->
                                <v-btn rounded  @click="printDrugDispense" dark>พิมพ์</v-btn>
                                <!------------->

                                </v-form>
                                </v-col>
                                </v-row>
                            </v-container>
                        </div>

                    </v-sheet>                
                </v-bottom-sheet>
                
            </div>
            
            
        </v-col>

        <!-- แสดงผลการ Search -->
        <v-col>
          <h2>ผลการค้นหา</h2>
            <v-text-field v-model="patientId" label="รหัสผู้ป่วย" disabled>
            </v-text-field>
            <v-text-field v-model="titlename" label="คำนำหน้า" disabled>
            </v-text-field>
            <v-text-field v-model="patientName" label=" ชื่อ-สกุล" disabled>
            </v-text-field>
            <v-text-field v-model="iDdrug" label="รหัสยา" disabled>
            </v-text-field>
            <v-text-field v-model="N_drug" label="ชื่อยา" disabled>
            </v-text-field>
            <v-text-field v-model="drugamount" label="จำนวนยา" disabled>
            </v-text-field>
            <v-text-field v-model="Unit" label="หน่วยของจำนวนยา" disabled>
            </v-text-field>
            
        </v-col>
        <!----------->
     
        
    </v-row>
</v-container>
</template>

<script>
import http from "../http-common";
import Vue from 'vue';
import VueHtmlToPaper from 'vue-html-to-paper';
import VueFlashMessage from 'vue-flash-message';

Vue.use(VueFlashMessage);

 
Vue.use(VueHtmlToPaper);

export default {
    name: "DrugDispense",
    data() {
        return {
            DrugDispense: {
                drugdispensechannelId: "",
                titlepharmacistId: "",
                pharmacistId: "",
                prescriptionId: "",
                reciever_name:"",
                channels:"",
                n_titlepharmacist:"",
                n_pharmacist:"",
                
            },
            items: [],
            drugdispensechannels: [],
            titlepharmacists: [],
            pharmacists: [],
            saveSC: false,
            saveUSC: false,
            orderNotFound: false,
            orderCheck: false,
            sheet: false,
            valid: false,

            patientId: "",
            titlename: "",
            Unit: "",
            patientName: "",
            iDdrug: "",
            N_drug: "",
            drugamount: "",
        };
    },
    methods: {
        /* eslint-disable no-console */
        print() {
            this.$htmlToPaper('printMe');
        },

        // ดึงข้อมูล DrugDispenseChannel ใส่ combobox
        getDrugDispenseChannels() {
            http
                .get("/drugdispensechannel")
                .then(response => {
                    this.drugdispensechannels = response.data;
                    console.log(response.data);
                })
                .catch(e => {
                    console.log(e);
                });
        },
        getPharmacists() {
            http
                .get("/pharmacist")
                .then(response => {
                    this.pharmacists = response.data;
                    console.log(response.data);
                })
                .catch(e => {
                    console.log(e);
                });
        },
        getTitlePharmacists() {
            http
                .get("/titlepharmacist")
                .then(response => {
                    this.titlepharmacists = response.data;
                    console.log(response.data);
                })
                .catch(e => {
                    console.log(e);
                });
        },
        clear() {
            this.saveSC = false;
            this.saveUSC = false;
        },
        findPrescription() {
            http
                .get("/prescription/" + this.DrugDispense.prescriptionId)
                .then(response => {
                    console.log(response);
                    if (response.data != null) {
                        this.patientId = response.data.patient_ID;
                        this.titlename = response.data.titleName.titlename;
                        this.patientName = response.data.name;
                        this.iDdrug = response.data.drug.id;
                        this.N_drug = response.data.drug.drugname;
                        this.drugamount = response.data.amount;
                        this.patientPrescription_id = response.data.Prescription_id;
                        this.Unit = response.data.unit_of_medicine.unit;
                        //console.log(this.patientName);
                        this.orderCheck = response.status;
                        this.orderNotFound = false;
                    } else {
                        this.clear()
                        this.orderNotFound = true;
                    }
                })
                .catch(e => {
                    console.log(e);
                });
            this.submitted = true;
        },

        findDrugDispenseChannel(){
            http
                .get("/drugdispensechannel/" + this.DrugDispense.drugdispensechannelId)
                .then(response => {
                    console.log(response);
                    if (response.data != null) {
                        this.DrugDispense.channels = response.data.drugdispensechannel;
                    }
                })
                .catch(e => {
                    console.log(e);
                });
        },

        findTitlePharmacist(){
            http
                .get("/titlepharmacist/" + this.DrugDispense.titlepharmacistId)
                .then(response => {
                    console.log(response);
                    if (response.data != null) {
                        this.DrugDispense.n_titlepharmacist = response.data.titlepharmacist;
                    }
                })
                .catch(e => {
                    console.log(e);
                });
        },

        findPharmacistName(){
            http
                .get("/pharmacist/" + this.DrugDispense.pharmacistId)
                .then(response => {
                    console.log(response);
                    if (response.data != null) {
                        this.DrugDispense.n_pharmacist = response.data.name;
                    }
                })
                .catch(e => {
                    console.log(e);
                });
        },

        // function เมื่อกดปุ่ม save
        saveDrugDispenses() {
            http
                .post(
                    "/drugdispense/" +
                    this.DrugDispense.drugdispensechannelId +
                    "/" + this.DrugDispense.titlepharmacistId +
                    "/" + this.DrugDispense.pharmacistId +
                    "/" + this.DrugDispense.prescriptionId +
                    "/" + this.DrugDispense.reciever_name
                )
                .then(response => {
                    console.log(response);
                    this.saveSC = true;
                    this.saveUSC = false;
                    this.findDrugDispenseChannel();
                    this.findTitlePharmacist();
                    this.findPharmacistName();
                })
                .catch(e => {
                    console.log(e);
                    this.saveSC = false;
                    this.saveUSC = true;
                });
            this.submitted = true;
        },

        printDrugDispense(){
            if(this.saveSC==true){
                this.flash('พิมพ์สำเร็จ', 'success');
                this.print();                                                                   
            }else
                this.flash('พิมพ์ไม่สำเร็จ โปรดป้อนข้อมูลให้ครบถ้วนและบันทึก', 'error');                   
                   
        },

        /* eslint-enable no-console */
    },

    mounted() {
        this.getDrugDispenseChannels();
        this.getPharmacists();
        this.getTitlePharmacists();
    }   
};
</script>
