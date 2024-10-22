package utils;

public class CustomList<D>{ //<D> is used for generic type of data

    public class Node <D>{ //<D> is used for generic type of data and stands for data type
        D data;
        public Node<D> next;
        Node (D d){ //d is just data by itself
            this.data = d;
            this.next = null;

        }
    }


        Node <D> head;

        public void add(D d){ //adding new node
            if (head == null) {
                this.head = new Node<D>(d); //the node is becoming head of the list
            }
            else{
                Node<D> newN = new Node<D>(d);
                Node<D> now = this.head; //last is assigned to the first node in the list

                while (now.next !=null){ //looking for the last node, the last node is going to be empty
                    now = now.next; //the last node is next node in the list

                }
                now.next = newN; //makes new node
            }
        }



        public void remove(int i){ //i is used as node number
            if (i == 0){
                this.head = head.next; //assigns to component 0 value of next one
            }
            else {
                int n = 1; //if index is not 0 its 1
                Node count = this.head;//start traversing from the head
                while(count != null){
                    if (n == i){
                        count.next = count.next.next; //removing the node by skipping it
                        break;
                    }
                    count = count.next; //moving to the next node wjile looking for the node to delete
                    n++; // increasing the value until its not equals to the i
                }
            }

        }

        public void show(){
            Node<D> node = head;
            while (node.next!=null){
                System.out.println(node.data);
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


}
