import { useState, useEffect,useContext } from "react";
import Layout from "../../components/ui/Layout";
import { getAccountDetail } from "../../api/AccountService";
import { getTransfers, getPendingTransfers } from "../../api/TransferService";
import { UserContext } from "../../context/UserContext";
import Login from "../Login/Login";
import Dashboard from "../../components/ui/Dashboard";
import ListOfTransfers from "../../components/ui/ListOfTransfers";


const Home = ({ children }) => {
    const userContext = useContext(UserContext);
    const currentUser = userContext.currentUser;
    
    const [accountDetail, setAccountDetail] = useState({});
    const [transfers, setTransfers] = useState([]);
    const [pendings, setPendings] = useState([]);
    const [callingDetail, setCallingDetail] = useState(-1);
    const [callingTransfers, setCallingTransfer] = useState(-1);
    const [callingPending, setCallingPending] = useState(-1);

    useEffect(() => {
        const getDetails = async () => {
            const accountDetails = async () => {
                if (callingDetail < 0) {
                    setCallingDetail(0);
                    const localDetail = await getAccountDetail(currentUser);
                    setAccountDetail(localDetail);
                    setCallingDetail(1);
                }
            }
            await accountDetails();
        };
        if (callingDetail < 0) {
            getDetails()
        }
    }, [callingDetail, currentUser]);

    useEffect(() => {
        const getTransfersLocal = async () => {
            const transferReturn = async () => {
                if (callingTransfers < 0) {
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
        if (callingTransfers < 0) {
            getTransfersLocal();
        }
    }, [callingTransfers, currentUser]);


    useEffect(() => {
        const getPendingLocal = async () => {
            const pendingReturn = async () => {
                if (callingPending < 0) {
                    setCallingPending(0);
                    getPendingTransfers().then(pendingList => {
                        setPendings(pendingList);
                        setCallingPending(1);
                    });
                }
            }
            await pendingReturn();
        }
        if (callingPending < 0) {
            getPendingLocal();
        }
    }, [callingPending, currentUser]);


    return (
        <>
            {(!currentUser || !currentUser.isAuthenticated) &&
                <Login></Login>
            }
            {(currentUser && currentUser.isAuthenticated) &&
                <Layout>
                    
                    {(currentUser && currentUser.isAuthenticated) &&
                        <>
                            <Dashboard transfers={transfers} pending={pendings} detail={accountDetail} />
                            {(transfers && transfers.length > 0) &&
                                <ListOfTransfers transfers={transfers} user={currentUser} />
                            }
                        </>
                    }

                    {children}
                </Layout>
            }
        </>
    );
}
export default Home;