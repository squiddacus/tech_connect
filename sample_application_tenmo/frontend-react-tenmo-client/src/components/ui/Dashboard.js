import React from 'react';
import { Row } from 'react-bootstrap';
import DashboardCard from './DashboardCard';




const Dashboard = (props) => {
  //props.transfers
  //props.pending
  //props.detail
  const totalAmount = props.detail.balance;;

  const getLastTransfer = () => {
  
    if (props.transfers.length > 0) {
      return props.transfers[props.transfers.length - 1]
    }
    return {};
  };

  const getPendingAmount = type => {
    return props.pending.reduce((sum, transfer) => { return sum + transfer.amount; }, 0);
  };
  const cardSettings = {
    account: {
      cardColor: "card-color-green",
      amountToShow: "$",
      iconToShow: "fas fa-coins",
      linkUrl: "/Account"
    },
    transfers: {
      cardColor: "card-color-blue",
      amountToShow: "$",
      iconToShow: "fas fa-money-bill-wave",
      linkUrl: "/Transfers"
    }
    ,
    request: {
      cardColor: "card-color-purple",
      amountToShow: "",
      linkUrl: "/New",
      iconToShow: "fas fa-exchange-alt"
    }
    ,
    pending: {
      cardColor: "card-color-orange",
      amountToShow: "$",
      iconToShow: "fas fa-hourglass-half",
      linkUrl: "/Pending"
    },
    other: {
      cardColor: "",
      amountToShow: "",
      iconToShow: "fas fa-exchange-alt",
      linkUrl: "/"
    }
  };

  return (
    <>
        <Row md={3} className="g-2">
          <DashboardCard 
              cardColor={cardSettings.account.cardColor}
              header="Account Detail"
              showAmount="true"
              amount={cardSettings.account.amountToShow + "" + totalAmount}
              iconToShow={cardSettings.account.iconToShow}
              message={"message"}
              linkUrl={cardSettings.account.linkUrl}
              />
          
          {getLastTransfer() && getLastTransfer().amount && 
            <DashboardCard 
                cardColor={cardSettings.transfers.cardColor}
                header="Transfers"
                showAmount="true"
                amount={cardSettings.transfers.amountToShow + "" + getLastTransfer().amount}
                iconToShow={cardSettings.transfers.iconToShow}
                message={"last one"}
                linkUrl={cardSettings.transfers.linkUrl}
                />
          }
          <DashboardCard 
              cardColor={cardSettings.request.cardColor}
              header="Create New Transfer"
              showAmount="false"
              amount=""
              iconToShow={cardSettings.request.iconToShow}
              message={"Send or request bucks"}
              linkUrl={cardSettings.request.linkUrl}
              />
          {(getPendingAmount()>0) &&
            <DashboardCard 
                cardColor={cardSettings.pending.cardColor}
                header="Pending"
                showAmount="true"
                amount={cardSettings.pending.amountToShow + "" + getPendingAmount()}
                iconToShow={cardSettings.pending.iconToShow}
                message={"Click to approve/reject pending transfers"}
                linkUrl={cardSettings.pending.linkUrl}
                />
          }
        </Row>
    </>
  );
};

export default Dashboard;
