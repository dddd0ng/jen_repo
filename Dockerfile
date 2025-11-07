# 1단계: 빌드
FROM gradle:8.5-jdk17-jammy AS build
WORKDIR /app
COPY . .
RUN gradle clean build --no-daemon -x test

# 2단계: 실행
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=build /app/build/libs/*.jar /app/
RUN set -eux; \
    JAR_FILE=$(ls /app | grep '\.jar$' | grep -v 'plain' | head -n 1); \
    mv "/app/${JAR_FILE}" /app/app.jar
EXPOSE 7777
ENTRYPOINT ["java", "-jar", "app.jar"]
