<template>
    <router-link class="info-card-link" :to="{name:type}">
      <div class="info-card" :class="cardColor" >
          <div class="card-inner">
            <div class="card-content">
              <div class="card-title">{{title}}</div>
              <div class="card-amount"><span v-if="amountToShow!='-1'">{{amountToShow}}</span>&nbsp;</div>
            </div>
            <div class="box-icon">
              <i :class="iconToShow"></i>
            </div>
          </div>
          <div class="card-message">{{message}}</div>
          <div class="card-bottom">&nbsp;</div>
        </div>
    </router-link>
</template>
  
  <script>
  export default {
    name:'info-card',
    props: {
        title: String,
        amount: Number,
        message: String,
        type: String
    },
    computed:{
        cardColor(){
            if (this.type==="account") {
              return "card-color-green";
            }
            if (this.type==="transfers") {
              return "card-color-blue";
            }
            if (this.type==="request") {
              return "card-color-purple";
            }
            if (this.type==="pending") {
              return "card-color-orange";
            }
            return "";
            //fa-caret-square-right
            //fa-hourglass-half

            //fa-exchange-alt
            //<font-awesome-icon icon="fas fa-chevron-circle-right" />
        },
        amountToShow() {          
          if (this.type==="account") {
              return "$" + this.amount;
            }
            if (this.type==="transfers") {
              return "$" + this.amount;
            }
            return this.amount;
        },
        iconToShow() {          
          if (this.type==="account") {
              return "fas fa-coins";
            }
            if (this.type==="transfers") {
              return "fas fa-money-bill-wave";
            }
            if (this.type==="pending") {
              return "fas fa-hourglass-half";
            }
            return "fas fa-exchange-alt";
            //fa-share-square
        }
    }
  };
  </script>
  