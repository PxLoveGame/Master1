import React, {Component} from 'react';
import {Card, CardActions, CardHeader, CardText} from 'material-ui/Card';
import BookComments from './BookComments.js';
import FlatButton from 'material-ui/FlatButton';
import request from 'superagent';
import CommentCreatePopUp from './CommentCreatePopUp.js'
import Comment from './Comment.js'
import {List, ListItem} from 'material-ui/List';


export default class Book extends Component {
    state = {
        buttonHidden: true,
        comments: []
    };

    render() {
        const book = this.props.book;
        const comments = book.commentaires;

        return <Card onExpandChange={() => this.setState({buttonHidden: !this.state.buttonHidden})}>
            <CardHeader
                title={book.titre + " - " + book.isbn}
                subtitle={book.auteur + " - " + book.editeur + " - " + book.nbExemplaire}
                actAsExpander={true}
                showExpandableButton={true}
            />
            <CardActions>
                    <span hidden={this.state.buttonHidden}>
                        <FlatButton label="Supprimer" onClick={this.delete} secondary={true}/>
                    </span>
            </CardActions>
            <CardText onClick={this.getComments} expandable={true}>
                <List>
                    {this.state.comments}
                </List>
            </CardText>
            <CommentCreatePopUp refresh={comments} bookId={this.props.book.id}> </CommentCreatePopUp>
        </Card>;

    }

    getComments = () => {
        request.get("http://localhost:16223/api/commentaire/" + this.props.book.id)
        .set("Authorization", "Bearer " + getCookie("token"))
        .then(
            (req) => {
                var comments = JSON.parse(req.text)
                var commentList = comments.map(comment => <Comment comment={comment}></Comment>)
                this.setState({
                    comments: commentList
                })
            }
        )
    }  

    delete = () => {
        request.delete("http://localhost:16223/api/livre/" + this.props.book.id)
        .set("Authorization", "Bearer " + getCookie("token"))
        .then(() => this.props.refresh())
    }

    //192.168.1.63:3000
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