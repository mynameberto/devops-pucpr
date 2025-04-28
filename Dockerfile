# Fase de construção
FROM maven:3.6.3-jdk-8 AS build
WORKDIR /app
COPY . .
RUN mvn clean package

# Fase de execução
FROM tomcat:8.5-jre8
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/sisrh.war
EXPOSE 8080
CMD ["catalina.sh", "run"]