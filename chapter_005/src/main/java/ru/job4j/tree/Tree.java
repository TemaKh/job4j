package ru.job4j.tree;

import java.util.*;

public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    private Node<E> root;
    private List<Node<E>> listAllNode = new LinkedList<>();

    public Tree(E value) {
        this.root = new Node<>(value);
    }

    @Override
    public boolean add(E parent, E child) {
        Optional<Node<E>> parentNode = findBy(parent);
        if (parentNode.isPresent() && !findBy(child).isPresent()) {
            parentNode.get().add(new Node<>(child));
            return true;
        }
        return false;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
               rsl = Optional.of(el);
               break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    private List<Node<E>> listAllElementTree(Node<E> node) {
        listAllNode.add(node);
        for (Node<E> child : node.leaves()) {
            if (child.leaves().size() > 0) {
                listAllElementTree(child);
            } else {
                listAllNode.add(child);
            }
        }
        return listAllNode;
    }

    @Override
    public Iterator<Node<E>> iterator() {
        return listAllElementTree(root).iterator();
    }

    public boolean isBinary() {
        boolean result = true;
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.leaves().size() > 2) {
                result = false;
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return result;
    }
}
