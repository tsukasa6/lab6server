import core.server_socket.Controller;
import core.utils.json_parser.ParserJson;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Runtime.getRuntime().addShutdownHook(new Thread(ParserJson::fromCollectionToJson));
        Controller controller = new Controller();
        controller.run(args[0]);
    }
}
