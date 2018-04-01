import React, {Component} from 'react';
import RaisedButton from 'material-ui/RaisedButton'
import FlatButton from 'material-ui/FlatButton';
import Dialog from 'material-ui/Dialog';
import TextField from 'material-ui/TextField';
import request from 'superagent';

export default class BookCreatePopup extends Component {

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
            <RaisedButton onClick={this.handleOpen}>Post book</RaisedButton>
              <Dialog
                  title="Création de livre"
                  actions={actions}
                  modal={true}
                  open={this.state.open}
              >
                <TextField hintText="Titre" onChange={(e, s) => this.setState({"title": s})}/><br/><br/>
                <TextField hintText="Auteur" onChange={(e, s) => this.setState({"author": s})}/><br/><br/>
                <TextField hintText="ISBN" onChange={(e, s) => this.setState({"isbn": s})}/><br/><br/>
                <TextField hintText="Editeur" onChange={(e, s) => this.setState({"editor": s})}/><br/><br/>
                <TextField hintText="Nombre d'exemplaire" onChange={(e, s) => this.setState({"nbEx": s})}/><br/><br/>
              </Dialog>
        </span>
    }

    postBook = () => {
        console.log(this.title);
        request.post("http://localhost:16223/api/livre")
        .set("Authorization", "Bearer " + getCookie("token"))
        .send({
            "isbn": Number(this.state.isbn),
            "titre": this.state.title,
            "auteur": this.state.author,
            "nbExemplaire": Number(this.state.nbEx),
            "editeur": this.state.editor
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