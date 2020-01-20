<template>
<v-container>

     <div v-if="orderNotFound">
        <v-alert dense outlined type="error">
            <strong>ไม่พบข้อมูลใบสั่งยา</strong>กรุณากรอกข้อมูลอีกครั้ง
        </v-alert>
    </div>

    <div v-if="saveUSC">
        <v-alert outlined dense text type="warning" prominent border="left">
            <strong>ไม่สามารถบันทึกได้</strong> โปรดตรวจสอบข้อมูลอีกครั้ง
        </v-alert>
    </div>

    <div v-if="saveSC">
        <v-alert dense outlined text prominent type="success">บันทึกข้อมูลสำเร็จ
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
                        v-model="DrugDispense.reciever_name" label="ชื่อผู้รับยา"
                    ></v-text-field>
                </v-col>
                <!------------------>

            </v-card>
            <br />

            <!-- ปุ่ม SAVE -->
            <div class="text-center">
                <v-btn rounded @click="saveDrugDispenses" dark>SAVE</v-btn>
            </div>
            <!------------->
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
            },
            items: [],
            drugdispensechannels: [],
            titlepharmacists: [],
            pharmacists: [],
            saveSC: false,
            saveUSC: false,
            orderNotFound: false,
            orderCheck: false,

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
                })
                .catch(e => {
                    console.log(e);
                    this.saveSC = false;
                    this.saveUSC = true;
                });
            this.submitted = true;
        }

        /* eslint-enable no-console */
    },

    mounted() {
        this.getDrugDispenseChannels();
        this.getPharmacists();
        this.getTitlePharmacists();
    }
};
</script>
