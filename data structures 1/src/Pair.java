//FINAL
public class Pair<K, V> {
    private K key;
   	private V val;
   	
    public Pair(K key, V val) {
    	this.key = key;
		this.val = val;
    }

    public K getKey() {
    	return key;
    }

    public V getValue() {
    	return val;
    }

    public void setValue(V val) {
    	this.val = val;
    }

    public boolean equals(Pair p) {
    	return this.key.equals(p.getKey()) && this.val.equals(p.getValue());
    }

    public String toString() {
    	return "(key = " + key.toString() + ", value = " + val.toString() + ")";
    }
}
