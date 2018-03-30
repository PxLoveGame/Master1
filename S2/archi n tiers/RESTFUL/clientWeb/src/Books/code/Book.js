import React, {Component} from 'react';
import {Card, CardActions, CardHeader, CardText} from 'material-ui/Card';
import BookComments from './BookComments.js';
import FlatButton from 'material-ui/FlatButton';
import request from 'superagent';

export default class Book extends Component {
    state = {
        buttonHidden: true
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
                        <FlatButton label="Commenter" primary={true}/>
                        <FlatButton label="Supprimer" onClick={this.delete} secondary={true}/>
                    </span>
            </CardActions>
            <CardText expandable={true}>
                <BookComments comments={comments}/>
            </CardText>
        </Card>;

    }

    delete = () => {
        request.delete("http://192.168.1.63:3000/api/livre/" + this.props.book.id).then(() => this.props.refresh())
    }
}