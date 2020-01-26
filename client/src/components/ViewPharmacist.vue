<template>
  <v-container>

  <v-container>
            <div v-if="PharmacistFound"> 
                <v-alert  outlined dense text type="warning" prominent border="left" > <strong>ไม่พบข้อมูลพนักงาน</strong> กรุณาตรวจสอบข้อมูลอีกครั้ง </v-alert>
            </div>

    </v-container>
  
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <h1 class="display-2 font-weight-bold mb-3">ระบบแสดงข้อมูลเภสัชกร</h1>
      </v-flex>
    </v-layout>

    <v-row justify="center">
      <v-col cols="4">
        <v-form v-model="valid" ref="form">
          <v-row justify="center">
            <v-col cols="10">

              <v-text-field
                outlined
                label="กรอกหมายเลขพนักงาน"
                v-model="pharmacist.pharmacist"
                :rules="[(v) => !!v || 'Item is required']"
                required
              ></v-text-field>

          </v-col>
            <v-col cols="2">
              <div class="my-2">
                <v-btn @click="findPharmacist" depressed large color="primary">ค้นหา</v-btn>
              </div>
            </v-col>
          </v-row>

          <div v-if="PharmacistCheck"> 

             <v-col cols="12">
                  <v-text-field block  prepend-icon="sort" label="รหัสบัตรประจำตัวประชาชน" v-model="pharmacist.id_card"></v-text-field>
              </v-col>

              <v-col cols="12">
                  <v-text-field block prepend-icon="text_format" label="ชื่อ-สกุล" v-model="pharmacist.name"></v-text-field>
              </v-col>

            <v-row justify="center">
              <v-col cols="12">
                <v-text-field block prepend-icon="view_quilt" label="วันเกิด" v-model="pharmacist.birthday"></v-text-field>
              </v-col>
            </v-row>

            <v-row justify="center">
              <v-col cols="12">
                  <v-text-field block prepend-icon="check" label="อายุ" v-model="pharmacist.age"></v-text-field>
              </v-col>
            </v-row>

           <v-row justify="center">
              <v-col cols="12">
                <v-text-field block prepend-icon="local_offer" label="ที่อยู่" v-model="pharmacist.address"></v-text-field>
              </v-col>
            </v-row> 

            <v-row justify="center">
              <v-col cols="12">
                <v-text-field block prepend-icon="person_add" label="โทรศัพท์" v-model="pharmacist.phone"></v-text-field>
              </v-col>
            </v-row>

            <v-row justify="center">
              <v-col cols="12">
                <v-text-field block prepend-icon="person_add" label="E-mail" v-model="pharmacist.mail"></v-text-field>
              </v-col>
            </v-row>

            <v-row justify="center">
              <v-col cols="12">
                <v-btn style="margin-left: 15px;" @click="clear">Refresh</v-btn>
              </v-col>
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
  name: "ViewPharmacist",
  data() {
    return {
      pharmacist: {
      name: "",
      age: "",
      phone: "",
      mail: "",
      address: "",
      birthday: "",
      id_card: ""
      },
      valid: false,
      PharmacistFound: false,
      PharmacistCheck: false,
     
      
    };
  },
  methods: {
    
    /* eslint-disable no-console */
    findPharmacist() {
          console.log(this.pharmacist.pharmacist);
            http
                .get("/pharmacist/" + this.pharmacist.pharmacist)
                .then(response => {
                    console.log(response);
                    if (response.data != null) {
                        this.pharmacist.id_card = response.data.id_card;
                        this.pharmacist.name = response.data.name;
                        this.pharmacist.birthday = response.data.birthday;
                        this.pharmacist.age = response.data.age;
                        this.pharmacist.address = response.data.address;
                        this.pharmacist.phone = response.data.phone;
                        this.pharmacist.mail = response.data.mail;
                        this.PharmacistCheck = response.status;
                        this.PharmacistFound = false;
                        
                        console.log(this.pharmacist.id_card);
                        console.log(this.pharmacist.titlePharmacist);
                        console.log(this.pharmacist.name);
                        console.log(this.pharmacist.sex);
                        console.log(this.pharmacist.birthday);
                        console.log(this.pharmacist.age);
                        console.log(this.pharmacist.address);
                        console.log(this.pharmacist.province);
                        console.log(this.pharmacist.phone);
                        console.log(this.pharmacist.mail);

                    } else {
                        this.clear();
                        this.PharmacistFound = true;
     
                    }
                })
                .catch(e => {
                    console.log(e);
                });
            this.submitted = true;
  
  },
  clear() {
      this.$refs.form.reset();
      this.PharmacistCheck = false;
      this.pharmacistFound = false;
    },
   

  },

};
</script>
