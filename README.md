# Frequency Analysis Simulator

The purpose of this project is to decrypt a monoalphabetic substitution cipher using frequency analysis and find the optimal algorithm and conditions of decrypting a Vigenere cipher. 

## Methods Used
* Frequency Analysis
* Kasiski Examination
* Freidman Test
* Kerckhoff's Method

## Concepts Used
* Advanced Data Structures - Java Collections API, including TreeSet, HashMap, ArrayList
* Layered Architecture - separated into presentation code (Java Swing framework), business logic (pure Java), and data analysis module (pure Java I/O)

## Usage

1. Clone this repo (for help see the [GitHub documentation](https://help.github.com/articles/cloning-a-repository/)).
2. Run Main.java in the main package of the presentation folder with the following command:

```
mvn compile exec:java -Dexec.mainClass="frequencyanalysissimulator.presentation.main.Main"
``` 

or for short, use `mvn`

### Collecting Data

Run the following command for data collection of a single input (~480 runs/data points):

```
mvn compile exec:java -Dexec.mainClass="dataanalysis.DataCollector"
```

Run the following command for data population of experiment data. To use a different key you will need to edit the default in DataCollector.java and make a new folder and subfolders of all the combinations of key length and caesar decryption algorithms in the following format:
```
outputs/
    [ioc, friedman]_[kasiski, kerckhoff]/
        
```

## Process


**Phase I: Monoalphabetic Ciphers**: July 22, 2019 - February 16, 2020

**Phase II: Vigenere Ciphers**: September 26, 2022 - May 8, 2023

### Upcoming - Phase III

- Rewrite of simple substitution cipher (efficient and accurate, no dictionary)
- Visualization of data
- Data collection GUI
- Variants of Vigenere cipher

### Phase II

* [Data from Phase II](https://docs.google.com/spreadsheets/d/e/2PACX-1vQIqW8qXtnbI1yTCQR_LcYpy6F7p6eZg5EP07no3c-lBoEkMUbpTPyxo_oa5mCCj7Gfk8LOTonOY-4a/pubhtml)
* [Presentation](https://docs.google.com/presentation/d/e/2PACX-1vR5Vu_MXCbKyHm0vHaMW5Tn4qaJWVDV34Z_WX1WpHbejcwIzODNiuNKExOOTFTRUUDs7CPsYwz8PA1T/pub?start=false&loop=false&delayms=3000)


### Phase I

* [Data from Phase I](https://docs.google.com/spreadsheets/d/130cqH1bGJPZ7mq2LrrTY6sMdm6E7qZP2Jea3s8cg3tA/edit#gid=0)
* [Presentation](https://docs.google.com/presentation/d/e/2PACX-1vT29PD0nv69KI9cNDpZdsEA1p4eDg4P8V_XLVCWtpIFXDGnp_WmLrg-xiH120KWJkqppP9DZ-DlREr_/pub?start=false&loop=false&delayms=3000)

## Built With

* [Java](https://www.java.com/en/) - The programming language
* [Maven](https://maven.apache.org/) - The dependency management system 
* [JUnit](https://junit.org/junit5/) - The testing framework

## Sources of Research

* Inspiration from _The Code Book_ by Simon Singh
* _The Cryptanalyst_ by Helen Fouche Gaines
* Michigan Technological University - explanations of the Kasiski Examination and index of coincidence with working examples