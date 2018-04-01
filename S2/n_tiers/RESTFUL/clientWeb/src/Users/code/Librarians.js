import React, {Component} from 'react';
import RaisedButton from 'material-ui/RaisedButton'
import Librarian from './Librarian.js'
import CreateLibrarianPopUp from './CreateLibrarianPopUp.js'
import request from 'superagent';


export default class Librarians extends Component {

    constructor(props) {
        super(props);
        this.state = {
            librarians: []
        };
    }

    render() {
        return <div className="container" width="500px">
            <div className="card">
                <span className="button">
                    <RaisedButton onClick={this.getLibrarians}>Get Librarians</RaisedButton>            
                </span>
                <span className="button">
                    <CreateLibrarianPopUp refresh={this.getLibrarians}></CreateLibrarianPopUp>
                </span>
            </div>
            {this.state.librarians}
        </div>;

    }

    getLibrarians = () => {
        request.get("http://localhost:16223/api/bibliothecaire")
        .set("Authorization", "Bearer " + getCookie("token"))
        .then(
            (req) => {
                var librarians = JSON.parse(req.text)
                var librarianCards = librarians.map(librarian => <Librarian refresh={this.getLibrarians} librarian={librarian}></Librarian>)
                this.setState({
                    librarians: librarianCards
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