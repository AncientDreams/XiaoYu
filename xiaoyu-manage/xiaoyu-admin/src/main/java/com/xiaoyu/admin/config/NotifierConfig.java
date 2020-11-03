package com.xiaoyu.admin.config;/**
 * Created by ZhangXianYu on 2019-12-17.
 */

import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.notify.CompositeNotifier;
import de.codecentric.boot.admin.server.notify.Notifier;
import de.codecentric.boot.admin.server.notify.RemindingNotifier;
import de.codecentric.boot.admin.server.notify.filter.FilteringNotifier;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

/**
 *  配置 过滤通知
 *
 * @author zxy
 */
@Configuration
public class NotifierConfig {
    private final InstanceRepository repository;
    private final ObjectProvider<List<Notifier>> otherNotifiers;

    /**
     * 提醒时间间隔 / 单位秒
     */
    @Value("${reminderPeriod}")
    private int reminderPeriod;

    /**
     * 到期提醒时间 / 单位秒
     */
    @Value("${checkReminderInterval}")
    private int checkReminderInterval;

    public NotifierConfig(InstanceRepository repository, ObjectProvider<List<Notifier>> otherNotifiers) {
        this.repository = repository;
        this.otherNotifiers = otherNotifiers;
    }

    @Bean
    public FilteringNotifier filteringNotifier() {
        CompositeNotifier delegate = new CompositeNotifier(otherNotifiers.getIfAvailable(Collections::emptyList));
        return new FilteringNotifier(delegate, repository);
    }

    @Primary
    @Bean(initMethod = "start", destroyMethod = "stop")
    public RemindingNotifier remindingNotifier() {
        RemindingNotifier notifier = new RemindingNotifier(filteringNotifier(), repository);
        notifier.setReminderPeriod(Duration.ofSeconds(reminderPeriod));
        notifier.setCheckReminderInverval(Duration.ofSeconds(checkReminderInterval));
        return notifier;
    }
}