<template>



  <div>

    <v-container class="grey lighten-5" style="margin-top: 200px">
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
          name: "Jatupol Sanithai",
          id: "B5903832",
          system: "ระบบเพิ่มข้อมูลยา",
          link: "/drug"
        },
        {
          name: "Narongsak Suwan",
          id: "B5903900",
          system: "ระบบบันทึกใบเสร็จค่ายา",
          link: "/RecordBill"
        },
        {
          name: "Tanapol Tasanthea",
          id: "B5903917",
          system: "ระบบบันทึกข้อมูลเภสัชกร",
          link: "/pharmacist"
        },
        {
          name: "Sarocha Suksabai",
          id: "B5905713",
          system: "ระบบบันทึกข้อมูลการสั่งยา",
          link: "/prescription"
        },
        {
          name: "Prawee Yutthaweeravong",
          id: "B5915477",
          system: "ระบบจ่ายยา",
          link: "/drugdispense"
        },
        {
          name: "Jenjira Klomtrakul",
          id: "B5917518",
          system: "ระบบกรอกข้อมูลฉลากยา",
          link: "/medicineLabel"
        },
        {
          name: "Jatupol Sanithai",
          id: "B5903832",
          system: "ระบบเรียกดูข้อมูลยา",
          link: "/viewdrug"
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