import React, {Component} from 'react';
import RaisedButton from 'material-ui/RaisedButton'
import FlatButton from 'material-ui/FlatButton';
import Dialog from 'material-ui/Dialog';
import TextField from 'material-ui/TextField';
import request from 'superagent';

export default class Connect extends Component {
    
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
                label="Connexion"
                primary={true}
                onClick={this.connectUser}
            />,
        ];

        return <span>
            <RaisedButton onClick={this.handleOpen}> Connexion </RaisedButton>
              <Dialog
                  title="Connexion"
                  actions={actions}
                  modal={true}
                  open={this.state.open}
              >
                <TextField hintText="Nom" onChange={(e, s) => this.setState({"nom": s})}/><br/><br/>
                <TextField hintText="Mot de passe" type="password" onChange={(e, s) => this.setState({"mdp": s})}/><br/><br/>
              </Dialog>
        </span>
    }

    connectUser = () => {
        console.log(this.title);
        request.post("http://localhost:16223/oauth2/token")
        .set('Content-Type','application/x-www-form-urlencoded')
        .send({
            "grant_type": "password",
            "username":  this.state.nom,
            "password": this.state.mdp
        })
        .then((param) => {
            console.log(param);
            setCookie("token", param.body.access_token);
            this.handleClose()
        })
    }
}

function setCookie(sName, sValue) {
    var today = new Date(), expires = new Date();
    expires.setTime(today.getTime() + (30*60*1000));
    document.cookie = sName + "=" + encodeURIComponent(sValue) + ";expires=" + expires.toGMTString();
}