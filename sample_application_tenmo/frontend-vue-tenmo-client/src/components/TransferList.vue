<template>
<div>
  <table class="transfer-list">
    <thead>
      <tr>
        <th>Id</th>
        <th>Amount</th>
        <th>User</th>
        <th>Status</th>
        <th>Type</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td Send><input type="text" v-model="transferFilter.id"/></td>
        <td><input type="number" v-model="transferFilter.amount"/></td>
        <td><input type="text" v-model="transferFilter.user"/></td>
        <td>
          <select style="min-width:100%;" v-model="transferFilter.status">
            <option key="" selected></option>
            <option key="Approved">Approved</option>
            <option key="Rejected">Rejected</option>
          </select>
        </td>
        <td>
          <select style="min-width:100%;" v-model="transferFilter.type">
            <option key=""  selected></option>
            <option key="Send">Send</option>
            <option key="Request">Request</option>
          </select></td>
      </tr>      
      <transfer-item 
                    v-for="transfer in filteredTransfers" 
                    :key="transfer.id" 
                    :transfer="transfer"
                    :high-amount="highAmount"
                    @click="showDetail(transfer.id)"
                    class="hover-item"
                    />
    </tbody>
  </table>
  </div>
</template>

<script>

import transferService from '../services/TransferService.js';
import TransferItem from './TransferItem.vue';

export default {
  name:'transfer-list',
  props: {
    transfer: String
  },
components:{
  TransferItem
},
data() {
  return {
    transfers: [],
    transferFilter: {
      id:"",
      amount:"",
      user:"",
      status:"",
      type:""
    }
  }
},
computed:{
  highAmount() {
    return this.transfers.reduce((high,transfer)=>{
      return (high>transfer.amount) ? high : transfer.amount;
    },0);
  },
  filteredTransfers() {
    let filteredList = this.transfers;
    filteredList = this.transfers.filter((t) => {
      return (
        (t.accountTo.user.username.includes(this.transferFilter.user) ||
          t.accountFrom.user.username.includes(this.transferFilter.user)) &&
          t.amount.toString().includes(this.transferFilter.amount) &&
          //t.id.toString().includes(this.transferFilter.id) &&
          ((t.transferStatus.desc===this.transferFilter.status) ||
           (this.transferFilter.status=="")) &&
          ((t.transferType.desc===this.transferFilter.type) ||
           (this.transferFilter.type=="")) && 
           (this.isIdInList(t.id, this.transferFilter.id))
      );

    });
    return filteredList;
  }
},
methods: {
  
  isIdInList(id,listOfIds) {
    
        if (listOfIds==="") {
          return true;
        }
        if (!listOfIds.includes(",")) {
          return id.toString().includes(listOfIds);
        }
        let ids = listOfIds.split(',').map((e) => {
            return e.trim();
        });
        
        let foundId = ids.find((n)=>{
          if (n === "") {
            return false;
          }
          return id.toString().includes(n.toString());
        });
        return (foundId);        

      },
  showDetail(transferId){
      console.log(transferId);
  }
},
created() {
  
  if (this.transfer==="pending") {
    transferService.getPendingTransfers().then((response) => {
        this.transfers = response.data;
        if (this.transfers.length > 0) {
          this.transferTotal = this.transfers.reduce((sum,transfer) => {
              return sum + transfer.amount;
          });
          this.transferLastone = this.transfers[this.transfers.length - 1];
          
        }
    });
  } else {
    transferService.getTransfers().then((response) => {
        this.transfers = response.data;
        if (this.transfers.length > 0) {
          this.transferTotal = this.transfers.reduce((sum,transfer) => {
              return sum + transfer.amount;
          });
          this.transferLastone = this.transfers[this.transfers.length - 1];
          
        }
    });
  }




}
}
</script>
