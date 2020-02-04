<template>




  <div>

    <v-container class="grey lighten-5" style="margin-top: 200px">
      <h1 style="text-align: center">ยินดีต้อนรับสู่ฝ่ายบุคคล</h1>
      <v-row no-gutters>
        <template v-for="n in systems">
          <v-col :key="n">
            <v-card class="mx-auto" width="350">
              <v-card-title class="align-end fill-height">{{n.system}}</v-card-title>
              <v-card-text>
                <p>{{n.id}} {{n.name}}</p>
              </v-card-text>
              <v-divider></v-divider>
              <v-card-actions>
                <v-btn text color="primary" @click="go(n.link)">Go to System</v-btn>
              </v-card-actions>
            </v-card>
            <br />
            <br />
            <br />
            <br />
          </v-col>
          <v-responsive v-if="n === 3" :key="`width-${n}`" width="100%"></v-responsive>
        </template>
      </v-row>
    </v-container>
  </div>

</template>

<script>
// import http from "../http-common"
export default {
  name: "Home",
  data() {
    return {
      systems: [
        {
          name: "Tanapol Tasanthea",
          id: "B5903917",
          system: "ระบบบันทึกข้อมูลเภสัชกร",
          link: "/pharmacist"
        },
        {
          name: "Tanapol Tasanthea",
          id: "B5903917",
          system: "ระบบระบบแสดงข้อมูลเภสัชกร",
          link: "/viewpharmacist"
        }
      ]
    }
  },
  methods: {
    /* eslint-disable no-console */
    go(link) {
      this.$router.push(link)
    }
  },
  mounted() {}
}
</script>