# Emoticons后端

## 一、版本号
* Java版本：17
* Spring Boot版本：3.1.4

Java可以使用大于等于17的版本(推荐使用17，LTS版本)，Spring Boot必须使用相同的版本，否则可能会出现版本冲突导致代码无法运行
## 二、项目模块架构

* emoticons:整个项目的父工程
* emoji-pojo:存放实体类、DTO、VO
* emoji-common:存放公共类，比如工具类、常量类、异常类、枚举类等等
* emoji-mapper:数据访问模块，基于MyBatis
* emoji-security:安全模块，基于Spring Security
* emoji-server:服务模块，包含service层、controller层、一些配置类等等

## 三、开发流程

### 1.克隆项目到本地

克隆项目到本地之后，使用IDEA导入(应该保证提前已经安装并配置好Maven)

### 2.修改配置信息

在`emoji-server`模块下，有三个`yml`配置文件，`application.yml`是主配置文件，`application-dev.yml`是开发环境配置文件，`application-prod.yml`是生产环境配置文件。

在本地进行开发时，需要修改`application-dev.yml`配置文件中的信息。这个配置文件中有三项配置：数据库、缓存、云存储。

主配置文件引用另外两个配置文件。

* 数据库配置：修改为自己本地的数据库（需要提前建立好相关的数据库和表，SQL脚本在`emoji-server`模块下`resources/sql`目录下）

* Redis缓存配置：需要修改为本地的Redis(也可以把生产环境的配置文件中的信息复制过来，直接使用服务器上的Redis)

* 云存储的配置：不用修改直接操作当前的云存储也没有什么影响

### 3.启动项目

修改了以上配置信息后，理论上项目已经可以正常启动。运行`emoji-server`模块下的`EmojiServerApplication`主类，即可启动服务(或者直接点击IDEA中的绿色小三角)。

如果出现报错，可能的原因有很多，需要根据具体的报错信息来判断。