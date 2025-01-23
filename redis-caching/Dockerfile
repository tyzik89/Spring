FROM openjdk:21

WORKDIR /app

COPY build/libs/*.jar redis-caching.jar

CMD ["java", "-jar", "redis-caching.jar"]