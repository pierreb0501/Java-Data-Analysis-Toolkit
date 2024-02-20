package finalproject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;




public class MyHashTable<K,V> implements Iterable<MyPair<K,V>>{
	// num of entries to the table
	private int size;
	// num of buckets 
	private int capacity = 16;
	// load factor needed to check for rehashing 
	private static final double MAX_LOAD_FACTOR = 0.75;
	// ArrayList of buckets. Each bucket is a LinkedList of HashPair
	private ArrayList<LinkedList<MyPair<K,V>>> buckets; 


	// constructors
	public MyHashTable() {
		// ADD YOUR CODE BELOW THIS
		this.size = 0;

		ArrayList<LinkedList<MyPair<K,V>>> list = new ArrayList<LinkedList<MyPair<K,V>>>(16);

		for(int i = 0; i < 16; i++) {
			LinkedList<MyPair<K,V>> ll = new LinkedList<MyPair<K,V>>();
			list.add(ll);
		}

		this.buckets = list;
		//ADD YOUR CODE ABOVE THIS
	}

	public MyHashTable(int initialCapacity) {
		// ADD YOUR CODE BELOW THIS
		this.size = 0;

		ArrayList<LinkedList<MyPair<K,V>>> list = new ArrayList<LinkedList<MyPair<K,V>>>(initialCapacity);

		for(int i = 0; i < initialCapacity; i++) {
			LinkedList<MyPair<K,V>> ll = new LinkedList<MyPair<K,V>>();
			list.add(ll);
		}

		this.buckets = list;
		this.capacity = initialCapacity;
		//ADD YOUR CODE ABOVE THIS
	}

	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return this.size == 0;
	}

	public int numBuckets() {
		return this.capacity;
	}

	/**
	 * Returns the buckets variable. Useful for testing  purposes.
	 */
	public ArrayList<LinkedList< MyPair<K,V> > > getBuckets(){
		return this.buckets;
	}

	/**
	 * Given a key, return the bucket position for the key. 
	 */
	public int hashFunction(K key) {
		int hashValue = Math.abs(key.hashCode())%this.capacity;
		return hashValue;
	}

	/**
	 * Takes a key and a value as input and adds the corresponding HashPair
	 * to this HashTable. Expected average run time  O(1)
	 */
	public V put(K key, V value) {
		//  ADD YOUR CODE BELOW HERE

		//IF THE KEY IS ALREADY IN THE TABLE, REPLACE THE VALUE
		//IF THE KEY IS NOT IN THE TABLE, ADD THE KEY-VALUE PAIR TO THE TABLE add to linked list
		MyPair<K,V> pair = new MyPair<K,V>(key, value);
		int hashValue = hashFunction(key);

		if(isEmpty() || get(key) == null){

			this.buckets.get(hashValue).add(pair);

			this.size++;

			if((1.0*this.size/this.capacity) > MAX_LOAD_FACTOR) {
				rehash();
			}
			return null;
		}else{
			V toReturn = this.buckets.get(hashValue).get(0).getValue();
			this.buckets.get(hashValue).get(0).setValue(value);
			return toReturn;
		}

		//  ADD YOUR CODE ABOVE HERE
	}


	/**
	 * Get the value corresponding to key. Expected average runtime O(1)
	 */

	public V get(K key) {
		//ADD YOUR CODE BELOW HERE

		int hashValue = hashFunction(key);

		if(this.buckets.get(hashValue).isEmpty()){
			return null;
		}

		LinkedList<MyPair<K, V>> ll = this.buckets.get(hashValue);
		for(MyPair<K,V> pair: ll){
			if(pair.getKey().equals(key)) {
				return (V) pair.getValue();
			}
		}

		return null;

		//ADD YOUR CODE ABOVE HERE
	}

	/**
	 * Remove the HashPair corresponding to key . Expected average runtime O(1) 
	 */
	public V remove(K key) {
		//ADD YOUR CODE BELOW HERE


		int hashValue = hashFunction(key);

		LinkedList<MyPair<K, V>> ll = this.buckets.get(hashValue);
		for(MyPair<K,V> pair: ll){
			if(pair.getKey().equals(key)) {
				this.buckets.get(hashValue).remove(pair);
				this.size--;
				return (V) pair.getValue();
			}
		}

		return null;

		//ADD YOUR CODE ABOVE HERE
	}


	/** 
	 * Method to double the size of the hashtable if load factor increases
	 * beyond MAX_LOAD_FACTOR.
	 * Made public for ease of testing.
	 * Expected average runtime is O(m), where m is the number of buckets
	 */
	public void rehash() {
		//ADD YOUR CODE BELOW HERE

		this.capacity = this.capacity * 2;
		ArrayList<MyPair<K,V>> newArr = getEntries();

		ArrayList<LinkedList<MyPair<K,V>>> list = new ArrayList<LinkedList<MyPair<K,V>>>(this.capacity);

		for(int i = 0; i < this.capacity; i++) {
			LinkedList<MyPair<K,V>> ll = new LinkedList<MyPair<K,V>>();
			list.add(ll);
		}

		this.size = 0;
		this.buckets = list;

		for (MyPair<K, V> myPairs : newArr) {
			put(myPairs.getKey(), myPairs.getValue());
		}

		//ADD YOUR CODE ABOVE HERE
	}


	/**
	 * Return a list of all the keys present in this hashtable.
	 * Expected average runtime is O(m), where m is the number of buckets
	 */

	public ArrayList<K> getKeySet() {
		//ADD YOUR CODE BELOW HERE

		ArrayList<K> toReturn = new ArrayList<K>();

		for(LinkedList<MyPair<K,V>> list : this.buckets) {
			if(!list.isEmpty()) {
				for (MyPair<K, V> pair : list) {
					toReturn.add(pair.getKey());
				}
			}
		}

		return toReturn;

		//ADD YOUR CODE ABOVE HERE
	}

	/**
	 * Returns an ArrayList of unique values present in this hashtable.
	 * Expected average runtime is O(m) where m is the number of buckets
	 */
	public ArrayList<V> getValueSet() {
		//ADD CODE BELOW HERE

		ArrayList<V> toReturn = new ArrayList<V>();

		for(LinkedList<MyPair<K,V>> list : this.buckets) {
			if(!list.isEmpty()){
				for (MyPair<K, V> pair : list) {
					if(!toReturn.contains(pair.getValue())) toReturn.add(pair.getValue());
				}
			}
		}

		return toReturn;

		//ADD CODE ABOVE HERE
	}


	/**
	 * Returns an ArrayList of all the key-value pairs present in this hashtable.
	 * Expected average runtime is O(m) where m is the number of buckets
	 */
	public ArrayList<MyPair<K, V>> getEntries() {
		//ADD CODE BELOW HERE

		ArrayList<MyPair<K,V>> toReturn = new ArrayList<MyPair<K,V>>();

		for(LinkedList<MyPair<K,V>> list : this.buckets) {
			if(!list.isEmpty()) toReturn.addAll(list);
		}

		return toReturn;

		//ADD CODE ABOVE HERE
	}

	
	
	@Override
	public MyHashIterator iterator() {
		return new MyHashIterator();
	}   

	
	private class MyHashIterator implements Iterator<MyPair<K,V>> {

		ArrayList<MyPair<K, V>> pairs;
		int index = 0;
		int size = 0;

		private MyHashIterator() {
			//ADD YOUR CODE BELOW HERE
			this.pairs = getEntries();
			this.size = pairs.size();
			//ADD YOUR CODE ABOVE HERE
		}

		@Override
		public boolean hasNext() {
			//ADD YOUR CODE BELOW HERE

			return index < size;

			//ADD YOUR CODE ABOVE HERE
		}

		@Override
		public MyPair<K,V> next() {
			//ADD YOUR CODE BELOW HERE

			return pairs.get(index++);
			
			//ADD YOUR CODE ABOVE HERE
		}

	}
	
}
