import {getAccountDetail} from "../../api/AccountService";
import moment from "moment";
import { useEffect, useState, useContext } from "react";
import { UserContext } from "../../context/UserContext";

const AccountDetail = () => {    
    const userContext = useContext(UserContext);
    const currentUser = userContext.currentUser;
    const [accountDetail, setAccountDetail] = useState({});
    const [callingDetail, setCallingDetail] = useState(-1);
    useEffect(() => {
        const getDetails = async () => {
            const accountDetails = async () => {
              if (callingDetail<0) {
                setCallingDetail(0);
                const localDetail = await getAccountDetail(currentUser);
                localDetail.lastModDate = moment(localDetail.lastModified).format("MMM Do YYYY h:mm:ss a");
                setAccountDetail(localDetail);
                setCallingDetail(1);
              }
            }
            await accountDetails();
          };
          if (callingDetail<0) {
            getDetails()
          }
     }, [callingDetail, currentUser]);
    return (
        <>
            <section className="cards-wrapper">
                <div className="card-grid-space">                
                <div className="card">
                    <div>
                    <h2>
                        ${ accountDetail.balance }
                    </h2>
                    <p>Something awesome</p>
                    <div className="date">{accountDetail.lastModDate}</div>
                    <div className="tags">
                        <div className="tag">
                        tag
                        </div>
                    </div>
                    
                    </div>
                </div>
                </div>
            </section>  
        </>
    );
}
export default AccountDetail;