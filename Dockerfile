1. FROM eclipse-temurin:17-jdk-alpine
2. VOLUME /tmp
3. COPY target/*.jar app.jar
4. ENTRYPOINT ["java","-jar","/app.jar"]
5. EXPOSE 8080
