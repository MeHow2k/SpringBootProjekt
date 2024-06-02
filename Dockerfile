FROM openjdk:19
COPY target/SpringBootProjekt.jar .
EXPOSE 8080
CMD ["-jar", "SpringBootProjekt.jar"]
ENTRYPOINT ["java"]


