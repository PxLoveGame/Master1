import React, {Component} from 'react';
import {Tabs, Tab} from 'material-ui/Tabs';
import Books from './Books/code/Books.js'
import Subscribers from './Users/code/Subscribers.js'
import  Librarians from './Users/code/Librarians.js'
/*
const styles = {
    headline: {
      fontSize: 24,
      paddingTop: 16,
      marginBottom: 12,
      fontWeight: 400,
    },
  };
*/
export default class SwitchTabs extends Component {
    render(){
        return <Tabs>
                    <Tab label="Livres" >
                    <div>
                        
                        <p>
                        Voici l'onglet regroupant l'ensemble des livres pour cette bibliotheque.
                        </p>
                        <p>
                        <Books></Books>
                        </p>
                    </div>
                    </Tab>
                    <Tab label="Inscrits" >
                    <div>
                        
                        <p>
                            Voici l'onglet regroupant l'ensemble des Inscrits pour cette bibliotheque.
                        </p>
                        <p>
                            <Subscribers></Subscribers>
                        </p>
                    </div>
                    </Tab>
                    <Tab label="Bibliothecaires">
                    <div>
                        
                        <p>
                        Voici l'onglet regroupant l'ensemble des Bibliothecaires pour cette bibliotheque.
                        </p>
                        <p>
                            <Librarians></Librarians>
                        </p>
                    </div>
                    </Tab>
        </Tabs>
    }
}


