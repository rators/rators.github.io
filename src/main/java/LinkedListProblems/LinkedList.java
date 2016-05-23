package LinkedListProblems;

import java.util.HashSet;
import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 */
public class LinkedList {
    private Node head = null;

    public void append(int n) {
        if (head == null) {
            head = new Node(n);
        } else {
            head.appendToTail(n);
        }
    }

    public void deleteNode(int d) {
        Node n = head;

        //check if the head needs to be removed;
        if (n.data == d) {
            head = head.next;
            return;
        }

        while (n.next != null) {
            if (n.next.data == d) {
                n.next = n.next.next;
                return;
            }
            n = n.next; // go to the next node.
        }
    }

    void removeDuplicates() {

        if (head.next == null) return;

        Node current = head; // the node to remove dups for.
        while (current != null) { // current = n -> n1 -> n2 (current) -> n3
            Node n = current;
            System.out.println(n);
            while (n.next != null) { // start with the neighbor of the head.
                if (n.next.data == current.data) { // find duplicate
                    n.next = n.next.next; // delete duplicate
                } else {
                    n = n.next; // only change the parent node when you don't need to delete it's child
                    //
                }
            }
            current = current.next;
        }
    }

    public String toString() {
        String s = "";
        Node n = head;
        while (n != null) {
            if (n.next != null) s += n.data + " -> ";
            else s += n.data;
            n = n.next;
        }
        return s;
    }

    public static void main(String[] args) {
        LinkedList l = new LinkedList();
        l.append(3);
        l.append(4);
        l.append(9);
        l.append(9);
        l.append(3);
        System.out.println(l.isPalindrome());

    }


    private void partition(int pivot) {
        LinkedList left = new LinkedList();
        LinkedList right = new LinkedList();
        LinkedList center = new LinkedList();
        Node n = head;

        while (n != null) {
            if (n.data > pivot) right.append(n.data);
            else if (n.data < pivot) left.append(n.data);
            else center.append(n.data);
            n = n.next;
        }
        System.out.println("Left list: " + left);
        System.out.println("Center list: " + center);
        System.out.println("Right list: " + right);
    }

    private static Node add(Node first, Node second, int carry) {
        if (first == null && second == null) return carry > 0 ? new Node(carry): null;
        int result = first.data + second.data + carry;
        String resultStr = Integer.toString(result);
        if (result > 10) {
            int inPlaceValue = Integer.parseInt(resultStr.charAt(1) + ""); // if 32 then 2.
            int carryValue = Integer.parseInt(resultStr.charAt(0) + ""); // if 32 then 2.
            Node inPlace = new Node(inPlaceValue);
            inPlace.appendToTail(add(first.next, second.next, carryValue));
            return inPlace;
        } else {
            int inPlaceValue = Integer.parseInt(resultStr.charAt(0) + ""); // if 32 then 2.
            Node resultNode = new Node(inPlaceValue);
            resultNode.appendToTail(add(first.next, second.next, 0));
            return resultNode;
        }
    }

    private Node findCircular(){
        HashSet<Node> set = new HashSet<Node>();
        Node n = head;
        while(n != null){
            if(set.contains(n)) return n;
            set.add(n);
        }
        return null;
    }

    private boolean isPalindrome(){
        Stack<Integer> reversed = new Stack<Integer>();
        Node n = head;

        while(n != null){
            reversed.push(n.data);
            n = n.next;
        }

        n = head;
        while(n != null && reversed.pop() == n.data) n = n.next;
        return n == null;
    }

}
