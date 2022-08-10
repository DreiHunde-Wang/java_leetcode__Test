package src.classdesign;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Nio template
 */
public class NIOTest {
    public static void main(String[] args) {
        try {
            NioServer nioServer = new NioServer();
            ExecutorService pool = Executors.newFixedThreadPool(2);
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        nioServer.start(6666);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            NioClient nioClient = new NioClient("127.0.0.1", 6666);
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        nioClient.start();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

class NioServer {
    private ServerSocketChannel server;
    public NioServer() throws IOException {
        //获取通道
        server = ServerSocketChannel.open();
        //配置为非阻塞模式
        server.configureBlocking(false);
    }

    public void start(int port) throws IOException {
        if (server == null) {
            return;
        }
        //绑定监听端口
        server.bind(new InetSocketAddress(port));
        //获取选择器
        Selector selector = Selector.open();
        //将通道注册到选择器上，指定为接受模式
        server.register(selector, SelectionKey.OP_ACCEPT);

        //select() > 0说明有已就绪的事件
        while (selector.select() > 0) {
            //获取迭代器已就绪时间的key
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            //获取已就绪的事件
            while (iterator.hasNext()) {
                //获取key
                SelectionKey key = iterator.next();
                //接受事件就绪
                if (key.isAcceptable()) {
                    //获取客户端链接
                    SocketChannel client = server.accept();
                    //切换成非阻塞模式
                    client.configureBlocking(false);
                    //注册到选择器上, 指定为可读模式
                    client.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    //获取当前就绪状态的通道
                    SocketChannel client = (SocketChannel) key.channel();
                    //设置缓冲区
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    //创建文件通道，将客户端传来的文件写在本地(写模式，没有则创建)
                    FileChannel fileChannel = FileChannel.open(Paths.get("1.txt"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);

                    while (client.read(buffer) > 0) {
                        //从写位置切换成读位置
                        buffer.flip();
                        //写文件
                        fileChannel.write(buffer);
                        //读完切换成写模式
                        buffer.clear();
                    }
                }
                //用完的键就删除
                iterator.remove();
            }
        }
    }


}

class NioClient {
    private SocketChannel socketChannel;
    public NioClient(String ip, int port) throws IOException {
        socketChannel = SocketChannel.open(new InetSocketAddress(ip, port));
        socketChannel.configureBlocking(false);
    }

    public void start() throws IOException {
        Selector selector = Selector.open();
        //获取服务器返回的数据
        socketChannel.register(selector, SelectionKey.OP_READ);
        //发送一个txt文件给服务器端，读模式
        FileChannel fileChannel = FileChannel.open(Paths.get("C:\\Users\\I569683\\Documents\\id.txt"), StandardOpenOption.READ);
        //分配缓存池
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (fileChannel.read(buffer) != -1) {
            //从写模式切换到读模式
            buffer.flip();
            socketChannel.write(buffer);
            //读完切换成写模式
            buffer.clear();
            while (selector.select() > 0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    //读事件就绪
                    if (key.isReadable()) {
                        //获取通道
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer responseBuffer = ByteBuffer.allocate(1024);

                        //接受客户端返回来的数据
                        int readBytes = channel.read(responseBuffer);
                        if (readBytes > 0) {
                            //切换成读模式
                            responseBuffer.flip();
                            System.out.println(new String(responseBuffer.array(), 0, readBytes));
                        }
                    }
                    //用完就删除
                    iterator.remove();
                }
            }
        }
    }

}
