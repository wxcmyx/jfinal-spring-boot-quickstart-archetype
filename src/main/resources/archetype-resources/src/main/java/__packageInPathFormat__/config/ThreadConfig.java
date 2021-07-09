${package}.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;


@Configuration
@EnableAsync
public class ThreadConfig implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);//核心线程数目
        executor.setMaxPoolSize(30);//最大线程数目
        executor.setQueueCapacity(10);//队列中最大的数目
        executor.initialize();
        return executor;
    }

}
