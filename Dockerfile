FROM java:8
WORKDIR /
ADD build/libs/gradle_proj.jar gradle_proj.jar
EXPOSE 8080
CMD java -jar gradle_proj.jar