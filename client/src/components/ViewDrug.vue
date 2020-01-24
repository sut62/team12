<template>
  <v-container>

  <v-container>
            <div v-if="drugFound"> 
                <v-alert  outlined dense text type="warning" prominent border="left" > <strong>ไม่พบข้อมูลยานี้</strong> โปรดตรวจสอบข้อมูลอีกครั้ง </v-alert>
            </div>

    </v-container>


    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <h1 class="display-2 font-weight-bold mb-3">ระบบเรียกดูข้อมูลยา</h1>
      </v-flex>
    </v-layout>

    <v-row justify="center">
      <v-col cols="4">
        <v-form v-model="valid" ref="form">
          <v-row justify="center">
            <v-col cols="10">


              <v-text-field
                outlined
                label="กรอกชื่อยา"
                v-model="drug.drugname_s"
                :rules="[(v) => !!v || 'Item is required']"
                required
              ></v-text-field>

            </v-col>
            <v-col cols="2">
              <div class="my-2">
                <v-btn @click="findDrug" depressed large color="primary">ค้นหา</v-btn>
              </div>
            </v-col>
          </v-row>

          <div v-if="drugCheck">

            <v-row justify="center">
              <v-col cols="12">
                  <v-text-field block  prepend-icon="sort" label="ID ยา" v-model="drug.drug_id"></v-text-field>
              </v-col>
            </v-row>


            <v-row justify="center">
              <v-col cols="12">
                  <v-text-field block prepend-icon="text_format" label="ชื่อยา" v-model="drug.drugname"></v-text-field>
              </v-col>
            </v-row>


            <v-row justify="center">
              <v-col cols="12">
                  <v-text-field block prepend-icon="view_module" label="ชนิดยา" v-model="drug.unit"></v-text-field>
              </v-col>
            </v-row>


            <v-row justify="center">
              <v-col cols="12">
                <v-text-field block prepend-icon="view_quilt" label="ประเภทยา" v-model="drug.category"></v-text-field>
              </v-col>
            </v-row>

            <v-row justify="center">
              <v-col cols="12">
                  <v-text-field block prepend-icon="check" label="วิธีใช้" v-model="drug.how"></v-text-field>
              </v-col>
            </v-row>

            <v-row justify="center">
              <v-col cols="12">
                <v-text-field block prepend-icon="error_outline" label="ผลข้างเคียง" v-model="drug.sideeffect"></v-text-field>
              </v-col>
            </v-row> 

            <v-row justify="center">
              <v-col cols="12">
                <v-text-field block prepend-icon="local_offer" label="ราคา" v-model="drug.price"></v-text-field>
              </v-col>
            </v-row> 

            <v-row justify="center">
              <v-col cols="12">
                <v-text-field block prepend-icon="person_add" label="ผู้เพิ่มยา" v-model="drug.pharmacist"></v-text-field>
              </v-col>
            </v-row>

            <v-row justify="center">
              <v-col cols="12">
                <v-btn style="margin-left: 15px;" @click="clear">ค้นหายาอื่น</v-btn>
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
  name: "ViewDrug",
  data() {
    return {
      drug: {
        drugname_s:"",
        drug_id:"",
        drugname: "",
        unit: "",
        category: "",
        how: "",
        sideeffect: "",
        price: "",
        pharmacist: "",
      },
      valid: false,
      drugCheck: false,
      drugFound: false,
    };
  },
  methods: {
    /* eslint-disable no-console */
    findDrug() {
          console.log(this.drug.drugname_s);
            http
                .get("/drug/drugname/" + this.drug.drugname_s)
                .then(response => {
                    console.log(response);
                    if (response.data[0] != null) {
                        this.drug.drug_id = response.data[0].id;
                        this.drug.drugname = response.data[0].drugname;
                        this.drug.category = response.data[0].category.category;
                        this.drug.unit = response.data[0].unit.unit;
                        this.drug.how = response.data[0].how;
                        this.drug.sideeffect = response.data[0].sef;
                        this.drug.price = response.data[0].price;
                        this.drug.pharmacist = response.data[0].pharmacist.titlePharmacist.titlepharmacist + response.data[0].pharmacist.name;
                        this.drugCheck = response.status;
                        this.drugFound = false;

                        console.log(this.drug.drug_id);
                        console.log(this.drug.drugname);
                        console.log(this.drug.cetagory);
                        console.log(this.drug.unit);
                        console.log(this.drug.how);
                        console.log(this.drug.sideeffect);
                        console.log(this.drug.price);
                        console.log(this.drug.pharmacist);

                    } else {
                        this.clear();
                        this.drugFound = true;
     
                    }
                })
                .catch(e => {
                    console.log(e);
                });
            this.submitted = true;
  
  },
  clear() {
      this.$refs.form.reset();
      this.drugCheck = false;
      this.drugFound = false;
    },




  },

};
</script>
