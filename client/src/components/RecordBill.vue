<template>
  <v-container>
    <div v-if="saveUSC">
      <v-alert outlined dense text type="warning" prominent border="left">
        <strong>ไม่สามารถบันทึกได้</strong> กรุณากรอกข้อมูลให้ครบก่อนบันทึกข้อมูล
      </v-alert>
    </div>

    <div v-if="saveSC">
      <v-alert dense outlined text prominent type="success">บันทึกข้อมูลสำเร็จ</v-alert>
    </div>
    <v-row>
      
      <v-col md="5">
        <h1>ใบเสร็จยา</h1>
      <!-- ผูกข้อมูลid_card กับ textfield-->
      <v-row> 
        <v-col cols="12" md="6">
          <v-text-field
            label="รหัสประจำตัวประชาชน"
            outlined
            maxlenght="13"
          ></v-text-field>
        </v-col>
        <div class="my-2" right >
        <v-btn absolute height="60px" width="100px" color="blue">ค้นหา</v-btn>
      </div>
      </v-row>
      <v-row >
        <!-- ผูกข้อมูลibloodgroupId กับ combobox-->
        <v-col class="d-flex" cols="12" md="6">
          <v-select
            label="สิทธิการรักษา"
            v-model="patient.bloodgroupId"
            :items="bloodgroups"
            item-text="type"
            item-value="id"
          ></v-select>
        </v-col>
        <v-col class="d-flex" cols="12" md="6" >
          <v-select
            label="ช่องชำระเงินที่ ..."
            v-model="patient.bloodgroupId"
            :items="bloodgroups"
            item-text="type"
            item-value="id"
          ></v-select>
        </v-col>
      </v-row>
        <v-row >
        <v-col right cols="12" md="4">
          <v-text-field
            label="รวมราคาสุทธิ"
            outlined
          ></v-text-field>
        </v-col>
        </v-row>

      <!-- ผู้รับเงิน -->
      <v-row>
        <v-col class="d-flex" cols="12" md="4" >
          <v-select
            label="ผู้รับเงิน"
            v-model="patient.bloodgroupId"
            :items="bloodgroups"
            item-text="type"
            item-value="id"
          ></v-select>
        </v-col>
      </v-row>
         <!-- ปุ่ม click-->
      <v-row justify="center" >
        <v-col class="d-flex">
        <v-btn class="yellow" @click="savePatients">SAVE</v-btn>
        </v-col>
      </v-row>
      </v-col>
      <!-- ตาราง -->
        <v-col >
          <v-row >
            <v-col   cols="12">
              <v-data-table 
              :headers="headers"
              :items="items" 
              :items-per-page="10" 
              class="elevation-1">

              </v-data-table>
            </v-col>
          </v-row>
      </v-col>  
    </v-row>
  </v-container>
</template>

<script>
import http from "../http-common";
import moment from "moment";

export default {
  computed: {
    computedDateFormattedMomentjs() {
      return this.birthday
        ? moment(this.birthday).format("dddd Do, MMMM YYYY")
        : "";
    }
  },
  name: "patient",
  data() {
    return {
      patient: {
        patientId: "",
        titlenameId: "",
        bloodgroupId: "",
        provinceId: "",
        genderId: "",
        firstname: "",
        lastname: "",
        age: "",
        address: "",
        drugallergy: "",
        congenitaldisease: "",
        id_card: ""
      },
      titlenames: [],
      genders: [],
      bloodgroups: [],
      provinces: [],
      tempGender: [],
      saveUSC: false,
      saveSC: false,
      menu: false,
      birthday: "",
      menu1: false,
      headers: [
        { text: "Prescription Id", value: "doctorProfile.id" },
        { text: "ชื่อยา", value: "doctorProfile.name" },
        { text: "ราคายา", value: "room.room" },
        { text: "จำนวนของยา", value: "day.day" },
        { text: "หน่วยของยา", value: "periodTime.periodTime" }
      ],
    };
  },
  methods: {
    /* eslint-disable no-console */
    // 
    filterGender() {
      this.genders = [];
      this.patient.genderId = "";
      if (
        this.titlenames[this.patient.titlenameId - 1].name === "นาย" ||
        this.titlenames[this.patient.titlenameId - 1].name === "เด็กชาย"
      ) {
        this.genders.push(this.tempGender[0]);
      } else {
        this.genders.push(this.tempGender[1]);        
      }
    },
    // ดึงข้อมูล TitleName ใส่ combobox
    getTitleNames() {
      http
        .get("/titlename")
        .then(response => {
          this.titlenames = response.data;
        })
        .catch(e => {
          console.log(e);
        });
    },
    // ดึงข้อมูล BloodGroup ใส่ combobox
    getBloodGroups() {
      http
        .get("/bloodgroup")
        .then(response => {
          this.bloodgroups = response.data;
        })
        .catch(e => {
          console.log(e);
        });
    },
    // ดึงข้อมูล Gender ใส่ combobox
    getGenders() {
      http
        .get("/gender")
        .then(response => {
          this.tempGender = response.data;
        })
        .catch(e => {
          console.log(e);
        });
    },
    // ดึงข้อมูล Province ใส่ combobox
    getProvinces() {
      http
        .get("/province")
        .then(response => {
          this.provinces = response.data;
        })
        .catch(e => {
          console.log(e);
        });
    },
    /* eslint-disable */
    // function เมื่อกดปุ่ม SAVE
    savePatients() {
      http
        .post(
          "/patient/" +
            this.patient.id_card +
            "/" +
            this.patient.titlenameId +
            "/" +
            this.patient.genderId +
            "/" +
            this.patient.bloodgroupId +
            "/" +
            this.patient.firstname +
            "/" +
            this.patient.lastname +
            "/" +
            this.patient.age +
            "/" +
            this.birthday +
            "/" +
            this.patient.address +
            "/" +
            this.patient.provinceId +
            "/" +
            this.patient.drugallergy +
            "/" +
            this.patient.congenitaldisease
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
    this.getTitleNames();
    this.getBloodGroups();
    this.getGenders();
    this.getProvinces();
  }
};
</script>
