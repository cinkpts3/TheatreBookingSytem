package utils;

public class CustomList<D> { //<D> is used for generic type of data

    public class Node<D> { //<D> is used for generic type of data and stands for data type
        D data;
        public Node<D> next;

        Node(D d) { // d is just data by itself
            this.data = d;
            this.next = null;
        }
    }

    Node<D> head;

    public void add(D d) { // adding new node
        if (head == null) {
            this.head = new Node<D>(d); // the node is becoming head of the list
            System.out.println("Added head: " + d); // Debugging
        } else {
            Node<D> newN = new Node<D>(d);
            Node<D> now = this.head; // start from the head

            while (now.next != null) { // looking for the last node
                now = now.next;
            }
            now.next = newN; // makes new node at the end
            System.out.println("Added node: " + d); // Debugging
        }
    }

    public void remove(int i) { // i is used as node number
        if (i == 0) {
            this.head = head.next; // assigns to component 0 value of next one
        } else {
            int n = 1; // if index is not 0, start counting from 1
            Node<D> count = this.head; // start from the head
            while (count != null) {
                if (n == i) {
                    count.next = count.next.next; // remove by skipping it
                    break;
                }
                count = count.next; // moving to the next node
                n++; // increase until it matches the index
            }
        }
    }

    public void show() {
        Node<D> node = head;
        while (node != null) {
            System.out.println(node.data); // Print each node's data
            node = node.next;
        }
    }

    public D get(int index) {
        Node<D> current = head;
        int count = 0;

        while (current != null) {
            if (count == index) {
                return current.data;
            }
            count++;
            current = current.next;
        }
        return null; // In case of invalid index
    }

    public int size() {
        int count = 0;
        Node<D> current = head; // starting from the head
        while (current != null) { // traverse until the end
            count++;
            current = current.next;
        }
        System.out.println("Size of CustomList: " + count); // Debugging
        return count;
    }
}
