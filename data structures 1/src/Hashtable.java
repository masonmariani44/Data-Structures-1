@SuppressWarnings("unchecked")
//FINAL
public class Hashtable<K, V> {
    private Pair[] table;
    private int n;//the number of key-value pairs in the table
    private int m;//the size of the table
    private double alphaHigh = 0.5;//resize if n/m exceeds this (1/2)
    private double alphaLow = 0.125;//resize if n/m goes below this (1/8)
    private Pair open_marker;

    //constructor--default table size is 11
    public Hashtable() {
		n = 0;
		m = 11;
		table = new Pair[m];
		open_marker = new Pair(-9999, -9999);
    }

    //constructor
    public Hashtable(int m) {
    	n = 0;
		this.m = m;
		table = new Pair[m];
		open_marker = new Pair(-9999, -9999);
    }

    //returns the value associated with key <key>
    //return null if key is not in table
    //do not forget that you will have to cast the result to (V)
    public V get(K key) {
    	int addr = search(key);
    	if (addr == -999) {
    		return null;
    	}
    	
    	return (V) table[addr].getValue();
    }

    //puts (key, val) into the table or updates value
    //if the key is already in the table
    //resize to getNextNum(2*m) if (double)n/m exceeds alphaHigh after the insert
    public void put(K key, V val) {
    	int hash_val = (key.hashCode());
    	int address = hash_val % m;
    	
    	while (address < 0) {
    		address = address + m;
    	}
    	
    	while (table[address] != null && !(table[address].equals(open_marker)) && !(table[address].getKey().equals(key))) {
    		address = (address+1) % m;
    	}
    	
		if (table[address] == null) {
			n++;
		} else {
			if (table[address].equals(open_marker)) {
				n++;
			}
		}
    	
		Pair add_pair = new Pair(key, val);
		table[address] = add_pair;
    	
    	//resize condition
    	if ((double)(n/m) > alphaHigh) {
    		resize(getNextNum(2*m));
    	}
    }

    //removes the (key, value) pair associated with <key>
    //returns the deleted value or null if the element was not in the table
    //resize to getNextNum(m/2) if m/2 >= 11 AND (double)n/m < alphaLow after the delete
    public V delete(K key) {
    	int del_addr = search(key);
    	V ret_val = (V) table[del_addr].getValue();
    	table[del_addr] = open_marker;
    	n--;
    	
    	//resize condition
    	if (m/2 >= 11 && (double) (n/m) < alphaLow) {
    		resize(getNextNum(m/2));
    	}
    	
    	return ret_val;
    }

    //return true if table is empty
    public boolean isEmpty() {
    	if (n == 0) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }

    //return the number of (key,value) pairs in the table
    public int size() {
    	return n;
    }

    //This method is used for testing only. Do not use this method yourself for any reason
    //other than debugging. Do not change this method.
    public Pair[] getTable() {
    	return table;
    }

    
    
    
    //PRIVATE
    
    //gets the next multiple of 6 plus or minus 1,
    //which has a decent probability of being prime.
    //Use this method when resizing the table.
    private int getNextNum(int num) {
		if(num == 2 || num == 3)
		    return num;
		
		int rem = num % 6;
		switch(rem) {
			case 0: num++; break;
			case 2: num+=3; break;
			case 3: num+=2; break;
			case 4: num++; break;
		}
		return num;
    }
    
    //searches for a key in the table and returns its index, if not in the table, returns -999
    private int search(K key) {
    	int hash_val = Math.abs(key.hashCode());
    	int address = hash_val % m;
    	
    	int counter = 0;
    	while (counter < m) {
    		if (table[address] != null) {
	    		if (table[address].getKey().equals(key)) {
	    			return address;
	    		}
    		}
    		address = (address+1) % m;
    		counter++;
    	}
    	
    	return -999;
    }
    
    private void resize(int new_size) {
    	//loop through array and copy every value to new array of size "new_size"
    	//TODO fix, needs to be rehashed (resizing smaller)
    	Pair[] new_table = new Pair[new_size];
    	Pair[] temp_table = table;
    	table = new_table;
    	m = new_size;
    	
    	for (int i=0; i < temp_table.length; i++) {
    		if (temp_table[i] != null) {
    			if (!(temp_table[i].equals(open_marker))) {
    				put((K) temp_table[i].getKey(), (V) temp_table[i].getValue());
    			}
    		}
    	}
    }
}
      
