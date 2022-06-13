@SuppressWarnings("unchecked")
//FINAL
public class MinPQ<T extends Comparable<T>> {
    private T[] array;//the underlying array
    private int nextOpen;//marks the next open index in the array
    private final int CAP;//the starting capacity of the array--it should never drop below this
    private int size;

    /***
     *constructor: constructs a new 
     *MinPQ with starting capacity of 10
     ***/
    public MinPQ() {
		this.CAP = 10;
		this.array = (T[]) new Comparable[CAP];
		this.nextOpen = 0;
		this.size = 0;
    }

    /**
     *constructor: constructs a new MinPQ with 
     *starting capacity of cap
     ***/
    public MinPQ(int cap) {
		this.CAP = cap;
		this.array = (T[]) new Comparable[CAP];
		this.nextOpen = 0;
		this.size = 0;
    }

    /***
     *@param item to be inserted into PQ
     *if the array is full after the insert, 
     *resize the array to be twice as large
     ***/
    public void insert(T item) {
    	//full conditition
    	if (this.size >= this.array.length) {
    		resize(this.array.length * 2);
    	}
    	
    	//add to array 
    	this.array[nextOpen] = item;  	
    	nextOpen++;
    	size++;
    	
    	heapHelper();
    	
//    	retHelper();
//    	if (item.compareTo(array[0]) < 0) {
//    	swap(0, nextOpen);
//    	}

    	

    	
    }

    /***
     *@return and remove the min item in the PQ and re-heapify
     *throw EmptyQueueException if the PQ is empty
     *if array.length/2 >= CAP and the number of elements drops below
     *array.length/4 (after the delete), resize the array to array.length/2
     ***/
    public T delMin() throws EmptyQueueException {
    	//if empty
    	if (this.size == 0) {
    		throw new EmptyQueueException();
    	}

    	T ret_val = array[0];
    	this.size--;
    	this.nextOpen--;
    	for (int i = 0; i < array.length; i++) {
    		if (i+1 < array.length) {
    			if (array[i+1] == null){
    				array[i] = null;
    				break;
    			}
    			array[i] = array[i+1];
    		}
    	}
    	
    	array[nextOpen] = null;
    	heapHelper();
    	//resize condition
    	if (this.array.length/2 >= CAP && this.size-1 < this.array.length/4) {
    		resize((this.array.length)/(2));
    	}

    	return ret_val;
    }

    /***
     *@return but do not remove the min item in the PQ
     *throw EmptyQueueException if the PQ is empty
     ***/
    public T getMin() throws EmptyQueueException {
    	//if empty
    	if (this.size == 0) {
    		throw new EmptyQueueException();
    	}
    	return this.array[0];
    }

    /***
     *@return the number of items currently in 
     *the PQ
     ***/
    public int size() {
    	return this.size;
    }

    /***
     *@return true if the PQ is empty and false
     *otherwise
     ***/
    public boolean isEmpty() {
    	if (this.size == 0) {
    		return true;
    	}
    	return false;
    }

    /***
     *returns the underlying array
     *This method is only used for testing.
     *Do not change it, and do not use it in your
     *own code!!!
     ***/
    public T[] getArray() {
    	return this.array;
    }
    
    private void swap(int index1, int index2) {
    	T temp_val = this.array[index1];
    	this.array[index1] = this.array[index2];
    	this.array[index2] = temp_val;
    }
    
    private void resize(int new_size) {

    	T[] temp_array = (T[]) new Comparable[new_size];
    	for (int i = 0; i < this.nextOpen; i++) {
    		temp_array[i] = this.array[i];
    	}
    	this.array = temp_array;
    }
    
    private void retHelper() {
		for (int i = 0; i < array.length; i++) {
			T value = array[i];
			if (value == null) {
				break;
			}
			int j;
			for (j = i; (j > 0) && array[j-1] != null && (array[j - 1].compareTo(value) > 0); j = j - 1) {
				array[j] = array[j-1];
			}
			array[j] = value;
		}
    }
    
    private void heapHelper() {
    	int i = 0;
    	boolean did_swap = true;
    	while (did_swap) {
    		did_swap = false;
	    	while ((2*i)+1 < array.length && array[2*i+1] != null) {
	    		if (array[i].compareTo(array[2*i]) > 0) {
	    			swap(i, 2*i);
	    			did_swap = true;
	    		}
	    		if (array[i].compareTo(array[2*i+1]) > 0 ) {
	    			swap(i, 2*i+1);
	    			did_swap = false;
	    		}
	    		if (array[2*i+1].compareTo(array[2*i]) < 0) {
	    			swap(2*i+1, 2*i);
	    		}
	    		i++;
	    	}
    	}
    }
    	    
    public String retStr() {
    	String build_string = "[";
    	for (int i = 0; i < array.length; i++) {
    		String add_string;
    		if (array[i] != null) {
    			add_string = array[i].toString();
    		}
    		else {
    			add_string = "null";
    		}
    		if (i != array.length-2) {
    			build_string = build_string + add_string + ", ";
    		}
    		else {
    			build_string = build_string + add_string;
    		}
    	}
    	return build_string;
    }
}
    
