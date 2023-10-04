import { useState, useEffect, useContext} from "react";
import Layout from '../../components/ui/Layout';
import {getPendingTransfers} from "../../api/TransferService";
import ListOfTransfers from "../../components/ui/ListOfTransfers";
import { UserContext } from "../../context/UserContext";

const Transfer = () => {
    const userContext = useContext(UserContext);
    const currentUser = userContext.currentUser;
    const [pendings, setPendings] = useState([]);
    const [callingTransfers, setCallingTransfer] = useState(-1);

    useEffect(() => {
        const getTransfersLocal = async () => {
            const transferReturn = async () => {
                if (callingTransfers<0) {
                    setCallingTransfer(0);
                    const transferList = await getPendingTransfers();
                    if ((transferList) && transferList.length > 0) {
                         setPendings(transferList);
                    }
                    setCallingTransfer(1);
                }
            }
            await transferReturn();
        }
        if (callingTransfers<0) {
            getTransfersLocal();
        }
    }, [callingTransfers]);
     
    return (
        <>
            <Layout>                
                {(pendings) && (pendings.length > 0) &&
                    // <TransferList transfers={pendings} key={pendings.length} type="pending" />
                    <ListOfTransfers transfers={pendings} user={currentUser} />
                }
                {((!pendings) || (pendings.length === 0)) && 
                    <div>No pending transfers</div>
                }
            </Layout>
        </>
    );
}
export default Transfer;