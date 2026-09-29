# ---- Etapa de build ----
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app
COPY src ./src
RUN mkdir out && \
    javac --release 17 -d out $(find src/main/java -name "*.java") && \
    jar cfe app.jar pe.bennu.internship.App -C out .

# ---- Etapa de ejecución ----
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/app.jar .
ENTRYPOINT ["java", "-jar", "app.jar"]