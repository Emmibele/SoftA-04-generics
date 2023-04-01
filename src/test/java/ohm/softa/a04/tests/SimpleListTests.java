package ohm.softa.a04.tests;

import ohm.softa.a04.SimpleFilter;
import ohm.softa.a04.SimpleList;
import ohm.softa.a04.SimpleListImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;
import java.util.function.Function;

/**
 * @author Peter Kurfer
 * Created on 10/6/17.
 */
public class SimpleListTests {

	private final Logger logger = LogManager.getLogger();
	private SimpleList<Integer> testList;

	@BeforeEach
	void setup(){
		testList = new SimpleListImpl<Integer>();

		testList.add(1);
		testList.add(2);
		testList.add(3);
		testList.add(4);
		testList.add(5);
	}

	@Test
	void testAddElements(){
		logger.info("Testing if adding and iterating elements is implemented correctly");
		int counter = 0;
		for(Integer o : testList){
			Integer i = o;
			o = i;
			counter++;
		}
		assertEquals(5, counter);
	}

	@Test
	void testSize(){
		logger.info("Testing if size() method is implemented correctly");
		assertEquals(5, testList.size());
	}

	@Test
	void testFilterAnonymousClass(){
		logger.info("Testing the filter possibilities by filtering for all elements greater 2");
		SimpleList<Integer> result = testList.filter(new SimpleFilter<Integer>() {
			@Override
			public boolean include(Integer item) {
				int current = (int)item;
				return current > 2;
			}
		});

		for(Object o : result){
			int i = (int)o;
			assertTrue(i > 2);
		}
	}

	@Test
	void testFilterLambda(){
		logger.info("Testing the filter possibilities by filtering for all elements which are dividable by 2");
		SimpleList<Integer> result = testList.filter(o -> ((int) o) % 2 == 0);
		for(Object o : result){
			int i = (int)o;
			assertTrue(i % 2 == 0);
		}
	}

	@Test
	void testCreateDefault(){
		logger.info("Testing the creation of default value");
		
		SimpleListImpl<String> list = new SimpleListImpl<String>();
		try {
			list.addDefault(String.class);
		} catch (Exception e) {
			// TODO: handle exception
		}
		assertEquals(list.size(), 1);
	}

	@Test
	void testTransformList(){
		logger.info("Testing the transformation of List Elements");

		SimpleList<Double> squaredList = testList.map(i -> Math.pow(i, 2));
		Iterator<Integer> firstIterator = testList.iterator();
		Iterator<Double> targetIterator = squaredList.iterator();

		while(firstIterator.hasNext() && targetIterator.hasNext()){
			assertEquals(Math.pow(firstIterator.next(), 2), targetIterator.next());
		}

	}
}