<template>
  <v-container>
    <div v-if="saveUSC">
      <v-alert outlined dense text type="warning" prominent border="left">
        <strong>ไม่สามารถบันทึกได้</strong> โปรดตรวจสอบข้อมูลอีกครั้ง
      </v-alert>
    </div>

    <div v-if="saveSC">
      <v-alert dense outlined text prominent type="success">บันทึกข้อมูลสำเร็จ</v-alert>
    </div>


    <div>
      <h1 class="display-2" text--left>ทะเบียนประวัติเภสัชกร</h1>
      <br />
     <v-form v-model="valid" ref="form" >
        <v-col cols="12" md="6">
          <v-text-field label="รหัสบัตรประจำตัวประชาชน" v-model="pharmacist.id_card" counter maxlength="13"
          :rules="[(v) => !!v || 'Item is required']"
                  required></v-text-field>
        </v-col>

        <v-row>
          <v-col cols="12" md="2">
            <v-select
              label="คำนำหน้าชื่อ"
              v-model="pharmacist.titlepharmacistId"
              :items="titlepharmacist"
              item-text="titlepharmacist"
              item-value="id"
              v-on:change="filterGender"
            ></v-select>
          </v-col>

          <v-col cols="12" md="8">
            <v-text-field label="ชื่อ-สกุล" v-model="pharmacist.name"
            :rules="[(v) => !!v || 'Item is required']"
                  required></v-text-field>
          </v-col>
        </v-row>

        <v-row>
          <v-col cols="12" md="2">
            <v-select
              label="เพศ"
              v-model="pharmacist.sexId"
              :items="sex"
              item-text="sex"
              item-value="id"
            ></v-select>
          </v-col>

          <v-col col="12" md="5">
            <v-menu v-model="menu1" :close-on-content-click="false" full-width max-width="290">
              <template v-slot:activator="{ on }">
                <v-text-field
                  :value="computedDateFormattedMomentjs"
                  clearable
                  label="วันเกิด"
                  readonly
                  v-on="on"
                  locale="th"
                ></v-text-field>
              </template>

              <v-date-picker locale="th" v-model="birthday" @change="menu1 = false"></v-date-picker>
            </v-menu>
          </v-col>

          <v-col cols="12" md="2">
            <v-text-field label="อายุ" suffix="ปี" v-model="pharmacist.age"
              :rules="[(v) => !!v || 'Item is required']"
                  required ></v-text-field>
          </v-col>
        </v-row>

        <v-row>
          <v-col cols="12" md="4">
            <v-select
              label="จังหวัด"
              v-model="pharmacist.provinceId"
              :items="province"
              item-text="province"
              item-value="id"
            ></v-select>
          </v-col>

          <v-col cols="12" md="6">
            <v-text-field label="ที่อยู่" v-model="pharmacist.address"
            :rules="[(v) => !!v || 'Item is required']"
                  required></v-text-field>
          </v-col>
             <v-col cols="12" md="3">
            <v-text-field label="โทรศัพท์" v-model="pharmacist.phone"
            :rules="[(v) => !!v || 'Item is required']"
                  required></v-text-field>
          </v-col>
            <v-col cols="12" md="5">
            <v-text-field label="E-mail" v-model="pharmacist.mail"
            :rules="[(v) => !!v || 'Item is required']"
                  required
            ></v-text-field>
          </v-col>

        </v-row>


        <div class="text-center">
          <v-btn
            @click="savePharmacists"
            :class="{red : !valid,green : valid}"
            color="darken-2"
            dark
          >SAVE</v-btn>
        </div>
        </v-form>
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
  name: "pharmacist",
  data() {
    return {
      pharmacist: {
        titlepharmacistId: "",
        sexId: "",
        provinceId: "",
      name: "",
      age: "",
      phone: "",
      mail: "",
      address: "",
      id_card: ""
      },
      valid: false,
      saveSC: false,
      saveUSC: false,

      menu: false,
      birthday: "",
      menu1: false,

      titlepharmacist: [],
      sex: [],
      province: [],
      tempGender: []
    };
  },
  methods: {
    filterGender() {
      this.sex = [];
      this.pharmacist.sexId = "";
      if (
        this.titlepharmacist[this.pharmacist.titlepharmacistId - 1].titlepharmacist === "เภสัชกรชาย"
      ) {
        this.sex.push(this.tempGender[0]);
      } else {
        this.sex.push(this.tempGender[1]);
      }
    },
    /* eslint-disable no-console */

    // ดึงข้อมูล Title ใส่ combobox
    getTitlePharmacist() {
      http
        .get("/titlepharmacist")
        .then(response => {
          this.titlepharmacist = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    // ดึงข้อมูล Sex ใส่ combobox
    getSex() {
      http
        .get("/sex")
        .then(response => {
          this.tempGender = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    
    // ดึงข้อมูล Province ใส่ combobox
    getProvince() {
      http
        .get("/province")
        .then(response => {
          this.province = response.data;
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

    // function เมื่อกดปุ่ม save
    savePharmacists() {
      console.log(this.Pharmacists);
      http
        .post(
          "/pharmacist/" +
            this.pharmacist.id_card +
            "/" +
            this.pharmacist.titlepharmacistId +
            "/" +
            this.pharmacist.name +
            "/" +
            this.pharmacist.sexId +
            "/" +
            this.birthday +
            "/" +
            this.pharmacist.age +
            "/" +
            this.pharmacist.provinceId +
            "/" + 
            this.pharmacist.address +
            "/" +
            this.pharmacist.phone +
            "/" +
            this.pharmacist.mail
             ,this.pharmacist
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
    this.getTitlePharmacist();
    this.getSex();
    this.getProvince();
  }
};
</script>