import React, {Component} from 'react';
import RaisedButton from 'material-ui/RaisedButton'
import FlatButton from 'material-ui/FlatButton';
import Dialog from 'material-ui/Dialog';
import TextField from 'material-ui/TextField';
import request from 'superagent';

export default class CreateSubPopUp extends Component {

    state = {
        open: false
    };

    handleOpen = () => {
        this.setState({open: true});
    };

    handleClose = () => {
        this.setState({open: false});
    };

    render() {
        const actions = [
            <FlatButton
                label="Annuler"
                secondary={true}
                onClick={this.handleClose}
            />,
            <FlatButton
                label="Poster"
                primary={true}
                onClick={this.postBook}
            />,
        ];

        return <span>
            <RaisedButton onClick={this.handleOpen}>Post Subscriber</RaisedButton>
              <Dialog
                  title="Création d'un inscrit"
                  actions={actions}
                  modal={true}
                  open={this.state.open}
              >
                <TextField hintText="Nom" onChange={(e, s) => this.setState({"nom": s})}/><br/><br/>
                <TextField hintText="Mot de passe" onChange={(e, s) => this.setState({"mdp": s})}/><br/><br/>
                <TextField hintText="Confirmation" onChange={(e, s) => this.setState({"confirmation": s})}/><br/><br/>
              </Dialog>
        </span>
    }

    postBook = () => {
        console.log(this.title);
        request.post("http://localhost:16223/api/inscrit")
        .set("Authorization","Bearer " + getCookie("token"))
        .send({
            "nom": Number(this.state.nom),
            "mdp": this.state.mdp,
            "confirmation": this.state.confirmation,
        }).then(() => {
            this.props.refresh();
            this.handleClose()
        })
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