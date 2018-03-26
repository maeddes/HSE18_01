FROM openjdk:8-alpine
RUN mkdir -p /opt/HalloEsslingen
WORKDIR /opt/HalloEsslingen
COPY target/HalloEsslingen-0.0.1-SNAPSHOT.jar /opt/HalloEsslingen
CMD ["java", "-Dspring.profiles.active=k8s", "-jar", "HalloEsslingen-0.0.1-SNAPSHOT.jar"]
