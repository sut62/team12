<template>
  <v-container>

   <v-container>
            <div v-if="saveUSC"> <v-alert  outlined dense text type="warning" prominent border="left" > <strong>ไม่สามารถบันทึกได้</strong> โปรดตรวจสอบข้อมูลอีกครั้ง </v-alert>
            </div>

            <div v-if="saveSC">
              <v-alert @change="saveUSC = false" dense outlined text prominent type="success" > <strong>บันทึกข้อมูลเรียบร้อย</strong></v-alert>
            </div>
    </v-container>

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
                <v-text-field block prepend-icon="sort" label="ชื่อยา" v-model="drug.drugName"    
                :rules="[(v) => !!v || 'กรุณากรอกข้อมูล']" required></v-text-field>
            </v-col>
          </v-row>
            <!-- ชื่อยา text field -->


          <!-- เลือก unit -->
                <v-row>
              <v-col cols>
                <v-select
                  label="ชนิดยา"
                  v-model="drug.unit_id"
                  :items="units"
                  item-text="unit"
                  item-value="id"
                  prepend-icon="view_module"
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
                  :items="categorynames"
                  item-text="category"
                  item-value="id"
                  prepend-icon="view_quilt"
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
                <v-text-field block prepend-icon="check" label="วิธีใช้" v-model="drug.how"    
                :rules="[(v) => !!v || 'กรุณากรอกข้อมูล']" required></v-text-field>
            </v-col>
          </v-row>
            <!-- วิธีใช้ text field -->

             <!--ผลข้างเคียง text field -->
            <v-row justify="center">
              <v-col cols="15">
                <v-text-field block prepend-icon="error_outline" label="ผลข้างเคียง" v-model="drug.sideeffect"    
                :rules="[(v) => !!v || 'กรุณากรอกข้อมูล']" required></v-text-field>
            </v-col>
          </v-row>
            <!-- ผลข้างเคียง text field -->

              <!--ราคา text field -->
            <v-row justify="center">
              <v-col cols="15">
                <v-text-field block prepend-icon="local_offer" label="ราคา" v-model="drug.price"    
                :rules="[(v) => !!v || 'กรุณากรอกข้อมูล']" required></v-text-field>
            </v-col>
          </v-row>
            <!-- ราคา text field -->

            <!-- เลือก เภสัช -->
                <v-row>
              <v-col cols>
                <v-select
                  label="เภสัชกร"
                  v-model="drug.pharmacist_id"
                  :items="pharmacists"
                  item-text="name"
                  item-value="id"
                  prepend-icon="person_add"
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
//import moment from 'moment'
//import Vue from 'vue';

export default {
  name: "drug",
  data() {
    return {
      drug: {
        drugName: "",
        unit_id: "",
        drugcategory_id: "",
        how: "",
        sideeffect: "",
        price: "",
        pharmacist_id: "",

      },
      units: [],
      categorynames: [],
      pharmacists: [],

      saveSC: false,
      saveUSC: false,
    };
  },
//-----------------------------------------------------------------------METTODS----------------------------
  methods: {
    /* eslint-disable no-console */
    getUnit() {    
      http
        .get("/unit_of_medicine")
        .then(response => {
          this.units = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    /* eslint-disable no-console */
    getDrugCategorys() {
      http
        .get("/drugcategory")
        .then(response => {
          this.categorynames  = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    /* eslint-disable no-console */
    getPharmacist() {   
      http
        .get("/pharmacist")
        .then(response => {
          this.pharmacists = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    saveDrug() {
      console.log(this.drug);
      http
        .post(
          "/drug/" + this.drug.drugName + "/" + this.drug.drugcategory_id + "/" +this.drug.unit_id +
            "/" + this.drug.how + "/" + this.drug.sideeffect + "/" + this.drug.price + "/" + this.drug.pharmacist_id,this.drug 
        )
       .then(response => {
          console.log(response);
          this.saveSC = true; 
          this.saveUSC = false;
        })
        .catch(e => {
          console.log(e);
          this.saveUSC = true; this.saveSC = false;
        });

    },
    /* eslint-enable no-console */
    },
  mounted() {
    this.getDrugCategorys();
    this.getUnit();
    this.getPharmacist();
  }
};


 

</script>