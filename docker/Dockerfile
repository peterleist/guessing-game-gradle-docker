FROM alpine:latest
RUN apk --update add openjdk8-jre
COPY gradle_proj.jar gradle_proj.jar
EXPOSE 8080
CMD ["java","-jar","gradle_proj.jar"]