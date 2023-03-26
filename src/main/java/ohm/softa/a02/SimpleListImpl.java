package ohm.softa.a02;

import java.util.Iterator;

/**
 * @author Peter Kurfer
 * Created on 10/6/17.
 */
public class SimpleListImpl implements SimpleList, Iterable<Object> {
    Element head;

    @Override
    public void add(Object o) {
        Element newElement = new Element(o);
        newElement.next = head;
        head = newElement;
    }

    @Override
    public int size() {
        Iterator<Object> iterator = iterator();
        int counter = 0;
        while (iterator.hasNext()){
            iterator.next();
            counter++;
        }
        return counter;
    }

    @Override
    public SimpleList filter(SimpleFilter filter) {
        SimpleList simpleList = new SimpleListImpl();
        for (Object o : this) {
            if (filter.include(o)) {
                simpleList.add(o);
            }
        }
        return simpleList;
    }

    @Override
    public Iterator<Object> iterator() {
        return new SimpleIteratorImpl();
    }

    private static class Element {
        Element next;
        Object item;
        Element(Object item){
            this.item = item;
        }
    }

    class SimpleIteratorImpl implements Iterator<Object> {
        Element current;
        SimpleIteratorImpl() {
            current = head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Object next() {
            Object o = current.item;
            current = current.next;
            return o;
        }
    }
}
