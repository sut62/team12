<template>
<v-container>
    <v-container>
        <v-row justify="center">
            <div v-if="searchNotFound">
                <v-alert dense outlined type="error">
                    <strong>ไม่พบข้อมูล</strong> กรุณากรอกข้อมูลอีกครั้ง
                </v-alert>
            </div>
        </v-row>
    </v-container>
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
                    <v-text-field label="รวมราคาสุทธิ" outlined v-model="RecordBill.DrugPrice"></v-text-field>
                </v-col>
            </v-row>

            <!-- ผูกข้อมูลcashierId กับ combobox-->
            <v-row>
                <v-col class="d-flex" cols="12" md="4">
                    <v-select label="ผู้รับเงิน" v-model="RecordBill.cashierId" :items="cashiers" item-text="cashier" item-value="id"></v-select>
                </v-col>
            </v-row>
        </v-col>
        <!-- แสดงผลการ Search -->
        <v-col>
            <h2>ผลการค้นหา</h2>
            <v-text-field v-model="RecordBill.patientId" label="รหัสผู้ป่วย" disabled></v-text-field>
            <v-text-field v-model="RecordBill.titlename" label="คำนำหน้า" disabled></v-text-field>
            <v-text-field v-model="RecordBill.patientName" label=" ชื่อ-สกุล" disabled></v-text-field>
            <v-text-field v-model="RecordBill.iDdrug" label="รหัสยา" disabled></v-text-field>
            <v-text-field v-model="RecordBill.N_drug" label="ชื่อยา" disabled></v-text-field>
            <v-text-field v-model="RecordBill.drugamount" label="จำนวนยา" disabled></v-text-field>
            <v-text-field v-model="RecordBill.Unit" label="หน่วยของจำนวนยา" disabled></v-text-field>
            <v-text-field v-model="RecordBill.Price" label="ราคายา" disabled></v-text-field>
        </v-col>
    </v-row>

    <!-- form before print -->
    <v-row justify="center">
        <div class="text-center">
            <v-bottom-sheet v-model="sheet">
                <template v-slot:activator="{ on }">
                    <v-row>
                        <v-btn class="mr-12" rounded :class="{ red: !valid, green: valid }" @click="saveRecordBills"  v-on="on">SAVE</v-btn>
                    </v-row>
                </template>
                <v-sheet class="text-center" height="750px">
                    <div v-if="saveUSC">
                        <v-alert outlined dense text type="warning" prominent border="left">
                            <strong>ไม่สามารถบันทึกได้</strong>
                            กรุณากรอกข้อมูลให้ครบก่อนบันทึกข้อมูล
                        </v-alert>
                    </div>

                    <div v-if="saveSC">
                        <v-alert dense outlined text prominent type="success">บันทึกข้อมูลสำเร็จ</v-alert>
                    </div>
                    <div id="printMe">
                        <v-container>
                            <v-form v-model="valid" ref="form">
                                <h1 class="text-center">ใบเสร็จค่ายา</h1>
                                <v-row>
                                    <v-col md="2">
                                        <p class="text-left mt-9"><strong>รหัสผู้ป่วย : </strong>{{RecordBill.patientId}}</p>
                                    </v-col>
                                    <v-col md="2">
                                        <p class="text-left mt-9"><strong>คำนำหน้า : </strong> {{RecordBill.titlename}}</p>
                                    </v-col>
                                    <v-col md="4">
                                        <p class="text-left mt-9"><strong>ชื่อ-สกุลผู้ป่วย : </strong> {{RecordBill.patientName}}</p>
                                    </v-col>
                                </v-row>

                                <v-row>
                                    <v-col md="3">
                                        <p class="text-left mt-9"><strong>สิทธิการรักษา : </strong> {{medicalRight2}}</p><br>
                                    </v-col>
                                    <v-col md="3">
                                        <p class="text-left mt-9"><strong>ช่องชำระเงิน : </strong> {{channel2}}</p><br>
                                    </v-col>
                                    <v-col md="3">
                                        <p class="text-left mt-9"><strong> ผู้รับเงิน :  </strong> {{cashier2}}</p><br>
                                    </v-col>
                                    
                                    
                                </v-row>
                               <h4 class="text-left">____________________________________________________________________________________________________________________________________________________________________________</h4>
                                
                                <v-row class="mt-9"><h4>รหัสยา : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{RecordBill.patientId}}</h4></v-row>
                                <v-row class="mt-9"><h4>ชื่อยา : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; {{RecordBill.N_drug}}</h4></v-row>
                                <v-row class="mt-9"><h4>จำนวนยา : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; {{RecordBill.drugamount}} {{RecordBill.Unit}}</h4></v-row>
                                <v-row class="mt-9"><h4>ราคา(บาท) : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; {{RecordBill.Price}}</h4></v-row>
                            </v-form>
                        </v-container>
                    </div>
                    <div class="text-center mr-12">
                        <v-btn class="mr-12" rounded color="yellow" @click="printRecordBill">PRINT</v-btn>
                    </div>
                </v-sheet>
            </v-bottom-sheet>
        </div>
    </v-row>

</v-container>
</template>

<script>
import http from "../http-common";
import Vue from 'vue';
import VueHtmlToPaper from 'vue-html-to-paper'
Vue.use(VueHtmlToPaper);
export default {
    name: "RecordBill",
    data() {
        return {
            RecordBill: {
                paymentchannelId: "",
                medicalRightId: "",
                cashierId: "",
                TotalPrice: "",
                prescriptionId: "",
                DrugPrice: "",
                patientId: "",
                titlename: "",
                Unit: "",
                patientName: "",
                iDdrug: "",
                N_drug: "",
                drugamount: "",
                Price: "",
            },
           
            items: [],
            paymentchannels: [],
            cashiers: [],
            medicalRights: [],
            saveUSC: false,
            saveSC: false,
            menu: false,
            menu1: false,
            sheet: false,
            valid: false,
            Price: [],
            searchNotFound: false,
            searchCheck: false,
             channel2:"",
                medicalRight2:"",
                cashier2:""
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
        findPaymentChannel(){
            http
                .get("/paymentChannel/" + this.RecordBill.paymentchannelId)
                .then(response => {
                    console.log(response);console.log("ans" + response.data.channel);
                    if (response.data != null) {
                        this.channel2 = response.data.channel;
                    }
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
        findCashier(){
            http
                .get("/cashier/" + this.RecordBill.cashierId)
                .then(response => {
                    console.log(response);
                    console.log("anns : " + response.data.cashier);
                    if (response.data != null) {
                        this.cashier2 = response.data.cashier;
                    }
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
        findMedicalRight(){
            http
                .get("/medicalRight/" + this.RecordBill.medicalRightId)
                .then(response => {
                    console.log(response);
                    console.log("ans : "+ response.data.medicalRight);
                    if (response.data != null) {
                        this.medicalRight2 = response.data.medicalRight;
                    }
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
                        this.RecordBill.patientId = response.data.patient_ID;
                        this.RecordBill.titlename = response.data.titleName.titlename;
                        this.RecordBill.patientName = response.data.name;
                        this.RecordBill.iDdrug = response.data.drug.id;
                        this.RecordBill.N_drug = response.data.drug.drugname;
                        this.RecordBill.drugamount = response.data.amount;
                        this.RecordBill.patientPrescription_id = response.data.Prescription_id;
                        this.RecordBill.Unit = response.data.unit_of_medicine.unit;
                        this.RecordBill.Price = response.data.drug.price;
                        this.searchCheck = response.status;
                        this.searchNotFound = false;
                    } else {
                        this.clear();
                        this.searchNotFound = true;
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
                    this.RecordBill.DrugPrice +
                    "/" +
                    this.RecordBill.prescriptionId
                )
                .then(response => {
                    this.saveUSC = false;
                    this.saveSC = true;
                    console.log(response);
                    this.findPaymentChannel();
                    this.findCashier();
                    this.findMedicalRight();
                })
                .catch(e => {
                    console.log(e);
                    this.saveUSC = true;
                    this.saveSC = false;
                });

        },
        printRecordBill(){
            if(this.saveSC==true){
                this.print(); 
            }
                   
        },
        print() {
            this.$htmlToPaper('printMe');
        },
        clear() {
            this.saveSC = false;
            this.saveUSC = false;
            this.searchNotFound = false;
        },
        /* eslint-enable no-console */
    },
    mounted() {
        this.getPaymentChannels();
        this.getCashiers();
        this.getMedicalRights();
    }
};
</script>
