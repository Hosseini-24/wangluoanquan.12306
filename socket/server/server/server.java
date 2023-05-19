package 数据库12306.socket.server.server;
import 数据库12306.socket.server.task.serverTask;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class server {
    public static void main(String[] args) throws Exception{
        ExecutorService pool = new ThreadPoolExecutor(20, 20, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(512),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        ServerSocket server=new ServerSocket(8888);
        System.out.println("服务器正在运行，等待客户端连接！");
        while (true){
            Socket client = server.accept();
            System.out.println(client.getInetAddress()+" 已成功连接到此台服务器上。");
            serverTask serverTask = new serverTask(client);
            pool.execute(serverTask);//提交线程执行

        }
    }
}

