# Frequency Analysis Simulator
**Start Date for Part 1: Monoalphabetic Ciphers**: July 22, 2019
**Start Date for Part 2: Vigenere Ciphers**: September 26, 2022

Frequency Analysis Simulator is a Java program that simulates frequency analysis in which the user inputs cipher text into the console and the System outputs as close to the corresponding plain text as possible. If the type of cipher has been identified, the process may be sped up after the user inputs the type of cipher (monoalphabetic or Vigenere) on prompt. Furthermore, Frequency Analysis Simulator can decipher the caesar shift cipher, a cipher that does not involve the use of frequency analysis for decipherment. As another added bonus, the application is able to encrypt messages.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

What things you need to install the software and how to install them

```
Give examples
```

### Installing

1. Download and install Eclipse IDE. 

Say what the step will be

```
Give the example
```

And repeat

```
until finished
```

End with an example of getting some data out of the system or using it for a little demo

## Running the tests

To run a unit test, use the keyboard shortcut `Ctrl + F11` in the Eclipse IDe or click the green circumscribed triangle button in the menu. The result is in the console. 

### Unit Tests

#### secretwriting Package OOP Classes

1. Put the files of the secretwriting package (secretwriting directory) in a new Java project. 
2. In a new class file named SecretWritingTest, insert the following code into the main method

```
Cipher mc = new MonoalphabeticCipher("Green eggs and ham");
System.out.println(mc.getText());
//System.out.println(mc.encrypt()); // Run this line, and it should given an error
System.out.println((MonoalphabeticCipher) mc.encrypt()); // Downcasts
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Eclipse](https://www.eclipse.org/) - The IDE used
* [Java](https://www.java.com/en/) - The programming language used
* [WordNet](https://wordnet.princeton.edu/) - API for predefined list of words used
* [extJWNL](http://extjwnl.sourceforge.net/) - Java library imported; extension of JWNL

## Authors

* **Varun Singh** - *Owner and Programmer* - [varunsingh87](https://github.com/varunsingh87)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Inspiration from _The Code Book_ by Simon Singh
* _The Cryptanalyst_ by Helen Fouche Gaines
* [Kelalaka](https://crypto.stackexchange.com/users/18298/kelalaka) for help on real-world application of the project
* Princeton University "About WordNet." WordNet. Princeton University. 2010.
* Stack Overflow, Inc.
* Gale

Copyright 2022 Varun Singh

<script src = "../script.js"></script>

<script>
   createLinkElement('image/x-icon', 'shortcut icon', 'icon.ico');
</script>  
