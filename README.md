# Java Send Mailer

Java Tool for Sending EMail with CID attachment.


## Prerequires

1. Git 2.6+
2. Maven 3.3+
3. Java 8+


## How to Play

Clone

```
git clone https://github.com/humbertodias/java-send-mailer.git
```

Inside the folder

```
cd java-send-mailer
```

**Package**

```
mvn clean package
```

**Send**

```
java -jar target/send-mail-0.0.1-SNAPSHOT.jar \
-from from@gmail.com \
-to mail1@gmail.com,mail2@yahoo.com.br \
-replyto noreply@gmail.com \
-subject SUBJECT \
-smtp.host smtp.gmail.com \
-smtp.port 587  \
-html "<html><body><img src='cid:java-logo.png'/></body></html>" \
-attachs doc/java-logo.png \
-user USERNAME \
-password

```

**Arguments**

Parameter | Description
------------- | -------------
-from | From Address
-to | To Address
-replyto | Reply To Address
-subject |  Subject
-smtp.host | SMTP Host
-smtp.port | SMTP Port
-html | HTML Message
-attachs | Attachment
-user | User
-password | Password

## Output

```
mvn clean package
[INFO] Scanning for projects...
[INFO]                                                                         
[INFO] ------------------------------------------------------------------------
[INFO] Building java-send-mail 0.0.1-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO] Copying 0 resource
[INFO] 
[INFO] --- maven-compiler-plugin:3.1:compile (default-compile) @ send-mail ---
[INFO] 
[INFO] --- maven-jar-plugin:2.4:jar (default-jar) @ send-mail ---
[INFO] Building jar: /Users/humbertodias/git/java-send-mailer/target/send-mail-0.0.1-SNAPSHOT.jar
[INFO] 
[INFO] --- maven-dependency-plugin:2.8:copy-dependencies (default) @ send-mail ---
[INFO] Copying commons-logging-1.2.jar to /Users/humbertodias/git/java-send-mailer/target/send-mail-0.0.1-SNAPSHOT.lib/commons-logging-1.2.jar
[INFO] Copying commons-email-1.4.jar to /Users/humbertodias/git/java-send-mailer/target/send-mail-0.0.1-SNAPSHOT.lib/commons-email-1.4.jar
[INFO] Copying javax.mail-1.5.2.jar to /Users/humbertodias/git/java-send-mailer/target/send-mail-0.0.1-SNAPSHOT.lib/javax.mail-1.5.2.jar
[INFO] Copying activation-1.1.1.jar to /Users/humbertodias/git/java-send-mailer/target/send-mail-0.0.1-SNAPSHOT.lib/activation-1.1.1.jar
[INFO] Copying jcommander-1.48.jar to /Users/humbertodias/git/java-send-mailer/target/send-mail-0.0.1-SNAPSHOT.lib/jcommander-1.48.jar
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 3.151 s
[INFO] Finished at: 2016-06-24T02:01:12-03:00
[INFO] Final Memory: 18M/215M
[INFO] ------------------------------------------------------------------------

```

## Recieved

![Preview](doc/inbox.png)


## References

![Preview](doc/gmail-less-security.png)

1. [GMail Less Security](https://support.google.com/accounts/answer/6010255?hl=en)

