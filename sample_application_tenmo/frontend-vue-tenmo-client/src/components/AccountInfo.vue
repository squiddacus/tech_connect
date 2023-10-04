<template>
    <section class="cards-wrapper">
      <div class="card-grid-space">
        <!-- <div class="num">01</div> -->
        <div class="card">
          <div>
            <h2>
              ${{ accountDetail.balance }}
            </h2>
            <p>Something awesome</p>
            <div class="date">{{ lastModDate }}</div>
            <div class="tags">
              <div class="tag">
                tag
              </div>
            </div>
            
          </div>
        </div>
      </div>
    </section>  
</template>

<script>
import accountService from '../services/AccountService';
import moment from "moment";

export default {
    name:"account-info",
    data(){
        return {
            accountDetail:{}
        }
    },
    computed:{
      lastModDate(){
        return moment(this.accountDetail.lastModified).format("MMM Do YYYY h:mm:ss a");
      }

    },
    created(){
        accountService.getAccountDetail().then((response)=>{
            this.accountDetail = response.data;
        });
    }
    

}
</script>

<style>

</style>