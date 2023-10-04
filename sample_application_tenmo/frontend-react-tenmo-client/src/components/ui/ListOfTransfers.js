import React from 'react';
import Table from './table/Table';

const ListOfTransfers = (props) => {

  const columns = [
    { label: "Id", accessor: "id", sortable: true, sortbyOrder: "desc", filterType: "multiple", link:"/transfer/:{id}" },
    { label: "Amount", accessor: "formatAmount", sortable: true, filterType: "multiple" },
    { label: "User", accessor: "user", sortable: true, filterType: "multiple" },
    { label: "Status", accessor: "status", sortable: true, filterType: "list", filterList: ["Approved", "Rejected"] },
    { label: "Type", accessor: "type", sortable: true, filterType: "list", filterList: ["Send", "Request"] },
  ];

  const flattenTransfers = () => {
    if (props.transfers.length > 0) {
      return props.transfers.map(transfer => {
        let newTransfer = transfer;
        newTransfer.formatAmount = '$' + transfer.amount;
        newTransfer.status = transfer.transferStatus.desc;
        newTransfer.type = transfer.transferType.desc;
        if (props.user.username === transfer.accountTo.user.username) {
          newTransfer.user = transfer.accountFrom.user.username;
        } else {
          newTransfer.user = transfer.accountTo.user.username;
        }
        return newTransfer;
      });
    }
    return {};
  };
  return (
    <>
      {
        (flattenTransfers()) && (flattenTransfers().length > 0) &&
        <Table
          caption="Note the column headers are sortable."
          data={flattenTransfers()}
          columns={columns}
        />
      }
      {
        ((!flattenTransfers()) || (flattenTransfers().length === 0)) &&
        <div>No transactions</div>
      }
    </>
  );
};

export default ListOfTransfers;
