<template>
<v-container>

    <div class="text-center">
        <h1 class="display-2">ระบบกรอกข้อมูลฉลากยา</h1>
    </div><br><br>
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

    <v-row>
        <v-col md="4">
            <v-row>
                <v-text-field v-model="medicine.prescriptionId" label="กรุณาค้นหารหัสใบสั่งยา" :rules="[(v) => !!v || 'กรุณากรอกข้อมูล']" required>
                </v-text-field>
                <v-btn class="ma-2" small color="primary" dark @click="findPrescription">ค้นหา
                </v-btn>
            </v-row>

            <v-row>
                <v-col cols="8">
                    <v-text-field v-model="patientId" label="รหัสผู้ป่วย" disabled>
                    </v-text-field>
                    <v-text-field v-model="ttname" label="คำนำหน้า" disabled>
                    </v-text-field>
                    <v-text-field v-model="patientName" label=" ชื่อ-สกุล" disabled>
                    </v-text-field>
                    <v-text-field v-model="iDdrug" label="รหัสยา" disabled>
                    </v-text-field>
                    <v-text-field v-model="N_drug" label="ชื่อยา" disabled>
                    </v-text-field>
                    <v-text-field v-model="patientamount" label="จำนวนยา" disabled>
                    </v-text-field>
                    <v-text-field v-model="Unit" label="หน่วยของจำนวนยา" disabled>
                    </v-text-field>
                </v-col>
            </v-row>
        </v-col>

        <v-row>
            <v-card class="mx-auto" color="white" width="700px" outlined>
                <v-col md="10">
                    <v-row>
                        <v-text-field v-model="medicine.drugId" label="ค้นหา ID ยา" :rules="[(v) => !!v || 'กรุณากรอกข้อมูล']" required>
                        </v-text-field>
                        <v-btn class="ma-2" small color="primary" dark @click="findDrug">ค้นหา
                        </v-btn>
                    </v-row>
                    <v-text-field v-model="Namedrug" label="ชื่อยา" disabled>
                    </v-text-field>
                    <v-row>
                        <v-col cols="3">
                            <v-textarea v-model="quantity" auto-grow outlined rows="1" label="ป้อนจำนวน">
                            </v-textarea>
                        </v-col>
                        <v-select v-model="medicine.medicineQuantityId" :items="medicineQuantitys" item-text="medicineQuantity" item-value="id" label="โปรดเลือกหน่วยการรับประทานยา" :rules="[(v) => !!v || 'กรุณากรอกข้อมูล']" required>
                        </v-select>
                    </v-row>

                    <v-select v-model="medicine.medicineFrequencyId" :items="medicineFrequencys" item-text="medicineFrequency" item-value="id" label="โปรเลือกความถี่การรับประทานยา" :rules="[(v) => !!v || 'กรุณากรอกข้อมูล']" required>
                    </v-select>
                    <v-select v-model="medicine.medicineDurationId" :items="medicineDurations" item-text="medicineDuration" item-value="id" label="โปรดเลือกช่วงเวลา" :rules="[(v) => !!v || 'กรุณากรอกข้อมูล']" required>
                    </v-select>

                    <v-bottom-sheet v-model="sheet">
                        <template v-slot:activator="{ on }">
                            <v-row justify="center">
                                <v-btn color="primary" @click="saveMedicine">บันทึกข้อมูล
                                </v-btn>
                            </v-row>
                        </template>
                        <v-sheet class="text-center" height="750px">
                            <div>
                                <br>
                                <h2>ข้อมูลฉลากยา</h2>
                            </div>

                            <div>
                                รหัสผู้ป่วย : {{patientId}} <br>
                                ชื่อ-สกุล :{{patientName}}<br>
                                ชื่อยา : {{Namedrug}}<br>
                                วิธีใช้ : <br>
                                คำเตือน : {{drugEffet}}<br>
                            </div>
                        </v-sheet>
                    </v-bottom-sheet>
                </v-col>
            </v-card>
        </v-row>

    </v-row>
</v-container>
</template>

<script>
import http from "../http-common"
export default {
    name: "medicine",
    data() {
        return {
            medicine: {
                medicineQuantityId: "",
                medicineFrequencyId: "",
                medicineDurationId: "",
                prescriptionId: "",
                drugId: "",

                //คลาสที่เอามาเก็บตัวแปร

            },
            valid: false,
            sheet: false,
            orderCheck: false,
            saveSC: false,
            saveUSC: false,
            patientId: "",
            ttname: "",
            patientName: "",
            iDdrug: "",
            Unit: "",
            N_drug: "",
            patientamount: "",
            patientPrescription_id: "",
            drugEffet: "",
            quantity: "",
            Namedrug: "",
            orderNotFound: false,

            drugCheck: false,

            medicineQuantitys: [],
            medicineFrequencys: [],
            medicineDurations: [],
            medicineLabels: [],

            items: []
        };
    },

    methods: {
        /* eslint-disable no-console */

        // ดึงข้อมูล Room ใส่ combobox
        getMedicineDurations() {
            http
                .get("/duration")
                .then(response => {
                    this.medicineDurations = response.data;
                    console.log(response.data);
                })
                .catch(e => {
                    console.log(e);
                });
        },

        // ดึงข้อมูล Day ใส่ combobox
        getMedicineQuantitys() {
            http
                .get("/quantity")
                .then(response => {
                    this.medicineQuantitys = response.data;
                    console.log(response.data);
                })
                .catch(e => {
                    console.log(e);
                });
        },

        // ดึงข้อมูล Room ใส่ combobox
        getMedicineFrequencys() {
            http
                .get("/frequency")
                .then(response => {
                    this.medicineFrequencys = response.data;
                    console.log(response.data);
                })
                .catch(e => {
                    console.log(e);
                });
        },

        // ค้นหารหัสใบสั่งยา
        findPrescription() {
            http
                .get("/prescription/" + this.medicine.prescriptionId)
                .then(response => {
                    console.log(response);
                    if (response.data != null) {
                        this.patientId = response.data.patient_ID;
                        this.ttname = response.data.titleName.titlename;
                        this.patientName = response.data.name;
                        this.iDdrug = response.data.drug.id;
                        this.N_drug = response.data.drug.drugname;
                        this.patientamount = response.data.amount;
                        this.patientPrescription_id = response.data.Prescription_id;
                        this.Unit = response.data.unit_of_medicine.unit;
                        this.orderCheck = response.status;
                        this.orderNotFound = false;
                    } else {
                        
                        this.OrderNotFound = true;
                    }
                })
                .catch(e => {
                    console.log(e);
                });
            this.submitted = true;
        },

        //ค้นหาชื่อยา
        findDrug() {
            http
                .get("/drug/" + this.medicine.drugId)
                .then(response => {
                    console.log(response);
                    if (response.data != null) {
                        this.Namedrug = response.data.drugname;
                        this.drugEffet = response.data.sideeffect;
                        this.drugCheck = response.status;
                        this.drugFound = false;
                    } else {
                        this.clear()
                        this.drugNotFound = true;
                    }
                })
                .catch(e => {
                    console.log(e);
                });
            this.submitted = true;
        },

        // function เมื่อกดปุ่ม submit
        saveMedicine() {
            http
                .post(
                    "/MedicineLabel/" + this.medicine.prescriptionId +
                    
                    "/" +
                    this.iDdrug +
                                       
                    "/" +
                    this.quantity +
                    "/" +
                    this.medicine.medicineQuantityId +
                    "/" +
                    this.medicine.medicineFrequencyId +
                    "/" +
                    this.medicine.medicineDurationId, 
                    this.medicine
                )
                .then(response => {
                    console.log(response);
                    this.saveUSC = false;
                    this.saveSC = true;
                })

                .catch(e => {
                    console.log(e);
                    this.saveUSC = true;
                    this.saveSC = false;
                });
            this.submitted = true;
        },

        /* eslint-enable no-console */
    },
    mounted() {

        this.getMedicineFrequencys();
        this.getMedicineQuantitys();
        this.getMedicineDurations();
    }

};
</script>
