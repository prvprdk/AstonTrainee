package org.example;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Consumer;

public class DefaultCustomArrayList<E> implements CustomArrayList<E> {


    private E[] array;
    private int size;


    public DefaultCustomArrayList() {
        array = (E[]) new Object[10];
        size = 0;
    }


    @Override
    public void add(E element) {

        if (size == array.length) boostCapacity();

        if (size == 0) array[0] = element;
        else array[size] = element;
        size++;

    }

    @Override
    public void add(int index, E element) {
        if (index < 0 | index > size - 1) throw new IndexOutOfBoundsException();

        if (size == array.length) boostCapacity();

        E[] updateArray = (E[]) new Object[array.length];

        System.arraycopy(array, 0, updateArray, 0, index);
        updateArray[index] = element;
        System.arraycopy(array, index, updateArray, index + 1, size - index);

        size++;
        array = updateArray;

    }


    private void boostCapacity() {
        int newLength = (int) ((array.length * 1.5) + 1);
        E[] updateArray = (E[]) new Object[newLength];
        System.arraycopy(array, 0, updateArray, 0, size);
        array = updateArray;

    }

    @Override
    public void addAll(Collection c) {

        E[] newElements = (E[]) c.toArray();
        System.arraycopy(newElements, 0, array, size, newElements.length);
        size += newElements.length;
    }

    @Override
    public void clear() {
        for (E element : array) element = null;
        size = 0;
    }

    @Override
    public E get(int index) {
        if (index < 0 | index > size - 1) throw new IndexOutOfBoundsException();
        return array[index];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void remove(int index) {
        E[] updateArray = (E[]) new Object[array.length];

        System.arraycopy(array, 0, updateArray, 0, index);
        System.arraycopy(array, index + 1, updateArray, index, size - index);

        size--;
        array = updateArray;
    }

    @Override
    public void remove(E o) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(o)) {
                remove(i);
                break;
            }
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void sort(Comparator<? super E> c) {
        quickSort(this.array, 0, size - 1, c);
    }


    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer action) {
        for (int i = 0; i < size; i++) action.accept(array[i]);
    }


    private static <T> void quickSort(T[] arr, int low, int high, Comparator<T> comparator) {

        if (low < high) {
            int pi = partition(arr, low, high, comparator);

            quickSort(arr, low, pi - 1, comparator);
            quickSort(arr, pi + 1, high, comparator);

        }
    }

    private static <T> int partition(T[] arr, int low, int high, Comparator<T> comparator) {

        T pivot = arr[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (comparator.compare(arr[j], pivot) <= 0) {
                i++;

                T temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;

            }
        }
        T temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }
}
