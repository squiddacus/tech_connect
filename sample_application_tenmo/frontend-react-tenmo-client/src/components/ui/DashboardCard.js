import React from 'react';
import { Card, Col} from 'react-bootstrap';
import { Link } from "react-router-dom";

const DashboardCard = (props) => {
    return (
        <Col>
            <Link to={props.linkUrl}>
                <Card className={props.cardColor}>
                    <Card.Header>{props.header}</Card.Header>
                    <Card.Body>
                        {/* Use divs/custom classes to get custom view, colors, icons */}
                        <div className="info-card">
                            <div className="card-inner">
                                <div className="card-content">
                                    <div className="card-amount">
                                        {(props.showAmount === 'true') &&
                                            <span>{props.amount}&nbsp;</span>
                                        }
                                        {(props.showAmount === 'false') &&
                                            <span>&nbsp;</span>
                                        }
                                    </div>
                                </div>
                                <div className="box-icon">
                                    <i className={props.iconToShow}></i>
                                </div>
                            </div>
                        </div>
                    </Card.Body>
                    <Card.Footer>
                        <small className="text-muted">{props.message}</small>
                    </Card.Footer>
                </Card>
            </Link>
        </Col> 
    );
};

export default DashboardCard;
