<template>
  <v-container>

    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <h2 class="font-weight-black text-uppercase display-4 text-center">เพิ่มยา</h2>
      </v-flex>
    </v-layout>

    <v-row justify="center">
      <v-col cols="4">
        <v-form v-model="valid" ref="form">
          

            <!--ชื่อยา text field -->
            <v-row justify="center">
              <v-col cols="15">
                <v-text-field block prepend-icon="accessible" label="ชื่อยา" v-model="drug.patientId"    
                :rules="[(v) => !!v || 'กรุณากรอกข้อมูล']" required></v-text-field>
            </v-col>
          </v-row>
            <!-- ชื่อยา text field -->


          <!-- เลือก unit -->
                <v-row>
              <v-col cols>
                <v-select
                  label="ชนิดยา"
                  v-model="drug.unit"
                  :items="Unit_of_medicine"
                  item-text="unit"
                  item-value="id"
                  prepend-icon="access_time"
                  :rules="[(v) => !!v || 'กรุณากรอกข้อมูล']"
                  required
                ></v-select>
              </v-col>
              </v-row>
              <!-- ///////////จบส่วน เลือกunit -->

              <!-- เลือก unit -->
                <v-row>
              <v-col cols>
                <v-select
                  label="ประเภทยา"
                  v-model="drug.drugcategory_id"
                  :items="drugcategorys"
                  item-text="drugcategoryname"
                  item-value="id"
                  prepend-icon="access_time"
                  :rules="[(v) => !!v || 'กรุณากรอกข้อมูล']"
                  required
                ></v-select>
              </v-col>
              </v-row>
              <!-- ///////////จบส่วน เลือก
              
              tegory -->

              <!--วิธีใช้ text field -->
            <v-row justify="center">
              <v-col cols="15">
                <v-text-field block prepend-icon="accessible" label="วิธีใช้" v-model="drug.how"    
                :rules="[(v) => !!v || 'กรุณากรอกข้อมูล']" required></v-text-field>
            </v-col>
          </v-row>
            <!-- วิธีใช้ text field -->

             <!--ผลข้างเคียง text field -->
            <v-row justify="center">
              <v-col cols="15">
                <v-text-field block prepend-icon="accessible" label="ผลข้างเคียง" v-model="drug.how"    
                :rules="[(v) => !!v || 'กรุณากรอกข้อมูล']" required></v-text-field>
            </v-col>
          </v-row>
            <!-- ผลข้างเคียง text field -->

              <!--ราคา text field -->
            <v-row justify="center">
              <v-col cols="15">
                <v-text-field block prepend-icon="accessible" label="ราคา" v-model="drug.price"    
                :rules="[(v) => !!v || 'กรุณากรอกข้อมูล']" required></v-text-field>
            </v-col>
          </v-row>
            <!-- ราคา text field -->

            <!-- เลือก เภสัช -->
                <v-row>
              <v-col cols>
                <v-select
                  label="เภสัชก"
                  v-model="drug.pharmacist_id"
                  :items="pharmacist"
                  item-text="firstname"
                  item-value="id"
                  prepend-icon="access_time"
                  :rules="[(v) => !!v || 'กรุณากรอกข้อมูล']"
                  required
                ></v-select>
              </v-col>
              </v-row>
              <!-- ///////////จบส่วน เลือกเภสัช -->


                <!-- ปุ่ม -->

              <v-row justify="center">
          <v-btn @click="saveDrug" class="mt-2" tile outlined color="green">
            <v-icon left>save</v-icon> บันทึกข้อมูล</v-btn> 
            </v-row>
          

        </v-form>
      </v-col>
    </v-row>
  </v-container>
  
</template>
<script>
import http from "../http-common"
import moment from 'moment'
import Vue from 'vue';


const options = {
  name: '_blank',
  specs: [
    'fullscreen=yes',
    'titlebar=yes',
    'scrollbars=yes'
  ],
  
}
export default {
  name: "drug",
  data() {
    return {
      drug: {
        drugname: "",
        unit: "",
        drugcategory_id: "",
        how: "",
        sideeffect: "",
        price: "",
        pharmacist_id: "",
      }
    };
  },
//-----------------------------------------------------------------------METTODS----------------------------
  methods: {
    /* eslint-disable no-console */
    getDrugCategorys() {
      http
        .get("/drugcategory")
        .then(response => {
          this.drugcategorys  = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    
    getUnit() {    
      http
        .get("/unit_of_medicine")
        .then(response => {
          this.rooms = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    
    getPharmacist() {    // ดึงข้อมูล Duration ใส่ combobox
      http
        .get("/pharmacist")
        .then(response => {
          this.durations = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    // function ค้นหา doctorprofile ด้วย ID
    findDoctorprofiles() {
      http
        .get("/doctorprofile/" + this.drug.doctorprofileId)
        .then(response => {
          console.log(response);
            this.doctorprofileName = response.data.name;
            this.findRooms();
            this.findDurations();
          })
        .catch(e => {
          console.log(e);
        });
    },
    findDurations() {
      http
        .get("/duration/" + this.drug.durationId)
        .then(response => {
          console.log(response);
            this.durationTitle = response.data.time;
            })
        .catch(e => {
          console.log(e);
        });
        
    },

    // function เมื่อกดปุ่ม submit
    /*
    saveDrug() {
      console.log(this.drug);
      http
        .post(
          "/drug/" + this.drug.patientId + "/" + this.drug.doctorprofileId + "/" +this.drug.roomId +
            "/" + this.drug.durationId + "/" + this.date + "/" + this.drug.detail,this.drug 
        )
       .then(response => {
          console.log(response);
          this.saveSC = true; 
          this.saveUSC = false;
          this.print(); //สังเรียก Method พิมพ์ใบนัด
          //this.$router.push("/viewAppm");
          
        })
        .catch(e => {
          console.log(e);
          this.saveUSC = true; this.saveSC = false;

        });

    },
    */
    clear() {
      this.$refs.form.reset();
      this.patientCheck = false;
      this.patientNotFound = false;
      this.saveUSC = false;
      this.saveSC = false;
      this.sheet= false;
    },
 

    
    /* eslint-enable no-console */
    },
  mounted() {
    this.getdDoctorprofiles();
    this.getRooms();
    this.getDurations();
  }
};


 

</script>