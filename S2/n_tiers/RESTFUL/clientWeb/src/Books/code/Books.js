import React, {Component} from 'react';
import RaisedButton from 'material-ui/RaisedButton'
import Book from './Book.js'
import BookCreatePopup from './BookCreatePopup.js'
import request from 'superagent';
import './../design/Books.css';

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
                    <BookCreatePopup refresh={this.getBooks}></BookCreatePopup>
                </span>
            </div>
            {this.state.books}
        </div>;

    }

    getBooks = () => {
        request.get("http://localhost:16223/api/livre")
        .then(
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