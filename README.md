DictionaryMaker
===============

Creates a key-value dictionary using the google dictionary API and a given a word list.

Usage: java -jar DictionaryMaker.jar WORDLIST DEFINITIONS FAILEDWORDS

where 	WORDLIST is the a text file (.txt) of the words
	DEFINITIONS is the name of the file in which to write the definitions
	FAILEDWORDS is the name of the file in which to wriet the words that failed.

A sample word list is provided {words.txt}

Current Stage: Completed

ATTENTION: The GOOGLE dictionary API is limited to approximately 8000 requests
per hour.
