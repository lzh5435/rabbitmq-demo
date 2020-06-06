package test.liuzehua.rabbitmqdemo.fanout;import com.rabbitmq.client.*;import test.liuzehua.rabbitmqdemo.utils.ConnectionFactoryUtil;import java.io.IOException;import java.util.concurrent.TimeUnit;/** * @author liuzehua * 2020/6/6 **/public class Consumer1 {    public static void main(String[] args) throws IOException {        Connection connection = ConnectionFactoryUtil.getConnection();        Channel channel = connection.createChannel();        // 1，交换机名称  2，交换机类型  fanout 广播类型 不同消费者消费所有消息        channel.exchangeDeclare("order","fanout");        //创建临时队列  广播模式        String queue = channel.queueDeclare().getQueue();        // 绑定交换机和队列        channel.queueBind(queue,"order","");        channel.basicQos(1);        //消费        channel.basicConsume(queue,false,new DefaultConsumer(channel){            @Override            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {                System.out.println("consumer11111  receive message "+new String(body));                try {                    TimeUnit.SECONDS.sleep(2);                } catch (InterruptedException e) {                    e.printStackTrace();                }                channel.basicAck(envelope.getDeliveryTag(),false);            }        });    }}