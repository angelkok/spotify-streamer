# spotify-streamer
Android Spotify Streamer

##### Features
- Search for an artist and display results
- Display top tracks for a selected artis
- Play the selected track (not yet implemented)

Dependencies
---
##### Picasso
```
Add compile `com.squareup.picasso:picasso:2.5.2` to dependencies block.
```

##### Spotify Wrapper
Here, we’ll compile a new aar (Android archive library) from the most recent git clone of the [Spotify Web API repository on GitHub!](https://www.google.com/url?q=https%3A%2F%2Fgithub.com%2Fkaaes%2Fspotify-web-api-android&sa=D&sntz=1&usg=AFQjCNGRQJF5ocb1Q8v6n9hW99OqXDGRjA). See the [README!](https://www.google.com/url?q=https%3A%2F%2Fgithub.com%2Fkaaes%2Fspotify-web-api-android%2Fblob%2Fmaster%2FREADME.md&sa=D&sntz=1&usg=AFQjCNFQ02qADtjqOhRu7DLu07ASM6E6yg) for how to compile the source. After you’ve followed the README, your app/build.gradle file should contain the following:
```
       // Add this block
      repositories {
         flatDir {
              mavenCentral()
              dirs 'libs'
         }
      }

      dependencies {
          compile fileTree(dir: 'libs', include: ['*.jar']) // this line is already created in Android Studio
          compile 'com.android.support:appcompat-v7:22.2.0' // you may need this line when targeting earlier Android APIs
          // Add these lines
         compile(name:'spotify-web-api-android-0.1.0', ext:'aar')
          compile 'com.squareup.picasso:picasso:2.5.2'
         compile 'com.squareup.retrofit:retrofit:1.9.0'
         compile 'com.squareup.okhttp:okhttp:2.2.0'
      }
```

