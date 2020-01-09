<template>
  <div>
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <v-card class="mx-auto" max-width="400">
      <v-card-title class="align-end fill-height">Login</v-card-title>
      <br />
      <v-row justify="center">
        <v-form ref="form" v-model="valid" lazy-validation style="width: 70%">
          <v-text-field v-model="username" label="Username" outlined></v-text-field>
          <v-text-field 
            v-model = "password"
            :append-icon="show4 ? 'visibility' : 'visibility_off'"
            :type="show4 ? 'text' : 'password'"
            label="Password" 
            @click:append="show4 = !show4" 
            outlined
            ></v-text-field>
        </v-form>
      </v-row>

      <v-row justify="center">
        <v-btn @click="login">Login</v-btn>
      </v-row>
      <br />

    </v-card>
  </div>
</template>


<script>
import http from "../http-common";

export default {
  /* eslint-disable */
  data() {
    return {
        username: '',
        password: '',
        show4:false,
    };
  },
  methods: {
      login() {
          http.get('/login/' + this.username + '/' + this.password)
          .then(res => {
              if (res) {
                if(this.username >= 10000 && this.username <= 19999){
                  alert('เข้าสู่ระบบในฐานะแพทย์สำเร็จ!')
                  this.$router.push('/docselect');
                }
              else if (this.username >=20000 && this.username <= 29999){
                  alert('เข้าสู่ระบบในฐานะพยาบาลสำเร็จ!')
                  this.$router.push('/patient_information');
              }else if (this.username >=30000 && this.username <= 39999){
                  alert('เข้าสู่ระบบในฐานะเจ้าหน้าที่ฝ่ายเวชระเบียนสำเร็จ!')
                  this.$router.push('/register');
              }else if (this.username >=40000 && this.username <= 49999){
                  alert('เข้าสู่ระบบในฐานะเจ้าหน้าที่ฝ่ายบุคคลสำเร็จ!')
                  this.$router.push('/personnelSelect');
              }
              }
          })
      },

  }
};
</script>

