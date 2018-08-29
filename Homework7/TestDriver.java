/**
 * Start of testdriver giving examples of declaring and allocating 
 * hashtable and dictionary.
 * 
 * Note, when you work with the dictionary, you won't create your own
 * hashtable, the dictionary will do that.
 *
 *
 * @author Austin Hollis
 * I certify that everything here that I made is of my own work.
 **/
package homework7;
import java.util.*;
public class TestDriver {

	public static void main(String [] args){
		
		HashTable<Integer, String> ht = new HashTable<Integer, String>(11); 
        ht.dumpTable();
        System.out.println();
        ht.hashInsert(11, "abcd");
        ht.hashInsert(12, "cdwd");
        ht.hashInsert(13, "fetd");
        ht.hashInsert(14, "xpow");
        ht.hashInsert(15, "xywz");
        ht.hashInsert(16, "lmno");
        ht.hashInsert(17, "wwwi");
        ht.hashInsert(18, "1234");
        ht.hashInsert(19, "yyyw");
        ht.hashInsert(20, "xyxy");
        ht.hashInsert(21, "zpie");
        ht.hashRemove(11);

        ht.dumpTable();
        System.out.println();
        /***************************************************************
         * 															   *
         * MAKE SURE TO COMMENT THE PROBING FORMULAS THAT YOU ARE NOT  *
         * CURRENTLY TESTING AND TO UNCOMMENT THE PROBING FORMULAS     *
         * THAT YOU ARE TESTING THEY ARE ALL IN THE p(key,slot) METHOD *
         * 															   *
         ***************************************************************/
		//create a random number generator
		Random rand = new Random();
		//create a second random number generator
		Random rand2 = new Random();
		//key size to affect load factor
		int keySize = 90;
		//changeable HD size
		int hdSize = 100;
		//Creates the HashDictionary of size 100
        HashDictionary<Integer, Integer> hd = new HashDictionary<Integer, Integer>(hdSize);
        //integer keyArray with the size that will affect the load factor
        int keyArray[] = new int[keySize];
        //loop to create keys to place into the keyArray and checks for duplicate keys
        for(int i=0; i < keySize; i++) {
        	//set j to 0
        	int j=0;
        	//boolean to run while loop
        	boolean keyExists = false;
        	//generate a random key from 0 to 400
        	int randKey = rand2.nextInt(400);
        	//check to see if the key exists
        	while(j < keyArray.length && keyExists) {
        		//if key already exists add 98764 to that key value
        		if(keyArray[j] == randKey) {
        			randKey += 98764;
        		}
        		//increment j
        		j++;
        	}
        	//add randKey to the ith position of keyArray
        	keyArray[i] = randKey;
        }
        //Add all the values in keyArray into the HD
        for(int i=0; i < keyArray.length; i++) {
        	hd.insert(keyArray[i], rand.nextInt(100000));
        }
        //calculate load factor
        float lw = hd.calcLoadFactor();
        //the load factor of the whole hd
        System.out.println("The starting load factor for this run is " + lw + ".");
        //dump the information out for insert?
        hd.dumpDictionary();
       //remove a small number from the HD
        for(int i=0; i < keySize/10; i++) {
        	hd.remove(keyArray[rand.nextInt(keySize)]);
        }
        //dump the information out for remove?
        hd.dumpDictionary();
	}
}
