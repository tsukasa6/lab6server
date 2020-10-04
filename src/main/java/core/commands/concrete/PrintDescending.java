package core.commands.concrete;

import core.commands.Command;
import core.commands.CommandReceiver;

import java.io.IOException;
import java.net.Socket;

public class PrintDescending extends Command {
    private static final long serialVersionUID = 32L;

    @Override
    public void execute(Object argObject, Socket socket) throws IOException {
        CommandReceiver receiver = new CommandReceiver(socket);
        receiver.printDescending();
    }
}
