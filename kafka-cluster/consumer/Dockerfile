FROM openjdk:17-jdk-alpine

WORKDIR /app

# Копирование JAR-файла в контейнер
COPY consumer/build/libs/*.jar consumer.jar

# Команда для запуска JAR-файла
CMD ["java", "-jar", "consumer.jar"]