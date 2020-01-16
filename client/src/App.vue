<template>
  <v-app>
    <v-app-bar app class="yellow accent-1">
       <v-btn @click="drawer=!drawer" class="ma-2" tile large color="white" icon>
      <v-icon>MEDICINE ROOM</v-icon>
       </v-btn>
      <v-toolbar-title class="headline text-uppercase">
        <span>P</span>
        <span class="font-weight-light"> SYSTEM</span>
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
