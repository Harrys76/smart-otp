# smartotp_plugin

A Plugin smart otp

## Getting Started

This project is an open source fork of the Google Authenticator Android app on the Play Store. While this fork is open source, the official version of the app still remains proprietary. There is no guarantee that the open source repository will receive any changes made upstream (or vice versa).

Google Authenticator generates 2-Step Verification codes on your phone.

2-Step Verification provides stronger security for your Google Account by requiring a second step of verification when you sign in. In addition to your password, you’ll also need a code generated by the Google Authenticator app on your phone.

Learn more about 2-Step Verification: https://g.co/2step

Features:

Generate verification codes without a data connection
Google Authenticator works with many providers & accounts
Dark theme available
Automatic setup via QR code
Disclaimer: This open source fork of Authenticator is not an officially supported Google product.

Description
The Google Authenticator project includes implementations of one-time passcode generators for several mobile platforms, as well as a Pluggable Authentication Module (PAM). One-time passcodes are generated using open standards developed by the Initiative for Open Authentication (OATH) (which is unrelated to OAuth).

This project contains the Android app. All other apps and the PAM module are hosted in separate projects.

The Android implementation supports the HMAC-Based One-time Password (HOTP) algorithm specified in RFC 4226 and the Time-based One-time Password (TOTP) algorithm specified in RFC 6238.

By design, there are no account backups in any of the apps.

Further documentation is available in the Wiki.

Installation
The APKs for the official version and the open source version of Authenticator are hosted separately. Installing the app should be as simple as downloading the APK from your desired source.

Official Google Build
You can install the official (proprietary) version of Google Authenticator from the Google Play Store.

Open Source Version
The easiest way to install the open source flavor of Authenticator is to download the latest version of the APK from the releases page from the GitHub repository. To build the APK from the source code, see the section about building from source.

Reffence documentation : https://github.com/google/google-authenticator-android