# Frequency Analysis Simulator

The purpose of this project is to decrypt a monoalphabetic substitution cipher using frequency analysis and find the
optimal algorithm and conditions of decrypting a Vigenere cipher.

## Methods of Cryptanalysis

* Frequency Analysis
* Kasiski Examination
* Freidman Test
* Kerckhoff's Method
* Dictionary attack using WordNet API

## Usage

1. Clone this repo with `git clone https://github.com/varunsingh87/Frequency-Analysis-Simulator`
   (for help see the [GitHub documentation](https://help.github.com/articles/cloning-a-repository/)).
2. Run `mvn package`
2. Run the GUI with the following command:

```
mvn compile exec:java"
``` 

### Collecting Data

Run the following command for data collection of a single input (~480 runs/data points):

```
mvn compile exec:java -Dexec.mainClass="dataanalysis.DataCollector"
```

Run the following command for data population of experiment data. To use a different key you will need to edit the
default in DataCollector.java and make a new folder and subfolders of all the combinations of key length and caesar
decryption algorithms in the following format:

```
outputs/
[ioc, friedman]_[kasiski, kerckhoff]/

```

### Generate executable file

To create an executable file without the user needing the Java Runtime Environment on his or her computer, use
the `jpackage` utility from the Java Development Kit:

**Mac
**: `jpackage --input target/ --name 'Frequency Analysis Simulation' --main-jar com.varunsingh.frequencyanalysissimulator-1.00.jar --main-class frequencyanalysissimulator.presentation.main.Main --type dmg --icon ./assets/icon.icns`

**Windows
**: `jpackage --input target/ --name 'Frequency Analysis Simulation' --main-jar com.varunsingh.frequencyanalysissimulator-1.00.jar --main-class frequencyanalysissimulator.presentation.main.Main --type exe --icon ./assets/icon.ico`
*Does not work on Mac*

**Linux
**: `jpackage --input target/ --name 'Frequency Analysis Simulation' --main-jar com.varunsingh.frequencyanalysissimulator-1.00.jar --main-class frequencyanalysissimulator.presentation.main.Main --type deb --icon ./assets/icon.ico`
*Does not work on Mac*

Do the same for any other Java class with a main method that you would like to turn into an executable file.

## Process

**Phase I: Monoalphabetic Ciphers**: July 22, 2019 - February 16, 2020

**Phase II: Vigenere Ciphers**: September 26, 2022 - May 8, 2023

**Phase III: Graphics, Refactors, Variants**: July 22, 2023 - November 26, 2023

### Phase III

* Data GUI
* Data collection GUI
* Variants of Vigenere cipher

### Phase II

* [Data from Phase II](https://docs.google.com/spreadsheets/d/e/2PACX-1vQIqW8qXtnbI1yTCQR_LcYpy6F7p6eZg5EP07no3c-lBoEkMUbpTPyxo_oa5mCCj7Gfk8LOTonOY-4a/pubhtml)
* [Presentation](https://docs.google.com/presentation/d/e/2PACX-1vR5Vu_MXCbKyHm0vHaMW5Tn4qaJWVDV34Z_WX1WpHbejcwIzODNiuNKExOOTFTRUUDs7CPsYwz8PA1T/pub?start=false&loop=false&delayms=3000)

### Phase I

* [Data from Phase I](https://docs.google.com/spreadsheets/d/130cqH1bGJPZ7mq2LrrTY6sMdm6E7qZP2Jea3s8cg3tA/edit#gid=0)
* [Presentation](https://docs.google.com/presentation/d/e/2PACX-1vT29PD0nv69KI9cNDpZdsEA1p4eDg4P8V_XLVCWtpIFXDGnp_WmLrg-xiH120KWJkqppP9DZ-DlREr_/pub?start=false&loop=false&delayms=3000)

## Concepts Used

* Advanced Data Structures - Java Collections API, including TreeSet, HashMap, ArrayList
* Layered Architecture - separated into presentation code (Java Swing framework), business logic (pure Java), and data
  analysis module (I/O Streams)
* Recursion

## Built With

* [Java](https://www.java.com/en/) - The programming language
* [Maven](https://maven.apache.org/) - The dependency management system
* [JUnit](https://junit.org/junit5/) - The testing framework

## Sources of Research

* Inspiration from _The Code Book_ by Simon Singh
* _The Cryptanalyst_ by Helen Fouche Gaines
* Michigan Technological University - explanations of the Kasiski Examination and index of coincidence with working
  examples