FROM openjdk:19
COPY target/SpringBootProjekt.jar .
COPY KeyStore/keystore.jks ./KeyStore/keystore.jks
EXPOSE 444
CMD ["-jar", "SpringBootProjekt.jar"]
ENTRYPOINT ["java"]


