import javax.swing.*;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URISyntaxException;

public class Game {

    static Socket socket;
    static JFrame frame;

    public static void main(String[] args) {
        frame = new JFrame("Conquest Game");
        JPanel panel = new JPanel();

        frame.add(panel);
        frame.setSize(1920, 1080);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            socket = IO.socket("http://localhost:5000");
            socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                public void call(Object... objects) {
                    //when connected runs this
                    final Account account = new Account();
                    account.login.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            account.sendLogin();
                        }
                    });
                }
            });
        } catch (URISyntaxException e) {
            //swallow
        }
    }
}
