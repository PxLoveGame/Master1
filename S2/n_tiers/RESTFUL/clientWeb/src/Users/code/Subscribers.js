import React, {Component} from 'react';
import RaisedButton from 'material-ui/RaisedButton'
import Subscriber from './Subscriber.js'
import CreateSubPopUp from './CreateSubPopUp.js'
import request from 'superagent';


export default class Subscribers extends Component {

    constructor(props) {
        super(props);
        this.state = {
            subscribers: []
        };
    }

    render() {
        return <div className="container" width="500px">
            <div className="card">
                <span className="button">
                    <RaisedButton onClick={this.getSubs}>Get Subscribers</RaisedButton>            
                </span>
                <span className="button">
                    <CreateSubPopUp refresh={this.getSubs}></CreateSubPopUp>
                </span>
            </div>
            {this.state.subscribers}
        </div>;

    }

    getSubs = () => {
        request.get("http://localhost:16223/api/inscrit")
        .set("Authorization", "Bearer " + getCookie("token"))
        .then(
            (req) => {
                var subs = JSON.parse(req.text)
                var subCards = subs.map(sub => <Subscriber refresh={this.getSubs} subscriber={sub}></Subscriber>)
                this.setState({
                    subscribers: subCards
                })
            }
        )
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