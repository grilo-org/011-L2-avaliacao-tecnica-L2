FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src

RUN mvn package -DskipTests

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "target/empacotamento-0.0.1-SNAPSHOT.jar"]