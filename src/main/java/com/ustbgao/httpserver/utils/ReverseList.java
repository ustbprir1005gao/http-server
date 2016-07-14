package com.ustbgao.httpserver.utils;

/**
 * Created by ustbgao
 */
public class ReverseList {
    class node{
        int data;
        node next;

        node(int data) {
            this.data = data;
        }
    }
    public node createList(){
        node head = new node(0);
        head.next = null;
        node temp = head;
        for(int i=1; i<7; i++){
            node p = new node(i);
            temp.next =p;
            temp = p;
        }
        temp.next = null;
        return head;
    }
    public void displayList(node head){
        node p = head.next;
        while(p != null){
            System.out.print(p.data + " ");
            p = p.next;
        }
    }
    public node reverseList(node head){
        node p = head.next;
        node q = null;
        while(p.next != null){
            q = p.next;
            p.next = q.next;
            q.next = head.next;
            head.next = q;
        }

        return head;
    }
    public static void main(String [] args){

        ReverseList reverseList = new ReverseList();
        node head = reverseList.createList();
        reverseList.displayList(head);
        reverseList.displayList(reverseList.reverseList(head));
    }
}
