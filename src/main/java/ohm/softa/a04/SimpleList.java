package ohm.softa.a04;

import java.util.function.Function;

public interface SimpleList<T> extends Iterable<T> {
	/**
	 * Add a given object to the back of the list.
	 */
	void add(T o);

	/**
	 * Add default object of given class to list.
	 */
	void addDefault(Class<T> c) throws IllegalAccessException, InstantiationException;

	/**
	 * @return current size of the list
	 */
	int size();

	/**
	 * Removes first Element with provided value from List
	 * @param value
	 */
	void removeElement(T value);

	/**
	 * Get a new SimpleList instance with all items of this list which match the given filter
	 * @param filter SimpleFilter instance
	 * @return new SimpleList instance
	 */
	public default SimpleList<T> filter(SimpleFilter<T> filter){
		SimpleList<T> result = new SimpleListImpl<T>();
		for(T o : this){
			if(filter.include(o)){
				result.add(o);
			}
		}
		return result;
	}

	/**
	 * Transforms all Elements of the List according to the transform Function
	 * @param <R> Type of Elements in new SimpleList
	 * @param transform Function that transforms List Elements
	 * @return List of transformed Elements
	 */
	public default <R> SimpleList<R> map(Function<T,R> transform){
		SimpleList<R> result;// = new SimpleList<>();
		
		try{
			result = (SimpleList<R>)this.getClass().newInstance();
		}
		catch(InstantiationException | IllegalAccessException e){
			result = new SimpleListImpl<>();
		}
		
		for (T subject : this){
			result.add(transform.apply(subject));
		}
		
		return result;
	}

}
