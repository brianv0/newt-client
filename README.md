newt-client
===========

A Java based client for NERSC's NEWT

This client depends on Jackson and Jersey.

Check out src/main/java/gov/nersc/newt/NewtClient.java for the default client.

Unit tests also show how to use most of the methods.

To run the unit tests, you must add your username and password to a maven profile, and activate that profile.

The majority of the application only throws Exceptions that a RuntimeExceptions, 
( mostly classes extending WebApplicationException / ClientErrorException ) 
so it's up to the user to catch those exceptions.
