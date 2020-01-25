<template>
<v-container>

    <v-layout text-center wrap>
        <v-flex mb-4>
            <h1 class="display-2 font-weight-bold mb-3">บันทึกข้อมูลการสั่งยา</h1>
        </v-flex>
    </v-layout>

    <v-row>
        <v-col cols="4">
            <v-form v-model="valid" ref="form">
                <v-row>
                    <v-row justify="center">
                        <h3>ข้อมูลผู้ป่วย</h3>
                    </v-row>
                </v-row>
                <v-col cols="12" md="12">
                    <v-text-field label="ID ผู้ป่วย" v-model="prescription.patient_id"></v-text-field>
                </v-col>
                <v-row>
                    <v-col cols="12" md="4">
                        <v-select label="คำนำหน้าชื่อ" v-model="prescription.titlenameId" :items="titlename" item-text="titlename" item-value="id" :rules="[(v) => !!v || 'กรุณาเลือกข้อมูล']" required></v-select>
                    </v-col>
                    <v-col cols="8">
                        <v-text-field label="ชื่อ-สกุล" v-model="prescription.name"></v-text-field>
                    </v-col>
                </v-row>
                <v-row>
                    <v-col cols="12" md="4">
                        <v-text-field label="อายุ" suffix="ปี" v-model="prescription.age"></v-text-field>
                    </v-col>
                    <v-col cols="12" md="8">
                        <v-text-field label="แพ้ยา" v-model="prescription.drugallergy"></v-text-field>
                    </v-col>
                </v-row>
                <v-col>
                    <v-select label="แพทย์ผู้สั่งยา" v-model="prescription.doctornameId" :items="doctorname" item-text="doctor_name" item-value="id" :rules="[(v) => !!v || 'กรุณาเลือกข้อมูล']" required></v-select>
                </v-col>
            </v-form>
        </v-col>
        <v-col>
            <v-row justify="center">
                <h3>รายการยาที่แพทย์สั่ง</h3>
            </v-row>
            <v-row justify="center">
                <!-- Drug ID text field -->
                <v-col cols="12" md="6">
                    <v-text-field block label="ชื่อยา" v-model="prescription.drugname_s" :rules="[(v) => !!v || 'กรุณากรอกข้อมูล']" required></v-text-field>
                </v-col>
            </v-row>
            <v-row justify="center">
                <div class="my-2">
                    <v-btn color="blue-grey darken-1" dark @click="findDrug">ค้นหาข้อมูลยา</v-btn>
                </div>
            </v-row>
            <v-container>
                <v-row justify="center">
                    <div v-if="drugNotFound">
                        <v-alert dense outlined type="error">
                            <strong>ไม่พบข้อมูล</strong> กรุณากรอกข้อมูลอีกครั้ง
                        </v-alert>
                    </div>
                </v-row>
            </v-container>
            <v-row justify="center">
                <v-col cols="8" md="2">
                    <v-text-field label="ชื่อยา" v-model="prescription.drugname" disabled></v-text-field>
                </v-col>
                <v-col cols="8" md="2">
                    <v-text-field label="ราคายา" v-model="prescription.price" disabled></v-text-field>
                </v-col>
                <v-col cols="8" md="2">
                    <v-text-field label="จำนวนยา" v-model="prescription.amount"></v-text-field>
                </v-col>
                <v-col cols="8" md="2">
                    <v-select label="หน่วยของยา" v-model="prescription.unit_of_medicineId" :items="unit" item-text="unit" item-value="id" :rules="[(v) => !!v || 'กรุณาเลือกข้อมูล']" required></v-select>
                </v-col>

            </v-row>
            <v-row justify="center">
                <v-btn @click="savePrescription" class="mt-2" tile outlined color="green">
                    <v-icon left>save</v-icon> บันทึกข้อมูลการสั่งยา
                </v-btn>
            </v-row>
            <v-container>
                <div v-if="saveUSC">
                    <v-alert outlined dense text type="warning" prominent border="left"> <strong>ไม่สามารถบันทึกได้</strong> โปรดตรวจสอบข้อมูลอีกครั้ง </v-alert>
                </div>

                <div v-if="saveSC">
                    <v-alert @change="saveUSC = false" dense outlined text prominent type="success">
                        <strong>บันทึกข้อมูลเรียบร้อย</strong>
                    </v-alert>
                    <div id="printMe">
                    <v-col>
                <v-row justify="center">
                    <v-bottom-sheet v-model="sheet">
                        <template v-slot:activator="{ on }">
                            <v-btn color="green" dark v-on="on">
                                รายละเอียดใบสั่งยา
                            </v-btn>
                        </template>
                        <v-sheet class="text-center" height="750px">
                            <div id="printMe">
                                <v-container>
                                    <v-row justify="center">
                                        <v-col justify="center" cols="6">
                                            <v-form v-model="valid" ref="form">
                                                <h1>รายละเอียดใบสั่งยา</h1>
                                                <br />
                                                <v-row>
                                                    <v-col>
                                                        <p class="text-left">
                                                            <strong>รหัสผู้ป่วย :</strong>
                                                            {{prescription.patient_id}}
                                                        </p>
                                                    </v-col>
                                                    <v-col>
                                                        <p class="text-left">
                                                            <strong>ชื่อผู้ป่วย :</strong>
                                                            {{prescription.titlename_s}} {{prescription.name}}
                                                        </p>
                                                    </v-col>
                                                </v-row>
                                                <v-row>
                                                    <v-col>
                                                        <p class="text-left">
                                                            <strong>อายุ :</strong>
                                                            {{prescription.age}} ปี
                                                        </p>
                                                    </v-col>
                                                    <v-col>
                                                        <p class="text-left">
                                                            <strong>แพ้ยา :</strong>
                                                            {{prescription.drugallergy}}

                                                        </p>
                                                    </v-col>
                                                    <v-row>
                                                        <v-col>
                                                            <p class="text-left">
                                                                <strong>แพทย์ผู้สั่งยา :</strong>
                                                                {{prescription.doctorname_s}}
                                                            </p>
                                                        </v-col>
                                                    </v-row>
                                                </v-row>
                                                <v-col>
                                                    <v-row>
                                                        <v-col cols="12" md="12">
                                                            <v-data-table :headers="headers" :items="items" :items-per-page="10" class="elevation-1"></v-data-table>
                                                        </v-col>
                                                    </v-row>

                                                </v-col>
                                                <v-btn rounded  @click="printDrugDispense" dark>พิมพ์</v-btn>
                                            </v-form>
                                        </v-col>
                                    </v-row>
                                </v-container>
                            </div>
                        </v-sheet>
                    </v-bottom-sheet>
                </v-row>
            </v-col>
                    </div>
                </div>
            </v-container>
            <v-col>
                <v-row>
                    <v-col cols="12" md="12">
                        <v-data-table :headers="headers" :items="items" :items-per-page="10" class="elevation-1"></v-data-table>
                    </v-col>
                </v-row>

            </v-col>
        </v-col>
    </v-row>
</v-container>
</template>

<script>
import http from "../http-common";
import Vue from 'vue';
import VueHtmlToPaper from 'vue-html-to-paper'
 
Vue.use(VueHtmlToPaper);
export default {
    name: "prescription",
    data() {
        return {
            prescription: {
                titlenameId: "",
                doctornameId: "",
                unit_of_medicineId: "",
                patient_id: "",
                name: "",
                drugname: "",
                age: "",
                drugallergy: "",
                price: "",
                amount: "",
                drugname_s: "",
                doctorname_s: "",
                titlename_s: "",
            },
            drugNotFound: false,
            valid: false,
            sheet: false,
            saveSC: false,
            saveUSC: false,
            titlename: [],
            doctorname: [],
            unit: [],
            items: [],
            headers: [
                { text: "ชื่อยา", value: "drug.drugname" },
                { text: "จำนวนยา", value: "amount" },
                { text: "หน่วยของยา", value: "unit_of_medicine.unit" },
                { text: "ราคา/หน่วย", value: "drug.price" },
            ],
        };
    },
    methods: {
        print() {
            this.$htmlToPaper('printMe');
        },
        /* eslint-disable no-console */
        getTitlenames() {
            http
                .get("/titleName")
                .then(response => {
                    this.titlename = response.data;
                    console.log(response.data);
                })
                .catch(e => {
                    console.log(e);
                });
        },
        /* eslint-disable no-console */
        getDoctornames() {
            http
                .get("/doctor")
                .then(response => {
                    this.doctorname = response.data;
                    console.log(response.data);
                })
                .catch(e => {
                    console.log(e);
                });
        },
        getUnitnames() {
            http
                .get("/unit_of_medicine")
                .then(response => {
                    this.unit = response.data;
                    console.log(response.data);
                })
                .catch(e => {
                    console.log(e);
                });
        },
        savePrescription() {
            console.log(this.Prescription);
            http
                .post(
                    "/prescription/" + this.prescription.patient_id + "/" + this.prescription.titlenameId + "/" + this.prescription.name +
                    "/" + this.prescription.age + "/" + this.prescription.drugallergy + "/" + this.prescription.doctornameId + "/" +
                    this.prescription.Drug_id + "/" + this.prescription.amount + "/" + this.prescription.unit_of_medicineId, this.prescription
                )
                .then(response => {
                    console.log(response);
                    this.saveSC = true;
                    this.saveUSC = false;
                    this.getPrescriptions();
                    this.findDoctor();
                    this.findTitleName();
                })
                .catch(e => {
                    console.log(e);
                    this.saveUSC = true;
                    this.saveSC = false;
                });

        },findTitleName() {
            http
                .get("/titleName/" + this.prescription.titlenameId)
                .then(response => {
                    console.log(response);
                    if (response.data != null) {
                        this.prescription.titlename_s = response.data.titlename;
                    }
                })
                .catch(e => {
                    console.log(e);
                });
        },
        findDoctor() {
            http
                .get("/doctor/" + this.prescription.doctornameId)
                .then(response => {
                    console.log(response);
                    if (response.data != null) {
                        this.prescription.doctorname_s = response.data.doctor_name;
                    }
                })
                .catch(e => {
                    console.log(e);
                });
        },
        getPrescriptions() {
            http
                .get("/prescription")
                .then(response => {
                    this.items = response.data;
                    console.log(this.items);
                })
                .catch(e => {
                    console.log(e);
                });
        },

        findDrug() {
            console.log(this.prescription.drugname_s);
            http
                .get("/drug/drugname/" + this.prescription.drugname_s)
                .then(response => {
                    console.log(response);
                    if (response.data[0] != null) {
                        this.prescription.Drug_id = response.data[0].id;
                        this.prescription.drugname = response.data[0].drugname;
                        this.prescription.price = response.data[0].price;
                        this.drugCheck = response.status;
                        this.drugNotFound = false;

                    } else {
                        this.clear();
                        this.drugNotFound = true;

                    }
                })
                .catch(e => {
                    console.log(e);
                });
            this.submitted = true;
        },
        printDrugDispense(){
            if(this.saveSC==true){
                this.print(); 
            }
                   
        },
        clear() {
            this.drugCheck = false;
            this.prescription.drugname = "";
            this.prescription.price = "";
            this.prescription.amount = "";
            this.prescription.unit_of_medicineId = "";
            this.saveSC = false;
            this.saveUSC = false;
            this.drugNotFound = false;
        },
    },
    mounted() {
        this.getTitlenames();
        this.getDoctornames();
        this.getUnitnames();
    }

};
</script>
