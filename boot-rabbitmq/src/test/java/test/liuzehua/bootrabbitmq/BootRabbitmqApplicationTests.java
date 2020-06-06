package test.liuzehua.bootrabbitmq;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@SpringBootTest(classes = BootRabbitmqApplication.class)
class BootRabbitmqApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

//    路由模式 通配符
    @Test
    void testtopic() {
        String s = "info.mq,error.mq,debug.mq";
        String[] split = s.split(",");
        for (int i = 0; i < split.length; i++) {
            rabbitTemplate.convertAndSend("topics",split[i], split[i]+"topics 广播消息 test"+i);
        }
    }

    // 多队列 路由模式
    @Test
    void testdirect() {
        String s = "info.mq,error.mq,debug.mq";
        String[] split = s.split(",");
        for (int i = 0; i < split.length; i++) {
            rabbitTemplate.convertAndSend("direct", split[i], split[i] + "direct 广播消息 test" + i);
        }
    }

    //  每个人都要干 广播
    @Test
    void testfanout() {
        for (int i = 1; i <= 10; i++) {
            rabbitTemplate.convertAndSend("fanout", "", i + "fanout 广播消息 test" + i);
        }
    }

//    一人一个
    @Test
    void testwork() {
        for (int i = 1; i <= 10; i++) {
            rabbitTemplate.convertAndSend("work", i+"work type queue test"+i);
        }
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

//    一对一模式
    @Test
    void testsingle() {
        rabbitTemplate.convertAndSend("template", " rabbittemplate test");
    }

}
