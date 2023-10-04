import { useState, useEffect } from 'react';
import {saveTransfer} from "../../api/TransferService";
import { useNavigate } from "react-router-dom";
import { getAccountDetail, getAccounts } from '../../api/AccountService';

const TransferFunds = () => {
    const navigate = useNavigate();
    const defaultTransfer = {
            amount:"0",
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
    };
    const [newTransfer, setNewTransfer] = useState(defaultTransfer);
    const [actionAccountId, setActionAccountId] = useState("");
    const [accounts, setAccounts] = useState([]);
    const [currentAccount, setCurrentAccount] = useState({});
    const [transferTypeId, setTransferTypeId] = useState("");
    const [callingAccounts, setCallingAccounts] = useState(-1);
    const [amount, setAmount] = useState(0);
    const [message, setMessage] = useState("");

    const onInputchange = (event) => {
        if (event !== undefined) event.preventDefault();        
        let localTransfer = newTransfer;
        if ((event.target.name==="transferTypeId")){
            localTransfer.transferType.id = parseInt(event.target.value);
            setTransferTypeId(event.target.value);            
        } else if ((event.target.name==="amount")){
            localTransfer.amount = parseInt(event.target.value);
            setAmount(parseInt(event.target.value));
        } else if ((event.target.name==="message")){
            localTransfer.message = event.target.value;
            setMessage(event.target.value);
        }         
        console.log(localTransfer);
        setNewTransfer(localTransfer);        
    };
    
    
    const saveCurrentTransfer = (event) => {
        if (event !== undefined) event.preventDefault();
        const actionAccount = accounts.find((account)=>{
            return account.id===parseInt(actionAccountId);
        })
        if (transferTypeId==="1"){
            newTransfer.accountTo=currentAccount;
            newTransfer.accountFrom=actionAccount;
        } else {            
            newTransfer.accountFrom=currentAccount;                
            newTransfer.accountTo=actionAccount;
        }
        
        saveTransfer(newTransfer).then(updatedTransfer => {
            navigate('/');
        });       
        
    }     
    
    const onAccountChange = (event) => {
        if (event !== undefined) event.preventDefault();
        setActionAccountId(event.target.value);
    };

    
    useEffect(() => {
        const getLocalAccounts = async () => {
            const localAccountList = async () => {
              if (callingAccounts<0) {
                setCallingAccounts(0);
                const localAccounts = await getAccounts();
                const localDetail = await getAccountDetail();
                setAccounts(localAccounts);
                setCurrentAccount(localDetail);
                if (localAccounts.length>0) {
                    setActionAccountId(localAccounts[0].id);
                }
                setCallingAccounts(1);
              }
            }
            await localAccountList();
          };
          if (callingAccounts<0) {
            getLocalAccounts()
          }
     }, [callingAccounts]);


    return (
        <>
          <div>
            <form className="new-transfer-form" onSubmit={saveCurrentTransfer}>
                <div>
                    <select value={transferTypeId}  onChange={onInputchange}
                    style={{minWidth:'300px'}} name="transferTypeId">
                            <option value="0"></option>
                            <option value="1">Request</option>
                            <option value="2">Send</option>
                    </select>
                </div>
                <div>
                    {(transferTypeId==="1") && 
                        <select value={actionAccountId}  onChange={onAccountChange}
                                style={{minWidth:'300px'}}>
                            {accounts.map(account =>
                            <option key={account.id}
                                value={account.id}
                                >{ account.user.username }</option>
                            )}
                        </select>
                    }
                    {(transferTypeId==="2") && 
                        <select value={actionAccountId}  onChange={onAccountChange}
                                style={{minWidth:'300px'}}>
                            {accounts.map(account =>
                            <option key={account.id}
                                value={account.id}
                                >{ account.user.username }</option>
                            )}
                        </select>
                    }
                </div>
                <div>
                    <input className="message-input" type="text" placeholder="Message about the awesome transfer" 
                        value={message}  onChange={onInputchange}
                        style={{width:'300px'}} name="message"/>
                </div>
                <div>
                    <input className="amount-input" type="number" placeholder="Amount"
                    value={amount}  onChange={onInputchange}
                    style={{width:'100px'}} name="amount"/>
                </div>
                <button type="submit" className="btn btn-submit">Save</button>
            </form>
        </div>
        </>
    );
}
export default TransferFunds;