<template>
<v-container>
    <div v-if="saveUSC">
        <v-alert outlined dense text type="warning" prominent border="left">
            <strong>ไม่สามารถบันทึกได้</strong>
            กรุณากรอกข้อมูลให้ครบก่อนบันทึกข้อมูล
        </v-alert>
    </div>

    <div v-if="saveSC">
        <v-alert dense outlined text prominent type="success">บันทึกข้อมูลสำเร็จ</v-alert>
    </div>
    <v-row>
        <v-col md="5">
            <h1>ใบเสร็จยา</h1>
            <!-- ค้นหา -->
            <v-row>
                <v-col cols="12" md="6">
                    <v-text-field label="รหัสใบสั่งยา" outlined maxlenght="13" v-model="RecordBill.prescriptionId"></v-text-field>
                </v-col>
                <div class="my-2" right>
                    <v-btn absolute height="60px" width="100px" color="blue" @click="findPrescription">ค้นหา</v-btn>
                </div>
            </v-row>

            <v-row>
                <!-- ผูกข้อมูลmedicalRightId กับ combobox-->
                <v-col class="d-flex" cols="12" md="6">
                    <v-select label="สิทธิการรักษา" v-model="RecordBill.medicalRightId" :items="medicalRights" item-text="medicalRight" item-value="id"></v-select>
                </v-col>

                <!-- ผูกข้อมูลmpaymentChannelId กับ combobox-->
                <v-col class="d-flex" cols="12" md="6">
                    <v-select label="ช่องชำระเงินที่ ..." v-model="RecordBill.paymentchannelId" :items="paymentchannels" item-text="channel" item-value="id"></v-select>
                </v-col>
            </v-row>

            <!-- ผูกข้อมูลTotalPrice กับ TextField-->
            <v-row>
                <v-col cols="12" md="4">
                    <v-text-field label="รวมราคาสุทธิ" outlined v-model="RecordBill.TotalPrice"></v-text-field>
                </v-col>
            </v-row>

            <!-- ผูกข้อมูลcashierId กับ combobox-->
            <v-row>
                <v-col class="d-flex" cols="12" md="4">
                    <v-select label="ผู้รับเงิน" v-model="RecordBill.cashierId" :items="cashiers" item-text="cashier" item-value="id"></v-select>
                </v-col>
            </v-row>

            <!-- ปุ่ม SAVE -->
            <v-row justify="center">
                <v-col class="d-flex">
                    <v-btn class="yellow" @click="saveRecordBills">SAVE</v-btn>
                </v-col>
            </v-row>
        </v-col>
        <!-- แสดงผลการ Search -->
        <v-col>
            <h2>ผลการค้นหา</h2>
            <v-text-field v-model="patientId" label="รหัสผู้ป่วย" disabled></v-text-field>
            <v-text-field v-model="titlename" label="คำนำหน้า" disabled></v-text-field>
            <v-text-field v-model="patientName" label=" ชื่อ-สกุล" disabled></v-text-field>
            <v-text-field v-model="iDdrug" label="รหัสยา" disabled></v-text-field>
            <v-text-field v-model="N_drug" label="ชื่อยา" disabled></v-text-field>
            <v-text-field v-model="drugamount" label="จำนวนยา" disabled></v-text-field>
            <v-text-field v-model="Unit" label="หน่วยของจำนวนยา" disabled></v-text-field>
        </v-col>
    </v-row>
</v-container>
</template>

<script>
import http from "../http-common";
export default {
    name: "RecordBill",
    data() {
        return {
            RecordBill: {
                paymentchannelId: "",
                medicalRightId: "",
                cashierId: "",
                TotalPrice: "",
                prescriptionId: ""
            },
            patientId: "",
            titlename: "",
            Unit: "",
            patientName: "",
            iDdrug: "",
            N_drug: "",
            drugamount: "",

            items: [],
            paymentchannels: [],
            cashiers: [],
            medicalRights: [],
            saveUSC: false,
            saveSC: false,
            menu: false,
            menu1: false,
            headers: [{
                    text: "Prescription Id",
                    value: "doctorProfile.id"
                },
                {
                    text: "ชื่อยา",
                    value: "doctorProfile.name"
                },
                {
                    text: "ราคายา",
                    value: "room.room"
                },
                {
                    text: "จำนวนของยา",
                    value: "day.day"
                },
                {
                    text: "หน่วยของยา",
                    value: "periodTime.periodTime"
                }
            ]
        };
    },
    methods: {
        /* eslint-disable no-console */
        // ดึงข้อมูล PaymentChannel ใส่ combobox
        getPaymentChannels() {
            http
                .get("/paymentChannel")
                .then(response => {
                    this.paymentchannels = response.data;
                })
                .catch(e => {
                    console.log(e);
                });
        },
        // ดึงข้อมูล Cashier ใส่ combobox
        getCashiers() {
            http
                .get("/cashier")
                .then(response => {
                    this.cashiers = response.data;
                })
                .catch(e => {
                    console.log(e);
                });
        },
        // ดึงข้อมูล MedicalRight ใส่ combobox
        getMedicalRights() {
            http
                .get("/medicalRight")
                .then(response => {
                    this.medicalRights = response.data;
                })
                .catch(e => {
                    console.log(e);
                });
        },
        findPrescription() {
            http
                .get("/prescription/" + this.RecordBill.prescriptionId)
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
                        this.orderCheck = response.status;
                        this.orderNotFound = false;
                    } else {
                        this.clear();
                        this.OrderNotFound = true;
                    }
                })
                .catch(e => {
                    console.log(e);
                });
            this.submitted = true;
        },
        /* eslint-disable */
        // function เมื่อกดปุ่ม SAVE
        saveRecordBills() {
            http
                .post(
                    "/recordBill/" +
                    this.RecordBill.medicalRightId +
                    "/" +
                    this.RecordBill.paymentchannelId +
                    "/" +
                    this.RecordBill.cashierId +
                    "/" +
                    this.RecordBill.TotalPrice +
                    "/" +
                    this.RecordBill.prescriptionId
                )
                .then(response => {
                    this.saveUSC = false;
                    this.saveSC = true;
                    console.log(response);
                })
                .catch(e => {
                    console.log(e);
                    this.saveUSC = true;
                    this.saveSC = false;
                });
        }
        /* eslint-enable no-console */
    },
    mounted() {
        this.getPaymentChannels();
        this.getCashiers();
        this.getMedicalRights();
    }
};
</script>
