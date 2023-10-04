<template>
  <div class="home">
    <h1>Home</h1>
    <p>You must be authenticated to see this</p>
    <!-- <div class="info-boxes">

      <div class="info-box">
        <div class="box-icon">
          <i class="fas fa-coins"></i>
        </div>
        <div class="box-content">
          <span class="big">{{ accountDetail.balance }}</span>
          Current balance ($)
        </div>
      </div>


      <div class="info-box">
        <div class="box-icon">
          <i class="fas fa-money-bill-wave"></i>
        </div>
        <div class="box-content">
          <span class="big">77.98</span>
          Last transfer ($)
        </div>
      </div>


    </div> -->



    <div class="info-boxes">

      <div class="info-card card-color-blue">
        <div class="card-inner">
          <div class="card-content">
            <div class="card-title">Current balance</div>
            <div class="card-amount">${{ accountDetail.balance }}</div>
          </div>
          <div class="box-icon">
            <i class="fas fa-coins"></i>
          </div>
        </div>
        <div class="card-message">&nbsp;</div>
        <div class="card-bottom">&nbsp;</div>
      </div>


      <div class="info-card card-color-green">
        <div class="card-inner">
          <div class="card-content">
            <div class="card-title">Last transfer</div>
            <div class="card-amount">${{transferLastone.amount}}</div>
          </div>
          <div class="box-icon">
            <i class="fas fa-money-bill-wave"></i>
          </div>
        </div>
        <div class="card-message">+$11 from last month</div>
        <div class="card-bottom">&nbsp;</div>
      </div>

    </div>

    <!-- list -->

  </div>
</template>

<script>
import accountService from '../services/AccountService.js';
import transferService from '../services/TransferService.js';

export default {
  
  name: 'home',
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
    transferService.getTransfers.then((response) => {
        this.transfers = response.data;                
        if (this.transfers.any()) {
          this.transferTotal = this.transfers.reduce((sum,transfer) => {
              return sum + transfer.amount;
          });
          this.transferLastone = this.transfers[this.transfers.length - 1];
        }
    });    
    transferService.getPendingTransfers.then((response) => {
        this.pendings = response.data;                   
        if (this.pendings.any()) {
          this.pendingTotal = this.pendings.reduce((sum,transfer) => {
              return sum + transfer.amount;
          });
          this.pendingLastone = this.pendings[this.pendings.length - 1];
        }
    });
  }
}
</script>

