package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SingletonTest {
    @Test
    void singletonBeanFind(){
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(SingletonBean.class);
        SingletonBean singletonBean1 = annotationConfigApplicationContext.getBean(SingletonBean.class);
        SingletonBean singletonBean2 = annotationConfigApplicationContext.getBean(SingletonBean.class);
        Assertions.assertThat(singletonBean1).isSameAs(singletonBean2);
        annotationConfigApplicationContext.close();
    }
    @Scope("singleton")
    static class SingletonBean{
        @PostConstruct
        public void init(){
            System.out.println("SingletonBean.init");
        }
        @PreDestroy
        public void destroy(){
            System.out.println("SingletonBean.destroy");
        }
    }
}
