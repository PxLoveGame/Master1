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
                <TextField hintText="Mot de passe" onChange={(e, s) => this.setState({"mdp": s})}/><br/><br/>
              </Dialog>
        </span>
    }

    connectUser = () => {
        console.log(this.title);
        request.post("http://192.168.1.63:3000/oauth2/token").send({
            "nom":  this.state.nom,
            "mdp": this.state.mdp,
        }).then(() => {
            this.props.refresh();
            this.handleClose()
        })
    }
}