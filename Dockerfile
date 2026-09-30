FROM eclipse-temurin:25-jdk-alpine AS builder
WORKDIR /app

# Copia os ficheiros do projeto
COPY . .

# Garante permissões de execução para o wrapper e compila
RUN chmod +x ./mvnw && ./mvnw clean package -DskipTests

FROM eclipse-temurin:25-jre-alpine
WORKDIR /app

# O Maven gera a saída na pasta target/
COPY --from=builder /app/target/*.jar batuque.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "batuque.jar"]