import { useState, useEffect, useContext} from "react";
import Layout from '../../components/ui/Layout';
import {getTransfers} from "../../api/TransferService";
import ListOfTransfers from "../../components/ui/ListOfTransfers";
import { UserContext } from "../../context/UserContext";

const Transfer = () => {
    const userContext = useContext(UserContext);
    const currentUser = userContext.currentUser;
    const [transfers, setTransfers] = useState([]);
    const [callingTransfers, setCallingTransfer] = useState(-1);

    useEffect(() => {
        const getTransfersLocal = async () => {
            const transferReturn = async () => {
                if (callingTransfers<0) {
                    setCallingTransfer(0);
                    const transferList = await getTransfers();
                    if (transferList.length > 0) {
                        setTransfers(transferList);
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
                {(transfers && transfers.length > 0) &&
                            <ListOfTransfers transfers={transfers} user={currentUser} />
                }
                {(transfers.length === 0) && 
                    <div>No transfers</div>
                }
            </Layout>
        </>
    );
}
export default Transfer;