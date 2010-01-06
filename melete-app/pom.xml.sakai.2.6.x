<?xml version="1.0"?>
<!--
 ***********************************************************************************
 * $URL: https://source.sakaiproject.org/contrib/etudes/melete/tags/2.7.2/melete-app/pom.xml $
 * $Id: pom.xml 59695 2009-04-06 23:00:53Z mallika@etudes.org $  
 ***********************************************************************************
 *
 * Copyright (c) 2008, 2009 Etudes, Inc.
 *
 * Portions completed before September 1, 2008 Copyright (c) 2004, 2005, 2006, 2007, 2008 Foothill College, ETUDES Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License.
 *
 **********************************************************************************
--><project xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://maven.apache.org/POM/4.0.0" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <parent>
    <artifactId>melete-base</artifactId>
    <groupId>org.etudes</groupId>
    <version>2.7.2</version>
    <relativePath>../pom.xml</relativePath>
  </parent>
  <name>etudes-melete-tool</name>
  <groupId>org.etudes</groupId>
  <artifactId>etudes-melete-tool</artifactId>
  <version>2.7.2</version>
  <organization>
    <name>Etudes</name>
    <url>http://etudes.org/</url>
  </organization>
  <inceptionYear>2003</inceptionYear>
  <packaging>war</packaging>
  <dependencies>
    <dependency>
      <groupId>org.sakaiproject.kernel</groupId>
      <artifactId>sakai-kernel-api</artifactId>
    </dependency>
    <dependency>
      <groupId>org.sakaiproject.kernel</groupId>
      <artifactId>sakai-component-manager</artifactId>
    </dependency>
    <dependency>
      <groupId>org.sakaiproject.kernel</groupId>
      <artifactId>sakai-kernel-util</artifactId>
    </dependency>
    <dependency>
      <groupId>org.etudes</groupId>
      <artifactId>etudes-melete-util</artifactId>
      <version>2.7.2</version>
    </dependency>
    <dependency>
      <groupId>org.etudes</groupId>
      <artifactId>etudes-melete-impl</artifactId>
      <version>2.7.2</version>
      <scope>provided</scope>
    </dependency>
    <dependency>
      <groupId>org.etudes</groupId>
      <artifactId>etudes-melete-hbm</artifactId>
      <version>2.7.2</version>
      <scope>provided</scope>
    </dependency>
    <dependency>
      <groupId>org.etudes</groupId>
      <artifactId>etudes-melete-api</artifactId>
      <version>2.7.2</version>
      <scope>provided</scope>
    </dependency>
    <dependency>
      <groupId>commons-logging</groupId>
      <artifactId>commons-logging</artifactId>
      <version>1.0.4</version>
      <scope>provided</scope>
    </dependency>
    <dependency>
      <groupId>commons-fileupload</groupId>
      <artifactId>commons-fileupload</artifactId>
      <version>${sakai.commons.fileupload.version}</version>
      <scope>provided</scope>
    </dependency>
    <dependency>
      <groupId>dom4j</groupId>
      <artifactId>dom4j</artifactId>
      <version>1.6.1</version>
      <scope>provided</scope>
    </dependency>
    <dependency>
      <groupId>xerces</groupId>
      <artifactId>xmlParserAPIs</artifactId>
      <version>2.6.2</version>
      <scope>compile</scope>
    </dependency>
    <dependency>
      <groupId>xerces</groupId>
      <artifactId>xercesImpl</artifactId>
      <version>2.6.2</version>
      <scope>compile</scope>
    </dependency>
<!--JSF DEPENDENCIES -->
    <dependency>
      <groupId>org.sakaiproject</groupId>
      <artifactId>sakai-jsf-tool</artifactId>
      <version>${sakai.version}</version>
    </dependency>
    <dependency>
      <groupId>org.sakaiproject</groupId>
      <artifactId>sakai-jsf-app</artifactId>
      <version>${sakai.version}</version>
    </dependency>
    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>jstl</artifactId>
      <version>1.1.2</version>
      <scope>runtime</scope>
    </dependency>
    <dependency>
      <groupId>taglibs</groupId>
      <artifactId>standard</artifactId>
      <version>1.1.2</version>
    </dependency>
    <dependency>
      <groupId>org.sakaiproject</groupId>
      <artifactId>sakai-depend-jsf-widgets-sun</artifactId>
      <version>${sakai.version}</version>
      <type>pom</type>
      <exclusions>
        <exclusion>
          <groupId>org.apache.myfaces.tomahawk</groupId>
          <artifactId>tomahawk</artifactId>
        </exclusion>
      </exclusions>
    </dependency>
    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>servlet-api</artifactId>
      <version>${sakai.servletapi.version}</version>
      <scope>provided</scope>
    </dependency>
    <dependency>
      <groupId>commons-collections</groupId>
      <artifactId>commons-collections</artifactId>
      <version>3.1</version>
    </dependency>
    <dependency>
      <groupId>commons-beanutils</groupId>
      <artifactId>commons-beanutils</artifactId>
      <version>1.7.0</version>
    </dependency>
    <dependency>
      <groupId>taglibs</groupId>
      <artifactId>standard</artifactId>
      <version>1.0.4</version>
    </dependency>
    <dependency>
      <groupId>commons-digester</groupId>
      <artifactId>commons-digester</artifactId>
      <version>1.6</version>
    </dependency>
  </dependencies>
  <build>
    <resources>
      <resource>
        <directory>${basedir}/src/bundle</directory>
        <includes>
          <include>**/*.properties</include>
        </includes>
      </resource>
      <resource>
        <directory>${basedir}/src/java</directory>
        <includes>
          <include>**/*.xml</include>
        </includes>
      </resource>
    </resources>
    <sourceDirectory>src/java</sourceDirectory>
  </build>
</project>
