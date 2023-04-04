package ohm.softa.a04;

import java.util.Iterator;

/**
 * @author Peter Kurfer
 * Created on 10/6/17.
 */
public class SimpleListImpl<T> implements SimpleList<T> {

	private ListElement<T> head;
	private int size;

	public SimpleListImpl() {
		head = null;
	}

	/**
	 * Add an object to the end of the list
	 * @param item item to add
	 */
	public void add(T item){
		/* special case empty list */
		if(head == null){
			head = new ListElement<T>(item);
		}else {
			/* any other list length */
			ListElement<T> current = head;
			while (current.getNext() != null){
				current = current.getNext();
			}
			current.setNext(new ListElement<T>(item));
		}
		size++;
	}

	public void removeElement(T value){
		if(head.item.equals(value)){
			head = head.next;
			size--;
		}
		else{
			boolean removal = head.removeElement(value);
			if(removal)
				size--;
		}
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void addDefault(Class<T> _class) throws IllegalAccessException, InstantiationException{
		add(_class.newInstance());
	}


	/**
	 * @return size of the list
	 */
	public int size() {
		return size;
	}


	@Override
	public Iterator<T> iterator() {
		return new SimpleIterator();
	}

	/**
	 * Helper class which implements the Iterator interface
	 * Has to be non static because otherwise it could not access the head of the list
	 */
	private class SimpleIterator implements Iterator<T> {

		private ListElement<T> current = head;

		/**
		 * @inheritDoc
		 */
		@Override
		public boolean hasNext() {
			return current != null;
		}

		/**
		 * @inheritDoc
		 */
		@Override
		public T next() {
			T tmp = current.getItem();
			current = current.getNext();
			return tmp;
		}
	}

	/**
	 * Helper class for the linked list
	 * can be static because the ListElement does not need to access the SimpleList instance
	 */
	private static class ListElement<U> {
		private U item;
		private ListElement<U> next;

		ListElement(U item) {
			this.item = item;
			this.next = null;
		}

		/**
		 * @return get object in the element
		 */
		public U getItem() {
			return item;
		}

		/**
		 * @return successor of the ListElement - may be NULL
		 */
		public ListElement<U> getNext() {
			return next;
		}

		/**
		 * Sets the successor of the ListElement
		 * @param next ListElement
		 */
		public void setNext(ListElement<U> next) {
			this.next = next;
		}

		public boolean removeElement(U value){
			// if(this.next == null)
			// 	return false;
			// boolean returnVal = false;
			if (this.next != null)
			{
				if (this.next.getItem().equals(value)){
					this.next = this.next.getNext();
					return true;
				}
				else{
					return this.getNext().removeElement(value);
				}
			}
			return false;
		}
	}

}
