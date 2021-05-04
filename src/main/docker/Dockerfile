FROM openjdk:11

LABEL creator="Curt Lytle"

EXPOSE 8080

WORKDIR /usr/local/bin/

COPY maven/claims-1.0.0.jar webapp.jar

CMD ["java", "-Dspring.profiles.active=container", "-jar", "webapp.jar"]