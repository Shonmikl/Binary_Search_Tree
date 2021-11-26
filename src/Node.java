public class Node {
    public Node left;   // left child
    public Node right;  // right child
    public Integer value;  // value

    //  9  2  5  13  6  10  14  7  33  2  3  //
    public static void main(String[] args) {
        Integer[] digit = {9, 2, 5, 13, 6, 10, 14, 7, 33, 44, 3};
        Node node = new Node();
        createNode(node, 9);
        for (int i = 1; i < digit.length; i++) {
            insert(node, digit[i]);
        }
        inOrderTraversal(node);
        remove(node, 10);
        System.out.println();
        inOrderTraversal(node);
    }

    private static boolean isNodeExist(Node node) {
        return node != null && node.value != null;
    }

    private static void createNode(Node node, int value) {
        node.left = new Node();
        node.right = new Node();
        node.value = value;
    }

    private static void insert(Node node, int value) {
        if (!isNodeExist(node)) {
            createNode(node, value);
        } else if (value < node.value) {
            insert(node.left, value);
        } else {
            insert(node.right, value);
        }
    }

    private static Node search(Node node, int value) {
        if (!isNodeExist(node)) {
            return null;
        }
        if (node.value == value) {
            return node;
        }
        if (value < node.value) {
            return search(node.left, value);
        }
        return search(node.right, value);
    }

    private static Node getMin(Node node) {
        if (!isNodeExist(node)) {
            return null;
        }
        if (!isNodeExist(node.left)) {
            return node;
        }
        return getMin(node.left);
    }

    private static Node getMax(Node node) {
        if (!isNodeExist(node)) {
            return null;
        }
        if (!isNodeExist(node.right)) {
            return node;
        }
        return getMax(node.right);
    }

    //to sorting
    private static void inOrderTraversal(Node node) {
        if (!isNodeExist(node)) {
            return;
        }
        inOrderTraversal(node.left);
        System.out.print("[ " + node.value + " ]");
        inOrderTraversal(node.right);
    }

    //to delete
    private static void postOrderTraversal(Node node) {
        if (!isNodeExist(node)) {
            return;
        }
        postOrderTraversal(node.left);
        postOrderTraversal(node.right);
        System.out.print(node.value);
    }

    //to copy a tree
    private static void directOrderTraversal(Node node) {
        if (!isNodeExist(node)) {
            return;
        }
        System.out.print(node.value);
        directOrderTraversal(node.left);
        directOrderTraversal(node.right);
    }

    private static void moveNode(Node toNode, Node fromNode) {
        toNode.value = fromNode.value;
        toNode.left = fromNode.left;
        toNode.right = fromNode.right;
    }

    private static int getChildrenCount(Node node) {
        int count = 0;
        if (isNodeExist(node.left)) {
            count += 1;
        }
        if (isNodeExist(node.right)) {
            count += 1;
        }
        return count;
    }

    private static Node getChildOrNull(Node node) {
        return isNodeExist(node.left) ? node.left : node.right;
    }

    private static void removeNodeWithOneOrZeroChild(Node nodeToDelete) {
        Node childOrNull = getChildOrNull(nodeToDelete);
        moveNode(nodeToDelete, childOrNull);
    }

    private static boolean remove(Node root, int value) {
        Node nodeToDelete = search(root, value);
        if (!isNodeExist(nodeToDelete)) {
            return false;
        }
        int childrenCount = getChildrenCount(nodeToDelete);
        if (childrenCount < 2) {
            removeNodeWithOneOrZeroChild(nodeToDelete);
        } else {
            Node minNode = getMin(nodeToDelete.right);
            nodeToDelete.value = minNode.value;
            removeNodeWithOneOrZeroChild(minNode);
        }
        return true;
    }
}