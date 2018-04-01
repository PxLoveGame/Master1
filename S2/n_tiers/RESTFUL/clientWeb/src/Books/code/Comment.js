import React, {Component} from 'react';
import '../design/Books.css';
import {List, ListItem} from 'material-ui/List';
import FlatButton from 'material-ui/FlatButton';
import request from 'superagent';


export default class Comment extends Component{
    render(){
        var comment = this.props.comment;
        return 
            <ListItem
                primaryText={comment.auteur}
                secondaryText={comment.contenu}
            />,
            <FlatButton label="Supprimer" onClick={this.delete} secondary={true}/>
            
    }

    delete = () => {
        request.delete("http://localhost:16223/api/Commentaire/" + this.props.comment.id)
        .set("Authorization", "Bearer " + getCookie("token"))
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