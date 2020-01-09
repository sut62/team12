<template>
  <v-container>
    <div v-if="patientNotFound">
    <v-alert dense outlined type="error">
      <strong>ไม่พบข้อมูลผู้ป่วย</strong> กรุณากรอกข้อมูลอีกครั้ง
    </v-alert>
    </div>

    

    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <h2 class="font-weight-black text-uppercase display-4 text-center">ออกใบนัด</h2>
      </v-flex>
    </v-layout>

    <v-row justify="center">
      <v-col cols="4">
        <v-form v-model="valid" ref="form">
          <v-row justify="center">
            <!-- Patient ID text field -->
            <v-col cols="10">
              <v-text-field 
                block 
                prepend-icon="accessible" 
                label="รหัสผู้ป่วย"
                 v-model="appointments.patientId"    
                :rules="[(v) => !!v || 'กรุณากรอกข้อมูล']"
                required
                >
                </v-text-field>
             

              <p v-if="patientCheck != ''">ชื่อผู้ป่วย : {{patientFName}} {{patientLName}}</p>
              
              

            </v-col>
            <!-- ///////////Patient ID text field -->

            <!-- Search BT -->
            <v-col cols="2">
              <div class="my-2">
                <v-btn color="blue-grey darken-1" dark @click="findPatients" >ค้นหา </v-btn>
              </div>
            </v-col>
            <!-- ///////////End Search BT -->

          </v-row>

          <div v-if="patientCheck">
              <!-- เลือกแพทย์ Combobox -->
          <v-row justify="center">
            <v-col>
                <v-select
                  label="แพทย์ผู้ออกใบนัด"
                  v-model="appointments.doctorprofileId"
                  :items="doctorProfiles"
                  item-text="name"
                  item-value="id"
                  prepend-icon="supervised_user_circle"
                  :rules="[(v) => !!v || 'กรุณากรอกข้อมูล']"
                  required
                ></v-select>
              </v-col>
               </v-row> 
          <!-- ///////////จบส่วน เลือกแพทย์ Combobox -->

          <!-- เลือกช่วงเวลา Combobox -->
                <v-row>
              <v-col cols>
                <v-select
                  label="เวลา"
                  v-model="appointments.durationId"
                  :items="durations"
                  item-text="time"
                  item-value="id"
                  prepend-icon="access_time"
                  :rules="[(v) => !!v || 'กรุณากรอกข้อมูล']"
                  required
                ></v-select>
              </v-col>
              </v-row>
              <!-- ///////////จบส่วน เลือกช่วงเวลา Combobox -->

        <!-- เลือกวันนัด -->
        <v-row>
        <v-col >
        <v-menu
          v-model="menu1"
          :close-on-content-click="false"
          full-width
          max-width="290"
        >
          <template v-slot:activator="{ on }">
            <v-text-field
              :value="computedDateFormattedMomentjs"
              clearable
              label="เลือกวันนัด"
              readonly
              prepend-icon="event"
              v-on="on"
              locale="th"
            ></v-text-field>
          </template>
          <v-date-picker
          locale="th"
            v-model="date"
            @change="menu1 = false"
          ></v-date-picker>
        </v-menu>
      </v-col>
      </v-row>
        <!-- ///////////////////////////////////////////// จบส่วนเลือกวันนัด -->

            <!-- เลือกห้องตรวจ Combobox -->
            <v-row>
              <v-col>
                <v-select
                  label="ห้องตรวจ"
                  v-model="appointments.roomId"
                  :items="rooms"
                  item-text="room"
                  item-value="id"
                  prepend-icon="room"
                  :rules="[(v) => !!v || 'กรุณากรอกข้อมูล']"
                  required
                ></v-select>
              </v-col>
            </v-row>
            <!-- ///////////จบส่วนเลือกห้องตรวจ Combobox -->

            <!-- รายละเอียด Text area -->
            <v-row>
              <v-col>
                  <v-textarea block prepend-icon="comment" label="รายละเอียด" v-model="appointments.detail"></v-textarea>
              </v-col>
            </v-row>
            <!-- ///////////จบส่วน รายละเอียด Text area -->

           <!--ปุ่ม Clear -->
            <v-row justify="center">
                
                <!-- <v-btn class="mt-6" style="margin-left: 15px;" @click="clear">ล้างข้อมูล</v-btn> -->
                <div class="text-center">
                  <!--สรุปรายละเอียดการนัด Button Sheet -->
    <v-bottom-sheet v-model="sheet">
      <template v-slot:activator="{ on }">
        <v-btn prepend-icon="check_box" :class="{ red: !valid, green: valid } " dark v-on="on" @click="findDoctorprofiles">สรุปรายละเอียด</v-btn>
  
      </template>
  
      <v-sheet class="text-center" height="750px">
        <v-container>
            <div v-if="saveUSC"> <v-alert  outlined dense text type="warning" prominent border="left" > <strong>ไม่สามารถบันทึกได้</strong> โปรดตรวจสอบข้อมูลอีกครั้ง </v-alert>
            </div>

            <div v-if="saveSC">
              <v-alert @change="saveUSC = false" dense outlined text prominent type="success" > <strong>บันทึกข้อมูลเรียบร้อย</strong></v-alert>
            </div>
            </v-container>
        <div id="printMe">
          <v-container>
            <v-row justify="center">
             <v-col justify="center" cols="6">
            <v-form v-model="valid" ref="form" >
            <!-- SOURCE -->
            
            


          <h1>รายละเอียดการออกใบนัด</h1> <br> 

       <v-row>
          <v-col cols="10">
              <p class="text-left"><strong>แพทย์ผู้ออกใบนัด :</strong> {{doctorprofileName}} </p> 
            </v-col>
          </v-row>  
       <v-row>
            <v-col cols="5">
              <p class="text-left"><strong>รหัสผู้ป่วย :</strong> {{this.appointments.patientId}}</p>
            </v-col>
            <v-col>
              <p class="text-left" ><strong>ชื่อผู้ป่วย :</strong> คุณ{{patientFName}} {{patientLName}}</p>
            </v-col>
        </v-row> 
        <v-row>
            <v-col cols="5">
              <p class="text-left"><strong>วันที่นัด :</strong> {{date}}</p>
            </v-col>
            <v-col cols="5">
              <p class="text-left"><strong>เวลานัด :</strong> {{durationTitle}}</p>
            </v-col>
        </v-row>
        <v-row>
          <v-col cols="5">
              <p class="text-left"><strong>ห้อง :</strong> {{roomTitle}}</p>
            </v-col>
          </v-row> 
       <v-row>
          <v-col cols="5">
              <p class="text-left"><strong>รายละเอียด :</strong> {{this.appointments.detail}}</p>
            </v-col>
          </v-row> 

    </v-form>
     </v-col>
    </v-row>
     </v-container>
    </div>
  <!-- OUTPUT -->
         <v-row justify="center">
          <v-btn @click="saveAppointments" class="mt-2" tile outlined color="green">
            <v-icon left>save</v-icon> บันทึกข้อมูลและพิมพ์</v-btn> 
         </v-row>
      </v-sheet>
    </v-bottom-sheet>
  </div>
            </v-row>
            <br />
          </div>
        </v-form>
      </v-col>
    </v-row>
  </v-container>
  
</template>

<script>
import http from "../http-common"
import moment from 'moment'
import Vue from 'vue';
import VueHtmlToPaper from 'vue-html-to-paper'

const options = {
  name: '_blank',
  specs: [
    'fullscreen=yes',
    'titlebar=yes',
    'scrollbars=yes'
  ],
  
}
 
Vue.use(VueHtmlToPaper, options);

export default {

  computed: {
      computedDateFormattedMomentjs () {
        return this.date ? moment(this.date).format('dddd Do, MMMM YYYY') : ''
      },
    }, 
    
  name: "appointments",
  data() {
    return {
      appointments: {
        patientId: "",
        doctorprofileId: "",
        roomId: "",
        durationId: "",
        detail: "",
      },
      sheet: false,
      valid: false,
      patientCheck: false,
      patientNotFound: false,
      saveSC: false,
      saveUSC: false,

      patientFName: "",
       patientLName: "",
      doctorprofileName: "",
      roomTitle:"",
      durationTitle:"",
      detailPrint:"-",
      output: null,

      // ส่วนของ DatePicker
      menu: false,
      modal: false,
      menu2: false,
      date : "",
      menu1: false,
    };
  },
//-----------------------------------------------------------------------METTODS----------------------------
  methods: {
     //ปริ้น
    print() {
      this.$htmlToPaper('printMe');
    },
    /* eslint-disable no-console */
    // ดึงข้อมูล doctorprofile ใส่ combobox
    getdDoctorprofiles() {
      http
        .get("/doctorprofile")
        .then(response => {
          this.doctorProfiles = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    // ดึงข้อมูล Room ใส่ combobox
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
    },
    // ดึงข้อมูล Duration ใส่ combobox
    getDurations() {
      http
        .get("/duration")
        .then(response => {
          this.durations = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    // function ค้นหา Patient ด้วย ID
    findPatients() {
      http
        .get("/patient/" + this.appointments.patientId)
        .then(response => {
          console.log(response);
          if (response.data != null) {
            this.patientFName = response.data.firstname;
            this.patientLName = response.data.lastname;
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
    // function ค้นหา doctorprofile ด้วย ID
    findDoctorprofiles() {
      http
        .get("/doctorprofile/" + this.appointments.doctorprofileId)
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
        .get("/duration/" + this.appointments.durationId)
        .then(response => {
          console.log(response);
            this.durationTitle = response.data.time;
            })
        .catch(e => {
          console.log(e);
        });
        
    },
    findRooms() {
      http
        .get("/room/" + this.appointments.roomId)
        .then(response => {
          console.log(response);
            this.roomTitle = response.data.room;
          })
        .catch(e => {
          console.log(e);
        });
    },
    // function เมื่อกดปุ่ม submit
    saveAppointments() {
      console.log(this.appointments);
      http
        .post(
          "/appointments/" + this.appointments.patientId + "/" + this.appointments.doctorprofileId + "/" +this.appointments.roomId +
            "/" + this.appointments.durationId + "/" + this.date + "/" + this.appointments.detail,this.appointments 
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
      this.submitted = true;
    },
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
