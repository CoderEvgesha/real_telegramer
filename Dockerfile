FROM openjdk:15-jdk-alpine
WORKDIR /usr/src/app
COPY build/libs/RealTelegramer-2.0.jar ./
ENTRYPOINT ["java", "-jar", "RealTelegramer-2.0.jar"]