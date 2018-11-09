# CS-555
Reposity for CS 555 Group 6 Final Project: A syntax checker for GEDCOM files.

To run code on acceptance test case:
```shell
javac Person.java Family.java ReadGedcom.java FindErrors.java ProjectMain.java
java ProjectMain
```

To run individual tests for User Stories, compile the same java files as well as the test file with naming convention TestS followed by Sprint number and User story letter code (ex: TestS2A.java):
```shell
javac Person.java Family.java ReadGedcom.java FindErrors.java TestS2A.java
java TestS2A
```

|User Story| Story Description | Sprint #  |  Letter Code | Test File Name
|----------|:-----------------:|:-------------:|------:|:-----------------:|
| US07     |Less then 150 years old|  2 |        A | TestS2A.java|
| US08     |Birth before marriage of parents | 2 | B| TestS2B.java|
| US09     | Birth before death of parents | 2 | C| TestS2C.java|
| US10     | Marriage after 14     | 2| D| TestS2D.java|
| US13     | Siblings spacing      | 2 | E |TestS2E.java|
| US15     | Fewer than 15 siblings | 2 | F |TestS2F.java|
| US25     | Unique first names in families | 3 | A  |TestS3A.java|
| US27     | Include individual ages | 3 | C |TestS3C.java|
