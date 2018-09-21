## Steps
1. [Setup Ionic and MFP CLI](#step-1-setup-ionic-and-mfp-cli)
2. [Create Ionic Sample Application](#step-2-create-ionic-sample-application)
  - 2.1 [Create a new Ionic project](#21-create-a-new-ionic-project)
  - 2.2 [Start a local dev server for app dev/testing](#22-start-a-local-dev-server-for-app-devtesting)
  - 2.3 [Update App ID, Name and Description](#23-update-app-id-name-and-description)
  - 2.4 [Run application on Android phone](#24-run-application-on-android-phone)
    - 2.4.1 [Install Android Studio and Android SDK platform](#241-install-android-studio-and-android-sdk-platform)
    - 2.4.2 [Enable developer options and USB debugging on your Android phone](#242-enable-developer-options-and-usb-debugging-on-your-android-phone)
    - 2.4.3 [Enable Android platform for Ionic application](#243-enable-android-platform-for-ionic-application)
    - 2.4.4 [Build/Run the Ionic application on Android phone](#244-buildrun-the-ionic-application-on-android-phone)
  - 2.5 [Update App Logo and Splash](#25-update-app-logo-and-splash)
  - 2.6 [Fix issue where you see a blank screen after your splash screen disappears](#26-fix-issue-where-you-see-a-blank-screen-after-your-splash-screen-disappears)
3. [Add pre-emptive login](#step-3-add-pre-emptive-login)
  - 3.1 [Create login page](#31-create-login-page)
    - 3.1.1 [Add Login UI](#311-add-login-ui)
    - 3.1.2 [Handle login action](#312-handle-login-action)
    - 3.1.3 [Show login page upon app launch](#313-show-login-page-upon-app-launch)
  - 3.2 [Create Mobile Foundation service and configure MFP CLI](#32-create-mobile-foundation-service-and-configure-mfp-cli)
  - 3.3 [Add MFP Security Adapter](#33-add-mfp-security-adapter)
  - 3.4 [Add the Cordova plugin for MFP](#34-add-the-cordova-plugin-for-mfp)
  - 3.5 [Register the app to MobileFirst Server](#35-register-the-app-to-mobilefirst-server)
  - 3.6 [Create a new provider in Ionic mobile app to assist in handling MFP security challenges](#36-create-a-new-provider-in-ionic-mobile-app-to-assist-in-handling-mfp-security-challenges)
  - 3.7 [Initialize AuthHandler after MobileFirst SDK is loaded](#37-initialize-authhandler-after-mobilefirst-sdk-is-loaded)
  - 3.8 [Update Login controller to use MFP based user authentication](#38-update-login-controller-to-use-mfp-based-user-authentication)
  - 3.9 [Test pre-emptive login](#39-test-pre-emptive-login)
4. [Fetch data from Cloudant database via MFP Adapter](#step-4-fetch-data-from-cloudant-database-via-mfp-adapter)
  - 4.1 [Create Cloudant database and populate it with sample data](#41-create-cloudant-database-and-populate-it-with-sample-data)
  - 4.2 [Create MFP adapter to query Cloudant data](#42-create-mfp-adapter-to-query-cloudant-data)
    - 4.2.1 [Download sample MFP Java adapter for Cloudant](#421-download-sample-mfp-java-adapter-for-cloudant)
    - 4.2.2 [Point the MFP adapter to your Cloudant service instance](#422-point-the-mfp-adapter-to-your-cloudant-service-instance)
    - 4.2.3 [Update adapter methods to return MyWard Grievances data](#423-update-adapter-methods-to-return-myward-grievances-data)
    - 4.2.4 [Build and Deploy the MFP adapter](#424-build-and-deploy-the-mfp-adapter)
    - 4.2.5 [Map MyWardData's protecting scope to UserLogin security check](#425-map-mywarddatas-protecting-scope-to-userlogin-security-check)
    - 4.2.6 [Test the newly created MFP adapter](#426-test-the-newly-created-mfp-adapter)
  - 4.3 [Update Ionic app to fetch and display data from MFP Adapter](#step-43-update-ionic-app-to-fetch-and-display-data-from-mfp-adapter)
    - 4.3.1 [Create a new provider in Ionic app for calling MFP adapter API](#431-create-a-new-provider-in-ionic-app-for-calling-mfp-adapter-api)
    - 4.3.2 [Modify home page to display the list of problems reported](#432-modify-home-page-to-display-the-list-of-problems-reported)
    - 4.3.3 [Test updated home page](#433-test-updated-home-page)
5. [Use IBM Cloud Object Storage for storing and retrieving images](#step-5-use-ibm-cloud-object-storage-for-storing-and-retrieving-images)
  - 5.1 [Create IBM Cloud Object Storage service and API key](#51-create-ibm-cloud-object-storage-service-and-api-key)
    - 5.1.1 [Create IBM Cloud Object Storage service and populate it with sample data](#511-create-ibm-cloud-object-storage-service-and-populate-it-with-sample-data)
    - 5.1.2 [Create Service ID and API Key for accessing objects](#512-create-service-id-and-api-key-for-accessing-objects)
  - 5.2 [Add function in MFP Adapter to fetch Authorization token from IBM Cloud Object Storage](#52-add-function-in-mfp-adapter-to-fetch-authorization-token-from-ibm-cloud-object-storage)
  - 5.3 [Modify Ionic App to display images](#53-modify-ionic-app-to-display-images)
6. [Show problem details page with location marked on Google Maps](#step-6-show-problem-details-page-with-location-marked-on-google-maps)
7. [Capture image and geolocation and upload to server](#step-7-capture-image-and-geolocation-and-upload-to-server)

## Step 1. Setup Ionic and MFP CLI
* Install `Node.js` by downloading the setup from https://nodejs.org/en/ (Node.js 8.x or above)
```
$ node --version
v8.6.0
```

* Install Cordova
```
$ sudo npm install -g cordova
$ cordova --version
7.0.1
```

**Note**: If you are on Windows, instead of using `sudo`, run the above command (and the ones below) in a command prompt opened in administrative mode.

* Install Ionic
```
$ sudo npm install -g ionic
$ ionic --version
3.19.0
```

* Install IBM MobileFirst Platform CLI
```
$ sudo npm install -g mfpdev-cli
$ mfpdev --version
8.0.0-2017091111
```

  Note: While installing MFP CLI, if you hit an error saying `npm ERR! package.json npm can't find a package.json file in your current directory.`, then it is most likely due to [MFP CLI not being supported in your npm version](https://stackoverflow.com/questions/46168090/ibm-mobile-first-mfpdev-cli-installation-failure). In such a case, downgrade your npm as below, and then install MFP CLI.
  ```
  $ sudo npm install -g npm@3.10.10
  ```

* Install GIT https://git-scm.com/downloads
```
$ git --version
git version 2.9.3 ...
```

* Install Maven:
On Mac, you can use `brew install` for installing Maven as shown below:
```
$ /usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
$ brew install maven
$ mvn --version
Apache Maven 3.5.0 ...
```

* Install Java SDK from http://www.oracle.com/technetwork/java/javase/downloads/index.html
```
$ java -version
java version "1.8.0_101"
```

* Install an [IDE for TypeScript](https://www.typescriptlang.org/index.html#download-links) such as [Atom](https://atom.io/) on Mac.

  After installing [Atom](https://atom.io/), install TypeScript plugin for Atom as shown below.
```
apm install atom-typescript
```

## Step 2. Create Ionic Sample Application

### 2.1 Create a new Ionic project

Create a new Ionic project with blank starter template
```
$ ionic start IonicMobileApp blank
✔ Creating directory ./IonicMobileApp - done!
...
? Would you like to integrate your new app with Cordova to target native iOS and Android? Yes
...
> npm i
...
> git init

? Install the free Ionic Pro SDK and connect your app? No
...
> git add -A
> git commit -m "Initial commit" --no-gpg-sign
...
```

Change directory to the newly created project:
```
$ cd ./IonicMobileApp
```

### 2.2 Start a local dev server for app dev/testing

To get a preview of the application, Ionic/Cordova provides a feature by the which the application can be launched in a browser by using the `cordova serve` or `ionic serve` as shown below:
```
$ ionic serve -c
[INFO] Starting app-scripts server: --address 0.0.0.0 --port 8100 
       --livereload-port 35729 --dev-logger-port 53703 --consolelogs --nobrowser - Ctrl+C to cancel
[17:20:10]  watch started ... 
[17:20:10]  build dev started ... 
[17:20:10]  clean started ... 
[17:20:10]  clean finished in 1 ms 
[17:20:10]  copy started ... 
[17:20:10]  deeplinks started ... 
[17:20:10]  deeplinks finished in 22 ms 
[17:20:10]  transpile started ... 
[17:20:13]  transpile finished in 3.58 s 
[17:20:13]  preprocess started ... 
[17:20:14]  copy finished in 3.83 s 
[17:20:14]  preprocess finished in 185 ms 
[17:20:14]  webpack started ... 
[17:20:21]  webpack finished in 7.48 s 
[17:20:21]  sass started ... 
[17:20:22]  sass finished in 1.01 s 
[17:20:22]  postprocess started ... 
[17:20:22]  postprocess finished in 5 ms 
[17:20:22]  lint started ... 
[17:20:22]  build dev finished in 12.36 s 
[17:20:22]  watch ready in 12.42 s 
[17:20:22]  dev server running: http://localhost:8100/ 

[INFO] Development server running!
       Local: http://localhost:8100
       External: http://192.xxx.xxx.xxx:8100, http://9.xxx.xxx.xxx:8100
       DevApp: IonicMobileApp@8100 on shivahr
```

The above command also launches the Cordova [live-reload](https://www.npmjs.com/package/cordova-plugin-browsersync) workflow. The live-reload feature watches for changes in your source files and automatically builds the project and reloads the application in browser.

Since the `ionic serve` command continues to run in foreground, to be able to run further Cordova/Ionic commands open a new terminal and change directory to the project.


### 2.3 Update App ID, Name and Description
Update `IonicMobileApp/config.xml` as below. Change `id`, `name`, `description` and `author` details appropriately.

<pre><code>
&lt;?xml version='1.0' encoding='utf-8'?&gt;
&lt;widget <b>id="org.mycity.myward"</b> version="0.0.1" xmlns="http://www.w3.org/ns/widgets" xmlns:cdv="http://cordova.apache.org/ns/1.0" xmlns:mfp="http://www.ibm.com/mobilefirst/cordova-plugin-mfp"&gt;
    <b>&lt;name&gt;MyWard&lt;/name&gt;
    &lt;description&gt;Get your civic issues resolved by posting through this app.&lt;/description&gt;
    &lt;author email="shivahr@gmail.com" href="https://developer.ibm.com/code/author/shivahr/"&gt;Shiva Kumar H R&lt;/author&gt;</b>
...
</code></pre>

### 2.4 Run application on Android phone

#### 2.4.1 Install Android Studio and Android SDK platform
* Download and install Android Studio from https://developer.android.com/studio/index.html
* Install Android SDK Platform 23 (or higher) as below:
  - Launch Android Studio.
  - Click on `Configure` -> `SDK Manager`.
  - Make a note of the Android SDK Location.
  - Under `SDK Platforms`, select `Android 6.0 (Marshmallow) API Level 23` or higher. Click `Apply` and then click `OK`. This will install Android SDK Platform on your machine.
 
#### 2.4.2 Enable developer options and USB debugging on your Android phone
* Enable USB debugging on your Android phone as per the steps in https://developer.android.com/studio/debug/dev-options.html
  - Launch the Settings app on your phone. Select `About Device` -> `Software Info`. Tap `Build number` 7 times to enable developer options.
  - Return to Settings list. Select `Developer options` and enable `USB debugging`.
* If you are developing on Windows, then you need to install the appropriate USB driver as per instructions in https://developer.android.com/studio/run/oem-usb.html.
* Connect the Android phone to your development machine by USB cable, and accept `allow` access on your phone.

#### 2.4.3 Enable Android platform for Ionic application

* Add [Cordova platform for Android](https://cordova.apache.org/docs/en/latest/guide/platforms/android/)
```
$ ionic cordova platform add android@6.3.0
> cordova platform add android@6.3.0 --save
...
```

  Note: Make sure the Cordova platform version being added is supported by the MobileFirst plug-ins. Site https://mobilefirstplatform.ibmcloud.com/tutorials/en/foundation/8.0/application-development/sdk/cordova/ lists the supported levels.
```
$ cordova platform version
Installed platforms:
  android 6.3.0
Available platforms: 
  blackberry10 ~3.8.0 (deprecated)
  browser ~4.1.0
  ios ~4.4.0
  osx ~4.0.1
  webos ~3.7.0
```

[Cordova Android 6.3.0](https://cordova.apache.org/announcements/2017/09/27/android-release.html) targets Android API level of API 26. Instead of that, if you want to [target API 23 or a different one](https://stackoverflow.com/questions/35573485/ionic-add-platform-android-with-custom-android-target), then edit `IonicMobileApp/config.xml` and add preference for `android-targetSdkVersion` as shown below.
```
  <preference name="android-minSdkVersion" value="16" />
  <preference name="android-targetSdkVersion" value="23" />
```

Note: Please make sure you install the *Android SDK Platform* for the API level that you use as per instructions in [Step 2.4.1](#241-install-android-studio-and-android-sdk-platform).

#### 2.4.4 Build/Run the Ionic application on Android phone

* Build Android application
```
$ ionic cordova build android
```

  Note: In case the Cordova build fails due to missing `ANDROID_HOME` and `JAVA_HOME` environment variables, then set those environment variables as per instructions in https://cordova.apache.org/docs/en/latest/guide/platforms/android/#setting-environment-variables. `ANDROID_HOME` should be set to the `Android SDK Location` that you noted in [Step 2.4.1](#241-install-android-studio-and-android-sdk-platform). Command `/usr/libexec/java_home` returns the value to be used for setting `JAVA_HOME` on [macOS](http://mattshomepage.com/articles/2016/May/22/java_home_mac_os_x/). On other platforms you could run `java -XshowSettings:properties 2>&1 | grep 'java.home'` as mentioned [here](http://sbndev.astro.umd.edu/wiki/Finding_and_Setting_JAVA_HOME#Sample_Perl_Script:_java_home).

* Run application on Android device
```
$ ionic cordova run android
```

Upon app launch, the sample page should get displayed as shown below:

<table><tr><td>
  <img src="doc/source/images/SampleIonicAppRunningOnAndroid.png" alt="Snapshot of app running on Android device" width="240" border="10" />
</td></tr></table>

### 2.5 Update App Logo and Splash

Reference: Automating Icons and Splash Screens https://blog.ionic.io/automating-icons-and-splash-screens/

Copy your desired app icon to `IonicMobileApp/resources/icon.png` and app splash to `IonicMobileApp/resources/splash.png`.

```
$ cd ../IonicMobileApp
$ ionic cordova resources
```

For running `ionic cordova resources` command, you would need to sign up on [ionicframework.com](https://ionicframework.com/) and specify the credentials on the command line.

Optionally, install the StatusBar plugin as below:

```
$ ionic cordova plugin add cordova-plugin-statusbar
> cordova plugin add cordova-plugin-statusbar --save
Installing "cordova-plugin-statusbar" for android
```

### 2.6 Fix issue where you see a blank screen after your splash screen disappears

Reference: http://www.codingandclimbing.co.uk/blog/ionic-2-fix-splash-screen-white-screen-issue

In `IonicMobileApp/config.xml`, add preferences for `AutoHideSplashScreen` and `FadeSplashScreen` after `SplashScreenDelay` as shown below:
<pre><code>
...
&lt;widget id=...&gt;
  &lt;preference name="SplashScreenDelay" value="3000" /&gt;
  <b>&lt;preference name="AutoHideSplashScreen" value="false" /&gt;
  &lt;preference name="FadeSplashScreen" value="false" /&gt;</b>
  ...
</code></pre>

Update `IonicMobileApp/src/app/app.component.ts` as below:
<pre><code>
...
export class MyApp {
  ...
    platform.ready().then(() => {
      ...
      statusBar.styleDefault();
      <b>setTimeout(() => {
        splashScreen.hide();
      }, 100);</b>
    });
  }
}
</code></pre>


## Step 3. Add pre-emptive login

### 3.1 Create login page

#### 3.1.1 Add Login UI

```
$ ionic generate page login
[OK] Generated a page named login!
```

Update `IonicMobileApp/src/pages/login/login.html` as below:

<pre><code>
&lt;ion-header&gt;
  &lt;ion-navbar&gt;
    &lt;ion-title&gt;<b>Login</b>&lt;/ion-title&gt;
  &lt;/ion-navbar&gt;
&lt;/ion-header&gt;

&lt;ion-content&gt;
  <b>&lt;form (submit)="processForm()" [formGroup]="form"&gt;
    &lt;ion-list&gt;
      &lt;ion-item&gt;
        &lt;ion-label fixed&gt;Username&lt;/ion-label&gt;
        &lt;ion-input formControlName="username" type="text"&gt;&lt;/ion-input&gt;
      &lt;/ion-item&gt;
      &lt;ion-item&gt;
        &lt;ion-label fixed&gt;Password&lt;/ion-label&gt;
        &lt;ion-input formControlName="password" type="password"&gt;&lt;/ion-input&gt;
      &lt;/ion-item&gt;
    &lt;/ion-list&gt;
    &lt;div padding&gt;
      &lt;button ion-button block type="submit"&gt;Sign In&lt;/button&gt;
    &lt;/div&gt;
  &lt;/form&gt;</b>
&lt;/ion-content&gt;
</code></pre>

#### 3.1.2 Handle login action
Add the code for handling pre-emptive login

Update `IonicMobileApp/src/pages/login/login.ts` as below:

<pre><code>
import { Component } from '@angular/core';
import { NavController, NavParams<b>, AlertController</b> } from 'ionic-angular';
<b>import { FormGroup, FormControl, Validators } from '@angular/forms';</b>

<b> // @IonicPage() </b>
@Component({
  selector: 'page-login',
  templateUrl: 'login.html',
})
export class LoginPage {
  <b>form;</b>

  constructor(public navCtrl: NavController, public navParams: NavParams<b>,
      public alertCtrl: AlertController</b>) {
    <b>console.log('--> LoginPage constructor() called');
    this.form = new FormGroup({
      username: new FormControl("", Validators.required),
      password: new FormControl("", Validators.required)
    });</b>
  }

  <b>processForm() {
    // Reference: https://github.com/driftyco/ionic-preview-app/blob/master/src/pages/inputs/basic/pages.ts
    let username = this.form.value.username;
    let password = this.form.value.password;
    if (username === "" || password === "") {
      this.showAlert('Login Failure', 'Username and password are required');
      return;
    }
    console.log('--> Sign-in with user: ' + username);
    this.showAlert('Login', 'Signing-in as ' + username);
  }

  showAlert(alertTitle, alertMessage) {
    let prompt = this.alertCtrl.create({
      title: alertTitle,
      message: alertMessage,
      buttons: [{
        text: 'Ok',
      }]
    });
    prompt.present();
  }</b>

  ionViewDidLoad() {
    console.log(<b>'--> LoginPage ionViewDidLoad() called'</b>);
  }

}
</code></pre>

#### 3.1.3 Show login page upon app launch

Update `IonicMobileApp/src/app/app.module.ts` as below:

<pre><code>
...
import { MyApp } from './app.component';
<b>import { LoginPage } from '../pages/login/login'</b>
import { HomePage } from '../pages/home/home'

@NgModule({
  declarations: [
    MyApp,
    <b>LoginPage,</b>
    HomePage
  ],
  imports: [
    BrowserModule,
    IonicModule.forRoot(MyApp)
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    <b>LoginPage,</b>
    HomePage
  ],
  providers: [
    ...
  ]
})
export class AppModule {}
</code></pre>

Update `IonicMobileApp/src/app/app.component.ts` as below:

<pre><code>
...
import { SplashScreen } from '@ionic-native/splash-screen';
import { <b>LoginPage</b> } from '<b>../pages/login/login</b>'

@Component({
  templateUrl: 'app.html'
})
export class MyApp {
  <b>rootPage:any = LoginPage;</b>

  constructor(platform: Platform, statusBar: StatusBar, splashScreen: SplashScreen) {
    ...
  }
}
</code></pre>

#### 3.1.4 Build/Run the Ionic application on Android phone

```
$ ionic cordova build android
$ ionic cordova run android
```

Upon app launch, the login page should get displayed as shown below.

  <img src="doc/source/images/MyWardAppLoginPage.png" alt="MyWard App - Login Page" width="240" border="10" />


### 3.2 Create Mobile Foundation service and configure MFP CLI

In the [IBM Cloud Dashboard](https://console.bluemix.net/), click on `Catalog` and select [Mobile Foundation](https://console.bluemix.net/catalog/services/mobile-foundation) service under `Platform` -> `Mobile`. Click on `Create` as shown below.

  <img src="doc/source/images/CreateMobileFoundationService.png" alt="Create IBM Mobile Foundation service" width="800" border="10" />

In the Mobile Foundation service overview page that gets shown, click on `Service credentials`. Expand `View credentials` and make a note of the `url`, `user` and `password` as shown below.

  <img src="doc/source/images/MobileFoundationServiceCredentials.png" alt="IBM Mobile Foundation service credentials" width="800" border="10" />

* Back on your local machine, configure MFP CLI to work with Mobile Foundation server by running following command in console.

  Note: For `Enter the fully qualified URL of this server:`, enter the `url` mentioned in credentials followed by `:443` (the default HTTPS port).

```
$ mfpdev server add
? Enter the name of the new server profile: Cloud-MFP
? Enter the fully qualified URL of this server: https://mobilefoundation-71-hb-server.mybluemix.net:443
? Enter the MobileFirst Server administrator login ID: admin
? Enter the MobileFirst Server administrator password: **********
? Save the administrator password for this server?: Yes
? Enter the context root of the MobileFirst administration services: mfpadmin
? Enter the MobileFirst Server connection timeout in seconds: 30
? Make this server the default?: Yes
Verifying server configuration...
The following runtimes are currently installed on this server: mfp
Server profile 'Cloud-MFP' added successfully.

$ mfpdev server info
Name         URL
--------------------------------------------------------------------------------------
Cloud-MFP  https://mobilefoundation-71-hb-server.mybluemix.net:443        [Default]
--------------------------------------------------------------------------------------
```

### 3.3 Add MFP Security Adapter

https://mobilefirstplatform.ibmcloud.com/tutorials/en/foundation/8.0/authentication-and-security/user-authentication/security-check/

Create directory for MFP Adapters
```
$ cd ..
$ mkdir MobileFoundationAdapters
$ cd MobileFoundationAdapters
```

Download UserLogin adapter from https://github.com/MobileFirst-Platform-Developer-Center/SecurityCheckAdapters/tree/release80/UserLogin. This is a simple security adapter that returns success when password equals username.

```
$ curl -LOk https://github.com/MobileFirst-Platform-Developer-Center/SecurityCheckAdapters/archive/release80.zip
$ unzip release80.zip
$ mv SecurityCheckAdapters-release80/UserLogin/ .
$ rm -rf SecurityCheckAdapters-release80/ release80.zip
$ ls
UserLogin
```

Edit `MobileFoundationAdapters/UserLogin/src/main/adapter-resources/adapter.xml` and update defaultValue of [successStateExpirationSec](https://mobilefirstplatform.ibmcloud.com/tutorials/en/foundation/8.0/authentication-and-security/user-authentication/security-check/#adding-rememberme-functionality) and [rememberMeDurationSec](https://mobilefirstplatform.ibmcloud.com/tutorials/en/foundation/8.0/authentication-and-security/user-authentication/security-check/#adding-rememberme-functionality) as below. This is to ensure that a successful login is remembered for 10 minutes. Update defaultValue of `blockedStateExpirationSec` to 300 (5 minutes).

<pre><code>
&lt;mfp:adapter name="UserLogin" ...&gt;
  ...
  &lt;securityCheckDefinition name="UserLogin" class="com.sample.UserLogin"&gt;
    &lt;property name="maxAttempts" defaultValue="3" description="How many attempts are allowed" type="integer"/&gt;
    &lt;property name="blockedStateExpirationSec" <b>defaultValue="300"</b> description="How long before the client can try again (seconds)" type="integer"/&gt;
    &lt;property name="successStateExpirationSec" <b>defaultValue="600"</b> description="How long is a successful state valid for (seconds)" type="integer"/&gt;
    &lt;property name="rememberMeDurationSec" <b>defaultValue="600"</b> description="How long is the user remembered when using RememberMe (seconds)" type="integer"/&gt;
  &lt;/securityCheckDefinition&gt;
&lt;/mfp:adapter&gt;
</code></pre>

Build and deploy the UserLogin sample adapter
```
$ cd ./UserLogin
$ mfpdev adapter build
Building adapter...
Successfully built adapter

$ mfpdev adapter deploy
Verifying server configuration...
Deploying adapter to runtime mfp on https://mobilefoundation-71-hb-server.mybluemix.net:443/mfpadmin...
Successfully deployed adapter
```

### 3.4 Add the Cordova plugin for MFP

Make sure you have enabled Android/iOS platform for the Ionic application as mentioned in [Step 2.4.3](#243-enable-android-platform-for-ionic-application) before continuing with the below steps.

  Add Cordova plugin for MFP as shown below.
```
$ cd ../../IonicMobileApp/
$ cordova plugin add cordova-plugin-mfp
Installing "cordova-plugin-mfp" for android
...
```

### 3.5 Register the app to MobileFirst Server
```
$ mfpdev app register
Verifying server configuration...
Registering to server:'https://mobilefoundation-71-hb-server.mybluemix.net:443' runtime:'mfp'
Updated config.xml file located at: .../Ionic-MFP-App/IonicMobileApp/config.xml
Run 'cordova prepare' to propagate changes.
Registered app for platform: android
```

  Propagate changes by running `cordova prepare`
```
$ cordova prepare
```

### 3.6 Create a new provider in Ionic mobile app to assist in handling MFP security challenges

Generate a new provider using Ionic CLI

```
$ ionic generate provider AuthHandler
[OK] Generated a provider named AuthHandler!
```

Update `IonicMobileApp/src/providers/auth-handler.ts` as below:

<pre><code>
/// <b>&lt;reference path="../../../plugins/cordova-plugin-mfp/typings/worklight.d.ts" /&gt;</b>
import { Injectable } from '@angular/core';

@Injectable()
export class AuthHandlerProvider {
  <b>securityCheckName = 'UserLogin';
  userLoginChallengeHandler;
  initialized = false;
  username = null;

  isChallenged = false;
  handleChallengeCallback = null;
  loginSuccessCallback = null;
  loginFailureCallback = null;</b>

  constructor() {
    <b>console.log('--> AuthHandlerProvider constructor() called');</b>
  }

  <b>// Reference: https://mobilefirstplatform.ibmcloud.com/tutorials/en/foundation/8.0/authentication-and-security/credentials-validation/javascript/
  init() {
    if (this.initialized) {
      return;
    }
    this.initialized = true;
    console.log('--> AuthHandler init() called');
    this.userLoginChallengeHandler = WL.Client.createSecurityCheckChallengeHandler(this.securityCheckName);
    // https://stackoverflow.com/questions/20279484/how-to-access-the-correct-this-inside-a-callback
    this.userLoginChallengeHandler.handleChallenge = this.handleChallenge.bind(this);
    this.userLoginChallengeHandler.handleSuccess = this.handleSuccess.bind(this);
    this.userLoginChallengeHandler.handleFailure = this.handleFailure.bind(this);
  }

  setHandleChallengeCallback(onHandleChallenge) {
    console.log('--> AuthHandler setHandleChallengeCallback() called');
    this.handleChallengeCallback = onHandleChallenge;
  }

  setLoginSuccessCallback(onSuccess) {
    console.log('--> AuthHandler setLoginSuccessCallback() called');
    this.loginSuccessCallback = onSuccess;
  }

  setLoginFailureCallback(onFailure) {
    console.log('--> AuthHandler setLoginFailureCallback() called');
    this.loginFailureCallback = onFailure;
  }

  handleChallenge(challenge) {
    console.log('--> AuthHandler handleChallenge called.\n', JSON.stringify(challenge));
    this.isChallenged = true;
    if (challenge.errorMsg !== null && this.loginFailureCallback != null) {
      var statusMsg = 'Remaining attempts = ' + challenge.remainingAttempts + '<br>' + challenge.errorMsg;
      this.loginFailureCallback(statusMsg);
    } else if (this.handleChallengeCallback != null) {
      this.handleChallengeCallback();
    } else {
      console.log('--> AuthHandler: handleChallengeCallback not set!');
    }
  }

  handleSuccess(data) {
    console.log('--> AuthHandler handleSuccess called');
    this.isChallenged = false;
    if (this.loginSuccessCallback != null) {
      this.loginSuccessCallback();
    } else {
      console.log('--> AuthHandler: loginSuccessCallback not set!');
    }
  }

  handleFailure(error) {
    console.log('--> AuthHandler handleFailure called.\n' + JSON.stringify(error));
    this.isChallenged = false;
    if (this.loginFailureCallback != null) {
      this.loginFailureCallback(error.failure);
    } else {
      console.log('--> AuthHandler: loginFailureCallback not set!');
    }
  }

  // Reference: https://mobilefirstplatform.ibmcloud.com/tutorials/en/foundation/8.0/authentication-and-security/user-authentication/javascript/
  checkIsLoggedIn() {
    console.log('--> AuthHandler checkIsLoggedIn called');
    WLAuthorizationManager.obtainAccessToken('UserLogin')
    .then(
      (accessToken) => {
        console.log('--> AuthHandler: obtainAccessToken onSuccess');
      },
      (error) => {
        console.log('--> AuthHandler: obtainAccessToken onFailure: ' + JSON.stringify(error));
      }
    );
  }

  login(username, password) {
    console.log('--> AuthHandler login called. isChallenged = ', this.isChallenged);
    this.username = username;
    if (this.isChallenged) {
      this.userLoginChallengeHandler.submitChallengeAnswer({'username':username, 'password':password});
    } else {
      // https://stackoverflow.com/questions/20279484/how-to-access-the-correct-this-inside-a-callback
      var self = this;
      WLAuthorizationManager.login(this.securityCheckName, {'username':username, 'password':password})
      .then(
        (success) => {
          console.log('--> AuthHandler: login success');
        },
        (failure) => {
          console.log('--> AuthHandler: login failure: ' + JSON.stringify(failure));
          self.loginFailureCallback(failure.errorMsg);
        }
      );
    }
  }

  logout() {
    console.log('--> AuthHandler logout called');
    WLAuthorizationManager.logout(this.securityCheckName)
    .then(
      (success) => {
        console.log('--> AuthHandler: logout success');
      },
      (failure) => {
        console.log('--> AuthHandler: logout failure: ' + JSON.stringify(failure));
      }
    );
  }</b>
}
</code></pre>


### 3.7 Initialize AuthHandler after MobileFirst SDK is loaded

Update `IonicMobileApp/src/app/app.component.ts` as below:

<pre><code>
import { Component<b>, Renderer</b> } from '@angular/core';
import { Platform } from 'ionic-angular';
import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';

import { LoginPage } from '../pages/login/login'
<b>import { AuthHandlerProvider } from '../providers/auth-handler/auth-handler';</b>

@Component({
  templateUrl: 'app.html'
})
export class MyApp {
  rootPage:any = LoginPage;

  constructor(platform: Platform, statusBar: StatusBar, splashScreen: SplashScreen<b>,
    renderer: Renderer, private authHandler: AuthHandlerProvider</b>) {
    <b>console.log('--> MyApp constructor() called');

    renderer.listenGlobal('document', 'mfpjsloaded', () => {
      console.log('--> MyApp mfpjsloaded');
      this.authHandler.init();
    })</b>

    platform.ready().then(() => {
      // Okay, so the platform is ready and our plugins are available.
      // Here you can do any higher level native things you might need.
      <b>console.log('--> MyApp platform.ready() called');</b>
      statusBar.styleDefault();
      splashScreen.hide();
    });
  }

}
</code></pre>

### 3.8 Update Login controller to use MFP based user authentication
Add the code for handling pre-emptive login

Update `IonicMobileApp/src/pages/login/login.ts` as below:

<pre><code>
import { Component } from '@angular/core';
import { NavController, NavParams, AlertController<b>, LoadingController</b> } from 'ionic-angular';
import { FormGroup, FormControl, Validators } from '@angular/forms';

<b>import { AuthHandlerProvider } from '../../providers/auth-handler/auth-handler';
import { HomePage } from '../home/home';</b>

@Component({
  selector: 'page-login',
  templateUrl: 'login.html',
})
export class LoginPage {
  form;
  <b>loader: any;</b>

  constructor(public navCtrl: NavController, public navParams: NavParams,
      public alertCtrl: AlertController<b>, public authHandler:AuthHandlerProvider, public loadingCtrl: LoadingController</b>) {
    console.log('--> LoginPage constructor() called');

    this.form = new FormGroup({
      username: new FormControl("", Validators.required),
      password: new FormControl("", Validators.required)
    });

    <b>this.authHandler.setLoginFailureCallback((error) => {
      this.loader.dismiss();
      if (error !== null) {
        this.showAlert('Login Failure', error);
      } else {
        this.showAlert('Login Failure', 'Failed to login.');
      }
    });
    this.authHandler.setLoginSuccessCallback(() => {
      let view = this.navCtrl.getActive();
      if (!(view.instance instanceof HomePage )) {
        this.navCtrl.setRoot(HomePage);
      }
    });
    this.authHandler.setHandleChallengeCallback(() => {
      this.navCtrl.setRoot(LoginPage);
    });</b>
  }

  processForm() {
    // Reference: https://github.com/driftyco/ionic-preview-app/blob/master/src/pages/inputs/basic/pages.ts
    let username = this.form.value.username;
    let password = this.form.value.password;
    if (username === "" || password === "") {
      this.showAlert('Login Failure', 'Username and password are required');
      return;
    }
    console.log('--> Sign-in with user: ', username);
    <b>this.loader = this.loadingCtrl.create({
      content: 'Signining in. Please wait ...',
      dismissOnPageChange: true
    });
    this.loader.present().then(() => {
      this.authHandler.login(username, password);
    });</b>
  }

  showAlert(alertTitle, alertMessage) {
    ...
  }

  ionViewDidLoad() {
    ...
  }

}
</code></pre>

### 3.9 Test pre-emptive login

Build/Run the Ionic application on Android phone as below:

```
$ ionic cordova build android
$ ionic cordova run android
```

Upon app launch, the login page should get displayed as before in [Step 3.1.4](#314-buildrun-the-ionic-application-on-android-phone). Test by specifying any matching username and password (say `Username: Test` and `Password: Test`). Login should succeed and the sample home page should get shown as in [Step 2.4.4](#244-buildrun-the-ionic-application-on-android-phone).

## Step 4. Fetch data from Cloudant database via MFP Adapter

### 4.1 Create Cloudant database and populate it with sample data

* Log in to [IBM Cloud Dashboard](https://console.bluemix.net/) and create [*Cloudant NoSQL DB*](https://console.bluemix.net/catalog/services/cloudant-nosql-db) service.
* From the welcome page of Cloudant service that you just created, launch the Cloudant Dashboard.
* In the Cloudant dashboard, click on `Databases`.
* Click on `Create Database`. Specify name of database as `myward` as shown below. Click `Create`.

<img src="doc/source/images/CreateCloudantDatabase.png" alt="Create Database in Cloudant NoSQL DB" width="800" border="10" />

Once the database is created, the dashboard will update to show the documents inside `myward` database (which, as expected, will be empty to begin with).

* Click `Create Document`. Under document content, after the auto populated `_id` field, enter grievance details as shown below. Please note that you need to put a comma (,) after the auto populated `_id` field.

<pre><code>
{
  "_id": "50e9c4a69196a00201463ef2f9ffece5"<b>,
  "reportedBy": "shivahr@gmail.com",
  "reportedDateTime": "20171125_152627",
  "picture": {
    "large": "IMG-20171125-WA0012.jpeg",
    "thumbnail": "thumbnail_IMG-20171125-WA0012.jpg"
  },
  "problemDescription": "Car parking on busy market road chocking movement of other vehicles and pedestrians",
  "geoLocation": {
    "type": "Point",
    "coordinates": [
      77.7893168,
      13.0773568
    ]
  },
  "address": "Basaveshwara Temple road (behind Market Road), Hosakote, Bangalore 562114"</b>
}
</code></pre>

Click *Create Document* to create/save the document.

* Repeat the above steps and create documents for the remaining sample grievances: [SampleData/MyWardGrievance2.json](SampleData/MyWardGrievance2.json), [SampleData/MyWardGrievance3.json](SampleData/MyWardGrievance3.json), [SampleData/MyWardGrievance4.json](SampleData/MyWardGrievance4.json), [SampleData/MyWardGrievance5.json](SampleData/MyWardGrievance5.json), [SampleData/MyWardGrievance6.json](SampleData/MyWardGrievance6.json).

The `myward` database should now list the six documents as shown below under `Table` view:

<img src="doc/source/images/CloudantDatabasePopulated.png" alt="Cloudant database populated with sample data" width="800" border="10" />


### 4.2 Create MFP adapter to query Cloudant data

Reference: https://mobilefirstplatform.ibmcloud.com/tutorials/en/foundation/8.0/adapters/cloudant/#java-adapters

#### 4.2.1 Download sample MFP Java adapter for Cloudant
Download MFP Java adapter for Cloudant from https://github.com/MobileFirst-Platform-Developer-Center/CloudantAdapter/tree/release80/Adapters/CloudantJava

```
$ cd ../MobileFoundationAdapters/
$ curl -LOk https://github.com/MobileFirst-Platform-Developer-Center/CloudantAdapter/archive/release80.zip
$ unzip release80.zip
$ mv CloudantAdapter-release80/Adapters/CloudantJava/ ./MyWardData
$ rm -rf CloudantAdapter-release80/ release80.zip
$ ls
MyWardData	UserLogin
```

#### 4.2.2 Point the MFP adapter to your Cloudant service instance

Generate Cloudant API Key
 * In the Cloudant dashboard, under `myward` database, click on `Permissions` and then click on `Generate API Key` as shown in the snapshot below.
 * Make a note of the Key and Password generated.
 * The newly added key would get listed under Cloudant users with default permission of `_reader` only. Select the checkbox under `_writer` next to the new key to give it write permission as well.

  <img src="doc/source/images/CloudantGenerateAPIKey.png" alt="Generate Cloudant API Key" width="800" border="10" />

Specify Cloudant credentials in MFP adapter
 * Open `MobileFoundationAdapters/MyWardData/src/main/adapter-resources/adapter.xml` and update the properties `key` and `password` as per the newly generated API key.
 * For property `account`, specify the Cloudant dashboard URL portion upto (and including) *-bluemix.cloudant.com* as shown in the snapshot above.
 * For property `DBName`, specify value `myward`.


#### 4.2.3 Update adapter methods to return MyWard Grievances data

* Open `MobileFoundationAdapters/MyWardData/pom.xml` and change the value of fields `artifactId` and `name` to `MyWardData`.
* Open `MobileFoundationAdapters/MyWardData/src/main/adapter-resources/adapter.xml` and change the value of `mfp:adapter name`, `displayName` and `description` to `MyWardData`.

* Create new file `MobileFoundationAdapters/MyWardData/src/main/java/com/sample/MyWardGrievance.java` with following contents:

```
package com.sample;

public class MyWardGrievance {
	public String _id, _rev;
	public String reportedBy;
	public String reportedDateTime;
	public static class PictureInfo {
		public String large;
		public String thumbnail;
	}
	public PictureInfo picture;
	public String problemDescription;
	public static class GeoLocation {
		public String type = "Point";
		public Number[] coordinates = new Number[2];
	}
	public GeoLocation geoLocation;
	public String address;

	boolean hasRequiredFields() {
		if (reportedBy != null && !reportedBy.isEmpty() && reportedDateTime != null && !reportedDateTime.isEmpty()
				&& picture != null && picture.large != null && !picture.large.isEmpty() && picture.thumbnail != null
				&& !picture.thumbnail.isEmpty() && problemDescription != null && !problemDescription.isEmpty()
				&& geoLocation != null && address != null && !address.isEmpty()) {
			return true;
		}
		return false;
	}
}
```

* Update `MobileFoundationAdapters/MyWardData/src/main/java/com/sample/CloudantJavaResource.java` as below:
<pre><code>
...
<b>import com.ibm.mfp.adapter.api.OAuthSecurity;</b>

@Path("/")
<b>@OAuthSecurity(scope = "RestrictedData")</b>
public class CloudantJavaResource {
	...

	private Database getDB() throws Exception {
		...
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addEntry(<b>MyWardGrievance myWardGrievance</b>) throws Exception {
		<b>if (myWardGrievance != null && myWardGrievance.hasRequiredFields()) {
			getDB().save(myWardGrievance);</b>
			return Response.ok().build();
		} else {
			return Response.status(400).build();
		}
	}

	@DELETE
	@Path("/{id}")
	public Response deleteEntry(@PathParam("id") String id) throws Exception {
		try {
			<b>MyWardGrievance myWardGrievance = getDB().find(MyWardGrievance.class, id);
			getDB().remove(myWardGrievance);</b>
			return Response.ok().build();
		} catch (NoDocumentException e) {
			return Response.status(404).build();
		}
	}

	@GET
	@Produces("application/json")
	public Response getAllEntries() throws Exception {
		<b>List<MyWardGrievance> entries = getDB().view("_all_docs").includeDocs(true).query(MyWardGrievance.class);</b>
		return Response.ok(entries).build();
	}
}
</code></pre>

  Note: We are protecting all the REST APIs of this adapter with a custom security scope of `RestrictedData` (by using [@OAuthSecurity](https://mobilefirstplatform.ibmcloud.com/tutorials/en/foundation/8.0/authentication-and-security/#protecting-adapter-resources) at class level) which we will map to `UserLogin` security check in [Step 4.2.5](#425-map-mywarddatas-protecting-scope-to-userlogin-security-check).

* Delete `MobileFoundationAdapters/MyWardData/src/main/java/com/sample/User.java`

#### 4.2.4 Build and Deploy the MFP adapter

```
$ cd MyWardData/
$ mfpdev adapter build
...
$ mfpdev adapter deploy
...
```

#### 4.2.5 Map MyWardData's protecting scope to UserLogin security check

Launch MFP Dashboard as below:
  * In the [IBM Cloud dashboard](https://console.bluemix.net/dashboard/), under *Cloud Foundry Services*, click on the *Mobile Foundation* service you created in [Step 3.2](#32-create-mobile-foundation-service-and-configure-mfp-cli). The service overview page that gets shown, will have the MFP dashboard embedded within it. You can also open the MFP dashboard in a separate browser tab by appending `/mfpconsole` to the *url* mentioned in [Step 3.2](#32-create-mobile-foundation-service-and-configure-mfp-cli).
  * Inside the MFP dashboard, in the list on the left, you will see the `MyWard` application, and `MyWardData` and `UserLogin` adapters listed.
  * Click on the `MyWardData` adapter. Click on `Resources` tab. You should see the various REST APIs exposed by `MyWardData` adapter. The `Security` column should show the protecting scope `RestrictedData` against each REST method.
    
Map `RestrictedData` scope to `UserLogin` security check as below:
  * In the MFP dashboard, under `Applications` click on `MyWard` application. Click on `Android` and click on `Security` tab. Click on `New` button under `Scope-Elements Mapping` as shown below.
  * Specify `Scope element` as `RestrictedData`, and under `Custom Security Checks` select `UserLogin` as shown below. Click on `Add`. The new mapping should get created and shown under `Scope-Elements Mapping`.

  <img src="doc/source/images/MapRestrictedDataScopeToUserLoginCheck.png" alt="The REST APIs of MyWardData adapter are protected by RestrictedData security scope" width="800" border="10" />

  * Repeat above steps for `Applications` -> `MyWard` -> `iOS` in case you add Cordova platform for iOS as well.

#### 4.2.6 Test the newly created MFP adapter

Create temporary credentials to test adapter REST API as below:
  * Inside the MFP dashboard, click on `Runtime Settings`. Click on `Confidential Clients`. Then click on `New`.
  * In the form that pops up, specify values for `ID` and `Secret` as shown in snapshot below. For `Allowed Scope` enter \*\* and click on `Add`. Finally click on `Save`.

  <img src="doc/source/images/MFP_CreateCredentialsToTestAdapter.png" alt="MFP - Create Confidential Client to test Adapter REST APIs" width="800" border="10" />

Test adapter REST API as below:
  * Inside the MFP dashboard, click on the `MyWardData` adapter. Click on `Resources` and then click on `View Swagger Docs`. The Swagger UI for adapter REST APIs will get shown in a new window/tab.
  * Inside the Swagger UI, click on `Expand Operations`.
  * To test the `GET /` API, first click on `OFF` toggle button to enable authentication. Select `Default Scope` and click on `Authorize` as shown below. Enter the `ID` and `Secret` created above against `Username` and `Password`. Click `OK`. If authentication is successful, the toggle button will switch to `ON` position.

  <img src="doc/source/images/AuthorizeSwaggerUI.png" alt="Authorize Swagger UI for running MFP Adapter REST APIs" width="800" border="10" />

  * Now click on `Try it out` button to run the `GET /` API. The API response should get shown in the `Response Body` as shown in snapshot below.

  <img src="doc/source/images/SwaggerToolsForTestingMobileFirstAdapter.png" alt="Swagger UI for testing MobileFirst Adapters" width="800" border="10" />

Delete the temporary credentials after testing adapter REST API as below:
  * Inside the MFP dashboard, click on `Runtime Settings`. Click on `Confidential Clients`.
  * Delete the `Client ID` created previously.


### Step 4.3 Update Ionic app to fetch and display data from MFP Adapter

#### 4.3.1 Create a new provider in Ionic app for calling MFP adapter API

Generate a new provider in Ionic app:

```
$ cd ../../IonicMobileApp/
$ ionic generate provider MyWardDataProvider
[OK] Generated a provider named MyWardDataProvider!
```

Update `IonicMobileApp/src/providers/my-ward-data/my-ward-data.ts` as below:

<pre><code>
<b>/// &lt;reference path="../../../plugins/cordova-plugin-mfp/typings/worklight.d.ts" /&gt; </b>
import { Injectable } from '@angular/core';

@Injectable()
export class MyWardDataProvider {
  <b>data: any = null;

  constructor() {
    console.log('--> MyWardDataProvider constructor() called');
  }

  load() {
    console.log('--> MyWardDataProvider loading data from adapter ...');
    return new Promise((resolve, reject)  => {
      if (this.data) {
        // already loaded data
        return resolve(this.data);
      }
      // don't have the data yet
      let dataRequest = new WLResourceRequest("/adapters/MyWardData", WLResourceRequest.GET);
      dataRequest.send().then(
        (response) => {
          console.log('--> MyWardDataProvider loaded data from adapter\n', response);
          this.data = response.responseJSON;
          resolve(this.data);
        }, (failure) => {
          console.log('--> MyWardDataProvider failed to load data\n', JSON.stringify(failure));
          reject(failure);
        })
    });</b>
}
</code></pre>

#### 4.3.2 Modify home page to display the list of problems reported

  - Update `src/pages/home/home.ts` as below:

<pre><code>
import { Component } from '@angular/core';
import { NavController<b>, LoadingController</b> } from 'ionic-angular';
<b>import { MyWardDataProvider } from '../../providers/my-ward-data/my-ward-data';</b>

@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {
  <b>loader: any;
  grievances: any;</b>

  constructor(public navCtrl: NavController<b>, public loadingCtrl: LoadingController,
    public myWardDataProvider: MyWardDataProvider</b>) {
    <b>console.log('--> HomePage constructor() called');</b>
  }

  <b>ionViewDidLoad() {
    console.log('--> HomePage ionViewDidLoad() called');
    this.loadData();
  }

  loadData() {
    this.loader = this.loadingCtrl.create({
      content: 'Loading data. Please wait ...',
    });
    this.loader.present().then(() => {
      this.myWardDataProvider.load().then(data => {
        this.loader.dismiss();
        this.grievances = data;
      });
    });
  }</b>

}
</code></pre>

  - Update `src/pages/home/home.html` as below:

<pre><code>
&lt;ion-header&gt;
  &lt;ion-navbar&gt;
    &lt;ion-title&gt;
      <b>Problems Reported</b>
    &lt;/ion-title&gt;
  &lt;/ion-navbar&gt;
&lt;/ion-header&gt;

&lt;ion-content padding&gt;
  <b>&lt;ion-list&gt;
    &lt;ion-item *ngFor="let grievance of grievances"&gt;
      &lt;ion-thumbnail item-left&gt;
        &lt;img src="{{grievance.picture.thumbnail}}"&gt;
      &lt;/ion-thumbnail&gt;
      &lt;h2 text-wrap&gt;{{grievance.problemDescription}}&lt;/h2&gt;
      &lt;p&gt;@ {{grievance.address}}&lt;/p&gt;
    &lt;/ion-item&gt;
  &lt;/ion-list&gt;</b>
&lt;/ion-content&gt;
</code></pre>

#### 4.3.3 Test updated home page

Build/Run the Ionic application on Android phone as below:

```
$ ionic cordova build android
$ ionic cordova run android
```

After app launch and successful login, the home page should display the sample list of problems created in [Step 4.1](#41-create-cloudant-database-and-populate-it-with-sample-data). As of now, the image thumbnails would be blank.

## Step 5. Use IBM Cloud Object Storage for storing and retrieving images

### 5.1 Create IBM Cloud Object Storage service and API key

#### 5.1.1 Create IBM Cloud Object Storage service and populate it with sample data

* In the [IBM Cloud Dashboard](https://console.bluemix.net/), click on `Catalog` and select [*Object Storage*](https://console.bluemix.net/catalog/infrastructure/cloud-object-storage) service under `Infrastructure` -> `Storage`. Click on `Create` as shown below.

  <img src="doc/source/images/COS_CreateService.png" alt="Create IBM Cloud Object Storage service" width="800" border="10" />

* The *IBM Cloud Object Storage* dashboard will get shown. In the `Buckets and objects` page, click on `Create bucket`. Give a unique name for the bucket. Leave the default selections as-is for *Resiliency* (`Cross Region`), *Location* (`us-geo`) and *Storage class* (`Standard`), and click on `Create` as shown below.

  <img src="doc/source/images/COS_CreateBucket.png" alt="Create a bucket in IBM Cloud Object Storage" width="800" border="10" />

* The *Bucket overview* page for the newly created bucket will get shown. Click on `Add objects`. In `Upload obects` dialog, click on `Add files` and select all the images under [SampleData](SampleData) directory (the six images and their thumbnails). Click `Open`. Click on `Upload` as shown below. Once upload is complete, you should see the images listed under your bucket.

  <img src="doc/source/images/COS_UploadObjects.png" alt="Upload objects to IBM Cloud Object Storage" width="800" border="10" />

#### 5.1.2 Create Service ID and API Key for accessing objects

* Create Service ID
  - In a separate browser tab/window, launch the *IBM Cloud Identity & Access Management* dashboard using URL https://console.bluemix.net/iam/. 
  - In case you have multiple IBM Cloud accounts, then select the target Account, Region, Organization and Space.
  - Under `Identity & Access` (on the left side of the page), select `Service IDs` and click `Create`. Give a name and description, and click `Create`.
  - Make a note of the Service ID as shown below.

  <img src="doc/source/images/IAM_CopyServiceID.png" alt="Copy Service ID from IBM Cloud Identity and Access Management dashboard" width="800" border="10" />

* Add Cloud Object Storage *Writer* role to that service ID
  - Back in *IBM Cloud Object Storage* dashboard, select `Bucket permissions` under `Buckets and objects`.
  - Click on `Service IDs` tab. Under `Select a service ID`, select the service ID created in the above step. Under `Assign a role to this service ID for this bucket`, select `Writer`. Click `Create policy` as shown below. You should get a confirmation dialog saying “Service permission created“.

  <img src="doc/source/images/COS_CreatePolicyForServiceID.png" alt="Add Writer role to the Service ID in IBM Cloud Object Storage" width="800" border="10" />

* Create API Key
  - Back in *IBM Cloud Identity & Access Management* dashboard, under `Service IDs`, click on the service ID created earlier.
Under `Access policies`, you should see the `Writer` role for your bucket. 
  - Click on `API keys` tab and then click on `Create` button. In the `Create API key` dialog, give a name and description for the API key and click on `Create`. You should get a confirmation dialog saying `API key successfully created` as shown below.
  - Click on `Download` and save the API key as shown below. Note: This is the only time you will see the key. You cannot retrieve it later.
  - Finally click on `Close`.

  <img src="doc/source/images/IAM_DownloadAPIKey.png" alt="Create API key and download in IBM Cloud Identity and Access Management" width="800" border="10" />

### 5.2 Add function in MFP Adapter to fetch Authorization token from IBM Cloud Object Storage

Add [ibm-cos-java-sdk](https://github.com/IBM/ibm-cos-sdk-java) dependency to `MobileFoundationAdapters/MyWardData/pom.xml` as below:

<pre><code>
&lt;?xml version="1.0" encoding="UTF-8"?&gt;
&lt;project ...&gt;
  ...
  &lt;dependencies&gt;
    ...
    <b>&lt;dependency&gt;
      &lt;groupId&gt;com.ibm.cos&lt;/groupId&gt;
      &lt;artifactId&gt;ibm-cos-java-sdk&lt;/artifactId&gt;
      &lt;version&gt;1.1.0&lt;/version&gt;
    &lt;/dependency&gt;
    &lt;dependency&gt;
      &lt;groupId&gt;org.apache.httpcomponents&lt;/groupId&gt;
      &lt;artifactId&gt;httpclient&lt;/artifactId&gt;
      &lt;version&gt;4.5.2&lt;/version&gt;
    &lt;/dependency&gt;
    &lt;dependency&gt;
      &lt;groupId&gt;org.apache.httpcomponents&lt;/groupId&gt;
      &lt;artifactId&gt;httpcore&lt;/artifactId&gt;
      &lt;version&gt;4.4.5&lt;/version&gt;
    &lt;/dependency&gt;</b>
  &lt;/dependencies&gt;
  ...
&lt;/project&gt;
</code></pre>

Update `MobileFoundationAdapters/MyWardData/src/main/adapter-resources/adapter.xml` as shown in code snippet below.
  - Specify `defaultValue` for `bucketName` from [Step 5.1.1](#511-create-ibm-cloud-object-storage-service-and-populate-it-with-sample-data)
  - Specify `defaultValue` for `serviceId` and `apiKey` from [Step 5.1.2](#512-create-service-id-and-api-key-for-accessing-objects).
  - While creating the bucket in [Step 5.1.1](#511-create-ibm-cloud-object-storage-service-and-populate-it-with-sample-data), if you selected a different Location/Resiliency, then update the `endpointURL` as per the specification in https://console.bluemix.net/docs/services/cloud-object-storage/basics/endpoints.html#select-regions-and-endpoints.

<pre><code>
&lt;mfp:adapter name="MyWardData" ...&gt;
  ...
  &lt;property name="DBName" displayName="Cloudant DB name" defaultValue="myward"/&gt;

  <b>&lt;property name="endpointURL" displayName="Cloud Object Storage Endpoint Public URL" defaultValue="https://s3-api.us-geo.objectstorage.softlayer.net"/&gt;
  &lt;property name="bucketName" displayName="Cloud Object Storage Bucket Name" defaultValue=""/&gt;
  &lt;property name="serviceId" displayName="Cloud Object Storage Service ID" defaultValue=""  /&gt;
  &lt;property name="apiKey" displayName="Cloud Object Storage API Key" defaultValue=""/&gt;</b>
&lt;/mfp:adapter&gt;
</code></pre>

Add file `MobileFoundationAdapters/MyWardData/src/main/java/com/sample/ObjectStorageAccess.java` with contents as below:

<pre><code>
<b>package com.sample;

public class ObjectStorageAccess {
	public String baseUrl;
	public String authorizationHeader;

	public ObjectStorageAccess(String baseUrl, String authToken) {
		this.baseUrl = baseUrl;
		this.authorizationHeader = "Bearer " + authToken;
	}
}</b>
</code></pre>

Edit `MobileFoundationAdapters/MyWardData/src/main/java/com/sample/CloudantJavaApplication.java` as below:

<pre><code>
package com.sample;
...
<b>import com.amazonaws.SDKGlobalConfiguration;
import com.ibm.oauth.BasicIBMOAuthCredentials;
import com.ibm.oauth.IBMOAuthCredentials;
import com.ibm.oauth.OAuthServiceException;</b>

public class CloudantJavaApplication extends MFPJAXRSApplication{
	...
	@Context
	ConfigurationAPI configurationAPI;

	public Database db = null;

	<b>private IBMOAuthCredentials oAuthCreds = null;
	private String baseUrl = "";</b>

	protected void init() throws Exception {
		...

		<b>String endpointURL = configurationAPI.getPropertyValue("endpointURL");
		String bucketName = configurationAPI.getPropertyValue("bucketName");
		String serviceId = configurationAPI.getPropertyValue("serviceId");
		String apiKey = configurationAPI.getPropertyValue("apiKey");

		if (!endpointURL.isEmpty() && !bucketName.isEmpty() && !serviceId.isEmpty() && !apiKey.isEmpty()) {
			try {
				SDKGlobalConfiguration.IAM_ENDPOINT = "https://iam.bluemix.net/oidc/token";
				oAuthCreds = new BasicIBMOAuthCredentials(apiKey, serviceId);
				// initialize fetching and caching of token
				oAuthCreds.getTokenManager().getToken();
				this.baseUrl = endpointURL + "/" + bucketName + "/";
			} catch (OAuthServiceException e) {
				throw new Exception("Unable to connect to Object Storage, check the configuration.");
			}
		}</b>
	}

	<b>public ObjectStorageAccess getObjectStorageAccess() {
		return new ObjectStorageAccess(this.baseUrl, oAuthCreds.getTokenManager().getToken());
	}</b>
	...
}
</code></pre>

Edit `MobileFoundationAdapters/MyWardData/src/main/java/com/sample/CloudantJavaResource.java` as below:

<pre><code>
...
@Path("/")
public class CloudantJavaResource {

	@Context
	AdaptersAPI adaptersAPI;

	...

	<b>@GET
	@Path("/objectStorage")
	@Produces("application/json")
	public Response getObjectStorageAccess() throws Exception {
		CloudantJavaApplication app = adaptersAPI.getJaxRsApplication(CloudantJavaApplication.class);
		return Response.ok(app.getObjectStorageAccess()).build();
	}</b>
}
</code></pre>

Build and deploy the modified MFP adapter

```
$ cd ../MobileFoundationAdapters/MyWardData/
$ mfpdev adapter build
$ mfpdev adapter deploy
```

Test the newly added API as per instructions in [Step 4.2.6](#426-test-the-newly-created-mfp-adapter). The GET API on `/objectStorage` should return a JSON object containing `baseUrl` and `authorizationHeader` as shown below.

  <img src="doc/source/images/TestMFPAdapter_ObjectStorageAccess.png" alt="Test the newly added API in MFP Adapter for getting Cloud Object Storage Authorization token" width="1024" border="10" />


### 5.3 Modify Ionic App to display images

#### 5.3.1 Use imgcache.js for downloading and caching images

For downloading and caching images in the Ionic App, we will use the [ng-imgcache](https://github.com/fiznool/ng-imgcache) library. *ng-imgcache* uses the popular [imgcache.js](https://github.com/chrisben/imgcache.js) library that is based on [cordova-plugin-file](https://cordova.apache.org/docs/en/latest/reference/cordova-plugin-file/) and [cordova-plugin-file-transfer](https://cordova.apache.org/docs/en/latest/reference/cordova-plugin-file-transfer/) plugins.

```
$ cd ../../IonicMobileApp/
$ npm install ng-imgcache --save
$ ionic cordova plugin add cordova-plugin-file
$ ionic cordova plugin add cordova-plugin-file-transfer
```

Update `IonicMobileApp/src/app/app.module.ts` as below:

<pre><code>
...
import { StatusBar } from '@ionic-native/status-bar';
<b>import { ImgCacheModule } from 'ng-imgcache';</b>

import { MyApp } from './app.component';
...
@NgModule({
  ...
  imports: [
    BrowserModule,
    <b>ImgCacheModule,</b>
    IonicModule.forRoot(MyApp)
  ],
  ...
})
export class AppModule {}
</code></pre>

#### 5.3.2 Add additional function in MyWardDataProvider to call the new MFP adapter function

Update `IonicMobileApp/src/providers/my-ward-data/my-ward-data.ts` as below.

<pre><code>
...
@Injectable()
export class MyWardDataProvider {
  data: any = null;
  <b>objectStorageAccess: any = null;</b>

  constructor() {
    ...
  }

  load() {
    ...
  }

  <b>getObjectStorageAccess() {
    // console.log('--> MyWardDataProvider getting Object Storage AuthToken from adapter ...');
    return new Promise((resolve, reject) => {
      if (this.objectStorageAccess) {
        // already loaded data
        return resolve(this.objectStorageAccess);
      }
      let dataRequest = new WLResourceRequest("/adapters/MyWardData/objectStorage", WLResourceRequest.GET);
      dataRequest.send().then(
        (response) => {
          // console.log('--> MyWardDataProvider got Object Storage AuthToken from adapter ', response);
          this.objectStorageAccess = response.responseJSON;
          resolve(this.objectStorageAccess);
        }, (failure) => {
          console.log('--> MyWardDataProvider failed to get Object Storage AuthToken from adapter\n', JSON.stringify(failure));
          reject(failure);
        })
    });</b>
}
</code></pre>

#### 5.3.3 Update Home page to display images

Update `IonicMobileApp/src/pages/home/home.ts` as below.

<pre><code>
import { Component } from '@angular/core';
import { NavController, LoadingController } from 'ionic-angular';
<b>import { ImgCacheService } from 'ng-imgcache';</b>

import { MyWardDataProvider } from '../../providers/my-ward-data/my-ward-data';

@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {
  loader: any;
  grievances: any;
  <b>objectStorageAccess: any;</b>

  constructor(public navCtrl: NavController, public loadingCtrl: LoadingController,
    public myWardDataProvider: MyWardDataProvider<b>, public imgCache: ImgCacheService</b>) {
    console.log('--> HomePage constructor() called');
  }

  ionViewDidLoad() {
    console.log('--> HomePage ionViewDidLoad() called');
    this.loadData();
  }

  loadData() {
    this.loader = this.loadingCtrl.create({
      content: 'Loading data. Please wait ...',
    });
    this.loader.present().then(() => {
      this.myWardDataProvider.load().then(data => {
        <b>this.myWardDataProvider.getObjectStorageAccess().then(objectStorageAccess => {
          this.objectStorageAccess = objectStorageAccess;
          this.imgCache.init({
            headers: {
              'Authorization': this.objectStorageAccess.authorizationHeader
            }
          }).then( () => {
            console.log('--> HomePage initialized imgCache');
            this.loader.dismiss();
            this.grievances = data;
          });
        });</b>
      });
    });
  }
}
</code></pre>

Update `IonicMobileApp/src/pages/home/home.html` as below:

<pre><code>
&lt;ion-header&gt;
...
&lt;/ion-header&gt;

&lt;ion-content padding&gt;
  &lt;ion-list&gt;
    &lt;ion-item *ngFor="let grievance of grievances"&gt;
      &lt;ion-thumbnail item-left&gt;
        <b>&lt;img img-cache img-cache-src="{{objectStorageAccess.baseUrl}}{{grievance.picture.thumbnail}}"&gt;</b>
      &lt;/ion-thumbnail&gt;
      &lt;h2 text-wrap&gt;{{grievance.problemDescription}}&lt;/h2&gt;
      &lt;p&gt;@ {{grievance.address}}&lt;/p&gt;
    &lt;/ion-item&gt;
  &lt;/ion-list&gt;
&lt;/ion-content&gt;
</code></pre>

#### 5.3.4 Build/Run the Ionic application on Android phone

```
$ ionic cordova build android
$ ionic cordova run android
```

After login, the home page should display the list of problems reported along with image thumbnails as shown below.

  <img src="doc/source/images/MyWardAppHomePage.png" alt="MyWard App - Home Page" width="240" border="10" />

## Step 6. Show problem details page with location marked on Google Maps

Get an API key for using the Google Maps Android API as per instructions in https://developers.google.com/maps/documentation/android-api/signup.

Install Cordova plugin for Google Maps
https://ionicframework.com/docs/native/google-maps/

```
$ ionic cordova plugin add cordova-plugin-googlemaps --variable API_KEY_FOR_ANDROID="<Your_API_Key_for_using_GoogleMaps_Android_API>"
$ npm install --save @ionic-native/google-maps
```

Generate a new page for ProblemDetail

```
$ ionic generate page ProblemDetail
[OK] Generated a page named ProblemDetail!
```

Update `IonicMobileApp/src/pages/home/home.html` as below:

<pre><code>
&lt;ion-header&gt;
...
&lt;/ion-header&gt;

&lt;ion-content padding&gt;
  &lt;ion-list&gt;
    <b>&lt;button ion-item (click)="itemClick(grievance)" *ngFor="let grievance of grievances"&gt;</b>
      &lt;ion-thumbnail item-left&gt;
        &lt;img img-cache img-cache-src="{{objectStorageAccess.baseUrl}}{{grievance.picture.thumbnail}}"&gt;
      &lt;/ion-thumbnail&gt;
      &lt;h2 text-wrap&gt;{{grievance.problemDescription}}&lt;/h2&gt;
      &lt;p&gt;@ {{grievance.address}}&lt;/p&gt;
    <b>&lt;/button&gt;</b>
  &lt;/ion-list&gt;
&lt;/ion-content&gt;
</code></pre>

Update `IonicMobileApp/src/pages/home/home.ts` as below:

<pre><code>
...
<b>import { ProblemDetailPage } from '../problem-detail/problem-detail';</b>
...
export class HomePage {
  ...

  <b>// https://www.joshmorony.com/a-simple-guide-to-navigation-in-ionic-2/
  itemClick(grievance) {
    this.navCtrl.push(ProblemDetailPage, { grievance: grievance, baseUrl: this.objectStorageAccess.baseUrl });
  }</b>
}
</code></pre>

Update `IonicMobileApp/src/pages/problem-detail/problem-detail.ts` as below.

<pre><code>
import { Component } from '@angular/core';
<b>import { NavController, NavParams } from 'ionic-angular';
import { GoogleMaps, GoogleMap, GoogleMapsEvent, GoogleMapOptions, Marker, LatLng } from '@ionic-native/google-maps';</b>

<b>// @IonicPage()</b>
@Component({
  selector: 'page-problem-detail',
  templateUrl: 'problem-detail.html',
})
export class ProblemDetailPage {
  <b>grievance: any;
  baseUrl: any;
  map: GoogleMap;</b>

  constructor(public navCtrl: NavController, public navParams: NavParams) {
    <b>console.log('--> ProblemDetailPage constructor() called');
    this.grievance = navParams.get('grievance');
    this.baseUrl = navParams.get('baseUrl');</b>
  }

  ionViewDidLoad() {
    <b>console.log('--> ProblemDetailPage ionViewDidLoad() called');
    this.loadMap();</b>
  }

  <b>loadMap() {
    let loc = new LatLng(this.grievance.geoLocation.coordinates[1], this.grievance.geoLocation.coordinates[0]);
    let mapOptions: GoogleMapOptions= {
      camera: {
        target: loc,
        zoom: 15,
        tilt: 10
      }
    };
    this.map = GoogleMaps.create('map', mapOptions);
    this.map.one(GoogleMapsEvent.MAP_READY).then(() => {
      this.map.addMarker({
        title: 'Problem Location',
        position: loc
      }).then((marker: Marker) => {
        marker.showInfoWindow();
      }).catch(err => {
        console.log(err);
      });
    });
  }</b>
}
</code></pre>

Delete file `IonicMobileApp/src/pages/problem-detail/problem-detail.module.ts`.

Update `IonicMobileApp/src/pages/problem-detail/problem-detail.html` as below:

<pre><code>
&lt;ion-header&gt;
  &lt;ion-navbar&gt;
    &lt;ion-title&gt;
      <b>MyWard Problem Details</b>
    &lt;/ion-title&gt;
  &lt;/ion-navbar&gt;
&lt;/ion-header&gt;

&lt;ion-content padding&gt;
  <b>&lt;h2 text-wrap&gt;{{grievance.problemDescription}}&lt;/h2&gt;
  &lt;p&gt;Reported on: {{grievance.reportedDateTime}}&lt;/p&gt;
  &lt;img img-cache img-cache-src="{{baseUrl}}{{grievance.picture.large}}"&gt;
  &lt;p text-wrap&gt;@ {{grievance.address}}&lt;/p&gt;
  &lt;div id="map">&lt;/div&gt;</b>
&lt;/ion-content&gt;
</code></pre>

Update `IonicMobileApp/src/pages/problem-detail/problem-detail.scss` as below.

<pre><code>
page-problem-detail {
  <b>#map {
    height: 90%;
    width: 90%;
  }</b>
}
</code></pre>

Update `IonicMobileApp/src/app/app.module.ts` as below.

<pre><code>
...
<b>import { GoogleMaps } from '@ionic-native/google-maps';
import { ProblemDetailPage } from '../pages/problem-detail/problem-detail';</b>
@NgModule({
  declarations: [
    MyApp,
    LoginPage,
    HomePage<b>,
    ProblemDetailPage</b>
  ],
  imports: [
    BrowserModule,
    ImgCacheModule,
    IonicModule.forRoot(MyApp)
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    LoginPage,
    HomePage<b>,
    ProblemDetailPage</b>
  ],
  providers: [
    StatusBar,
    SplashScreen,
    {provide: ErrorHandler, useClass: IonicErrorHandler},
    AuthHandlerProvider,
    MyWardDataProvider<b>,
    GoogleMaps</b>
  ]
})
export class AppModule {}
</code></pre>

Build/Run the Ionic application on Android phone

```
$ ionic cordova build android
$ ionic cordova run android
```

Upon clicking of on any of the problems reported on the home page, a detail page should open up showing bigger image and the location should be marked on Google Maps as shown below.

  <img src="doc/source/images/MyWardAppDetailPage.png" alt="MyWard App - Problem Detail Page" width="240" border="10" />

## Step 7. Capture image and geolocation and upload to server

* Capture photo using Cordova Camera plugin https://ionicframework.com/docs/native/camera/
* Create thumbnail image using Cordova plugin For Image Resize https://ionicframework.com/docs/native/image-resizer/
* Upload image to Object Storage using Cordova File Transfer plugin https://ionicframework.com/docs/native/file-transfer/
* Reverse geocode latitude and longitude into an address using Cordova NativeGeocoder plugin https://ionicframework.com/docs/native/native-geocoder/

```
$ ionic cordova plugin add cordova-plugin-camera
$ npm install --save @ionic-native/camera

$ ionic cordova plugin add info.protonet.imageresizer
$ npm install --save @ionic-native/image-resizer

$ ionic cordova plugin add cordova-plugin-file-transfer
$ npm install --save @ionic-native/file-transfer

$ ionic cordova plugin add cordova-plugin-nativegeocoder
$ npm install --save @ionic-native/native-geocoder
```

Generate a new page for reporting new problem.

```
$ ionic generate page ReportNew
[OK] Generated a page named ReportNew!
```

Update `IonicMobileApp/src/pages/home/home.html` as below.

<pre><code>
&lt;ion-header&gt;
  &lt;ion-navbar&gt;
    &lt;ion-title&gt;
      Problems Reported
    &lt;/ion-title&gt;
    <b>&lt;ion-buttons end&gt;
      &lt;button ion-button icon-only (click)="reportNewProblem()"&gt;
        &lt;ion-icon name="add"&gt;&lt;/ion-icon&gt;
      &lt;/button&gt;
    &lt;/ion-buttons&gt;</b>
  &lt;/ion-navbar&gt;
&lt;/ion-header&gt;

&lt;ion-content padding&gt;
  ...
&lt;/ion-content&gt;
</code></pre>

Update `IonicMobileApp/src/pages/home/home.ts` as below.

<pre><code>
...
<b>import { ReportNewPage } from '../report-new/report-new';</b>
...
export class HomePage {
  ...

  <b>reportNewProblem(){
    this.navCtrl.push(ReportNewPage);
  }</b>
}
</code></pre>

Update `IonicMobileApp/src/app/app.module.ts` as below.

<pre><code>
...
<b>import { Camera } from '@ionic-native/camera';
import { ImageResizer } from '@ionic-native/image-resizer';
import { FileTransfer } from '@ionic-native/file-transfer';
import { NativeGeocoder } from '@ionic-native/native-geocoder';
import { ReportNewPage } from '../pages/report-new/report-new';</b>
@NgModule({
  declarations: [
    MyApp,
    LoginPage,
    HomePage,
    ProblemDetailPage<b>,
    ReportNewPage</b>
  ],
  imports: [
    BrowserModule,
    ImgCacheModule,
    IonicModule.forRoot(MyApp)
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    LoginPage,
    HomePage,
    ProblemDetailPage<b>,
    ReportNewPage</b>
  ],
  providers: [
    StatusBar,
    SplashScreen,
    {provide: ErrorHandler, useClass: IonicErrorHandler},
    AuthHandlerProvider,
    MyWardDataProvider,
    GoogleMaps<b>,
    Camera,
    ImageResizer,
    FileTransfer,
    NativeGeocoder,</b>
  ]
})
export class AppModule {}
</code></pre>

Update `IonicMobileApp/src/providers/my-ward-data/my-ward-data.ts` as below.

<pre><code>
...
<b>import { FileTransfer, FileUploadOptions, FileTransferObject } from '@ionic-native/file-transfer';</b>

@Injectable()
export class MyWardDataProvider {
  ...
  constructor(<b>private transfer: FileTransfer</b>) {
    console.log('--> MyWardDataProvider constructor() called');
  }
  ...
  <b>uploadNewGrievance(grievance) {
    return new Promise( (resolve, reject) => {
      console.log('--> MyWardDataProvider: Uploading following new grievance to server ...\n' + JSON.stringify(grievance));
      let dataRequest = new WLResourceRequest("/adapters/MyWardData", WLResourceRequest.POST);
      dataRequest.setHeader("Content-Type","application/json");
      dataRequest.send(grievance).then(
        (response) => {
          console.log('--> MyWardDataProvider: Upload successful:\n', response);
          resolve(response)
        }, (failure) => {
          console.log('--> MyWardDataProvider: Upload failed:\n', failure);
          reject(failure)
        })
    });
  }

  uploadImage(fileName, filePath) {
    return new Promise( (resolve, reject) => {
      let serverUrl = this.objectStorageAccess.baseUrl + fileName;
      console.log('--> MyWardDataProvider: Uploading image (' + filePath + ') to server (' + serverUrl + ') ...');
      let options: FileUploadOptions = {
        fileKey: 'file',
        fileName: fileName,
        httpMethod: 'PUT',
        headers: {
          'Authorization': this.objectStorageAccess.authorizationHeader,
          'Content-Type': 'image/jpeg'
        }
      }
      let fileTransfer: FileTransferObject = this.transfer.create();
      fileTransfer.upload(filePath, serverUrl, options).then((data) => {
        // success
        console.log('--> MyWardDataProvider: Image upload successful:\n', data);
        resolve(data);
      }, (err) => {
        // error
        console.log('--> MyWardDataProvider: Image upload failed:\n', err);
        reject(err);
      })
    });
  }</b>
}
</code></pre>


Update `IonicMobileApp/src/pages/report-new/report-new.html` as below.

<pre><code>
&lt;ion-header&gt;
  &lt;ion-navbar&gt;
    &lt;ion-title&gt;<b>Report New Problem</b>&lt;/ion-title&gt;
  &lt;/ion-navbar&gt;
&lt;/ion-header&gt;

&lt;ion-content padding&gt;
  <b>&lt;ion-list&gt;
    &lt;ion-item&gt;
      &lt;ion-label fixed&gt;Description&lt;/ion-label&gt;
      &lt;ion-input type="text" [(ngModel)]="description"&gt;&lt;/ion-input&gt;
    &lt;/ion-item&gt;
    &lt;ion-item&gt;
      &lt;ion-label fixed&gt;Address&lt;/ion-label&gt;
      &lt;ion-input type="text" [(ngModel)]="address"&gt;&lt;/ion-input&gt;
    &lt;/ion-item&gt;
  &lt;/ion-list&gt;
  &lt;img [src]="capturedImage" *ngIf="capturedImage" /&gt;
  &lt;ion-grid&gt;
    &lt;ion-row&gt;
      &lt;ion-col col-6&gt;
        &lt;button ion-button full (click)="takePhoto()" &gt;
          &lt;ion-icon name="camera"&gt;&lt;/ion-icon&gt;
          Take Photo
        &lt;/button&gt;
      &lt;/ion-col&gt;
      &lt;ion-col col-6&gt;
        &lt;button ion-button full (click)="captureLocation()"&gt;
          &lt;ion-icon name="locate"&gt;&lt;/ion-icon&gt;
          Get My Location
        &lt;/button&gt;
      &lt;/ion-col&gt;
    &lt;/ion-row&gt;
  &lt;/ion-grid&gt;
  &lt;div id="map"&gt;&lt;/div&gt;
  &lt;button ion-button full (click)="submit()"&gt;
    &lt;ion-icon name="cloud-upload"&gt;&lt;/ion-icon&gt;
    Submit
  &lt;/button&gt;</b>
&lt;/ion-content&gt;
</code></pre>

Update `IonicMobileApp/src/pages/report-new/report-new.ts` as below.

<pre><code>
import { Component<b>, NgZone</b> } from '@angular/core';
import { NavController, NavParams<b>, AlertController, LoadingController, ToastController</b> } from 'ionic-angular';
<b>import { Camera, CameraOptions } from '@ionic-native/camera';
import { GoogleMaps, GoogleMap, GoogleMapsEvent, GoogleMapOptions, Marker, LatLng, MyLocation } from '@ionic-native/google-maps';
import { NativeGeocoder, NativeGeocoderReverseResult } from '@ionic-native/native-geocoder';
import { ImageResizer, ImageResizerOptions } from '@ionic-native/image-resizer';

import { MyWardDataProvider } from '../../providers/my-ward-data/my-ward-data';
import { AuthHandlerProvider } from '../../providers/auth-handler/auth-handler';

// @IonicPage()</b>
@Component({
  selector: 'page-report-new',
  templateUrl: 'report-new.html',
})
export class ReportNewPage {
  <b>capturedImage: string = null;
  mapReady: boolean = false;
  map: GoogleMap;
  description: string = '';
  address: string = '';
  location: LatLng = null;
  loader: any;</b>

  constructor(public navCtrl: NavController, public navParams: NavParams<b>, public zone: NgZone,
    private camera: Camera, private alertCtrl: AlertController, private imageResizer: ImageResizer,
    private loadingCtrl: LoadingController, private toastCtrl: ToastController, private nativeGeocoder: NativeGeocoder,
    private myWardDataProvider: MyWardDataProvider, private authHandler:AuthHandlerProvider</b>) {
    console.log(<b>'--> ReportNewPage constructor() called'</b>);
  }

  ionViewDidLoad() {
    <b>console.log('--> ReportNewPage ionViewDidLoad() called');
    this.createMap();</b>
  }

  <b>// https://ionicframework.com/docs/native/camera/
  takePhoto() {
    const options : CameraOptions = {
      quality: 90, // picture quality
      destinationType: this.camera.DestinationType.FILE_URI,
      encodingType: this.camera.EncodingType.JPEG,
      correctOrientation: true,
      saveToPhotoAlbum: true
    }
    this.camera.getPicture(options) .then((imageData) => {
        // this.capturedImage = "data:image/jpeg;base64," + imageData;
        this.capturedImage = imageData;
      }, (err) => {
        console.log(err);
      }
    );
  }

  createMap() {
    // TODO need to store/retrieve prevLoc in app preferences/local storage
    let prevLoc = new LatLng(13.0768342, 77.7886087);
    let mapOptions: GoogleMapOptions = {
      camera: {
        target: prevLoc,
        zoom: 15,
        tilt: 10
      }
    };
    this.map = GoogleMaps.create('map', mapOptions);
    this.map.one(GoogleMapsEvent.MAP_READY).then(() => {
      console.log('--> ReportNewPage: Map is Ready To Use');
      this.mapReady = true;
      // https://stackoverflow.com/questions/4537164/google-maps-v3-set-single-marker-point-on-map-click
      this.map.on(GoogleMapsEvent.MAP_CLICK).subscribe( event => {
        this.location = event[0];
        console.log('--> ReportNewPage: User clicked location = ' + event[0]);
        this.map.clear();
        this.map.addMarker({
          title: 'Selected location',
          position: event[0]
        }).then((marker: Marker) => {
          this.autoFillAddress();
          marker.showInfoWindow();
        });
      });
    });
  }

  captureLocation() {
    if (!this.mapReady) {
      this.showAlert('Map is not yet ready', 'Map is not ready yet. Please try again.');
      return;
    }
    this.map.clear();

    // Get the location of you
    this.map.getMyLocation().then((location: MyLocation) => {
      this.location = location.latLng;
      console.log('--> ReportNewPage: Device Location = ' + JSON.stringify(location, null, 2));
      // Move the map camera to the location with animation
      this.map.animateCamera({
        target: location.latLng,
        zoom: 17,
        tilt: 30
      }).then(() => {
        // add a marker
        this.map.addMarker({
          title: 'Your device location',
          snippet: 'Accurate to ' + location.accuracy + ' meters!',
          position: location.latLng,
          animation: 'BOUNCE'
        }).then((marker: Marker) => {
          this.autoFillAddress();
          marker.showInfoWindow();
        });
      })
    }).catch(err => {
      this.showAlert('Try again', err.error_message);
      console.log(err);
    });
  }

  autoFillAddress() {
    let lat = this.location.lat;
    let lng = this.location.lng;
    this.nativeGeocoder.reverseGeocode(lat , lng).then((result: NativeGeocoderReverseResult) => {
      console.log('--> ReportNewPage: Result of reverseGeocode(' + lat + ', ' + lng + ') = ' + JSON.stringify(result));
      let address = result[0];
      let str = '';
      if (address.subLocality) {
        str += address.subLocality + ", ";
      }
      if (address.locality) {
        str += address.locality +  ", ";
      }
      if (address.subAdministrativeArea) {
        str += address.subAdministrativeArea + ", ";
      }
      if (address.administrativeArea) {
        str += address.administrativeArea + ", ";
      }
      if (address.countryName) {
        str += address.countryName + ".";
      }
      // https://blog.thoughtram.io/angular/2016/02/01/zones-in-angular-2.html
      this.zone.run(() => {
        this.address = str;
      });
      console.log('--> ReportNewPage: Reverse geocoded address = ' + str);
    }) .catch((error: any) => {
      console.log(error)
    });
  }

  showAlert(alertTitle, alertMessage, enableBackdropDismiss: boolean = true, okHandler?) {
    // Disable the map - https://stackoverflow.com/questions/45500031/ionic-3-unable-to-click-on-alert-dialog-shown-above-google-maps
    this.map.setClickable(false);
    let prompt = this.alertCtrl.create({
      title: alertTitle,
      message: alertMessage,
      buttons: [{
        text: 'Ok',
        handler: () => {
          // Enable the map again - https://stackoverflow.com/questions/45500031/ionic-3-unable-to-click-on-alert-dialog-shown-above-google-maps
          this.map.setClickable(true);
          if (okHandler) {
            okHandler();
          }
        }
      }],
      enableBackdropDismiss: enableBackdropDismiss
    });
    prompt.present();
  }

  showToast(message: string) {
    let toast = this.toastCtrl.create({
      message: message,
      duration: 2000,
      position: 'bottom'
    });
    toast.present(toast);
  }

  submit() {
    if (this.description === "") {
      this.showAlert('Missing Description', 'Please add a description for the problem you are reporting.');
      return;
    }
    if (this.address === "") {
      this.showAlert('Missing Address', 'Please specify the address of problem location.');
      return;
    }
    if (this.capturedImage === null) {
      this.showAlert('Missing Photo', 'Please take a photo of the problem location.');
      return;
    }
    if (this.location === null) {
      this.showAlert('Missing Geo Location', 'Please mark the location of problem on Maps.');
      return;
    }

    let username = this.authHandler.username;
    let timestamp = this.getDateTime();
    let imageFilename = timestamp + '_' + username + '.jpeg';
    let thumbnailImageFilename = 'thumbnail_' + imageFilename;
    let grievance = {
      "reportedBy": username,
      "reportedDateTime": timestamp,
      "picture": {
        "large": imageFilename,
        "thumbnail": thumbnailImageFilename
      },
      "problemDescription": this.description,
      "geoLocation": {
        "type": "Point",
        "coordinates": [
          this.location.lng,
          this.location.lat
        ]
      },
      "address": this.address
    }

    this.loader = this.loadingCtrl.create({
      content: 'Uploading data to server. Please wait ...',
      dismissOnPageChange: true
    });
    this.loader.present().then(() => {
      this.myWardDataProvider.uploadNewGrievance(grievance).then(
        (response) => {
          this.loader.dismiss();
          this.showToast('Data Uploaded Successfully');
          this.loader = this.loadingCtrl.create({
            content: 'Uploading image to server. Please wait ...',
            dismissOnPageChange: true
          });
          this.loader.present().then(() => {
            this.myWardDataProvider.uploadImage(imageFilename, this.capturedImage).then(
              (response) => {
                this.imageResizer.resize(this.getImageResizerOptions()).then(
                  (filePath: string) => {
                    this.myWardDataProvider.uploadImage(thumbnailImageFilename, filePath).then(
                      (response) => {
                        this.loader.dismiss();
                        this.showToast('Image Uploaded Successfully');
                        this.showAlert('Upload Successful', 'Successfully uploaded problem report to server', false, () => {
                          this.myWardDataProvider.data.push(grievance);
                          this.navCtrl.pop();
                        })
                      }, (failure) => {
                        this.loader.dismiss();
                        this.showAlert('Thumbnail Upload Failed', 'Encountered following error while uploading thumbnail image to server:\n' + failure.errorMsg);
                    });
                  }).catch(e => {
                    console.log(e)
                    this.showAlert('Error Creating Thumbnail', 'Encountered following error while creating thumbnail:\n' + JSON.stringify(e));
                  });
              }, (failure) => {
                this.loader.dismiss();
                this.showAlert('Image Upload Failed', 'Encountered following error while uploading image to server:\n' + failure.errorMsg);
              });
          });
        }, (failure) => {
          this.loader.dismiss();
          this.showAlert('Data Upload Failed', 'Encountered following error while uploading data to server:\n' + failure.errorMsg);
        });
    });
  }

  getImageResizerOptions() {
    let options = {
      uri: this.capturedImage,
      quality: 90,
      width: 400,
      height: 400
    } as ImageResizerOptions;
    return options;
  }

  getDateTime() {
    // https://stackoverflow.com/questions/10211145/getting-current-date-and-time-in-javascript
    let currentdate = new Date();
    let fullYear = currentdate.getFullYear();
    let month = (((currentdate.getMonth()+1) &lt; 10)? "0" : "") + (currentdate.getMonth()+1);
    let date = ((currentdate.getDate() &lt; 10)? "0" : "") + currentdate.getDate();
    let hours = ((currentdate.getHours() &lt; 10)? "0" : "") + currentdate.getHours();
    let minutes = ((currentdate.getMinutes() &lt; 10)? "0" : "") + currentdate.getMinutes();
    let seconds = ((currentdate.getSeconds() &lt; 10)? "0" : "") + currentdate.getSeconds();
    let datetime = fullYear + month + date + "_" + hours + minutes + seconds;
    return datetime;
  } </b>

}
</code></pre>

Update `IonicMobileApp/src/pages/report-new/report-new.scss` as below.

<pre><code>
page-report-new {
  <b>#map {
    height: 90%;
    width: 90%;
  }</b>
}
</code></pre>

Delete file `IonicMobileApp/src/pages/report-new/report-new.module.ts`.

Build/Run the Ionic application on Android phone

```
$ ionic cordova build android
$ ionic cordova run android
```

Upon clicking the `+` button on the home page, the `Report New Problem` page should show up, allowing the user to specify problem description and address as shown below. User should be able to take a photo of the problem and specify the location of problem either by grabbing device's geo-location or by marking the location on Maps.

  <img src="doc/source/images/MyWardAppReportNewPage.png" alt="MyWard App - Report New Problem Page" width="240" border="10" />

Add refresh button in Home page:

Update `IonicMobileApp/src/pages/home/home.html` as below.

<pre><code>
&lt;ion-header&gt;
  &lt;ion-navbar&gt;
    <b>&lt;ion-buttons start&gt;
      &lt;button ion-button icon-only (click)="refresh()"&gt;
        &lt;ion-icon name="refresh"&gt;&lt;/ion-icon&gt;
      &lt;/button&gt;
    &lt;/ion-buttons&gt;</b>
    &lt;ion-title&gt;
      Problems Reported
    &lt;/ion-title&gt;
    &lt;ion-buttons end&gt;
      &lt;button ion-button icon-only (click)="reportNewProblem()"&gt;
        &lt;ion-icon name="add"&gt;&lt;/ion-icon&gt;
      &lt;/button&gt;
    &lt;/ion-buttons&gt;
  &lt;/ion-navbar&gt;
&lt;/ion-header&gt;

&lt;ion-content padding&gt;
  ...
&lt;/ion-content&gt;
</code></pre>

Update `IonicMobileApp/src/pages/home/home.ts` as below.

<pre><code>
...
export class HomePage {
  ...
  <b>refresh() {
    this.myWardDataProvider.data = null;
    this.loadData();
  }</b>
}
</code></pre>

Handle login timeout in Report New Problem page and Home page

Update `IonicMobileApp/src/pages/login/login.ts` as below:
<pre><code>
...
export class LoginPage {
  form;
  loader: any;
  <b>isPushed = null;
  isUsernameDisabled: boolean = false;
  fixedUsername = null;</b>

  constructor(public navCtrl: NavController, public navParams: NavParams,
    public alertCtrl: AlertController, public authHandler:AuthHandlerProvider, public loadingCtrl: LoadingController) {
    console.log('--> LoginPage constructor() called');

    <b>this.isPushed = navParams.get('isPushed');
    this.fixedUsername = navParams.get('fixedUsername');
    if (this.fixedUsername != null) {
      this.isUsernameDisabled = true;
    }</b>

    this.form = new FormGroup({
      <b>username: new FormControl({value: this.fixedUsername, disabled: this.isUsernameDisabled}, Validators.required),</b>
      password: new FormControl("", Validators.required)
    });

    this.authHandler.setLoginFailureCallback((error) => {
      this.loader.dismiss();
      if (error !== null) {
        this.showAlert('Login Failure', error);
      } else {
        this.showAlert('Login Failure', 'Failed to login.');
      }
    });
    <b>if (this.isPushed == null) {</b>
      this.authHandler.setLoginSuccessCallback(() => {
        let view = this.navCtrl.getActive();
        if (!(view.instance instanceof HomePage )) {
          this.navCtrl.setRoot(HomePage);
        }
      });
      this.authHandler.setHandleChallengeCallback(() => {
        this.navCtrl.setRoot(LoginPage);
      });
    <b>}</b>
  }

  processForm() {
    // Reference: https://github.com/driftyco/ionic-preview-app/blob/master/src/pages/inputs/basic/pages.ts
    <b>let username = this.fixedUsername != null ? this.fixedUsername : this.form.value.username;</b>
    let password = this.form.value.password;
    ...
  }

  showAlert(alertTitle, alertMessage) {
    ...
  }
  ...
}
</code></pre>

Update `IonicMobileApp/src/pages/report-new/report-new.ts` as below:
<pre><code>
...
<b>import { LoginPage } from '../login/login';</b>
...
export class ReportNewPage {
  ...
  ionViewDidLoad() {
    console.log('--> ReportNewPage ionViewDidLoad() called');
    this.createMap();
    <b>this.initAuthChallengeHandler();</b>
  }
  ...
  <b>initAuthChallengeHandler() {
    this.authHandler.setHandleChallengeCallback(() => {
      this.navCtrl.push(LoginPage, { isPushed: true, fixedUsername: this.authHandler.username });
    });
    this.authHandler.setLoginSuccessCallback(() => {
      let view = this.navCtrl.getActive();
      if (view.instance instanceof LoginPage) {
        this.navCtrl.pop().then(() =>{
          this.loader = this.loadingCtrl.create({
            content: 'Uploading data to server. Please wait ...'
          });
          this.loader.present();
        });
      }
    });
  }</b>
}
</code></pre>

Update `IonicMobileApp/src/pages/home/home.ts` as below:
<pre><code>
...
<b>import { AuthHandlerProvider } from '../../providers/auth-handler/auth-handler';
import { LoginPage } from '../login/login';</b>
...
export class HomePage {
  ...
  constructor(public navCtrl: NavController, public loadingCtrl: LoadingController,
    public myWardDataProvider: MyWardDataProvider, public imgCache: ImgCacheService<b>,
    private authHandler:AuthHandlerProvider</b>) {
    console.log('--> HomePage constructor() called');
  }

  ...

  <b>ionViewWillEnter() {
    console.log('--> HomePage ionViewWillEnter() called');
    this.initAuthChallengeHandler();
  }

  initAuthChallengeHandler() {
    this.authHandler.setHandleChallengeCallback(() => {
      this.loader.dismiss();
      this.navCtrl.push(LoginPage, { isPushed: true });
    });
    this.authHandler.setLoginSuccessCallback(() => {
      let view = this.navCtrl.getActive();
      if (view.instance instanceof LoginPage) {
        this.navCtrl.pop().then(() =>{
          this.loader = this.loadingCtrl.create({
            content: 'Loading data. Please wait ...'
          });
          this.loader.present();
        });
      }
    });
  }</b>
}
</code></pre>
