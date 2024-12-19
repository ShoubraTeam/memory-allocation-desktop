package memalloc;

public class CustomPriorityQueue<T> {
    private Node<T> first;

    static class Node<T> {
        private Node<T> next;
        private final T item;
        private final int key;

        private Node(T item, int key) {
            this.item = item;
            this.key = key;
        }
    }

    public void add(T item, int key) {
        Node<T> newNode = new Node<T>(item, key);
        if (isEmpty()) {
            first = newNode;
        } else {
            if (first.next == null && key < first.key) {
                newNode.next = first;
                first = newNode;
            } else {
                Node<T> current = first;
                while (current.next != null && current.key > key) {
                    current = current.next;
                }
                newNode.next = current.next;
                current.next = newNode;
            }
        }
    }

    public void display() {
        Node<T> current = first;
        while (current != null) {
            System.out.println(current.item);
            current = current.next;
        }

    }

    public T peek() {
        return first.item;
    }

    public T poll() {
        T temp = first.item;
        first = first.next;
        return temp;
    }

    public boolean isEmpty() {
        return first == null;
    }


}
