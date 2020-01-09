<template>
  <v-container>
    <div v-if="patientNotFound">
      <v-alert dense outlined type="error">
        <strong>ไม่พบข้อมูล</strong> กรุณากรอกข้อมูลอีกครั้ง
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

    <v-layout text-center wrap>
      <v-flex mb-4>
        <h1 class="display-2 font-weight-bold mb-3">บันทึกการวินิจฉัยโรค</h1>
      </v-flex>
    </v-layout>

    <v-row justify="center">
      <v-col cols="4">
        <v-form v-model="valid" ref="form">
          <v-row>
            <v-col cols="10">
              <v-text-field
                outlined
                label="ลำดับผู้ป่วย"
                v-model="examination.queueId"
                :rules="[(v) => !!v || 'กรุณากรอกข้อมูล']"
                required
              ></v-text-field>
              <!--แสดงข้อมูลผู้ป่วย-->

              <p v-if="patientCheck != ''">
                <strong>ข้อมูลผู้ป่วย :</strong>
                <br />
                คุณ {{patientFName}}
                {{patientLName}}
                อายุ {{patientAge}} ปี
                <br />
                โรคประจำตัว {{patientCongenitaldisease}}
                <br />
                แพ้ยา {{patientDrugallergy}}
                <br />
                น้ำหนัก {{patientWeight}} kg.
                <br />
                ส่วนสูง {{patientHight}} cm.
                <br />
                อุณหภูมิร่างกาย {{patientTemp}} °C
                <br />
                ความดันสูง {{patientPressurehight}}
                <br />
                ความดันต่ำ {{patientPressurelow}}
                <br />
                อาการเบื้องต้น {{patientSymptom}}
              </p>
            </v-col>

            <v-row justify="center"></v-row>

            <v-col cols="2">
              <div class="my-2">
                <v-btn color="blue-grey darken-1" dark @click="findQueues">Serach</v-btn>
              </div>
            </v-col>
          </v-row>
          <div v-if="patientCheck">
            <v-col>
              <v-select
                label="แพทย์ผู้ตรวจ"
                v-model="examination.doctorprofileId"
                :items="doctorProfiles"
                item-text="name"
                item-value="id"
                prepend-icon="supervised_user_circle"
                :rules="[(v) => !!v || 'กรุณาเลือกข้อมูล']"
                required
              ></v-select>
            </v-col>
            <v-row>
              <v-col>
                <v-textarea v-model="examination.result" block label="ข้อมูลการวินิจฉัย"></v-textarea>
              </v-col>
            </v-row>
            <v-row>
              <v-col>
                <v-select
                  label="โรค"
                  v-model="examination.diseaseId"
                  :items="diseases"
                  item-text="disease_name"
                  item-value="id"
                  :rules="[(v) => !!v || 'กรุณาเลือกข้อมูล']"
                  required
                ></v-select>
              </v-col>

              <v-col>
                <v-select
                  label="ความรุนแรง"
                  v-model="examination.levelId"
                  :items="levels"
                  item-text="level"
                  item-value="id"
                  :rules="[(v) => !!v || 'กรุณาเลือกข้อมูล']"
                  required
                ></v-select>
              </v-col>

              <v-col>
                <v-select
                  label="ชนิดการรักษา"
                  v-model="examination.typeId"
                  :items="types"
                  item-text="type_name"
                  item-value="id"
                  :rules="[(v) => !!v || 'กรุณาเลือกข้อมูล']"
                  required
                ></v-select>
              </v-col>
            </v-row>
            <v-row>
              <v-col>
                <v-btn @click="saveExamination" :class="{ blue: !valid, green: valid }">save</v-btn>
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
  name: "examination",
  data() {
    return {
      examination: {
        patientId: "",
        diseaseId: "",
        levelId: "",
        typeId: "",
        result: "",
        queueId: "",
        doctorprofileId: ""
      },
      valid: false,
      patientCheck: false,
      patientFName: "",
      patientLName: "",
      patientAge: "",
      patientCongenitaldisease: "",
      patientDrugallergy: "",
      patientPressurehight: "",
      patientPressurelow: "",
      patientWeight: "",
      patientHight: "",
      patientTemp: "",
      patientSymptom: "",
      patientNotFound: false,
      saveSC: false,
      saveUSC: false,
      diseases: [],
      types: [],
      levels: [],
      doctorProfiles: []
    };
  },
  methods: {
    /* eslint-disable no-console */
    findQueues() {
      http
        .get("/patient_information/" + this.examination.queueId)
        .then(response => {
          console.log(response);
          if (response.data != null) {
            this.examination.patientId = response.data.patient.id;
            this.patientPressurehight = response.data.pressurehight;
            this.patientPressurelow = response.data.pressurelow;
            this.patientWeight = response.data.weight;
            this.patientHight = response.data.hight;
            this.patientTemp = response.data.temp;
            this.patientSymptom = response.data.symptom;
            this.patientCheck = response.status;
            this.patientNotFound = false;
            this.getdDoctorprofiles();
            this.findPatients();
            this.getDiseases();
            this.getTypes();
            this.getLevels();
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

    /* eslint-disable no-console */
    findPatients() {
      http
        .get("/patient/" + this.examination.patientId)
        .then(response => {
          console.log(response);
          this.patientFName = response.data.firstname;
          this.patientLName = response.data.lastname;
          this.patientAge = response.data.age;
          this.patientCongenitaldisease = response.data.congenitaldisease;
          this.patientDrugallergy = response.data.drugallergy;
          this.patientCheck = response.status;
        })
        .catch(e => {
          console.log(e);
        });
    },
    /* eslint-disable no-console */
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
    /* eslint-disable no-console */
    getDiseases() {
      http
        .get("/disease")
        .then(response => {
          this.diseases = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getLevels() {
      http
        .get("/level")
        .then(response => {
          this.levels = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getTypes() {
      http
        .get("/type")
        .then(response => {
          this.types = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });

      /* eslint-disable no-console */
    },
    saveExamination() {
      console.log(this.examination);
      console.log(this.examination.result);
      http
        .post(
          "/examination/" +
            this.examination.patientId +
            "/" +
            this.examination.result +
            "/" +
            this.examination.diseaseId +
            "/" +
            this.examination.levelId +
            "/" +
            this.examination.typeId +
            "/" +
            this.examination.doctorprofileId +
            "/" +
            this.examination.queueId,
          this.examination
        )
        .then(response => {
          console.log(response);
          this.saveUSC = false;
          this.saveSC = true;
          if (this.examination.typeId == 2) {
            this.$router.push("/appointments");
          }
        })
        .catch(e => {
          console.log(e);
          this.saveUSC = true;
          this.saveSC = false;
        });
    },
    clear() {
      this.$refs.form.reset();
      this.patientCheck = false;
      this.saveSC = false;
      this.saveUSC = false;
      this.patientNotFound = false;
    },
    mounted() {
      this.getdDoctorprofiles();
      this.getDiseases();
      this.getTypes();
      this.getLevels();
    }
  }
};
</script>