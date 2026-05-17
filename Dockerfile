# Estágio 1: Build da aplicação usando Maven e Java 17
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copia os arquivos de configuração do Maven e as dependências primeiro (otimiza o cache do Docker)
COPY pom.xml .
COPY src ./src

# Compila o projeto gerando o arquivo JAR (pulando os testes para agilizar o deploy)
RUN mvn clean package -DskipTests

# Estágio 2: Ambiente de execução leve com Java 17 JRE
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copia o JAR gerado no estágio anterior para a imagem final
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta padrão que o Render utiliza para rotear o tráfego Web
EXPOSE 8080

# Comando para iniciar a aplicação Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]