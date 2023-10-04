<template>
    <div>
        <form class="new-transfer-form" v-on:submit.prevent="saveTransfer">
            <div>
            <select v-model="newTransfer.transferType.id"
            style="min-width:300px">
                    <option value="0"></option>
                    <option value="1">Request</option>
                    <option value="2">Send</option>
            </select>
        </div>
        <div>
            <select v-model="actionAccountId" 
                    v-if="newTransfer.transferType.id==1"
                    style="min-width:300px">
                <option v-for="account in accounts" :key="account.id"  
                    :value="account.id"
                    >{{ account.user.username }}</option>
            </select>
            <select v-model="actionAccountId" 
                    v-if="newTransfer.transferType.id==2"
                    style="min-width:300px">
                <option v-for="account in accounts" :key="account.id"  
                    :value="account.id"
                    >{{ account.user.username }}</option>
            </select>
        </div>
        <div>
            <input class="message-input" type="text" placeholder="Message about the awesome transfer" 
            v-model.number="newTransfer.message"
            style="width:300px" />
        </div>
        <div>
            <input class="amount-input" type="text" placeholder="Amount" 
            v-model.number="newTransfer.amount"
            style="width:60px" />
        </div>
            <button type="submit" class="btn btn-submit">Save</button>
        </form>
    </div>
</template>

<script>
import accountService from '../services/AccountService';
import transferService from '../services/TransferService';

export default {
    data(){
            return {
                newTransfer:{
                    amount:0,
                    accountTo:{
                        user:{}
                    },
                    accountFrom:{
                        user:{}
                    },
                    transferType: {
                        id:0,                        
                        desc:""
                    },
                    transferStatus: {
                        id:0,                        
                        desc:""
                    }
            },
            accounts:[],
            currentAccount:{

            },
            actionAccountId:0
        }
    },
    methods:{
        saveTransfer(){
            const actionAccount = this.accounts.find((account)=>{
                return account.id===parseInt(this.actionAccountId);
            })
            if (parseInt(this.newTransfer.transferType.id)===1){
                //request
                this.newTransfer.accountTo=this.currentAccount;
                this.newTransfer.accountFrom=actionAccount;
            } else {            
                this.newTransfer.accountFrom=this.currentAccount;                
                this.newTransfer.accountTo=actionAccount;
            }
            
            transferService.saveTransfer(this.newTransfer).then((response)=>{
                console.log(response.data);
                this.$router.push('/');
            })
            return;
        }
    },
    created(){
        accountService.getAccounts().then((response)=>{
            this.accounts = response.data;
        });
        accountService.getAccountDetail().then((response)=>{
            this.currentAccount = response.data;
        });
    }
}
</script>

<style>

</style>