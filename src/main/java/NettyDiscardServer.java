import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 描述:
 * NettyServer
 *
 * @author wanghui email:wanghuiaf@yonyou.com
 * @create 2020-03-16 上午10:15
 */
public class NettyDiscardServer {

    private final int serverPort;

    public NettyDiscardServer(int serverPort) {
        this.serverPort = serverPort;
    }

    public static void main(String[] args) {
        int port;
        if(args.length>0){
            port = Integer.valueOf(args[1]);
        }else {
            port = 8080;
        }
        System.out.println("准备启动中.........");
        new NettyDiscardServer(port).run();
        //好像永远都执行不到回家的代码......
        System.out.println("跑完了....肥噶!");
    }

    private void run() {
        /**NioEventLoopGroup是一个处理I/O操作的多线程事件循环.Netty为不同类型的传输提供了各种EventLoopGroup实现.在这个例子中,我们正在实现一个
         * 服务器端应用程序,因此将使用两个NioEventLoopGroup.
         * 第一个 通常称为老板,接收传入的连接;
         * 第二个 通常称为工人,一旦老板接收连接并将接受的连接注册给工作人员,就处理接收的连接的流量.
         *        使用多少线程以及他们如何映射到创建的通道取决于EventLoopGroup实现,甚至可以通过构造函数进行配置.
         */
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            /**
             * ServerBootstrap是一个帮助类,用于设置服务器.可以直接使用Channel设置服务器.但是一般情况都不这样做..
             **/
            ServerBootstrap sb = new ServerBootstrap();
            sb.group(bossGroup, workerGroup)
                    /**
                     * 在这里,我们指定使用NioServerSocketChannel类来实例化一个新的Channel来接收传入的连接.
                     * (每个客户端连接服务端,我们都为他们创建一个channel,那么这个channel对于面向对象的我们来说就是一个类,我们统一对于我们接收
                     * 到的连接都初始化为:NioServerSocketChannel)
                     */
                    .channel(NioServerSocketChannel.class)
                    /**
                     * 这里指定的处理程序将始终由新接收的Channel进行评估.ChannelInitializer是一个特殊的处理程序,旨在帮助用户配置新的Channel.
                     * 若想通过添加一些处理程序(如DiscardServerHandler)来配置新Channel的ChannelPipeline来实现网络应用程序.
                     * 随着应用程序的复杂化,可能在管道中添加更多的处理程序..(其实这就是需要自己重新实现包含自己处理逻辑的Channel.
                     * 类似于DiscardServerHandler,继承ChannelInboundHandlerAdapter一样)
                     * */
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new DiscardServerHandler());
                        }
                    })
                    /**
                     * 设置特定于Channel实现的参数.正在编写一个TCP/IP服务器.因此可以设置套接字选项.如tcpNoDelay和keepAlive
                     *
                     * option()和childOption()方法.
                     * option()用于接收传入连接的NioServerChannel.
                     * childOption()用于在这种情况下由父级ServerChannel接收的通道,即NioServerSocketChannel.
                     * 个人认为:前者用于配置父级Channel,后者配置自定义的子集Channel.
                     */
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture future = sb.bind(serverPort).sync();
            future.channel().closeFuture().sync();

        } catch (Exception e) {
            System.out.println("有异常,快跑...........");
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}