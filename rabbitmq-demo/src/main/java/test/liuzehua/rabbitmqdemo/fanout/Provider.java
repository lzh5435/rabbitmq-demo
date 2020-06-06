package test.liuzehua.rabbitmqdemo.fanout;import com.rabbitmq.client.Channel;import com.rabbitmq.client.Connection;import test.liuzehua.rabbitmqdemo.utils.ConnectionFactoryUtil;import java.io.IOException;/** * @author liuzehua * 2020/6/6 **/public class Provider {    public static void main(String[] args) throws IOException {        Connection connection = ConnectionFactoryUtil.getConnection();        Channel channel = connection.createChannel();        // 1，交换机名称  2，交换机类型  fanout 广播类型        channel.exchangeDeclare("order","fanout");        // 1 交换机名称  2队列 无意义  3 持久化类型  4消息        for (int i = 1; i <= 10; i++) {            channel.basicPublish("order","",null,(i+"--------fanout tyoe message").getBytes());        }        ConnectionFactoryUtil.close(channel,connection);    }}