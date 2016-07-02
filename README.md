# nocake

Check if your computer is locked via your mobile.

## System Requirements

OS: Windows (tested on Win7 and above)

## Build

This application uses Spring Boot, run `gradlew build` to build the JAR and `gradlew bootRun` to run the application from sources.

## Install & run

Run `gradlew build` and save the JAR file `build/libs/nocake-<version>.jar`. Run the JAR via double click to launch the server.

## Usage

nocake runs a webserver on your computer to allow access via mobile phone. To connect your mobile to the webserver, the application
opens your webbrowser after statup and displays a QR-code which contains the URL to connect. Your mobile shows a red screen when accessing
the URL if your computer is not locked. You can lock your account by tapping the screen or quit the app via the black space at the bottom.
The screen turns green if your computer is locked.
