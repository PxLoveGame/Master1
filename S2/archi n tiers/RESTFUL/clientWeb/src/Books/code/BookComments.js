import React, {Component} from 'react';
import './design/Books.css';
import {List, ListItem} from 'material-ui/List';

export default class BookComments extends Component {

    render() {
        const comments = this.props.comments;
        const comList = comments.map(c =>
            <ListItem
                primaryText={c.auteur}
                secondaryText={c.contenu}
            />
        );
        return <List>
            {comList}
        </List>;
    }
}