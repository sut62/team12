<template>

<v-parallax
    dark
    src="https://cdn.vuetifyjs.com/images/backgrounds/vbanner.jpg"
  >

  <v-row justify="center" style="margin-top: 150px">
    <v-col cols="5">
      <h1 style="text-align: center">ยินดีต้อนรับสู่ระบบห้องยา</h1>
      <v-card class="mx-auto" max-width="500" style="margin-top: 20px;">
        <br />
        <h2 style="text-align: center">Login</h2>
        <br />
        <div style="margin: auto; width: 80%;">
          <v-text-field
            label="Username"
            v-model="username"
            outlined
            :rules="[(v) => !!v || 'Item is required']"
            required
          ></v-text-field>
          <v-text-field 
            v-model = "password"
            :append-icon="show4 ? 'visibility' : 'visibility_off'"
            :type="show4 ? 'text' : 'password'"
            label="Password" 
            @click:append="show4 = !show4" 
            outlined
            ></v-text-field>
          <div class="float-right">
            <v-btn color="primary" @click="login">Login</v-btn>
          </div>
        </div>
        <br />
        <br />
        <br />
      </v-card>
    </v-col>
  </v-row>
  </v-parallax>
</template>

<script>
// import http from "../http-common"
export default {
  name: "Login",
  data() {
    return {
      username: null,
      password: null,
      show4:false,
    }
  },
  methods: {
    /* eslint-disable no-console */
    login() {
      if (this.username === null || this.password === null) {
        alert("Please enter username and password")
      } else {
          if(this.username == "p001" && this.password == "1234") {
              alert("ยินดีต้อนรับเภสัชกร")
              this.$router.push("/home")
          } else if (this.username == "h001" && this.password == "1234"){
              alert("ยินดีต้อนรับฝ่ายบุคคล")
              this.$router.push("/home_hr")
          }else  {
              alert("ชื่อผู้ใช้และรหัสผ่านไม่ถูกต้อง ลองใหม่อีกครั้ง")
              this.username = null;
              this.password = null;
          }
      }
    }
  },
  mounted() {}
}
</script>