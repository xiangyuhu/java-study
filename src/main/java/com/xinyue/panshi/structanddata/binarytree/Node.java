package com.xinyue.panshi.structanddata.binarytree;

import java.util.List;

/**
 * @author hxy
 * @time 2018/1/22
 * @desc
 */
public class Node<T> {
    private Node leftNoe;
    private Node rightNo;
    private Node parentNode;
    private T element;

    public List<Node> preOrderTraversal(){
        return null;
    }

    public List<T> preOrderTraversalElement(){
        return null;
    }

    public Node getLeftNoe() {
        return leftNoe;
    }

    public void setLeftNoe(Node leftNoe) {
        this.leftNoe = leftNoe;
    }

    public Node getRightNo() {
        return rightNo;
    }

    public void setRightNo(Node rightNo) {
        this.rightNo = rightNo;
    }

    public Node getParentNode() {
        return parentNode;
    }

    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }
}
