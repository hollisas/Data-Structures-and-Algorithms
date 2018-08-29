/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/
package homework7;
/**
 * @author Austin Hollis
 * I certify that everything here that I made is of my own work.
 */
/** Dictionary implemented using hashing. */
class HashDictionary<Key extends Comparable<? super Key>, E>
                    implements Dictionary<Key, E> {
  private static final int defaultSize = 10;
  private HashTable<Key,E> T; // The hash table
  private int count = 0;          // # of records now in table
  private int maxsize;        // Maximum size of dictionary

  HashDictionary() { this(defaultSize); }
  HashDictionary(int sz) {
    T = new HashTable<Key,E>(sz);
    count = 0;
    maxsize = sz;
  }

  public void clear() {   /** Reinitialize */
    T = new HashTable<Key,E>(maxsize);
    count = 0;
  }

  public void insert(Key k, E e) {  /** Insert an element */
    assert count < maxsize : "Hash table is full";
    T.hashInsert(k, e);
    count++;
  }

  public E remove(Key k) {  /** Remove an element */
    E temp = T.hashRemove(k);
    if (temp != null) count--;
    return temp;
  }

  public E removeAny() {  /** Remove some element. */
    if (count != 0) {
      count--;
      return T.hashRemoveAny();
    }
    else return null;
  }

  /** Find a record with key value "k" */
  public E find(Key k) { return T.hashSearch(k); }

  /** Return number of values in the hash table */
  public int size() { return count; }
  
  //added to calculate the load factor
  public float calcLoadFactor() {
	  float loadFactor = ((float)count / (float)maxsize);
	  return loadFactor;
  }
  //added to calculate the average accesses per insert
  private float avgInsertAccess(float i) {
	  return ((float)i / (float)count);
  }
  //added to calculate the average accesses per remove
  private float avgRemoveAccess(float r) {
	  return ((float)r / (float)count);
  }
  //added to get the total accesses for insert from HT
  public float totalInsertAccesses() {
	  return (float)T.getTotalInsertAccesses();
  }
  //added to get the total accesses for remove from HT
  public float totalRemoveAccesses() {
	  return (float)T.getTotalRemoveAccesses();
  }
  //added to factor cost of the remove at certain load factors
  public float removeCost() {
	  double avgCost;
	  float lf = this.calcLoadFactor();
	  avgCost = (.5*(1+(1/(1-lf))));
	  return (float)avgCost;
  }
  //added to factor cost of the inserts at certain load factors
  public float insertCost() {
	  double avgCost;
	  float lf = this.calcLoadFactor();
	  avgCost = (.5*(1+(1/Math.pow((1-lf), 2))));
	  return (float)avgCost;
  }
  
  public void dumpDictionary() {
	  System.out.println("This dictionary contains " + count + " of " + maxsize + " possible records.");
	  T.dumpTable();
	  System.out.println("The average accesses per insert for this hash table is " + insertCost());
	  System.out.println("The average accesses per remove for this hash table is " + removeCost());
	  }  
  }