import React, {Component} from 'react';
import {Card, CardActions, CardHeader, CardText} from 'material-ui/Card';
import FlatButton from 'material-ui/FlatButton';
import request from 'superagent';

export default class Subscriber extends Component {
    state = {
        buttonHidden: true
    };

    render() {
        const subscriber = this.props.subscriber;
        
        return <Card onExpandChange={() => this.setState({buttonHidden: !this.state.buttonHidden})}>
            <CardHeader
                title={subscriber.id + " - " + subscriber.nom}
                actAsExpander={true}
                showExpandableButton={true}
            />
            <CardActions>
                    <span hidden={this.state.buttonHidden}>
                        <FlatButton label="Supprimer" onClick={this.delete} primary={true}/>
                    </span>
            </CardActions>
            <CardText expandable={true}>
            </CardText>
        </Card>;

    }

    delete = () => {
        request.delete("http://localhost:16223/api/inscrit/" + this.props.subscriber.id)
        .set("Authorization","Bearer " + getCookie("token"))
        .then(() => this.props.refresh())
    }
}

function getCookie(sName) {
    var cookContent = document.cookie, cookEnd, i, j;
    var sName = sName + "=";

    for (i=0; i<cookContent.length; i++) {
            j = i + sName.length;
            if (cookContent.substring(i, j) == sName) {
                    cookEnd = cookContent.indexOf(";", j);
                    if (cookEnd == -1) {
                            cookEnd = cookContent.length;
                    }
                    return decodeURIComponent(cookContent.substring(j, cookEnd));
            }
    }       
    return null;
}