FROM lwieske/java-8 
RUN mkdir /usr/local/firstspringboot/
ADD ./target/FirstSpringBoot-0.0.1-SNAPSHOT.jar /usr/local/firstspringboot/
WORKDIR /usr/local/firstspringboot
EXPOSE 80
CMD ["java","-jar","/usr/local/firstspringboot/FirstSpringBoot-0.0.1-SNAPSHOT.jar"]
