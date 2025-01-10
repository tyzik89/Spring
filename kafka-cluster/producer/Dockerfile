FROM openjdk:17-jdk-alpine

WORKDIR /app

# Копирование JAR-файла в контейнер
COPY producer/build/libs/*.jar producer.jar

# Команда для запуска JAR-файла
CMD ["java", "-jar", "producer.jar"]