<template>
  <v-app>
    <v-app-bar app class="yellow accent-1">
       
      <v-toolbar-title class="headline text-uppercase">
        <span>DOCTOR</span>
        <span class="font-weight-light"> information system</span>
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn @click="gohome" text href="#" target="_blank">
        <v-icon left>home</v-icon> 
        <span>หน้าแรก</span>
        
      </v-btn>
    </v-app-bar>
 

    <v-content>
      <!-- <Appointments/> -->
      <router-view />
    </v-content>
  </v-app>
</template>

<script>


export default {
  name: 'App',
  data() {
    return {
    username: 'Dr.Too Poptorn',
    drawer:true
    }
  },
  methods: {
    gohome(){
      this.$router.push('/');
    }
  }
}
</script>
