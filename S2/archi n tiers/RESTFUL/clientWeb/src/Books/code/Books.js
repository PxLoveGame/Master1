import React, {Component} from 'react';
import RaisedButton from 'material-ui/RaisedButton'
import Book from './Book.js'
import BookCreatePopup from './BookCreatePopup.js'
import request from 'superagent';
import './design/Books.css';

export default class Books extends Component {

    constructor(props) {
        super(props);
        this.state = {
            books: []
        };
    }

    render() {
        return <div className="container" width="500px">
            <div className="card">
                <span className="button">
                    <RaisedButton onClick={this.getBooks}>Get books</RaisedButton>            
                </span>
                <span className="button">
                    <BookCreatePopup refresh={this.getBooks}>Post book</BookCreatePopup>
                </span>
            </div>
            {this.state.books}
        </div>;

    }

    getBooks = () => {
        request.get("http://192.168.1.63:3000/api/livre").then(
            (req) => {
                var books = JSON.parse(req.text)
                var bookCards = books.map(book => <Book refresh={this.getBooks} book={book}></Book>)
                this.setState({
                    books: bookCards
                })
            }
        )
    }
}