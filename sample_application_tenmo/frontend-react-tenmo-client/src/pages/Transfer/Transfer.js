import { useParams } from 'react-router-dom';
import TransferDetail from '../../components/ui/TransferDetail';
import Layout from '../../components/ui/Layout';

const Transfer = () => {
    const { id } = useParams();
     
    return (
        <>
            <Layout>
                <TransferDetail id={id} />
            </Layout>
        </>
    );
}
export default Transfer;