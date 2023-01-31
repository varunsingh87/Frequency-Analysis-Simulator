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
* Layered Architecture - separated into presentation code (Java Swing framework) and business logic (pure Java)

## Project Description
Frequency Analysis Simulator uses frequency analysis to output a plain text from a ciphertext without being given the key. If the type of cipher has been identified, the process may be sped up after the user inputs the type of cipher (monoalphabetic or Vigenere) on prompt. Frequency Analysis Simulator can decipher the Caesar cipher, a cipher that does not involve the use of frequency analysis for deciphering. The application can encrypt messages, too.

## Getting Started

1. Clone this repo (for help see the [GitHub documentation](https://help.github.com/articles/cloning-a-repository/)).
2. Run Main.java in the main package of the presentation folder with the following command:

```
mvn compile exec:java -Dexec.mainClass="frequencyanalysissimulator.presentation.main.Main"
``` 

or for short, use `mvn`

### Running the data analysis tool

Run the following command:

```
mvn compile exec:java -Dexec.mainClass="dataanalysis.DataAnalysis"
```

## Process
* [Blog Post for Part 1](https://dev.to/varuns924/how-i-wrote-a-background-noise-remover-from-start-to-finish-3h9m)
* [Data from 2019 Phase]()
* [Current Data (Vigenere Phase)](https://docs.google.com/spreadsheets/d/1XFQO-QX4YYL0vaCMqR6Jr5qXWtcWEMhbKEUsunjp1KY/edit#gid=0)

## Built With

* [Java](https://www.java.com/en/) - The programming language
* [Maven] - The dependency management system 
* [JUnit] - The testing framework
* *No longer used for this project* [WordNet](https://wordnet.princeton.edu/) - API for predefined list of words used
* *No longer used for this project* [extJWNL](http://extjwnl.sourceforge.net/) - Java library imported; extension of JWNL

## Authors

* **Varun Singh** - *Creator* - [varunsingh87](https://github.com/varunsingh87)

## License

Copyright &copy; 2023 Varun Singh

## Acknowledgments

* Inspiration from _The Code Book_ by Simon Singh
* _The Cryptanalyst_ by Helen Fouche Gaines
* [Kelalaka](https://crypto.stackexchange.com/users/18298/kelalaka) for help on real-world application of the project
* Princeton University "About WordNet." WordNet. Princeton University. 2010.
* Michigan Technological University for explaining the Kasiski Examination and index of coincidence with working examples
* Pixel from IDTech for helping me through my first Java project