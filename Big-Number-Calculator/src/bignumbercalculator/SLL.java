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
public class SLL {
    
    private SLLNode first;
    
    public SLL(){
        this.first = null;
    }
    
    public void setFirst(SLLNode node){
        this.first = node;
    }
    
    public SLLNode getFirst(){
        return this.first;
    }
    
    public void printFirstToLast(){
        for(SLLNode current = first; current != null; current = current.snode){
            System.out.print(current.element + " ");
        }
        System.out.println("");
    }
    
    public void insertAtFront(SLLNode node){
        node.snode = this.first;
        this.first = node;
    }
    
    public void insertAfter(Object obj, SLLNode node){
        for(SLLNode here = this.first;here!=null;here=here.snode){
            if(here.element.equals(obj))
            {
                node.snode = here.snode;
                here.snode = node;
                return;
            }
        }
    }
    
    public void insertAtEnd(Object obj){
        SLLNode here = this.first;
        if(here == null){
            this.first = new SLLNode(obj, null);
        }
        else{
            for(; here.snode != null; here = here.snode);
            here.snode = new SLLNode(obj, null);
        }
    }
    
    public void concatSLL(SLL n){
        SLLNode node = n.getFirst();
        SLLNode here=this.first;
        if(here == null)
        {
            this.first = node;
        }
        else
        {
            for(;here.snode!=null;here=here.snode)  ;          
                here.snode=node; 
        }       
    }
    
    public void delete(SLLNode del){
        SLLNode snode = del.snode;
        if(del == first)
            first = snode;
        else{
            SLLNode pred = first;
            while(pred.snode != del)
                pred = pred.snode;
            pred.snode = snode;
        }
    }
    
    public int getLength(){
        int count = 0;
        for(SLLNode here = this.first; here != null; here = here.snode){
            count++;
        }
        return count;
    }
    
    public void replace(Object obj,SLLNode node){
        node.element = obj;
    }
    
    void modifyInt(int length)
    {
        int i = length - this.getLength(); 
        for(int j = 0; j < i; j++)
        {
            this.insertAtEnd("0");
        }
    }
    
    void modifyDec(int length)
    {
        int i = length - this.getLength();
        for(int j = 0; j < i; j++)
        {
            this.insertAtFront(new SLLNode("0",null));
        }
    }
}