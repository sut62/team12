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
                        <v-text-field v-model="N_drug" label="ชื่อยา" disabled>
                        </v-text-field>
                         <v-text-field v-model="d_Effet" label="ผลข้างเคียง" disabled>
                    </v-text-field>
                    </v-row>
                    
                    <v-row>
                        <v-col cols="4">
                            <v-textarea v-model="quantity" auto-grow outlined rows="1" label="ปริมาณการรับประทานยา">
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
                         <v-btn  color="primary" dark v-on="on" @click="saveMedicine">บันทึก</v-btn>                      
                        </template>
                       <v-sheet class="text-center" height="750px">
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
                            <h1 justify="center">ข้อมูลฉลากยา</h1><br>
                            <v-row >
                                <v-col >
                                    <p class="text-left"> 
                                        {{patientId}}
                                    </p><br>
                                    <p class="text-left"> 
                                        ชื่อ-สกุล:{{patientName}}
                                    </p><br>
                                    <p class="text-left"> 
                                        ชื่อยา:{{N_drug}}
                                    </p><br>
                                    <p class="text-left"> 
                                        รับประทานครั้งละ {{quantity}} 
                                        {{medicine.m_quantity}} 
                                        วันละ {{medicine.Frequency}}
                                    </p><br>
                                    <p class="text-left"> 
                                        {{medicine.m_duration}}
                                    </p><br>
                                    <p class="text-left"> 
                                        ผลข้างเคียง :{{d_Effet}}
                                    </p><br>
                                </v-col>
                                <v-col md = 6><br><br>
                                   จำนวน: {{patientamount}} {{Unit}}
                                </v-col>
                        

                                
                            </v-row> 
                            <v-row justify="center">
                                 <v-btn  color="primary" dark  >พิมพ์</v-btn>
                            </v-row>
                        </v-container>
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
                Frequency:"",
                m_quantity:"",
                m_duration:"",
              

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
            N_drug: "",
            Unit: "",
            d_Effet: "",
            patientamount: "",
            patientPrescription_id: "",
            drugEffet: "",
            quantity: "",
            orderNotFound: false,
            newFrequencys:"",

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
        findFrequency () {
            http
                .get("/frequency/" + this.medicine.medicineFrequencyId)
                .then(response => {
                    console.log(response);
                    if (response.data != null) {
                        this.medicine.Frequency = response.data.medicineFrequency;
                    }
                })
                .catch(e => {
                    console.log(e);
                });
        },

        findQuantity () {
            http
                .get("/quantity/" + this.medicine.medicineQuantityId)
                .then(response => {
                    console.log(response);
                    if (response.data != null) {
                        this.medicine.m_quantity = response.data.medicineQuantity;
                    }
                })
                .catch(e => {
                    console.log(e);
                });
        },

        findDuration () {
            http
                .get("/duration/" + this.medicine.medicineDurationId)
                .then(response => {
                    console.log(response);
                    if (response.data != null) {
                        this.medicine.m_duration = response.data.medicineDuration;
                    }
                })
                .catch(e => {
                    console.log(e);
                });
        },

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
        getMedicineLabel() {
            http
                .get("/MedicineLabel")
                .then(response => {
                    this.medicineLabels = response.data;
                    console.log(this.medicineLabels);
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
                        this.N_drug = response.data.drug.drugname;
                        this.d_Effet = response.data.drug.sef;
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

        

        // function เมื่อกดปุ่ม submit
        saveMedicine() {
            http
                .post(
                    "/MedicineLabel/" + this.medicine.prescriptionId +
                    
                    "/" +
                    this.N_drug +
                     "/" +
                    this.d_Effet +
                                       
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
                    this.findFrequency();
                    this.findQuantity();
                    this.findDuration ();
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
        this.getMedicineLabel();
    }

};
</script>
