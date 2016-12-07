# spring-boot-base
spring boot 中集成常用框架：JDBC，MongoDB，JPA ......
###本项目搭建的目的是作为spring-boot类型项目的基础模板

####暂时集成：
  1. jdbc
    ```
      - [x] 单数据源配置
      - [x] 保存记录，保存记录并返回主键
      - [x] 根据条件查询记录
      - [x] 更新
    ```
    
  2. JPA
    ```
      - [x] 数据源配置
      - [x] 根据方法名实现查询操作
      - [x] 自定义@Query查询
    ```
    
  3. mongoDB
    ```
      - [x] 数据源配置，采用mongodb.uri方式配置
      - [x] @Query方式实现查询
      - [x] 通过MongoTemplate进行灵活查询
      - [x] 封装MongoTemplate添加查询
    ```
    