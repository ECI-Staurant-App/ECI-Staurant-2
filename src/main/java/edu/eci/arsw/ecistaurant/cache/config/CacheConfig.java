package edu.eci.arsw.ecistaurant.cache.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Arrays;

@EnableJpaRepositories(basePackages = "edu.eci.arsw.ecistaurant.persistence")
@EnableCaching
@Configuration
public class CacheConfig {

    @Bean
    public CacheManager restaurantscacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(
                new ConcurrentMapCache("restaurantCache"),
                new ConcurrentMapCache("menusCache"),
                new ConcurrentMapCache("pedidosCache")));
        return cacheManager;

    }


    /*@Bean
    public EhCacheManagerFactoryBean cacheMangerFactory() {
        EhCacheManagerFactoryBean bean = new EhCacheManagerFactoryBean();
        bean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        bean.setShared(true);
        return bean;
    }*/
}