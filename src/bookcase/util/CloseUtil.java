package bookcase.util;

import java.io.FilterInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class CloseUtil {
    public static void close(ServerSocket ss){
        if (ss != null){
            try {
                ss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Socket socket){
        if (socket != null){
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(FilterInputStream filterInputStream){
        if (filterInputStream != null){
            try {
                filterInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(FilterOutputStream filteroutputStream){
        if (filteroutputStream != null){
            try {
                filteroutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
