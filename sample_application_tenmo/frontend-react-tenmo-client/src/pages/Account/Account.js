import Layout from "../../components/ui/Layout";
import AccountDetail from "../../components/ui/AccountDetail";

const Account = ({children}) => {    
     
    return (
        <>
            <Layout>
                <AccountDetail />
                {children}
            </Layout>
        </>
    );
}
export default Account;