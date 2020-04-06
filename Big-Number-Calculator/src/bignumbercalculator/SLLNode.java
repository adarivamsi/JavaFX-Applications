/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bignumbercalculator;

/**
 *
 * @author adari
 */
public class SLLNode {
    
    protected Object element;
    protected SLLNode snode;
    
    public SLLNode(Object ele, SLLNode n){
        element = ele;
        snode = n;
    }
    
}