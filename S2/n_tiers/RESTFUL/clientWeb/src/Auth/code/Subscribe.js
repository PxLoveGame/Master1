import React, {Component} from 'react';
import RaisedButton from 'material-ui/RaisedButton'
import FlatButton from 'material-ui/FlatButton';
import Dialog from 'material-ui/Dialog';
import TextField from 'material-ui/TextField';
import request from 'superagent';

export default class Subscribe extends Component {

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
                label="S'inscrire"
                primary={true}
                onClick={this.postUser}
            />,
        ];

        return <span>
            <RaisedButton onClick={this.handleOpen}> S'inscrire </RaisedButton>
              <Dialog
                  title="Inscription"
                  actions={actions}
                  modal={true}
                  open={this.state.open}
              >
                <TextField hintText="Nom" onChange={(e, s) => this.setState({"nom": s})}/><br/><br/>
                <TextField hintText="Mot de passe" type="password" onChange={(e, s) => this.setState({"mdp": s})}/><br/><br/>
                <TextField hintText="Vérification" type="password" onChange={(e, s) => this.setState({"confirmation": s})}/><br/><br/>
              </Dialog>
        </span>
    }

    postUser = () => {
        console.log(this.title);
        request.post("http://localhost:16223/api/inscrit").send({
            "nom":  this.state.nom,
            "mdp": this.state.mdp,
            "confirmation": this.state.confirmation,
        }).then(() => {
            this.handleClose()
        })
    }
}