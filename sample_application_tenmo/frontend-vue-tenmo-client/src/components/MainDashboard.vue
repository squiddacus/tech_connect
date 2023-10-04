<template>  
    <div class="home">
        <div class="info-boxes">
            <info-card title="Current balance" 
                :amount="accountDetail.balance"
                message="updated"
                type="account"
                />
            <info-card title="Last transfer" 
                :amount="transferLastone.amount"
                message="+$11 from last month"
                type="transfers"
                />
            <info-card title="Send / Request" 
                :amount="-1"
                message="create a transfer"
                type="request"
                />
            <info-card title="Pending transfers" 
                :amount="pendingTotal"
                message="5 last month"
                type="pending"
                v-if="(pendings.length>0)"
                />
        </div>
        <transfer-list/>
    </div>
</template>

<script>

import accountService from '../services/AccountService.js';
import transferService from '../services/TransferService.js';
import InfoCard from '../components/InfoCard.vue';
import TransferList from '../components/TransferList.vue';

export default {
  
  name: 'home',
  components:{
    InfoCard,
    TransferList
  },
  data() {
    return {
      accountDetail: {},
      transfers: [],
      pendings: [],
      pendingTotal: 0,
      transferTotal: 0,
      pendingLastone: {},
      transferLastone: {}
    }
  },
  created() {
    accountService.getAccountDetail().then((response) => {
      this.accountDetail = response.data;
    });
    transferService.getTransfers().then((response) => {
        this.transfers = response.data;
        if (this.transfers.length > 0) {
          this.transferTotal = this.transfers.reduce((sum,transfer) => {
              return sum + transfer.amount;
          });
          this.transferLastone = this.transfers[this.transfers.length - 1];
        }
    });    
    transferService.getPendingTransfers().then((response) => {
        this.pendings = response.data;                   
        if (this.pendings.length > 0) {
          this.pendingTotal = this.pendings.reduce((sum,transfer) => {
              return sum + transfer.amount;
          },0);
          this.pendingLastone = this.pendings[this.pendings.length - 1];
        }
    });
  }
}
</script>
