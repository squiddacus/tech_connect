import TransferFunds from "../../components/ui/TransferFunds";
import Layout from "../../components/ui/Layout";

const CreateTransfer = ({children}) => {    
     
    return (
        <>
            <Layout>
                <TransferFunds />
                {children}
            </Layout>
        </>
    );
}
export default CreateTransfer;