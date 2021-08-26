package soho.com.demo1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        ClassPathXmlApplicationContext  context = new ClassPathXmlApplicationContext("spring-beanscope.xml");
        BeanScope obj = (BeanScope) context.getBean("beanScope");
        obj.say();
        obj.say();
        obj.say();
    }
}
