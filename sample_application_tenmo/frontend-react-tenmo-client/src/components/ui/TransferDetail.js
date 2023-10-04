import { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
//import { useCurrentUser } from "../../context/UserContext";
import moment from 'moment';
import {getTransfer, updateTransfer} from "../../api/TransferService";
import { useNavigate } from "react-router-dom";
import { Button, Card, Col, ListGroup } from 'react-bootstrap';


const TransferDetail = (props) => {
    const { id } = useParams();
    
    const navigate = useNavigate();
  
    const defaultTransfer = {
        transferStatus:{},
        transferType:{},
        accountTo:{
          user:{}
        },
        accountFrom:{
          user:{}
        },
        formattedCreateDate:''
      };
    const [transfer, setTransfer] = useState(defaultTransfer);
    const [callingDetail, setCallingDetail] = useState(-1);
    
    const approveTransfer = (event) => {
        if (event !== undefined) event.preventDefault();
        let localTransfer = transfer;
        localTransfer.transferStatus.id=2;
        localTransfer.transferStatus.desc="Approved";
        setTransfer(localTransfer);
        saveTransfer();
    }

    const rejectTransfer = (event) => {
        if (event !== undefined) event.preventDefault();
        let localTransfer = transfer;
        localTransfer.transferStatus.id=3;
        localTransfer.transferStatus.desc="Rejected";
        setTransfer(localTransfer);
        saveTransfer();
    }

    const saveTransfer = () => {   
        updateTransfer(transfer.id, transfer);
        navigate("/");
    }

    useEffect(() => {
        const getDetails = async () => {
            const transferDetails = async () => {
              if (callingDetail<0) {
                setCallingDetail(0);
                const localDetail = await getTransfer(id);
                localDetail.formattedCreateDate = moment(localDetail.createdDate).format("MMM Do, YYYY hh:mm:ss a");
                setTransfer(localDetail);
                setCallingDetail(1);
              }
            }
            await transferDetails();
          };
          if (callingDetail<0) {
            getDetails()
          }
     }, [callingDetail, id]);
     
    return (
        <>
            <Col>
                
                    <Card className={props.cardColor}>
                        <Card.Header><div>
                                    {(transfer.transferType.desc === 'Request') && 
                                        <i className="fas fa-chevron-circle-right" style={{color:'red'}}></i>
                                    }
                                    {(transfer.transferType.desc === 'Send') && 
                                        <i className="fas fa-chevron-circle-left" style={{color:'green'}}></i>
                                    }
                                ${ transfer.amount }
                                </div></Card.Header>
                        <Card.Body>
                            <Card.Title>{transfer.transferType.desc}</Card.Title>
                            {/* Use divs/custom classes to get custom view, colors, icons */}
                            <ListGroup>
                            <ListGroup.Item>To: { transfer.accountTo.user.username }</ListGroup.Item>
                            <ListGroup.Item>From: { transfer.accountFrom.user.username }</ListGroup.Item>
                            {(transfer.endingBalance) && 
                            <ListGroup.Item>Ending Balance: ${ transfer.endingBalance }</ListGroup.Item>
                            }
                            <ListGroup.Item>Message: { transfer.message }</ListGroup.Item>
                            {(transfer.formattedCreateDate !== "Invalid date") && 
                            <ListGroup.Item>Date: { transfer.formattedCreateDate }</ListGroup.Item>
                            }
                            </ListGroup>
                                
                                {(transfer.transferStatus.desc === 'Pending') && 
                                <>
                                    <Button variant="success" onClick={approveTransfer}>Approve</Button>
                                    {' '}
                                    <Button variant="danger" onClick={rejectTransfer}>Reject</Button>
                                </>
                                }          
                        </Card.Body>
                        <Card.Footer>
                        <div className="tags">
                                    <div className={"tag " +  ((transfer.transferStatus.desc) ? transfer.transferStatus.desc.toLowerCase() : "")}>
                                        { transfer.transferStatus.desc }
                                    </div>
                                </div>                                
                        </Card.Footer>
                    </Card>
                
            </Col> 
        </>
    );
}
export default TransferDetail;