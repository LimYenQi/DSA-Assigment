/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt;

import adt.SortedListInterface;
/**
 *
 * @author Yap
 */
public class SortedLinkedList<T extends Comparable<T>> implements SortedListInterface<T> {
    private Node firstNode;
    private int numberOfEntries;
    
    public SortedLinkedList() {
        firstNode = null;
        numberOfEntries = 0;
    }    
    
    @Override
    public boolean add(T newEntry){
        Node newNode = new Node(newEntry);
        
        Node tempNode = firstNode;
        Node beforeNode = null;
        
        while(tempNode != null && newEntry.compareTo(tempNode.data) > 0){ 
            beforeNode = tempNode;
            tempNode = tempNode.next;
        }
        if(beforeNode != null){
            newNode.next = tempNode;
            beforeNode.next = newNode;
        }
        else{
            newNode.next = firstNode;
            firstNode = newNode;
        }
        numberOfEntries++;
        return true;
    }
    
    @Override
    public String toString(){
        Node tempNode = firstNode;
        String song = "";
        
        while(tempNode != null){
            song += tempNode.data;
            tempNode = tempNode.next;
        }
        return song;
    }
        
    @Override
    public T getEntry(int givenPosition){
        Node tempNode = firstNode;
        T result = null;
        int i = 1;
        
        if(givenPosition >= 1 && givenPosition <= numberOfEntries){
            while(i < givenPosition){
                tempNode = tempNode.next;
                i++;
            }
            result = tempNode.data;
        }
        return result;
    }
    
    @Override
    public boolean remove(T entry){ 
        
        if(!isEmpty()){        
            Node tempNode = firstNode;
            Node beforeNode = null;
            
            while(tempNode != null && entry.compareTo(tempNode.data) < 0){
                beforeNode = tempNode;
                tempNode = tempNode.next;
            }
            if(tempNode != null && tempNode.data.equals(entry)){
                if(tempNode == firstNode){
                    firstNode = firstNode.next;
                }
                else{
                    beforeNode.next = tempNode.next;
                }
                numberOfEntries--;
                return true;
            }
        }
        else
            return false;
        return false;
    }
    
    @Override
    public void clearAll(){
        firstNode = null;
        numberOfEntries = 0;
    }
    
    @Override
    public boolean isEmpty(){
        return (numberOfEntries == 0);
    }
    
    @Override
    public int getNumberOfEntries(){
        return numberOfEntries;
    }
    
    private class Node{
        private T data;
        private Node next;
        
        private Node(T data) {
        this.data = data;
        next = null;
        }

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
    
}
