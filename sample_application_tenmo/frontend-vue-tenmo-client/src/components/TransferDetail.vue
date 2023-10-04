<template>
  <div>
    <section class="cards-wrapper">
      <div class="card-grid-space">
        <!-- <div class="num">01</div> -->
        <div class="card">
          <div>
            <h2><i v-if="transfer.transferType.desc === 'Request'" class="fas fa-chevron-circle-right" style="color:red"></i>
              <i v-if="transfer.transferType.desc === 'Send'" class="fas fa-chevron-circle-left" style="color:green"></i>
              ${{ transfer.amount }}
            </h2>
            <p>To: {{ transfer.accountTo.user.username }}</p>
            <p>From: {{ transfer.accountFrom.user.username }}</p>
            <p>Ending Balance: {{ transfer.endingBalance }}</p>
            <p>Message: {{ transfer.message }}</p>
            <div class="date">&nbsp;{{ formattedCreateDate }}</div>
            <div class="tags">
              <div class="tag approved" :class="{
                approved: (transfer.transferStatus.desc === 'Approved'),
                rejected: (transfer.transferStatus.desc === 'Rejected'),
                pending: (transfer.transferStatus.desc === 'Pending')
              }">
                {{ transfer.transferStatus.desc }}
              </div>
            </div>
            <div v-if="transfer.transferStatus.desc === 'Pending'">
              <button class="btn btn-approve" @click="approveTransfer()">Approve</button>
              <button class="btn btn-reject" @click="rejectTransfer()">Reject</button>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>
  
<script>
  import transferService from '../services/TransferService';
  import moment from 'moment';

  export default {
    name:'transfer-detail',
    data(){
      return {
        transfer:{
          transferStatus:{},
          transferType:{},
          accountTo:{
            user:{}
          },
          accountFrom:{
            user:{}
          }
        }
      }
    },    
    computed: {
      formattedCreateDate(){
          if (this.transfer.createdDate !== null) {
            return moment(this.transfer.createdDate).format("MMM Do, YYYY hh:mm:ss a");
          }
          return "";
      }
    },
    methods:{
      approveTransfer(){
        this.transfer.transferStatus.id=2;
        this.transfer.transferStatus.desc="Approved";
        this.saveTransfer();
      },
      rejectTransfer(){
        this.transfer.transferStatus.id=3;
        this.transfer.transferStatus.desc="Rejected";
        this.saveTransfer();
      },
      saveTransfer(){        
        transferService.updateTransfer(this.transfer.id, this.transfer).then((response)=>{
          this.transfer = response.data;
          this.$router.push("/");
        });
      }
      
    },
    created(){
        const transferId = this.$route.params.id;
        transferService.getTransfer(parseInt(transferId)).then((response)=>{
          this.transfer = response.data;
        });
      }
    };  
</script>
<style scoped>

</style>