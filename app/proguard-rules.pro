# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /home/parallels/Android/Sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-keep class butterknife.*
-keepclasseswithmembernames class * { @butterknife.* <methods>; }
-keepclasseswithmembernames class * { @butterknife.* <fields>; }

-dontwarn dagger.internal.codegen.**
-keepclassmembers,allowobfuscation class * {
    @javax.inject.* *;
    @dagger.* *;
    <init>();
}

-keep class dagger.* { *; }
-keep class javax.inject.* { *; }
-keep class * extends dagger.internal.Binding
-keep class * extends dagger.internal.ModuleAdapter
-keep class * extends dagger.internal.StaticInjection


-keep class com.yandex.metrica.impl.* { *; }

-dontwarn com.yandex.metrica.impl.*

-keep class com.yandex.metrica.* { *; }

-dontwarn com.yandex.metrica.*

-keep class uk.co.chrisjenx.calligraphy.* { *; }
-keep class uk.co.chrisjenx.calligraphy.*$* { *; }

-keep class com.google.gson.** { *; }
-keep public class com.google.gson.** {public private protected *;}
-keep class com.google.inject.** { *; }
-keep class org.apache.http.** { *; }
-keep class org.apache.james.mime4j.** { *; }
-keep class javax.inject.** { *; }
-keep class javax.xml.stream.** { *; }
-keep class retrofit.** { *; }
-keep class com.google.appengine.** { *; }
-keepattributes *Annotation*
-keepattributes Signature
-dontwarn com.squareup.okhttp.*
-dontwarn rx.**
-dontwarn javax.xml.stream.**
-dontwarn com.google.appengine.**
-dontwarn java.nio.file.**

-dontwarn sun.misc.**

-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
   long producerIndex;
   long consumerIndex;
}

-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}

-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

-dontnote rx.internal.util.PlatformDependent

-keepnames public class * extends io.realm.RealmObject
-keep class io.realm.annotations.RealmModule
-keep @io.realm.annotations.RealmModule class *
-keep class io.realm.internal.Keep
-keep @io.realm.internal.Keep class *
-dontwarn io.realm.**

-keep class com.tozny.crypto.android.AesCbcWithIntegrity$PrngFixes$* { *; }

-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}
-keep class dagger.*
-keep class com.vungle.** { *; }
-keep class com.fyber.** { *; }
-keep class javax.inject.*
-adaptclassstrings
-keep class javax.** { *; }
-dontwarn javax.**
-dontwarn com.fyber.**
-dontwarn com.vungle.**

-keep class com.google.android.gms.** { *; }
-dontwarn com.google.android.gms.**

-keep public class com.google.gson
-keep class Gson**
-keepclassmembers class Gson** { *; }
-keepattributes Signature, *Annotation*
-keep class com.nativex.** { *; }

-keep class com.appnext.** { *; }
-dontwarn com.appnext.**

-keep class com.google.android.gms.** { *; }
-dontwarn com.google.android.gms.**

-keep class com.chartboost.** { *; }



-keep class butterknife.** { *; }

-dontwarn butterknife.internal.**

-keep class **$$ViewBinder { *; }



-keep class sun.misc.Unsafe { *; }

-keep class com.google.gson.stream.** { *; }




-keepclasseswithmembernames class * {

    @butterknife.* <fields>;

}

-keepclasseswithmembernames class * {

    @butterknife.* <methods>;

}


-keep public class org.jsoup.** {

    public *;

}


-dontwarn okio.**

-dontnote retrofit2.Platform

-dontnote retrofit2.Platform$IOS$MainThreadExecutor

-dontwarn retrofit2.Platform$Java8

-keepattributes Signature

-keepattributes Exceptions

-keepattributes *Annotation

-keep class retrofit2.** { *; }

-keep class sun.misc.Unsafe { *; }



-dontwarn sun.misc.**

-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {

   long producerIndex;

   long consumerIndex;

}

-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {

    rx.internal.util.atomic.LinkedQueueNode producerNode;

}

-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {

    rx.internal.util.atomic.LinkedQueueNode consumerNode;

}


-dontwarn java.lang.invoke.*



-keepclassmembers class * {

    @android.webkit.JavascriptInterface <methods>;

}



-keep class dagger.*

-keep class com.vungle.** { *; }

-keep class com.fyber.** { *; }

-keep class javax.inject.*

-adaptclassstrings

-keep class javax.** { *; }

-dontwarn javax.**

-dontwarn com.fyber.**

-dontwarn com.vungle.**


-keep class com.google.android.gms.** { *; }

-dontwarn com.google.android.gms.**


-keep public class com.google.gson

-keep class Gson**

-keepclassmembers class Gson** { *; }

-keepattributes Signature, *Annotation*

-keep class com.nativex.** { *; }



-keep class com.appnext.** { *; }

-dontwarn com.appnext.**


-keep class com.google.android.gms.** { *; }

-dontwarn com.google.android.gms.**


-dontwarn net.adxmi.android.**

-keep class net.adxmi.android.** {*;}

-dontwarn javax.annotation.**

-keep class mo.offers.lib.** { *;}
-dontwarn mo.offers.lib.**

-keep class **.R
-keep class **.R$* {
    <fields>;
}

-keep class com.google.android.gms.ads.identifier.** { *; }

