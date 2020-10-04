package core.commands;

import core.collection.CollectionManager;
import core.collection.CollectionUtils;
import core.commands.serialized.SerializedMessage;
import core.stored.Worker;
import core.utils.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class CommandReceiver {
    private final Socket socket;
    private static final Logger logger = LoggerFactory.getLogger(CommandReceiver.class);
    private CollectionManager collectionManager = CollectionManager.getCollectionManager();

    public CommandReceiver(Socket socket) {
        this.socket = socket;
    }

    // Done
    public void info() throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

        out.writeObject(new SerializedMessage(collectionManager.getInfo()));
        logger.info(String.format("Клиенту %s:%s отправлен результат работы команды INFO", socket.getInetAddress(), socket.getPort()));
    }

    // Done
    public void show() throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

        out.writeObject(new SerializedMessage(collectionManager.show()));
        logger.info(String.format("Клиенту %s:%s отправлен результат работы команды SHOW", socket.getInetAddress(), socket.getPort()));
    }

    // Done
    public void add(Object o) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        Worker worker = (Worker) o;

        if (Validator.validateWorker(worker)) {
            collectionManager.add(worker);
            out.writeObject(new SerializedMessage("Элемент добавлен в коллекцию."));
        } else {
            out.writeObject(new SerializedMessage("Полученный элемент не прошел валидацию на стороне сервера."));
        }

        logger.info(String.format("Клиенту %s:%s отправлен результат работы команды ADD", socket.getInetAddress(), socket.getPort()));
    }

    // Done
    public void update(String id, Worker worker) throws IOException {
        Integer workerId;
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        try {
            workerId = Integer.parseInt(id);
            if (CollectionUtils.checkExist(workerId)) {
                if (Validator.validateWorker(worker)) {
                    collectionManager.update(worker, workerId);
                    out.writeObject(new SerializedMessage("Команда update выполнена."));
                } else {
                    out.writeObject(new SerializedMessage("Полученный элемент не прошел валидацию на стороне сервера."));
                }
            }
            else {out.writeObject(new SerializedMessage("Элемента с таким ID нет в коллекции."));}
        } catch (NumberFormatException e) {
            out.writeObject(new SerializedMessage("Команда не выполнена. Вы ввели некорректный аргумент."));
        }

        logger.info(String.format("Клиенту %s:%s отправлен результат работы команды UPDATE", socket.getInetAddress(), socket.getPort()));
    }

    // Done
    public void removeById(String ID) throws IOException {
        Integer workerId;
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        try {
            workerId = Integer.parseInt(ID);
            if (CollectionUtils.checkExist(workerId)) {
                collectionManager.removeById(workerId);
                out.writeObject(new SerializedMessage("Элемент с ID " + workerId + " успешно удален из коллекции."));
            } else { out.writeObject(new SerializedMessage("Элемента с таким ID нет в коллекции."));}
        } catch (NumberFormatException e) {
            out.writeObject(new SerializedMessage("Команда не выполнена. Вы ввели некорректный аргумент."));
        }

        logger.info(String.format("Клиенту %s:%s отправлен результат работы команды REMOVE_BY_ID", socket.getInetAddress(), socket.getPort()));
    }

    // Done
    public void clear() throws IOException {
        collectionManager.clear();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

        out.writeObject(new SerializedMessage("Коллекция успешно очищена."));
        logger.info(String.format("Клиенту %s:%s отправлен результат работы команды CLEAR", socket.getInetAddress(), socket.getPort()));
    }

    // Done
    public void addIfMin(Object o) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        Worker worker = (Worker) o;

        if (Validator.validateWorker(worker)) {
            collectionManager.addIfMin(worker);
            out.writeObject(new SerializedMessage("Элемент добавлен в коллекцию."));
        } else {
            out.writeObject(new SerializedMessage("Полученный элемент не прошел валидацию на стороне сервера."));
        }

        logger.info(String.format("Клиенту %s:%s отправлен результат работы команды ADD_IF_MIN", socket.getInetAddress(), socket.getPort()));
    }

    // Done
    public void countGreaterThanSalary(String salary) throws IOException {
        String message = collectionManager.countGreaterThanSalary(salary);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

        out.writeObject(new SerializedMessage(message));
        logger.info(String.format("Клиенту %s:%s отправлен результат работы команды COUNT_GREATER_THAN_SALARY", socket.getInetAddress(), socket.getPort()));
    }

    // Done
    public void maxByCreationDate() throws IOException {
        String message = collectionManager.maxByCreationDate();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

        out.writeObject(new SerializedMessage(message));
        logger.info(String.format("Клиенту %s:%s отправлен результат работы команды MAX_BY_CREATION_DATE", socket.getInetAddress(), socket.getPort()));
    }

    // Done
    public void printDescending() throws IOException {
        String message = collectionManager.printDescending();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

        out.writeObject(new SerializedMessage(message));
        logger.info(String.format("Клиенту %s:%s отправлен результат работы команды PRINT_DESCENDING", socket.getInetAddress(), socket.getPort()));
    }

    // Done
    public void removeLast() throws IOException {
        String message = collectionManager.removeLast();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

        out.writeObject(new SerializedMessage(message));
        logger.info(String.format("Клиенту %s:%s отправлен результат работы команды REMOVE_LAST", socket.getInetAddress(), socket.getPort()));
    }

    // Done
    public void reorder() throws IOException {
        String message = collectionManager.reorder();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

        out.writeObject(new SerializedMessage(message));
        logger.info(String.format("Клиенту %s:%s отправлен результат работы команды REORDER", socket.getInetAddress(), socket.getPort()));
    }
}
