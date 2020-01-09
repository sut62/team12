<template>
  <div>
    <NavBar />
    <br />
    <br />
    <br />
    <br />
    <v-container class="grey lighten-5">
      <v-row no-gutters>
        <template v-for="n in student">
          <v-col :key="n">
            <v-card class="mx-auto" width="300">
              <v-card-title class="align-end fill-height">{{n.system}}</v-card-title>
              <v-card-text>
                <span>{{n.id}} {{n.name}}</span>
              </v-card-text>
              <v-divider></v-divider>
              <v-card-actions>
                <v-btn text color="orange" @click="go(n.link)">เข้าใช้งาน</v-btn>
              </v-card-actions>
            </v-card>
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
export default {
  components: {
    //NavBar
  },
  data() {
    return {
      student: [
        {
          system: "ระบบวินิจฉัยโรค",
          link: "/examination"
        },
        {
          system: "ระบบออกใบนัด",
          link: "/appointments"
        },
         
      ]
    };
  },
  methods: {
      go(link) {
      if (this.username == undefined) {
        if (link == "/examination") {
          this.$router.push('/examination');
        } else {
          this.$router.push('/appointments');
        }
      } else {
        this.$router.push(link + this.username);
      }
    }
  },
  mounted() {
  }
};
</script>

