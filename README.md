# Frequency Analysis Simulator
**Start Date for Part 1: Monoalphabetic Ciphers**: July 22, 2019

**Start Date for Part 2: Vigenere Ciphers**: September 26, 2022

## Objective
The purpose of this project is to decrypt a monoalphabetic substitution cipher using frequency analysis and find the most optimal algorithm of decrypting a Vigenere cipher. 

## Methods Used
* Frequency Analysis
* Kasiski Examination
* Freidman Test
* Kerckhoff's Method

## Concepts Used
* Advanced Data Structures - Java Collections API, including TreeSet, HashMap, ArrayList

## Project Description
Frequency Analysis Simulator is a Java program that simulates frequency analysis in which the user inputs cipher text into the console and the System outputs as close to the corresponding plain text as possible. If the type of cipher has been identified, the process may be sped up after the user inputs the type of cipher (monoalphabetic or Vigenere) on prompt. Furthermore, Frequency Analysis Simulator can decipher the caesar shift cipher, a cipher that does not involve the use of frequency analysis for decipherment. The application is additionally able to encrypt messages.

## Getting Started

1. Clone this repo (for help see the [GitHub documentation](https://help.github.com/articles/cloning-a-repository/)).
2. Run Main.java in the main package of the presentation folder with the following command:

```
mvn compile exec:java -Dexec.mainClass="frequencyanalysissimulator.presentation.main.Main"
``` 

or for short, use `mvn`

## Process
* [Blog Post for Part 1](https://dev.to/varuns924/how-i-wrote-a-background-noise-remover-from-start-to-finish-3h9m)


## Built With

* [Eclipse](https://www.eclipse.org/) - The IDE used
* [Java](https://www.java.com/en/) - The programming language used
* [WordNet](https://wordnet.princeton.edu/) - API for predefined list of words used
* [extJWNL](http://extjwnl.sourceforge.net/) - Java library imported; extension of JWNL

## Authors

* **Varun Singh** - *Owner and Programmer* - [varunsingh87](https://github.com/varunsingh87)

## License

Copyright &copy; 2022 Varun Singh

## Acknowledgments

* Inspiration from _The Code Book_ by Simon Singh
* _The Cryptanalyst_ by Helen Fouche Gaines
* [Kelalaka](https://crypto.stackexchange.com/users/18298/kelalaka) for help on real-world application of the project
* Princeton University "About WordNet." WordNet. Princeton University. 2010.
* Stack Overflow, Inc.
* Gale