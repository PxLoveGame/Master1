import React, {Component} from 'react';
import RaisedButton from 'material-ui/RaisedButton'
import FlatButton from 'material-ui/FlatButton';
import Dialog from 'material-ui/Dialog';
import TextField from 'material-ui/TextField';
import request from 'superagent';

export default class CommentCreatePopUp extends Component {

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
                onClick={this.postComment}
            />,
        ];

        return <span>
            <RaisedButton onClick={this.handleOpen}>Ecrire un commentaire</RaisedButton>
              <Dialog
                  title="Création d'un commentaire"
                  actions={actions}
                  modal={true}
                  open={this.state.open}
              >
                <TextField hintText="Auteur" onChange={(e, s) => this.setState({"author": s})}/><br/><br/>
                <TextField hintText="Contenu" onChange={(e, s) => this.setState({"content": s})}/><br/><br/>
              </Dialog>
        </span>
    }

    postComment = () => {
        request.post("http://localhost:16223/api/livre/" + this.props.bookId + "/commentaire")
        .set("Authorization", "Bearer " + getCookie("token"))
        .send({
            "auteur": this.state.author,
            "contenu": this.state.content
        }).then(() => {
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