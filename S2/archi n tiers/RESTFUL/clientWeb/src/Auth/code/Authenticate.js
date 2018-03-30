import React, {Component} from 'react';
import request from 'superagent';
import Connect  from './Connect.js';
import Subscribe from './Subscribe.js';
import FlatButton from 'material-ui/FlatButton';


export default class Authenticate extends Component {

    constructor(props){
        super(props);
    }

    render(){
        return <div>
            <span className="button">
                <Connect> Connexion </Connect>
            </span>
            <span className="button">
                <Subscribe> Inscription </Subscribe>
            </span>
        </div>
    }
    
}