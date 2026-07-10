class MyLinkedList {

    class Node {
        int val;
        Node next;

        Node(int val) {
            this.val = val;
        }
    }

    Node head;
    int size;

    public MyLinkedList() {
        head = null;
        size = 0;
    }

    // Get value at index
    public int get(int index) {
        if (index < 0 || index >= size)
            return -1;

        Node curr = head;

        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }

        return curr.val;
    }

    // Add at beginning
    public void addAtHead(int val) {
        Node node = new Node(val);
        node.next = head;
        head = node;
        size++;
    }

    // Add at end
    public void addAtTail(int val) {
        Node node = new Node(val);

        if (head == null) {
            head = node;
        } else {
            Node curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = node;
        }

        size++;
    }

    // Add at given index
    public void addAtIndex(int index, int val) {

        if (index < 0 || index > size)
            return;

        if (index == 0) {
            addAtHead(val);
            return;
        }

        Node curr = head;

        for (int i = 0; i < index - 1; i++) {
            curr = curr.next;
        }

        Node node = new Node(val);
        node.next = curr.next;
        curr.next = node;

        size++;
    }

    // Delete node at index
    public void deleteAtIndex(int index) {

        if (index < 0 || index >= size)
            return;

        if (index == 0) {
            head = head.next;
            size--;
            return;
        }

        Node curr = head;

        for (int i = 0; i < index - 1; i++) {
            curr = curr.next;
        }

        curr.next = curr.next.next;
        size--;
    }
}