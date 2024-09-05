public class AVLTree {
    private Node root;

    public void add(int value){
        add(root, value);
    }

    public void remove(int value){
        remove(root, value);
    }

    public Node getRoot(){
        return this.root;
    }

    public int getHeight(Node node){
        if(node == null) return -1;
        else return node.height;
    }

    public int fb(Node node){
        return Math.abs(getHeight(node.left) - getHeight(node.right));
    }

    private int greatest(int a, int b){
        if(a > b) return a;
        else return b;
    }

    private void rotateRight(Node root){
        Node node;
        node = root.left;
        root.left = node.right;
        node.right = root;
        root.height = greatest(getHeight(root.left), getHeight(root.right)) + 1;
        node.height = greatest(getHeight(node.left), getHeight(root)) + 1;
        root = node;
    }

    private void rotateLeft(Node root){
        Node node;
        node = root.right;
        root.right = node.left;
        node.left = root;
        root.height = greatest(getHeight(root.left), getHeight(root.right)) + 1;
        node.height = greatest(getHeight(node.right), getHeight(root)) + 1;
        root = node;
    }

    //RR: simples a esquerda
    //LL: simples a direita
    //RL: dupla a esquerda
    //LR: dupla a direita

    private void rotateDoubleRight(Node root){
        rotateLeft(root.left);
        rotateRight(root);
    }

    private void rotateDoubleLeft(Node root){
        rotateRight(root.right);
        rotateLeft(root);
    }

    public int add(Node root, int value){
        int ans = 0;
        if(root == null){
            Node node = new Node(value);
            root = node;
            return 1;
        }
        Node no = root;
        if(value < no.key){
            if((ans=add(no.left, value)) == 1){
                if(fb(no) >= 2){
                    if(value < root.left.key) rotateRight(root);
                    else rotateDoubleRight(root);
                }
            }
        }
        else if(value > no.key){
            if((ans=add(no.right, value)) == 1){
                if(fb(no) >= 2){
                    if(root.right.key < value) rotateLeft(root);
                    else rotateDoubleLeft(root);
                }
            }
        }
        else{
            System.out.println("Duplicated value!!");
            return 0;
        }
        no.height = greatest(getHeight(no.right), getHeight(no.left)) + 1;
        return ans;
    }

    public int remove(Node root, int value){
        if(root == null){
            return 0;
        }
        int ans = 0;
        if(value < root.key){
            if((ans=remove(root.left, value)) == 1){
                if(fb(root) >= 2){
                    if(getHeight(root.right.left) <= getHeight(root.right.right)){
                        rotateLeft(root);
                    }
                    else rotateDoubleLeft(root);
                }
            }
        }
        if(root.key < value){
            if((ans=remove(root.right, value)) == 1){
                if(fb(root) >= 2){
                    if(getHeight(root.left.right) <= getHeight(root.left.left)){
                        rotateRight(root);
                    }
                    else rotateDoubleRight(root);
                }
            }
        }
        if(root.key == value){
            if(root.left == null || root.right == null){
                if(root.left != null) root = root.left;
                else root = root.right;

            }
            else{
                Node temp = findMinor(root.right);
                root.key = temp.key;
                remove(root.right, root.key);
                if(fb(root) >= 2){
                    if(getHeight(root.left.right) <= getHeight(root.left.left)){
                        rotateRight(root);
                    }
                    else rotateDoubleLeft(root);
                }
            }
            return 1;
        }
        return ans;
    }

    private Node findMinor(Node root){
        Node node1 = root;
        Node node2 = root.left;
        while(node2 != null){
            node1 = node2;
            node2 = node2.left;
        }
        return node1;
    }

    public int countElements(Node root){
        if(root == null)
            return 0;
        else
            return countElements(root.left) + 1 + countElements(root.right);
    }

    public void printPreOrder(Node root){
        if(root != null){
            System.out.print(root.key + "(");
            printPreOrder(root.left);
            printPreOrder(root.right);
            System.out.print(")");
        }
    }
    
    public void printInOrder(Node root){
        if(root != null){
            printInOrder(root.left);
            System.out.print(root.key + " ");
            printInOrder(root.right);
        }
    }
    
    public void printPosOrder(Node root){
        if(root != null){
            printPosOrder(root.left);
            printPosOrder(root.right);
            System.out.print(root.key + " ");
        }
    }
}
