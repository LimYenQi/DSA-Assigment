/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt;

/**
 *
 * @author Lim Yen Qi
 */
public class DoublyLinkedList<T> implements ListInterface<T>{
    private Node firstNode;
    private Node lastNode;
    private int numOfEntries;

    public DoublyLinkedList() {
        firstNode = null;
        lastNode = null;
    }

    @Override
    public boolean add(T newEntry) {
        Node newNode = new Node(newEntry);
        
        if(isEmpty()) {     //if it is an empty list
            firstNode = newNode;
            lastNode = newNode;
        } else {        //add newNode to the last
            lastNode.next = newNode;
            newNode.previous = lastNode;
            lastNode = newNode;
            lastNode.next = null;
        }
        numOfEntries++;
        return true;
    }

    @Override
    public boolean add(T newEntry, Integer pos) {
        Node newNode = new Node(newEntry);
        Node currentNode = firstNode;
        boolean success = true;
        
        if ((pos >= 1) && (pos <= numOfEntries + 1)) {
            if ((isEmpty()) || (pos == 1)) {        //if add at the 1st position
                firstNode = newNode;
                lastNode = newNode;
            } else if (pos == numOfEntries + 1) {   //add at the last position
                lastNode.next = newNode;
                newNode.previous = lastNode;
                lastNode = newNode;
                newNode.next = null;
            } else {                        //add at other position
                Node be4Node;
                for (int i = 1; i < pos; i++) {
                    currentNode = currentNode.next;
                }
                be4Node = currentNode.previous;
                newNode.next = currentNode;
                newNode.previous = be4Node;
                be4Node.next = newNode;
                currentNode.previous = newNode;
            }
            numOfEntries++;
        } else {
            success = false;
        }
        return success;
    }

    @Override
    public boolean removeAll() {
        firstNode = null;
        lastNode = null;
        numOfEntries = 0;
        return (firstNode == null) && (lastNode == null);
    }

    @Override
    public boolean remove(Integer pos) {
        boolean success = false;
        Node currentNode = firstNode;
        
        if (pos >= 1 && pos <= numOfEntries) {
            if (pos == 1) {         //remove from 1st position
                firstNode = firstNode.next;
                if (numOfEntries == 1){
                    removeAll();
                }
            } else if (pos == numOfEntries) {      //remove from last position
                lastNode = lastNode.previous;
                lastNode.next = null;
            } else {                //remove from the position between 1 and last
                Node b4Node, nextNode;
                for (int i = 1; i < pos; i ++) {
                    currentNode = currentNode.next;
                }
                b4Node = currentNode.previous;
                nextNode = currentNode.next;
                b4Node.next = nextNode;
                nextNode.previous = b4Node;
            }
            numOfEntries -= 1;
            success = true;
        }
        
        return success;
    }

    @Override
    public T getEntry(Integer pos) {
        T entry = null;
        if ((pos >= 1) && (pos <= numOfEntries)) {
            Node currentNode = firstNode;
            for (int i = 1; i < pos; i++) {
                currentNode = currentNode.next;
            }
            entry = currentNode.data;
        }
        return entry;
    }

    @Override
    public boolean replace(Integer pos, T newEntry) {
        Node newNode = new Node(newEntry);
        Node currentNode = firstNode;
        boolean success = false;
        
        if ((pos >= 1) && (pos <= numOfEntries)) {
            Node b4Node, nextNode;
            for (int i = 1; i < pos; i++) {
                currentNode = currentNode.next;
            }
            b4Node = currentNode.previous;
            nextNode = currentNode.next;
            newNode.next = currentNode.next;
            newNode.previous = currentNode.previous;
            if (pos > 1) {
                b4Node.next = newNode;
            }
            if (pos < numOfEntries) {
                nextNode.previous = newNode;
            }
            if (pos == numOfEntries) {
                lastNode = newNode;
            } else if (pos == 1) {
                firstNode = newNode;
            }
            success = true;
        }
        
        return success;
    }

    @Override
    public String display(int order) {
        String output = "";
        if (order == 1) {
            Node currentNode = firstNode;
            while (currentNode != null) {
                output += currentNode.data;
                currentNode = currentNode.next;
            }
        } else if (order == 2) {
            Node currentNode = lastNode;
            while (currentNode != null) {
                output += currentNode.data;
                currentNode = currentNode.previous;
            }
        } else {
            output += "Invalid input. Please only enter 1 or 2.";
        }
        
        return output;
    }
    
    @Override
    public int getSize() {
        return numOfEntries;
    }

    @Override
    public int contains(T anEntry) {
        int found = -1;
        Node currentNode = firstNode;
        
        if (anEntry != null) {
            for (int i = 0; found == -1 && i < numOfEntries; i++) {
                if (currentNode != null && anEntry.equals(currentNode.data)) {
                    found = i + 1;
                }
                currentNode = currentNode.next;
            }
        }
        
        return found;
    }

    @Override
    public boolean isEmpty() {
        return (firstNode == null) && (lastNode == null);
    }
    
    private class Node {
        private T data;
        private Node previous;
        private Node next;

        private Node(T data) {
          this.data = data;
          this.previous = null;
          this.next = null;
        }

        private Node(T data, Node next, Node previous) {
          this.data = data;
          this.next = next;
          this.previous = previous;
        }
      }
}