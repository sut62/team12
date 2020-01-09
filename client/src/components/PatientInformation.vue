<template >
  <v-container>
    <div v-if="patientNotFound">
      <v-alert dense outlined type="error">
        <strong>ไม่พบข้อมูลผู้ป่วย</strong> กรุณากรอกข้อมูลอีกครั้ง
      </v-alert>
    </div>

    <div v-if="saveUSC">
      <v-alert outlined dense text type="warning" prominent border="left">
        <strong>ไม่สามารถบันทึกได้</strong> โปรดตรวจสอบข้อมูลอีกครั้ง
      </v-alert>
    </div>

    <div v-if="saveSC">
      <v-alert dense outlined text prominent type="success">บันทึกข้อมูลสำเร็จ ลำดับที่ได้คือ <h1>{{this.queue}}</h1></v-alert>
    </div>

    <v-layout text-center wrap >
      <v-flex mb-4>
        <h1 class="display-2 font-weight-bold mb-3">งานซักประวัติผู้ป่วย</h1>
      </v-flex>
    </v-layout>

    <v-row justify="center" >
      <v-col cols="4">
        <v-form v-model="valid" ref="form" >
          <v-row>
            <v-col cols="10">
              <v-text-field
                outlined
                label="รหัสผู้ป่วย"
                v-model="patient_information.patientId"
                :rules="[(v) => !!v || 'กรุณากรอกรหัสผู้ป่วย']"
                required
              ></v-text-field>

              <!--แสดงข้อมูลผู้ป่วย-->
              <p v-if="patientCheck != ''">
                ข้อมูลผู้ป่วย :
                <br />
                คุณ {{patientFName}} {{patientLName}}
                อายุ {{patientAge}} ปี
                <br />
                โรคประจำตัว {{patientCongenitaldisease}}
                <br />
                ประวัติการแพ้ยา {{patientDrugallergy}}
                
                <br />
              </p>
            </v-col>

            <v-col cols="2">
              <div class="my-2">
                <v-btn color="blue-grey darken-1" dark @click="findPatients">Search</v-btn>
              </div>
            </v-col>
          </v-row>
          <div v-if="patientCheck" @click="getStatuss(),getPatient_types(),getRooms()">
             <v-row>    <v-col cols="12" md="6">
          <v-text-field
          label="ความดันสูง" v-model="patient_information.pressurehight"
                :rules="[(v) => !!v || 'Item is required']"
                  required
         ></v-text-field> 
          </v-col>              
                    <v-col cols="12" md="6">
          <v-text-field block
                label="ความดันต่ำ" v-model="patient_information.pressurelow"
                :rules="[(v) => !!v || 'Item is required']"
                  required
          ></v-text-field> </v-col> </v-row>
           <v-row>    <v-col cols="12" md="3">
          <v-text-field
          label="น้ำหนัก" v-model="patient_information.weight"
          l="patient_information.weight"
          suffix="kg."
                :rules="[(v) => !!v || 'Item is required']"
                  required
          ></v-text-field>
         </v-col>                      
                    <v-col cols="12" md="3">
          <v-text-field block
           label="ส่วนสูง"  v-model="patient_information.hight"
          suffix="cm."
                :rules="[(v) => !!v || 'Item is required']"
                  required
          ></v-text-field> </v-col> <v-col cols="12" md="5">
          <v-text-field block
             prepend-icon="wb_sunny" label="อุณหภูมิร่างกาย" v-model="patient_information.temp"
          suffix="°C"
               :rules="[(v) => !!v || 'Item is required']"
                  required
          ></v-text-field></v-col> </v-row>
            <v-row>
              <v-col cols="12" md="12">
                <v-textarea block label="อาการเบื่องต้น" v-model="patient_information.symptom" > </v-textarea>
              </v-col>
            </v-row>
            <v-row>
              <v-col class="d-flex" cols="12" sm="7">
                <v-select
                  label="สถานภาพผู้ป่วย"
                  v-model="patient_information.statusID"
                  :items="statuss"
                  item-text="status"
                  item-value="id"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-select>
              </v-col>

              <v-col class="d-flex" cols="12" sm="7">
                <v-select
                  label="ประเภทผู้ป่วย"
                  v-model="patient_information.patient_typeId"
                  :items="patient_types"
                  item-text="patient_type"
                  item-value="id"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-select>
              </v-col>

              <v-col class="d-flex" cols="12" sm="7">
                <v-select
                  label="เรียกเข้าห้องตรวจ"
                  v-model="patient_information.roomId"
                  :items="rooms"
                  item-text="room"
                  item-value="id"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-select>
              </v-col><br />
            </v-row>
           <v-row justify="center"> 
               <!-- <v-col cols="12" >-->
                <v-btn @click="savePatient_information" :class="{ red: !valid, green: valid }">save</v-btn>
                
           <!-- </v-col>-->
            </v-row>
            <br />
          </div>
        </v-form>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import http from "../http-common";

export default {
  name: "patient_information",
  data() {
    return {
      patient_information: {
        patientId: "",
        statusID: "",
        patient_typeId: "",
        roomId: "",
        doctorId: "",
        pressurehight: "",
        pressurelow: "",
        weight: "",
        hight: "",
        temp: "",
        symptom: ""
      },
      valid: false,
      patientCheck: false,
      patientFName: "",
      patientLName: "",
      patientAge: "",
      patientCongenitaldisease: "",
      patientDrugallergy: "",
      queue:"",
      patientNotFound: false,
      saveSC: false,
      saveUSC: false,
      statuss: [],
      rooms: [],
      patient_types: []
    };
  },
  methods: {
    
    /* eslint-disable no-console */
    findPatients() {
      http
        .get("/patient/" + this.patient_information.patientId)
        .then(response => {
          console.log(response);
          if (response.data != null) {
            this.patientFName = response.data.firstname;
            this.patientLName = response.data.lastname;
            this.patientAge = response.data.age;
            this.patientCongenitaldisease = response.data.congenitaldisease;
            this.patientDrugallergy = response.data.drugallergy;
            console.log(this.patientFName);
            console.log(this.patientLName);
            console.log(this.patientAge);
            console.log(this.patientCongenitaldisease);
            console.log(this.patientDrugallergy);
            this.patientCheck = response.status;
            this.patientNotFound = false;
          } else {
            this.clear();
            this.patientNotFound = true;
          }
        })
        .catch(e => {
          console.log(e);
        });
      this.submitted = true;
    },
    /* eslint-disable no-console */
    getStatuss() {
      http
        .get("/status")
        .then(response => {
          this.statuss = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getPatient_types() {
      http
        .get("/patient_type")
        .then(response => {
          this.patient_types = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getRooms() {
      http
        .get("/room")
        .then(response => {
          this.rooms = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });

      /* eslint-disable no-console */
    },
    savePatient_information() {
      console.log(this.patient_information);
      console.log(this.patient_information.pressurehight);
      console.log(this.patient_information.pressurelow);
      console.log(this.patient_information.weight);
      console.log(this.patient_information.hight);
      console.log(this.patient_information.temp);
      console.log(this.patient_information.symptom);
      http
        .post(
          "/patient_information/" +
            this.patient_information.patientId +
            "/" +
            this.patient_information.pressurehight +
            "/" +
            this.patient_information.pressurelow +
            "/" +
            this.patient_information.weight +
            "/" +
            this.patient_information.hight +
            "/" +
            this.patient_information.temp +
            "/" +
            this.patient_information.symptom + 
            "/" +
            this.patient_information.statusID +
            "/" + 
            this.patient_information.patient_typeId +
            "/" +
            this.patient_information.roomId ,
            this.patient_information
            
        )
        .then(response => {
          console.log(response);
          this.saveUSC = false;
          this.saveSC = true;
          this.queue = response.data.id;
          console.log(this.queue);
        })
        .catch(e => {

          console.log(e);
          this.saveUSC = true;
          this.saveSC = false;
        });
      this.submitted = true;
    },
    clear() {
      this.$refs.form.reset();
      this.patientCheck = false;
      this.saveUSC = false;
      this.saveSC = false;
      this.patientNotFound = false;
    },
    mounted() {
      this.getStatuss();
      this.getPatient_types();
      this.getRooms();
    }
  }
};
</script>