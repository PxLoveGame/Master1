import React, {Component} from 'react';
import '../design/Books.css';
import {List, ListItem} from 'material-ui/List';
import FlatButton from 'material-ui/FlatButton';
import Comment from './Comment.js'
import request from 'superagent';


export default class BookComments extends Component {


    render() { 
        return <List>
            {this.props.comments}
        </List>;
    } 

   
}
