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
    <div>
      <h1>ลงทะเบียนผู้ป่วย</h1>
      <!-- ผูกข้อมูลid_card กับ textfield-->
      <v-row>
        <v-col cols="7" md="3">
          <v-text-field
            v-model="patient.id_card"
            label="รหัสบัตรประจำตัวประชาชน"
            counter
            maxlength="13"
          ></v-text-field>
        </v-col>
      </v-row>

      <!-- ผูกข้อมูลtitlenameId กับ combobox-->
      <v-row>
        <v-col class="d-flex" cols="12" sm="2">
          <v-select
            v-model="patient.titlenameId"
            label="คำนำหน้าชื่อ"
            :items="titlenames"
            item-text="name"
            item-value="id"
            v-on:change="filterGender"
          ></v-select>
        </v-col>
        <!-- ผูกข้อมูลfirstname กับ textfield-->
        <v-col cols="12" md="4">
          <v-text-field v-model="patient.firstname" label="ชื่อ"></v-text-field>
        </v-col>
        <!-- ผูกข้อมูลlastname กับ textfield-->
        <v-col cols="1" md="4">
          <v-text-field v-model="patient.lastname" label="นามสกุล"></v-text-field>
        </v-col>
      </v-row>

      <!-- ผูกข้อมูลgenderId กับ combobox-->
      <v-row>
        <v-col class="d-flex" cols="12" sm="2">
          <v-select
            label="เพศ"
            v-model="patient.genderId"
            :items="genders"
            item-text="name"
            item-value="id"
          ></v-select>
        </v-col>

        <!-- ผูกข้อมูลage กับ textfield-->
        <v-col cols="8" sm="2">
          <v-text-field label="อายุ" suffix="ปี" v-model="patient.age"></v-text-field>
        </v-col>

        <!-- ตารางวันเดือนปี-->
        <v-col cols="1" md="4">
          <v-menu v-model="menu1" :close-on-content-click="false" max-width="290">
            <template v-slot:activator="{ on }">
              <v-text-field
                :value="computedDateFormattedMomentjs"
                clearable
                label="วันเดือนปีเกิด"
                readonly
                v-on="on"
                locale="th"
              ></v-text-field>
            </template>

            <!-- ผูกข้อมูลbirthday กับ textfield-->
            <v-date-picker locale="th" v-model="birthday" @change="menu1 = false"></v-date-picker>
          </v-menu>
        </v-col>
      </v-row>

      <!-- ผูกข้อมูลaddress กับ textfield-->
      <v-row>
        <v-col cols="8" sm="15">
          <v-text-field label="ที่อยู่" v-model="patient.address"></v-text-field>
        </v-col>
        <!-- ผูกข้อมูลprovinceId กับ combobox-->
        <v-col class="d-flex" cols="12" sm="2">
          <v-select
            label="จังหวัด"
            v-model="patient.provinceId"
            :items="provinces"
            item-text="province"
            item-value="id"
          ></v-select>
        </v-col>
      </v-row>

      <h1>ข้อมูลทางการแพทย์</h1>
      <v-row>
        <!-- ผูกข้อมูลibloodgroupId กับ combobox-->
        <v-col class="d-flex" cols="12" sm="2">
          <v-select
            label="หมู่เลือด"
            v-model="patient.bloodgroupId"
            :items="bloodgroups"
            item-text="type"
            item-value="id"
          ></v-select>
        </v-col>
        <!-- ผูกข้อมูลdrugallergy กับ textarea-->
        <v-col cols="8" sm="5">
          <v-textarea v-model="patient.drugallergy" block label="การแพ้ยา"></v-textarea>
        </v-col>
        <!-- ผูกข้อมูลcongenitaldesease กับ textarea-->
        <v-col cols="8" sm="5">
          <v-textarea v-model="patient.congenitaldisease" block label="โรคประจำตัว"></v-textarea>
        </v-col>
      </v-row>
      <!-- ปุ่ม click-->
      <v-row justify="center">
        <v-btn class="yellow" @click="savePatients">SAVE</v-btn>
      </v-row>
    </div>
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
      valid: false,
      menu: false,
      modal: false,
      menu2: false,
      birthday: "",
      menu1: false
    };
  },
  methods: {
    /* eslint-disable no-console */
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
      this.submitted = true;
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
