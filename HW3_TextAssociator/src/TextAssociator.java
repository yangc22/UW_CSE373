import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/* Chaoyi Yang
 * 02/10/17
 * CSE373
 * TA: Raquel Van Hofwegen
 * 
 * TextAssociator represents a collection of associations between words.
 * See write-up for implementation details and hints
 * 
 */
public class TextAssociator {
	private WordInfoSeparateChain[] table;
	private int size;
	
	/* INNER CLASS
	 * Represents a separate chain in your implementation of your hashing
	 * A WordInfoSeparateChain is a list of WordInfo objects that have all
	 * been hashed to the same index of the TextAssociator
	 */
	private class WordInfoSeparateChain {
		private List<WordInfo> chain;
		
		/* Creates an empty WordInfoSeparateChain without any WordInfo
		 */
		public WordInfoSeparateChain() {
			this.chain = new ArrayList<WordInfo>();
		}
		
		/* Adds a WordInfo object to the SeparateCahin
		 * Returns true if the WordInfo was successfully added, false otherwise
		 */
		public boolean add(WordInfo wi) {
			 if (chain.contains(wi)) {
				 return false;
			 } else {
				 chain.add(wi);
				 return true;
			 }
		}
		
		/* Removes the given WordInfo object from the separate chain
		 * Returns true if the WordInfo was successfully removed, false otherwise
		 */
		public boolean remove(WordInfo wi) {
			if (!chain.contains(wi)) {
				return false;
			} else {
				chain.remove(wi);
				return true;
			}
		}
		
		// Returns the size of this separate chain
		public int size() {
			return chain.size();
		}
		
		// Returns the String representation of this separate chain
		public String toString() {
			return chain.toString();
		}
		
		// Returns the list of WordInfo objects in this chain
		public List<WordInfo> getElements() {
			return chain;
		}
	}
	
	
	/* Creates a new TextAssociator without any associations 
	 */
	public TextAssociator() {
		table = new WordInfoSeparateChain[11];
		size = 0;
	}
	
	
	/* Adds a word with no associations to the TextAssociator 
	 * Returns False if this word is already contained in your TextAssociator ,
	 * Returns True if this word is successfully added
	 */
	public boolean addNewWord(String word) {
		if (contain(word)) {
			return false;
		} else {
			addNewInfo(new WordInfo(word), table);
			size++;
			if (size == table.length) {
				rehash();
			}
			return true;
		}
	}
	/* Calculates the index in the table for the given string
	 */
	private int hashFunction(String word, int tableSize) {
		int index = Math.abs(word.hashCode()) % tableSize;
		return index;
	}
	
	/* Adds a WordInfo to the corresponding position in the hash table,
	 * Creates a new chain if the position is empty
	 */
	private void addNewInfo(WordInfo current, WordInfoSeparateChain[] table) {
		int index = hashFunction(current.getWord(), table.length);
		if (table[index] == null) {
			table[index] = new WordInfoSeparateChain();
		}
		table[index].add(current);
	}
	
	/* Finds the corresponding WordInfo of the given string in the TextAssociator
	 * Returns the WordInfo object if it exits
	 * Returns null if it doesn't
	 */
	private WordInfo find(String word) {
		int index = hashFunction(word, table.length);
		if (table[index] != null) {
			WordInfoSeparateChain bucket = table[index];
			//For each separate chain, grab each individual WordInfo
			for (WordInfo curr : bucket.getElements()) {
				if (curr.getWord().equals(word)) {
					return curr;
				}
			}
		}
		return null;
	}
	
	/* Checks whether the WordInfo of the given string exits in the TextAssociator
	 * return true if it exits
	 * return false if it doesn't
	 */
	private boolean contain(String word) {
		if (size == 0) {
			return false;
		} else {
			WordInfo newInfo = find(word);
			if (newInfo != null) {
				return true;
			}
		}
		return false;
	}
	
	/* Rehash all the elements in the current table to a new table which is two times
	 * bigger than the current table.
	 */
	private void rehash() {
		WordInfoSeparateChain[] newTable = new WordInfoSeparateChain[2 * table.length];
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) {
				WordInfoSeparateChain bucket = table[i];
				//For each separate chain, grab each individual WordInfo
				for (WordInfo curr : bucket.getElements()) {
					addNewInfo(curr, newTable);
				}
			}
		}
		this.table = newTable;
	}
		
	/* Adds an association between the given words. Returns true if association correctly added, 
	 * returns false if first parameter does not already exist in the TextAssociator or if 
	 * the association between the two words already exists
	 */
	public boolean addAssociation(String word, String association) {
		if (!contain(word)) {
			return false;
		} else {
			WordInfo newWord = find(word);
			if (newWord.getAssociations().contains(association)) {
				return false;
			} else {
				newWord.getAssociations().add(association);
			}
		}
		return true;
	}
	
	
	/* Remove the given word from the TextAssociator, returns false if word 
	 * was not contained, returns true if the word was successfully removed.
	 * Note that only a source word can be removed by this method, not an association.
	 */
	public boolean remove(String word) {
		if (!contain(word)) {
			return false;
		} else {
			int index = hashFunction(word, table.length);
			WordInfoSeparateChain bucket = table[index];
			WordInfo newWord = find(word);
			bucket.remove(newWord);
			size--;
		}
		return true;
	}
	
	
	/* Returns a set of all the words associated with the given String  
	 * Returns null if the given String does not exist in the TextAssociator
	 */
	public Set<String> getAssociations(String word) {
		if (!contain(word)) {
			return null;
		} else {
			WordInfo newWord = find(word);
			return newWord.getAssociations();
		}
	}
	
	
	/* Prints the current associations between words being stored
	 * to System.out
	 */
	public void prettyPrint() {
		System.out.println("Current number of elements : " + size);
		System.out.println("Current table size: " + table.length);
		
		//Walk through every possible index in the table
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) {
				WordInfoSeparateChain bucket = table[i];
				
				//For each separate chain, grab each individual WordInfo
				for (WordInfo curr : bucket.getElements()) {
					System.out.println("\tin table index, " + i + ": " + curr);
				}
			}
		}
		System.out.println();
	}
	
	/* Prints all the source word being stored to System.out
	 */
	public void wordPrint() {
		System.out.print("[");
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) {
				WordInfoSeparateChain bucket = table[i];
				for (WordInfo curr : bucket.getElements()) {
					System.out.print(curr.getWord() + " ");
				}
			}
		}
		System.out.println("]");
	}
}
