/// user-defined hashCode implementation ///
public final class Transaction implements Comparable<Transaction> {
    private final String who;   // reference data type (references object)
    private final Date when;         // reference data type
    private final double amount;    // primitive data type

    public Transaction(String who, Date when, double amount) { /* as before */ }

    public boolean equals(Object y) { /* as before */ }

    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + who.hashCode();
        hash = 31 * hash + when.hashCode();
        hash = 31 * hash + ((Double) amount).hashCode();  // need to use wrapper type for primitive types
        return hash;
    }
}

/// Separate Chaining - collision prevention method ///
private class SeparatechainingHastST<Key, Value> {
    private int M = 97;                 // number of chains
    private Node[] st = new Node[M];   // array of chains

    private static class Node {
        private Object key;
        private Object val;
        private Node next;
    }

    // to convert hash code to hash function (int from -2^31 to 2^31 - 1 convert to 0 to M - 1)
    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    // search:
    public Value get(Key key) {
        int i = hash(key);  // returns an index for the hash table
        for (Node x = st[i]; x != null; x = x.next) // set node to the chain in the st, then iterate
            if (key.equals(x.key)) return (Value) x.val;    // if key equals, then return the value
        return null;
    }

    public void put(Key key, Value val) {
        int i = hash(key);
        for (Node x = st[i]; x != null; x = x.next)
            if (key.equals(x.key)) {
                x.val = val;
                return;
            } // key exists, then insert the value
        st[i] = new Node(key, val, st[i]);  // if not, then create a new node with the input value

    }
}

/// Linear Probing ST implementation ///
public class LinearProbingHastST<Key, Value> {
    private int M = 30001;  // user-defined; may be an input
    private Value[] vals = (Value[]) new Object[M]; // values at array slots
    private Key[] keys = (Key[]) new Object[M];     // array slots

    private int hash(Key key) {/* as before */}

    public void put(Key key, Value val) {
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M)
            if (keys[i].equals(key))    // if input key equals key array slot index
                break;                  // break the loop to add the key-value pair
        keys[i] = key;
        vals[i] = val;
    }

    public Value get(Key key) {
        for (int i = hase(key); keys[i] != null; i = (i + 1) % M)
            if (key.equals(keys[i]))    // if input key equals key array slot index
                return vals[i];         // return the value at that slot
        return null;
    }
}

