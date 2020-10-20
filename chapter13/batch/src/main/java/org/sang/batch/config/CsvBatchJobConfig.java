package org.sang.batch.config;

import org.sang.batch.model.User;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

/**
 * @author Mr.Du
 * @date 2020/10/17 10:16
 *
 * 创建CsvBatchJobConfig进行SpringBatch配置，同时注入JobBuilderFactory、
 * StepBuilderFactory以及DataSource备用,其中JobBuilderFactory
 * 将用来构建Job,StepBuilderFactory用来构建Step，DataSource则用来
 * 支持持久化操作，这里持久化方案是Spring-jdbc
 *
 *
 */
@Configuration
public class CsvBatchJobConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final DataSource dataSource;

    public CsvBatchJobConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, DataSource dataSource) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.dataSource = dataSource;
    }

    /**
     * SpringBatch提供了一些常用的ItemReader，例如JdbcPagingItemReader
     * 用来读取数据库中的数据，StaxEventItemReader用来读取XML数据，本案例
     * 中的FlatFileItemReader则是一个加载普通文件的ItemReader。在FlatFileItemReader
     * 的配置过程中，由于date.csv文件第一行是标题，因此通过setLinedToSkip方法设置
     * 跳过一行，然后通过setResource方法配置data.csv文件的位置，data.csv
     * 文件放在classpath目录下，然后通过setLineMapper方法设置每一行的数据信息
     * setNames方法配置了data.csv文件一共4列，分别是id,username,address,以及gender
     * setDelimiter则是配置列与列之间的间隔符（将通过间隔符对每一行的数据进行切分）
     * 最后设置要映射的实体类属性即可。
     * @return
     */
    @Bean
    @StepScope
    FlatFileItemReader<User> itemReader(){
        FlatFileItemReader<User> reader = new FlatFileItemReader<>();
        reader.setLinesToSkip(1);
        reader.setResource(new ClassPathResource("data.csv"));
        reader.setLineMapper(new DefaultLineMapper<User>(){{
            setLineTokenizer(new DelimitedLineTokenizer(){{
                setNames("id","username","address","gender");
                setDelimiter("\t");
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<User>(){{
                setTargetType(User.class);
            }});
        }});
        return reader;
    }

    /**
     * 数据的写出逻辑,Spring Batch也提供了多个ItemWriter的实现，
     * 常见的如FlatFileItemWriter，表示将数据写出为一个普通文件，
     * StaxEventItemWriter表示将数据写出为XML。另外，还有针对不
     * 同数据库提供的写出操作支持类，如 MongoItemWriter、
     * JpaItemWriter、Neo4jItemWriter 以及HibernateItemWriter
     * 等，本案例使用的 JdbcBatchItemWriter 则是通过JDBC将数据
     * 写出到一个关系型数据库中。JdbcBatchItemWriter主要配直数据
     * 以及数据插入SQL ，注意占住符的写法是“：属性名"。最后通过
     * BeanPropertyItemSqlParameterSourceProvider实例将实体类
     * 的属性和 SQL 中的占住符一一映
     * @return
     */
    @Bean
    JdbcBatchItemWriter jdbcBatchItemWriter(){
        JdbcBatchItemWriter writer = new JdbcBatchItemWriter();
        writer.setDataSource(dataSource);
        writer.setSql("insert into user values(:id,:username,:address,:gender)");
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        return writer;
    }

    /**
     * 配置一个Step,Step通过stepBuilderFactory进行配置，首先通
     * 过get获取一个StepBuilder, get方法的参数就是该Step的name，
     * 然后调用chunk方法的参数 2，表示每读取到两条数据就执行一次
     * write操作，最后分别配置reader和writer
     * @return
     */
    @Bean
    Step csvStep(){
        return stepBuilderFactory.get("csvStep")
                .<User,User>chunk(2)
                .reader(itemReader())
                .writer(jdbcBatchItemWriter())
                .build();
    }

    /**
     * 配配一个 Job，通过jobBuilderFactory构建一个Job,get方法的
     * 参数为Job的name, 然后配置该Job的Step即可
     * @return
     */
    @Bean
    Job csvJob(){
        return jobBuilderFactory.get("csvJob")
                .start(csvStep())
                .build();
    }
}
