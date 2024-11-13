FROM openjdk:19
COPY target/SpringBootProjekt.jar .
EXPOSE 8081
CMD ["-jar", "SpringBootProjekt.jar"]
ENTRYPOINT ["java"]


