package core.server_socket;

import core.collection.CollectionManager;
import core.utils.cmdhandler.Decrypting;
import core.utils.json_parser.ParserJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Controller {
    private static Socket clientSocket; //сокет для общения
    private static ServerSocket server; // серверсокет
    private static ObjectInputStream in; // поток чтения из сокета
    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    public void run(String strPort) throws IOException {
        try {
            try {
                int port = 0;
                CollectionManager collectionManager = CollectionManager.getCollectionManager();
                collectionManager.initList();
                logger.info("Создана пустая коллекция");
                ParserJson.fromJsonToCollection();
                try {
                    port = Integer.parseInt(strPort);
                } catch (NumberFormatException ex) {
                    logger.info("Ошибка! Неправильный формат порта.");
                    System.exit(0);
                }

                server = new ServerSocket(port);
                logger.info("Сервер запущен!");
                while (true) {
                    clientSocket = server.accept();
                    logger.info("А я все думал, когда же ты появишься: " + clientSocket);
                    try {
                        while (true) {
                            in = new ObjectInputStream(clientSocket.getInputStream());
                            Decrypting decrypting = new Decrypting(clientSocket);
                            Object o = in.readObject();
                            decrypting.decrypt(o);
                        }

                    } catch (EOFException | SocketException ex) {
                        logger.info("Клиент " + clientSocket + " того, откинулся...");
                    } finally {
                        clientSocket.close();
                        if (in != null) { in.close(); }
                    }
                }
            } finally {
                if (clientSocket != null) { clientSocket.close(); }
                logger.info("Сервер закрыт!");
                server.close();
            }
        } catch (IOException | ClassNotFoundException | NullPointerException e) {
            e.printStackTrace();
            logger.error(String.valueOf(e));
        }
    }
}
