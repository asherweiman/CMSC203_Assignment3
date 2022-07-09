/*
 *  Program: Assignment 3
 Instructor: Grigoriy Grinberg
 Summary of Description: This prgram takes in strings and uses either belisario or caesaer cyphers to encrypt or decrypt strings
 Due Date: 07/09/2022 
 Integrity Pledge: I pledge that I have completed the programming assignment independently.
 I have not copied the code from a student or any source.
Student: Asher Weiman

 * 
 * 
 * 
 * */




public class CryptoManager {
	
	private static final char LOWER_BOUND = ' ';
	private static final char UPPER_BOUND = '_';
	private static final int RANGE = UPPER_BOUND - LOWER_BOUND + 1;

	/**
	 * This method determines if a string is within the allowable bounds of ASCII codes 
	 * according to the LOWER_BOUND and UPPER_BOUND characters
	 * @param plainText a string to be encrypted, if it is within the allowable bounds
	 * @return true if all characters are within the allowable bounds, false if any character is outside
	 */
	public static boolean stringInBounds (String plainText) {
		// sets an output boolean variable
		boolean out  = true;
		//runs for length of word
		for (int i = 0; i < plainText.length(); i++) {
			// makes sure teh character at index "i" is within the bounds
 			if (plainText.charAt(i) < ' ' || plainText.charAt(i) > '_') {
				// sets output to false if a char out of bounds
 				out = false;
				
			}
			
			
		}
		return out;
		
	}

	/**
	 * Encrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in plainText is replaced by the character \"offset\" away from it 
	 * @param plainText an uppercase string to be encrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the encrypted string
	 */
	public static String encryptCaesar(String plainText, int key) {
		
		// sets an output string variable
		String out= "";
		//int variable to keep track of key + char value
		int ext;
		//makes sure the word is within bounds
		if (stringInBounds(plainText)) {
			// runs for length of word to encrypt
			for (int i = 0; i < plainText.length(); i++) {
				// keeps track of key + char value so I may change  it to be within range in the while loop
				ext = (int)plainText.charAt(i)+ key;
				while (ext > 95) {ext-=64;} 
				
				//adds the encrypted character to the output string
				out += (char)ext;
				
				
			}
			
			return out;
		}else {
			//if the entered word is not within bounds then return an error 
			return "ERROR";
		}
		
		
	}
	
	/**
	 * Encrypts a string according the Bellaso Cipher.  Each character in plainText is offset 
	 * according to the ASCII value of the corresponding character in bellasoStr, which is repeated
	 * to correspond to the length of plainText
	 * @param plainText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the encrypted string
	 */
	public static String encryptBellaso(String plainText, String bellasoStr) {
		
		
		// sets an output string variable
		String out = "";
		// string to hold an extend string of the bellasoSTR that is the length of the  plainText
		String extendbel= "";
		// int to hold the asciii value of the encrypted char
		int key;
		//makes sure both strigns are within bounds
		if (stringInBounds(plainText) && stringInBounds(bellasoStr)) {
			//runs for the length of the word ot be encrypted
			for (int i =0; i < plainText.length();i++) {
				//adds the bellasoStr atthe index to the strign to hold it to extend the string to the length of the  plainText
				extendbel += bellasoStr.charAt(i % bellasoStr.length()) ;
				
				
			}
			//runs for the length of the word ot be encrypted
			for (int i =0; i < plainText.length();i++) {
				// ascii value of the encryoted character
				key = ((int)plainText.charAt(i) +(int)extendbel.charAt(i));
				// adjusts ascii value into the right range
				while (key > 95) {key -= RANGE;}
				// adds the encrypted char to the output string
				out += (char)key;
			}
			return out;
			
			
		}else {
			return "ERROR";
		}
		
		
		
		
	}
	
	/**
	 * Decrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in encryptedText is replaced by the character \"offset\" characters before it.
	 * This is the inverse of the encryptCaesar method.
	 * @param encryptedText an encrypted string to be decrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the plain text string
	 */
	public static String decryptCaesar(String encryptedText, int key) {
		// sets an output string variable
		String out= "";
		
		//int variable to keep track of key + char value
		int ext;
		
		
		//runs for the length of the word ot be encrypted
		for (int i = 0; i < encryptedText.length(); i++) {
			//stores ascii valu of unencrypted character
			ext = ((int)encryptedText.charAt(i)- key);
			// adjusts character to be in the right range
			while (ext > 95) {ext-=64;} 
			while (ext < 32) {ext+=64;} 
			
			// adds decrypted character to the output string
			out += (char)ext;
			
		}
			
		return out;
		
		
	}
	
	/**
	 * Decrypts a string according the Bellaso Cipher.  Each character in encryptedText is replaced by
	 * the character corresponding to the character in bellasoStr, which is repeated
	 * to correspond to the length of plainText.  This is the inverse of the encryptBellaso method.
	 * @param encryptedText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the decrypted string
	 */
	public static String decryptBellaso(String encryptedText, String bellasoStr) {
		// sets an output string variable
		String out = "";
		
		
		// string to hold an extend string of the bellasoSTR that is the length of the  plainText
		String extendbel= "";
		
		// int to hold the ASCII value of the decrypted char
		int key;
		//makes sure both strign is within bounds
		if (stringInBounds(bellasoStr)) {
			
			//runs for the length of the encrypted word 
			for (int i =0; i < encryptedText.length();i++) {
				//adds the bellasoStr atthe index to the strign to hold it to extend the string to the length of the encrypted word
				extendbel += bellasoStr.charAt(i % bellasoStr.length()) ;
			}
			//runs for the length of the encrypted word 
			for (int i =0; i < encryptedText.length();i++) {
				// ascii value of the decryoted character
				key = ((int)encryptedText.charAt(i) -(int)extendbel.charAt(i));
				// gets the ascii value in the right range
				while (key > 95) {key -= RANGE;}
				while (key < 32) {key += RANGE;}
				// adds the decrypted char within the range to the output string
				out += (char)key;
			}
			return out;
			
			
		}else {
			return "ERROR";
		}
		
		
		
	}
}
