
import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Amin
 */
public class Currency implements Serializable{

    private String name;
    private String price;
    private String change;
    private String graph;

    public Currency(String name, String price, String change, String graph) {
        this.name = name;
        this.price = price;
        this.change = change;
        this.graph = graph;
    }

    public Currency(String name, String price, String change) {
        this.name = name;
        this.price = price;
        this.change = change;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public String getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * @return the change
     */
    public String getChange() {
        return change;
    }

    /**
     * @param change the change to set
     */
    public void setChange(String change) {
        this.change = change;
    }

    /**
     * @return the graph
     */
    public String getGraph() {
        return graph;
    }

    /**
     * @param graph the graph to set
     */
    public void setGraph(String graph) {
        this.graph = graph;
    }
}
