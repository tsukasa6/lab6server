package core.commands.concrete;

import core.commands.Command;
import core.commands.CommandReceiver;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;

public class Info extends Command implements Serializable {
    private static final long serialVersionUID = 32L;

    @Override
    public void execute(Object argObject, Socket socket) throws IOException {
        CommandReceiver commandReceiver = new CommandReceiver(socket);
        commandReceiver.info();
    }
}
