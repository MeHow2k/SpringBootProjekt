FROM eclipse-temurin:19-jdk

WORKDIR /app

COPY target/SpringBootProjekt.jar app.jar
COPY KeyStore/keystore.jks KeyStore/keystore.jks

EXPOSE 444

ENTRYPOINT ["java","-jar","app.jar"]