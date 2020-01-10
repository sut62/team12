<template>
    <v-container >
      <div  class = "text-center"><h1 class="display-2">ระบบกรอกข้อมูลฉลากยา</h1></div><br><br>
      <div v-if="doctorNotFound">
          <v-alert dense outlined type="error">
            <strong>ไม่พบข้อมูลแพทย์</strong>กรุณากรอกข้อมูลอีกครั้ง
          </v-alert>
        </div>
      <div v-if="saveUSC">
          <v-alert outlined dense text type="warning" prominent border="left">
            <strong>ไม่สามารถบันทึกได้</strong> โปรดตรวจสอบข้อมูลอีกครั้ง
          </v-alert>
      </div>
      <div v-if="saveSC">
          <v-alert dense outlined text prominent type="success">บันทึกข้อมูลสำเร็จ</v-alert>
      </div>
      
      
        <v-row >  
           
           <v-col md="4"  >
              
                <v-row>
                    <v-text-field 
                        v-model="medicine.PrescriptionId" 
                        label="ค้นหารหัสใบสั่งยา" 
                        :rules="[(v) => !!v || 'กรุณากรอกข้อมูล']" 
                        required > 
                    </v-text-field>
                    <v-btn 
                        class="ma-2" 
                        small color="primary" dark
                        @click="findPrescription">ค้นหา</v-btn>
                </v-row>

                <v-row >
                      <v-col cols="8">
                      <v-text-field v-if="PrescriptionCheck != ''"
                          v-model="medicine.PrescriptionId" 
                          label=" ชื่อ-สกุล"
                          disabled>
                          {{name}}   
                      </v-text-field>
                      </v-col>
                </v-row>
              
          </v-col> 
          <v-row>
             <v-card  class="mx-auto" color="white" width="700px" outlined>
                <v-col md="10" >
                      <v-row>
                        <v-text-field   
                                v-model="medicine.DrugId" 
                                label="ค้นหา ID ยา" 
                                :rules="[(v) => !!v || 'กรุณากรอกข้อมูล']" 
                               required > 
                      </v-text-field>
                       <v-btn 
                            class="ma-2" 
                            small color="primary" dark
                            @click="findDoctorProfile">ค้นหา</v-btn>
                      </v-row>
                  <v-row>
                                    
                    <v-col cols="3">
                      <v-textarea 
                           v-model="quantity"
                          auto-grow
                          outlined
                          rows="1"
                          label="ป้อนจำนวน">
                      </v-textarea>
                      </v-col>
                      <v-select 
                          v-model="medicine.medicineQuantityId"  
                          :items="medicineQuantitys"
                          item-text="medicineQuantity"
                          item-value="id"  
                          label="โปรดเลือกหน่วยการรับประทานยา" 
                          :rules = "[(v) => !!v || 'กรุณากรอกข้อมูล']"
                          required >
                      </v-select>
                  </v-row>
                      <v-select 
                          v-model="medicine.medicineFrequencyId" 
                         :items="medicineFrequencys"
                          item-text="medicineFrequency"
                          item-value="id"  
                          label="โปรเลือกความถี่การรับประทานยา"
                          :rules = "[(v) => !!v || 'กรุณากรอกข้อมูล']"
                          required >
                      </v-select>
            
                     <v-select 
                          v-model="medicine.medicineDurationId" 
                          :items="medicineDurations" 
                          item-text="medicineDuration" 
                          item-value="id"  
                          label="โปรดเลือกช่วงเวลา"
                          :rules = "[(v) => !!v || 'กรุณากรอกข้อมูล']"
                          required >
                    </v-select> 


                    <v-bottom-sheet v-model="sheet">
                        <template v-slot:activator="{ on }">
                        <v-row  justify="center" >
                        <v-btn   color="primary"  @click="saveMedicine">บันทึกข้อมูล</v-btn>
                      </v-row>
                  </template>
                      <v-sheet class="text-center" height="750px">
                        <div><br>
                          <h2>ข้อมูลฉลากยา</h2>
                        </div>
                       
                        <div>
                            ชื่อ-สกุล <br>
                            ชื่อยา <br>
                            วิธีใช้ <br>
                            คำเตือน <br>
                        </div>
                      </v-sheet>
                </v-bottom-sheet>
              </v-col>
             </v-card>
          </v-row> 
               
        </v-row>
                
    </v-container>
</template>

<script>
import http from "../http-common"
  export default {
    name:"medicine",
    data ()  {
      return{ 
        medicine: {
          medicineQuantityId:"",
          medicineFrequencyId:"",
          medicineDurationId:"",
          PrescriptionId:"",
          DrugId:"",
           //คลาสที่เอามาเก็บตัวแปร
          
        },
        valid: false,
        sheet: false,
        PrescriptionCheck:false,
        saveSC:false,
        saveUSC:false,
        doctorName:"",
        doctorNotFound:false,
        quantity:"",
        medicineQuantitys:[],
        medicineFrequencys:[],
        medicineDurations:[],

      items: [] 
      };
    },
  
    methods:{
    /* eslint-disable no-console */
  
      // ดึงข้อมูล Room ใส่ combobox
      getMedicineDurations() {
      http
        .get("/duration")
        .then(response => {
          this.medicineDurations = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
        },
      
      // ดึงข้อมูล Day ใส่ combobox
      getMedicineQuantitys() {
      http
        .get("/quantity")
        .then(response => {
          this.medicineQuantitys = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
      },
      
      // ดึงข้อมูล Room ใส่ combobox
      getMedicineFrequencys() {
      http
        .get("/frequency")
        .then(response => {
          this.medicineFrequencys = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
      }, 
      
      // ค้นหารหัสใบสั่งยา
      findPrescription() {
      http
        .get("/Prescription/" + this.schedule.PrescriptionId)
        .then(response => {
          console.log(response);
          if (response.data != null) {
            this.doctorName = response.data.name;
            console.log(this.doctorName);
            this.PrescriptionCheck = response.status;
            this.doctorNotFound = false;
          } else {
            this.clear()
            this.doctorNotFound = true;  
          }          
        })
        .catch(e => {
          console.log(e);
        });
      this.submitted = true;
      },

      // ค้นหาชื่อยา
      findDrug() {
      http
        .get("/drug/" + this.schedule.DrugId)
        .then(response => {
          console.log(response);
          if (response.data != null) {
            this.doctorName = response.data.name;
            console.log(this.doctorName);
            this.PrescriptionCheck = response.status;
            this.doctorNotFound = false;
          } else {
            this.clear()
            this.doctorNotFound = true;  
          }          
        })
        .catch(e => {
          console.log(e);
        });
      this.submitted = true;
      },
      
      // function เมื่อกดปุ่ม submit
      saveMedicine() {
      http
        .post(
          "/MedicineLabel/" + this.medicine.PrescriptionId +
           "/" +
            this.medicine.DrugId +
            "/" +
            this.quantity+
            "/"+
            this.medicine.medicineQuantityId+
            "/" +
            this.medicine.medicineFrequencyId+
            "/" +
            this.medicine.PrescriptionId
        
        )
        .then(response => {
          console.log(response);
          this.getSchedules();
          
          this.saveUSC = false;
          this.saveSC = true;
        })
        
        .catch(e => {
          console.log(e);
          this.saveUSC = true;
          this.saveSC = false;
        });
      this.submitted = true;
    },
    clear() {
      this.PrescriptionCheck = false;
      this.saveSC = false;
      this.saveUSC = false;
      this.doctorNotFound = false;
     
    },
     
     /* eslint-enable no-console */
    },
    mounted(){
     
      this.getMedicineFrequencys();
      this.getMedicineQuantitys();
      this.getMedicineDurations();
    }
  
  };
</script>