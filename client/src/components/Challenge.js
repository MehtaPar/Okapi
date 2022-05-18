import React from "react";
import { Component } from "react";


class Challenge extends Component {
    constructor(props) {
        super();
        this.state = {
            cost: 0.0,
            candy: []
        }
 }


getLowStock = () => {
    //Fetch request
    fetch('http://localhost:4567/low-stock')
            .then(response => response.json())
            .then(data => {
                this.state.candyInventory = data;
                this.setState({})
            })
        }



//MAIN Func
render() {
  return (
    <>
      <table>
        <thead>
          <tr>
            <td>SKU</td>
            <td>Item Name</td>
            <td>Amount in Stock</td>
            <td>Capacity</td>
            <td>Order Amount</td>
          </tr>
        </thead>
        <tbody>
          {/* 
          TODO: Create an <ItemRow /> component that's rendered for every inventory item. The component
          will need an input element in the Order Amount column that will take in the order amount and 
          update the application state appropriately.
          */}
        </tbody>
      </table>
      {/* TODO: Display total cost returned from the server */}
      <div>Total Cost: </div>
      {/* 
      TODO: Add event handlers to these buttons that use the Java API to perform their relative actions.
      */
      }
      <button id = "lowStock" onClick={this.getLowStock}>Get Low-Stock Items</button>
      <button>Determine Re-Order Cost</button>
    </>
  );
}
}


export default Challenge;