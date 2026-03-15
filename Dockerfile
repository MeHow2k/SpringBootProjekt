FROM eclipse-temurin:19-jdk
COPY target/SpringBootProjekt.jar .
COPY KeyStore/keystore.jks ./KeyStore/keystore.jks
EXPOSE 444
CMD ["-jar", "SpringBootProjekt.jar"]
ENTRYPOINT ["java"]


