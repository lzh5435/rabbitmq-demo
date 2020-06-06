package test.liuzehua.rabbitmqdemo.utils;import com.rabbitmq.client.Channel;import com.rabbitmq.client.Connection;import com.rabbitmq.client.ConnectionFactory;import java.io.IOException;import java.util.concurrent.TimeoutException;/** * @author liuzehua * 2020/6/6 **/public class ConnectionFactoryUtil {    private static  ConnectionFactory connectionFactory;    static {        connectionFactory = new ConnectionFactory();        connectionFactory.setHost("192.168.81.166");        connectionFactory.setPort(5672);        connectionFactory.setVirtualHost("/");        connectionFactory.setUsername("name");        connectionFactory.setPassword("passwd");    }    public static Connection getConnection(){        try {            Connection connection = connectionFactory.newConnection();            return connection;        } catch (IOException e) {            e.printStackTrace();        } catch (TimeoutException e) {            e.printStackTrace();        }        return null;    }    public static void close(Channel channel,Connection connection){        try {            if (channel != null) channel.close();            if (connection != null) connection.close();        } catch (TimeoutException e) {            e.printStackTrace();        } catch (IOException e) {            e.printStackTrace();        }    }}