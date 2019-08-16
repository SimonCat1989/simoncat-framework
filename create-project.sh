#!/bin/bash

mvn archetype:generate -DgroupId=com.simoncat.framework -DartifactId=$1 -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false

sed "s/simoncat-framework-template-name/$1/" pom.xml.tmpl > $1/pom.xml

cd $1
mvn eclipse:eclipse

rm src/test/java/com/simoncat/framework/AppTest.java
rm src/main/java/com/simoncat/framework/App.java
