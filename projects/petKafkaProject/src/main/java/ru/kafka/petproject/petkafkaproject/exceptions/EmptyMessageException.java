package ru.kafka.petproject.petkafkaproject.exceptions;

public class EmptyMessageException extends Exception {
    public EmptyMessageException() {
        super("Файл производителя пуст.");
    }
}
