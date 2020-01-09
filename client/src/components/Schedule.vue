<template>
  <v-container >
    <div v-if="doctorNotFound">
      <v-alert dense outlined type="error">
        <strong>ไม่พบข้อมูลแพทย์</strong> กรุณากรอกข้อมูลอีกครั้ง
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
                v-model="schedule.doctorProfileId" 
                label="DOCTOR ID" 
                :rules="[(v) => !!v || 'กรุณากรอกข้อมูล']" 
                required > 
              </v-text-field>
              <v-btn 
                class="ma-2" 
                small color="primary" dark
                @click="findDoctorProfile"
                >
                Search
              </v-btn>
            </v-row>
            <v-row>
              <div v-if="doctorCheck != ''">
                DOCTOR NAME :
                  {{doctorName}}    
              </div>
            </v-row>
            
            <v-select 
              v-model="schedule.roomId"  
              :items="rooms"
              item-text="room"
              item-value="id"  
              label="โปรดเลือกห้องตรวจ" 
              :rules = "[(v) => !!v || 'กรุณากรอกข้อมูล']"
              required >
            </v-select>
            
            <v-select 
              v-model="schedule.dayId" 
              :items="days"
              item-text="day"
              item-value="id"  
              label="โปรดเลือกวัน"
              :rules = "[(v) => !!v || 'กรุณากรอกข้อมูล']"
              required >
            </v-select>
            
            <v-select 
              v-model="schedule.periodTimeId" 
              :items="periodTimes" 
              item-text="periodTime" 
              item-value="id"  
              label="โปรดเลือกเวลา"
              :rules = "[(v) => !!v || 'กรุณากรอกข้อมูล']"
              required >
            </v-select> 
            
            <v-row justify="center">  
              <v-btn 
                class="mr-4" 
                color="primary"
                @click="saveSchedule" 
                >
                ADD
              </v-btn>
              
            </v-row >
        </v-col> 
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
import http from "../http-common"
  export default {
    name:"schedule",
    data ()  {
      return{ 
        schedule: {
          doctorProfileId:"",
          roomId:"",
          dayId:"",
          periodTimeId:"", //คลาสที่เอามาเก็บตัวแปร
          
        },
        valid: false,
        doctorCheck:false,
        saveSC:false,
        saveUSC:false,
        doctorName:"",
        doctorNotFound:false,
        rooms:[],
        days:[],
        periodTimes:[],
         headers: [
        { text: "Doctor Id", value: "doctorProfile.id" },
        { text: "ชื่อแพทย์", value: "doctorProfile.name" },
        { text: "ห้องตรวจ", value: "room.room" },
        { text: "วัน", value: "day.day" },
        { text: "เวลา", value: "periodTime.periodTime" },
      ],
      items: [] 
      };
    },
  
    methods:{
    /* eslint-disable no-console */
  
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
      
      // ดึงข้อมูล Day ใส่ combobox
      getDays() {
      http
        .get("/day")
        .then(response => {
          this.days = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
      },
      
      // ดึงข้อมูล Room ใส่ combobox
      getPeriodTimes() {
      http
        .get("/PeriodTime")
        .then(response => {
          this.periodTimes = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
      }, 
      
      // ค้นหา id doctor
      findDoctorProfile() {
      http
        .get("/doctorprofile/" + this.schedule.doctorProfileId)
        .then(response => {
          console.log(response);
          if (response.data != null) {
            this.doctorName = response.data.name;
            console.log(this.doctorName);
            this.doctorCheck = response.status;
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
      saveSchedule() {
      http
        .post(
          "/schedule/" + this.schedule.doctorProfileId +
           "/" +
            this.schedule.roomId +
            "/" +
            this.schedule.dayId +
            "/" +
            this.schedule.periodTimeId,
          this.schedule
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
      this.doctorCheck = false;
      this.saveSC = false;
      this.saveUSC = false;
      this.doctorNotFound = false;
     
    },
       getSchedules() {
      http
        .get("/schedule")
        .then(response => {
          this.items = response.data;
          console.log(this.items);
        })
        .catch(e => {
          console.log(e);
        });
    
    },
     /* eslint-enable no-console */
    },
    mounted(){
      this.getRooms ();
      this.getDays();
      this.getPeriodTimes();
      this.getSchedules();
    }
  
  };
</script>