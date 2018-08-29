/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/
package homework7;
import java.util.Random; //added to utilize random library
/**
 * @author Austin Hollis
 * I certify that everything here that I made is of my own work.
 */
public class HashTable<Key extends Comparable<? super Key>, E> {
	// We don't actually care if Key is Comparable, we only do it for consistency
	private int M;
	private KVpair<Key, E>[] T;
	private KVpair<Key, E> TombStone = new KVpair<Key, E>(); // Tombstone to mark deleted data
	private int access; //added
	private int removeAccess; //added
	private float totalInsertAccesses = 0; //added
	private float totalRemoveAccesses = 0; //added
	/*
	 * (McCauley added) // this sfold method multiplies that ascii values in the
	 * string // by powers of 2, by bit-shifting left. // For example, 5 << 3
	 * results is the same as 5 * 8 // So for a 4-char chunk of the string, say
	 * "aa32" // we'd have 97 * 2-to the 24th + 97 * 2-to the 16th, + 51 * // 2-to
	 * the 8th, + 50
	 */
	private int sfold(String x, int M) {
		char ch[];
		ch = x.toCharArray();
		int intlength = x.length() / 4;
		long sum = 0;
		int count = 0;
		for (int i = 0; i < intlength; i++) {
			sum += (((long) ch[count]) << 24) + (((long) ch[count + 1]) << 16) + (((long) ch[count + 2]) << 8)
					+ ((long) ch[count + 3]);
			count += 4;
		}
		return (Math.abs((int) sum) % M);
	}

	/**
	 * If the key is an Integer, then use a simple mod function for the hash
	 * function. If the key is a String, then use folding.
	 */
	private int h(Key key) {
		Object keyO = (Object) key;
		if (keyO.getClass() == Integer.class)
			return (Integer) keyO % M;
		else if (keyO.getClass() == String.class)
			return sfold((String) keyO, M);
		else
			return key.hashCode() % M;
	}

	/** Implement collision resolution with linear probing */
	//Contains probing for linear, quadratic and pseudo random
	//uncomment the one you would like to test and comment the others out.
	private int p(Key key, int slot) {
		//Linear Probing Formula//
		int newPos = slot;
		return newPos;
		
		//Quadratic Probing Formula//
		//double newPos = Math.pow(slot, 2);
		//return (int)newPos;
		
		//Pseudo-Random Probing Formula//
		//Random rand = new Random();
		//int newPos = rand.nextInt(M);
		//return newPos;
	}
	//Public method to get the number of accesses to the HD.
	public int getAccesses() {
		int numAccesses = getAccesses(access);
		return numAccesses;
	}
	//Created this method to return the number of accesses to the public method.
	private int getAccesses(int access) {
		return access;
	}

	/* Constructor */
	@SuppressWarnings("unchecked") // Allow the generic array allocation
	HashTable(int m) {
		M = m;
		T = (KVpair<Key, E>[]) new KVpair[M];
		
	}

	/** Insert record r into T */
	// No longer buggy
	// On an insert, following a removal, a new item will replace
	// a TombStone if one is found, but duplicates are never inserted,
	// so must search until a null or duplicate is found, before inserting
	// a record. Record stored at first TombStone found, if any.
	public void hashInsert(Key k, E r) {
		int home; // Home position for r
		int pos = home = h(k); // Initial position
		boolean TombStoneFound = false;
		int foundWhere = 0;
		for (int i = 1; (T[pos] != null) && (T[pos] == TombStone || T[pos].key().compareTo(k) != 0); i++) {
			if (!TombStoneFound && T[pos] == TombStone) {
				TombStoneFound = true;
				foundWhere = pos;
			}
			pos = (home + p(k, i)) % M; // Next probe slot
			//accumulates the accesses
			if(T[pos]==null) { //added
				access = i + 1; //added
			}
		}
		// Loop above terminates if T[pos] is null or a duplicate is found
		if (T[pos] == null) {
			if (TombStoneFound) // TombStone found
				pos = foundWhere; // address of TombStone
			T[pos] = new KVpair<Key, E>(k, r); // Insert R here
			//Returns one if inserted on first attempt
			if(access <= 1) { //added
				access = 1; //added
			}
		}
		// else a duplicate was found, don't insert a duplicate
		totalInsertAccesses = totalInsertAccesses + access; //added; set global variable to this value
	}
	//added; returns global variable of total insert accesses
	public float getTotalInsertAccesses() {
		return totalInsertAccesses;
	}
	//added; returns global variable of total remove accesses
	public float getTotalRemoveAccesses() {
		return totalRemoveAccesses;
	}

	/** Search for the record with key k */
	E hashSearch(Key k) {
		int home; // Home position for k
		int pos = home = h(k); // Initial position
		for (int i = 1; (T[pos] != null) && (T[pos] == TombStone || T[pos].key().compareTo(k) != 0); i++) {
			pos = (home + p(k, i)) % M; // Next probe position
		}
		if (T[pos] == null)
			return null; // K not in hash table
		else
			return T[pos].value(); // Found it
	}

	/** Remove a record with key value k from the hash table */
	E hashRemove(Key k) {
		int home; // Home position for k
		removeAccess = 1;//added to set variable to one each remove
		int pos = home = h(k); // Initial position
		for (int i = 1; (T[pos] != null) && (T[pos] == TombStone || T[pos].key().compareTo(k) != 0); i++) {
			pos = (home + p(k, i)) % M; // Next probe position
			removeAccess++;//added to increment removeAccess by 1 if it enters here
		}
		totalRemoveAccesses = removeAccess; //added to set the totalRemoveAccesses variable to removeAccess
		if (T[pos] == null)
			return null; // K not in hash table
		else { // Found it
			E e = T[pos].value();
			T[pos] = TombStone;
			return e;
		}
	}

	/** Remove some record from the hash table */
	E hashRemoveAny() {
		int i;
		for (i = 0; i < M; i++)
			if (T[i] != null && T[i] != TombStone)
				break;
		E e = T[i].value();
		T[i] = TombStone;
		return e;
	}

	// dump HT to see what is happening
	public void dumpTable() {
		System.out.println("The HT contains:");
		for (int i = 0; i < M; i++)
			if (T[i] != null)
				if (T[i] == TombStone)
					System.out.println("Deleted");
				else
					System.out.println(T[i].key() + ", " + T[i].value());
			else
				System.out.println(T[i]);
	}
	
	public void dumpTableRemove() {
		System.out.println("The HT contains:");
		for (int i = 0; i < M; i++)
			if (T[i] != null)
				if (T[i] == TombStone)
					System.out.println("Deleted");
				else
					System.out.println(T[i].key() + ", " + T[i].value());
			else
				System.out.println(T[i]);
	}
}