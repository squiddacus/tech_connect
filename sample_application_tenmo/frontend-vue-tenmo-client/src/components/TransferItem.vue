<template>
  <tr @click="routeToDetail">
    <td>
      {{ transfer.id }}
    </td>
    <td>
      <div class="percent" :style="percent">${{ transfer.amount }}</div>
    </td>
    <td>
      {{ userToShow }}
    </td>
    <td>
      <div class="status" 
          :class="{
                  approved: (transfer.transferStatus.desc==='Approved'),
                  rejected: (transfer.transferStatus.desc==='Rejected'),
                  pending: (transfer.transferStatus.desc==='Pending')
                  }">
        {{ transfer.transferStatus.desc }}
      </div>
    </td>
    <td class="left">
        <i v-if="transfer.transferType.desc==='Send'" 
            class="fas fa-chevron-circle-right" 
            style="color:red"></i>
        <i v-if="transfer.transferType.desc==='Request'" 
            class="fas fa-chevron-circle-left" 
            style="color:green"></i>
        <span>{{ transfer.transferType.desc }}</span>
    </td>      
  </tr>
</template>

<script>

            //fa-caret-square-right
            //fa-hourglass-half

            //fa-exchange-alt
            //<font-awesome-icon icon="fas fa-chevron-circle-right" />
export default {
  name:'transfer-item',
  props: {
    transfer: Object,
    highAmount: Number
  },
  computed:{
    percent(){
      return "width:" + ((this.transfer.amount/this.highAmount)*100) + "%;";
    },
    userToShow(){
      if (this.$store.state.user.username == this.transfer.accountTo.user.username) {
        return this.transfer.accountFrom.user.username;
      }
      return this.transfer.accountTo.user.username;
    }
  },
  methods: {
    routeToDetail(){
      this.$router.push({name:'transfer', params: {id:this.transfer.id}})
    }
  }
};
</script>
