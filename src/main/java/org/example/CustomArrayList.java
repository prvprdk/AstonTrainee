package org.example;

import java.util.Collection;
import java.util.Comparator;

public interface CustomArrayList<E> extends Iterable {

    void add(E element);


    void add(int index, E element);

    void addAll(Collection<? extends E> c);

    void clear();

    E get(int index);

    boolean isEmpty();

    void remove(int index);

    void remove(E o);

    int size();


    void sort(Comparator<? super E> c);

}
