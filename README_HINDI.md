# शाही सिंगार – Kotlin Android Game Source

यह प्रोजेक्ट दिए गए `Royal Treasure ASMR` Kotlin/Jetpack Compose ZIP को आधार बनाकर `शाही सिंगार` नाम और नए Android package `com.shahisingaar.game` के साथ तैयार किया गया है।

## तकनीक
- Kotlin 2.0.21
- Jetpack Compose
- Android Gradle Plugin 8.7.3 / Gradle 8.9
- JDK 17, compileSdk 35, minSdk 24

## अभी क्या काम करता है
- दुल्हन की बेस इमेज और रेफरेंस इमेज
- गहनों को नीचे की ट्रे से drag करके सही जगह पर drop करना
- सही जगह पर snap, गलत drop feedback
- Hint, reference preview, reset, pause और sound toggle
- एक sample playable puzzle और साथ में दिए गए PNG assets

## अभी क्या पूरा नहीं है
- यह 300 अलग-अलग तैयार स्तरों वाला गेम नहीं है। अभी source में एक sample puzzle configuration है।
- Level select / 300 level content, विज्ञापन, इन-ऐप खरीदारी और cloud save अभी तैयार/कनेक्ट नहीं हैं।
- यह source ZIP है, तैयार या device-tested APK नहीं।
- APK बनाने और फोन पर जाँचने के लिए Android Studio या GitHub Actions build की जरूरत होगी।

## Android Studio में चलाएँ
1. ZIP extract करें।
2. Android Studio में `Shahi-Singaar-Kotlin` folder खोलें।
3. JDK 17 चुनें और Gradle sync पूरा होने दें।
4. Android फोन/एमुलेटर जोड़कर Run दबाएँ।

## GitHub Actions से Debug APK
1. पूरा folder GitHub repository में upload/push करें।
2. GitHub में Actions tab खोलें।
3. `Build Android APK` workflow चलाएँ (या `main` branch पर push करें)।
4. सफल run के बाद `Shahi-Singaar-APK` artifact डाउनलोड करें।

नोट: workflow केवल debug APK बनाता है। Play Store पर publish करने के लिए release signing, testing, privacy policy और Play Console की अलग प्रक्रिया होगी।
