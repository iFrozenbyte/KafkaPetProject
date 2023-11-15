package ru.kafka.petproject.petkafkaproject.source;

import ru.kafka.petproject.petkafkaproject.exceptions.EmptyMessageException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class SourceClass {
    public static String getMessagesFromTopic() throws IOException {
        int character;
        FileReader fileReader = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            if (fileReader.read() == -1) {
                throw new EmptyMessageException();
            }
            fileReader = new FileReader("messagesForKafka.txt");
            //if (fileReader.)
        } catch (FileNotFoundException fe) {
            System.out.println("File not found!\n" + Arrays.toString(fe.getStackTrace()));
        } catch (EmptyMessageException e) {
            System.out.println("Файл сообщентий от продюсера пуст\n" + Arrays.toString(e.getStackTrace()));
        }
        while ((character = fileReader.read()) != -1){
            System.out.print((char) character);
            stringBuilder.append((char) character);
        }
        fileReader.close();

        return String.valueOf(stringBuilder);
    }
}