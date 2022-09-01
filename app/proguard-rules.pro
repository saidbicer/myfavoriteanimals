# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\AndroidSDK/tools/proguard/proguard-android.txt
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

#--------------------------------1.LOCAL-------------------------------
#Entity and Models
-keep class com.said.myfavoriteanimals.data.db.entity.** { *; }
-keep class com.said.myfavoriteanimals.data.model.** { *; }


#--------------------------------2.THIRD-PARTY-------------------------------
#databinding
-keep class android.databinding.** { *; }
-dontwarn android.databinding.**

#annotation
-keep class android.support.annotation.** { *; }
-keep interface android.support.annotation.** { *; }

#retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

#gson
-keepattributes Signature
-keepattributes *Annotation*
-keep class com.google.gson.stream.** { *; }
-keep class com.sunloto.shandong.bean.** { *; }

#glide
-keep public class * implements com.bumptech.glide.module.AppGlideModule
-keep public class * implements com.bumptech.glide.module.LibraryGlideModule
-keep class com.bumptech.glide.** { *; }
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
    **[] $VALUES;
    public *;
}

#glide-transformations
-keep class jp.wasabeef.glide.transformations.** {*;}
-dontwarn jp.wasabeef.glide.transformations.**

#okhttp
-keepattributes Signature
-keepattributes *Annotation*
-keep class com.squareup.okhttp.** { *; }
-keep interface com.squareup.okhttp.** { *; }
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn com.squareup.okhttp.**
-dontwarn okhttp3.**
-dontwarn okio.**

#RxJava RxAndroid
-dontwarn rx.*
-dontwarn sun.misc.**

-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
   long producerIndex;
   long consumerIndex;
}

#RxLifecycle
-keep class com.trello.rxlifecycle2.** { *; }
-keep interface com.trello.rxlifecycle2.** { *; }
-dontwarn com.trello.rxlifecycle2.**


#---------------------------------6.Other custom areas-------------------------------
#native - methods are not obfuscated
-keepclasseswithmembernames class * {
    native <methods>;
}
#Parcelable not to be confused
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
#Serializable not to be confused
-keepnames class * implements java.io.Serializable
#Serializable Not obfuscated and enum classes are not obfuscated
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    !private <fields>;
    !private <methods>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
#Keep the enumeration enum class from being confused. If the confusion reports an error, it is recommended to use the above -keepclassmembers class * implements java.io.Serializable directly.
-keepclassmembers enum * {
  public static **[] values();
  public static ** valueOf(java.lang.String);
}
-keepclassmembers class * {
    public void *ButtonClicked(android.view.View);
}
#Do not obfuscate resource classes
-keepclassmembers class **.R$* {
    public static <fields>;
}
#Keep all method names in the class
-keepclassmembers class * {
    public <methods>;
    private <methods>;
}

#----------------------------------------------------------------------------

#------------------------------------------------- ---------------------------------------

#---------------------------------Basic command area------------- --------------------
# suppress warnings
-ignorewarnings
#Specify the compression level of the code
-optimizationpasses 5
#package name is not mixed case
-dontusemixedcaseclassnames
#Do not ignore non-public library classes
-dontskipnonpubliclibraryclasses
#Specify not to ignore class members of non-public libraries
-dontskipnonpubliclibraryclassmembers
 #optimize Do not optimize input class files
-dontoptimize
 # pre-check
-dontpreverify
 #Whether to record log when obfuscated
-verbose
 # The algorithm used for obfuscation
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

#obfuscate the package path
-repackageclasses ''
-flattenpackagehierarchy ''

#protect annotations
-keepattributes *Annotation*

#Avoid confusing generics If you are confused and report an error, it is recommended to turn it off
-keepattributes Signature

#Keep SourceFile and LineNumber properties
-keepattributes SourceFile,LineNumberTable

# ignore warnings
#-ignorewarning
#----------Record the generated log data, and output it in the root directory of the project during gradle build---------
#apk package The internal structure of all classes in the
-dump class_files.txt
# unobfuscated classes and members
-printseeds seeds.txt
#List the code removed from the apk
-printusage unused.txt
#Mapping before and after obfuscation
-printmapping mapping.txt
#------------------------------------------------- ---------------------------------------

#---------------------------------Default reserved area---------------------------------
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep class android.support.** {*;}

-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
-keep class **.R$* {
 *;
}
-keepclassmembers class * {
    void *(**On*Event);
}
#----------------------------------------------------------------------------

#---------------------------------webview------------------------------------
-keepclassmembers class fqcn.of.javascript.interface.for.Webview {
   public *;
}
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, jav.lang.String);
}
#----------------------------------------------------------------------------
#----------------------------------------------------------------------------