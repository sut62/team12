<template>
    <v-container>
        <div class="text-center">
            <h1 class="display-2" text--left>ระบบจ่ายยา</h1>
            <br />
        </div> 

        <v-row>
          <v-col md="6">
            <v-card  max-width="500">
              <v-row>
                &ensp;
                <v-col cols="10" md="4">
                    <v-text-field label="ID ผู้ป่วย" outlined></v-text-field>
                </v-col>
                
                <v-col cols="2">
                    <div class="my-2">
                        <v-btn color="blue-grey darken-1" dark >Serach</v-btn>
                    </div>
                </v-col>
              </v-row>

                <!-- combo box ช่องจ่ายยา -->
                <v-col  cols="12" md="6">
                    <v-select
                    label="ช่องจ่ายยา"
                    v-model="DrugDispense.drugdispensechannelId"
                    :items="drugdispensechannels"
                    item-text="drugdispensechannel"
                    item-value="id"
                    ></v-select>
                </v-col>
                <!------------------------->

                <!-- combo box คำนำหน้าชื่อ -->
                <v-col cols="12" md="7">
                    <v-select
                    label="คำนำหน้าชื่อ"
                    v-model="DrugDispense.titlepharmacistId"
                    :items="titlepharmacists"
                    item-text="titlepharmacist"
                    item-value="id"
                    
                    ></v-select>
                </v-col>
                <!------------------------->

                <!-- combobox ชื่อ-สกุล -->
                <v-col cols="12" md="9">
                    <v-select
                    label="ชื่อ-สกุล"
                    v-model="DrugDispense.pharmacistId"
                    :items="pharmacists"
                    item-text="name"
                    item-value="id"
                    
                    ></v-select>
                </v-col>
                <!---------------------->
            </v-card>
                <br/>
            
              <!-- ปุ่ม SAVE -->
              <div class="text-center">
                <v-btn 
                  rounded 
                  @click="saveDrugDispenses"  
                  dark
                  >SAVE</v-btn>
              </div>
              <!------------->    
          </v-col>

          <!-- ตาราง -->
          <v-col>
            <v-row >
              <v-col cols="12">
                <v-data-table 
                :headers="headers"
                :items="items" 
                :items-per-page="5" 
                class="elevation-1">
                </v-data-table>
              </v-col>
            </v-row>
          </v-col>
          <!----------->

        </v-row>
    </v-container>
</template>

<script>
import http from "../http-common";

export default {
  name: "DrugDispense",
  data() {
    return {
      DrugDispense: {
        drugdispensechannelId: "",
        titlepharmacistId:"",
        pharmacistId:"",
        
      },
      items:[],
      drugdispensechannels:[],
      titlepharmacists:[],
      pharmacists:[],
      saveSC: false,
      saveUSC: false,

      menu: false,
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

    // ดึงข้อมูล DrugDispenseChannel ใส่ combobox
    getDrugDispenseChannels() {
        http
            .get("/drugdispensechannel")
            .then(response => {
            this.drugdispensechannels = response.data;
            console.log(response.data);
            })
            .catch(e => {
            console.log(e);
            });
    },

    getPharmacists() {   
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
    getTitlePharmacists() {   
      http
        .get("/titlepharmacist")
        .then(response => {
          this.titlepharmacists = response.data;
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
    saveDrugDispenses() {
      http
        .post(
          "/drugdispense/" +
            this.DrugDispense.drugdispensechannelId 
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
    this.getDrugDispenseChannels();
    this.getPharmacists();
    this.getTitlePharmacists();
  }
};

</script>
