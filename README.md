# 简易进销存开发说明

## 一、搭建环境

### 1、环境变量

1、项目路径统一命名

```
工具类 i-tool，开发使用的所有工具统一放在此处。
工作区 i-work，所有开发项目的根目录。
```

2、将以上两个文件夹拖动到文件管理器的`快速访问`，便于后续其他协助

![](source/img/1531068996888.png)

3、参考下图进行参数配置

![](source/img/环境变量.png)

**3、安装路径请根据自身电脑进行配置，建议使用软件的默认安装配路径**

新建 JAVA_HOME

```
JAVA_HOME
C:\Program Files\Java\jdk1.8.0_181
```

新建变量 CLASSPATH
```
CLASSPATH
.;%JAVA_HOME%\lib
```

新建变量 MAVEN_HOME
```
MAVEN_HOME
C:\Users\l1091\Documents\i-tool\apache-maven-3.5.4
```

修改系统变量 Path

**注意：在原有变量后面追加，不要覆盖其他变量路径**

```
Path
;%JAVA_HOME%\bin;%JAVA_HOME%\jre\bin;%MAVEN_HOME%\bin
```

4、**检查环境配置是否成功**

在控制台分别输入以下命令

```
javac -version

mvn -v
```

出现以下画面则说明环境变量配置成功

![](source/img/控制台.png)

### 2、导入项目

2-1、点击`File`菜单，选择`Import`选项

![](source/img/1531065824845.png)

2-2、选择 `Maven` 类型导入

![](source/img/1531065942443.png)

2-3、导入 `i-work`目录下的 `summer-training`项目

![](source/img/环境变量.png)

2-4、等待大约3分钟，根据 `maven` 导入依赖包

### 3、添加tomcat

3-1、打开eclipse配置窗口

![](source/img/1531061163969.png)

3-2、添加`i-tool`目录下的tomcat容器

![](source/img/1531061313876.png)

### 4、运行项目

4-1、将 `summer-traning` 加入 `tomcat`容器

![](source/img/1531066369028.png)

![](source/img/1531060638738.png)

4-2、将 `server.xml`的端口为 `80`

![](source/img/1531060785125.png)

4-3、移动到文件底部，将 `summer-training`放置到到根目录 `/`


![](source/img/1531060737058.png)

4-4、启动项目

![](source/img/1531060928768.png)

出现以下提示则说明项目运行成功了

![](source/img/1531061009115.png)

打开浏览器输入 `127.0.0.1` 或者 `localhost`，出现以下画面则说明运行成功

![](source/img/1531061054036.png)

### 5、连接Mysql

5-1、安装`i-tool`目录下的 `sqlyog_x64`软件

5-2、参考 `application.properties` 下的rds配置，添加到以下窗口

![](source/img/1531068541105.png)

## 二、需求说明

### 1、系统首页

1、能看到每个菜单的连接

2、确保每个连接都是可用

### 2、商品资料

#### 2-1 样例数据

| 编号 | 品名 | 单位              | 规格 | 库存 |
| ---- | ---- | ----------------- | ---- | ---- |
| P001 | 电脑 | 电脑，15.6寸-黑色 | 台   | 4    |
| P002 | 电脑 | 电脑，13.3寸-白色 | 台   | 5    |
| P003 | 手机 | 小米MIX，5.9寸    | 台   | 6    |
| P004 | 手机 | iPhoneX，5.5寸    | 台   | 7    |

#### 2-2 数据结构

表名  `s_partinfo`

| 字段    | 说明     | 备注                       |
| ------- | -------- | -------------------------- |
| corpNo_ | 所属帐套 | 手机号后4位                |
| code_   | 商品编号 |                            |
| desc_   | 商品名称 |                            |
| spec_   | 商品规格 |                            |
| unit_   | 商品单位 |                            |
| stock_  | 商品库存 | 通过单据回写，不能直接输入 |

说明

1、商品编号要确保唯一性

2、每个帐套商品是唯一的

3、不要查询出其他帐套的数据

4、商品资料建立不能直接设置库存，库存需要通过单据进行修改

### 3、单据管理

#### 3-1 样例数据

##### 3-1-1 单头数据

**注意：帐套代码建议是个人的手机号后4位**

| 帐套代码 | 单据类别 | 单据编号    | 单据日期   | 建档人员 | 建档时间 |
| -------- | :------: | ----------- | ---------- | -------- | -------- |
| 2747     |    AB    | AB180701001 | 2018-07-01 | 自己姓名 | 当前时间 |
| 2747     |    AB    | AB180701002 | 2018-07-01 | 自己姓名 | 当前时间 |
| 2747     |    BC    | BC180701001 | 2018-07-01 | 自己姓名 | 当前时间 |
| 2747     |    BC    | BC180701004 | 2018-07-01 | 自己姓名 | 当前时间 |
| 2747     |    AE    | AE180701001 | 2018-07-01 | 自己姓名 | 当前时间 |
| 2747     |    AE    | AE180701002 | 2018-07-01 | 自己姓名 | 当前时间 |

##### 3-1-2 进货/销售单身数据

| 帐套代码 | 单据编号    | 单序 | 商品名称          | 单位 | 数量 |
| -------- | ----------- | :--: | ----------------- | ---- | ---- |
| 2747     | AB180701001 |  1   | 电脑，15.6寸-黑色 | 台   | 2    |
| 2747     | AB180701001 |  2   | 电脑，13.3寸-白色 | 台   | 3    |
| 2747     | AB180701001 |  3   | 小米MIX，5.9寸    | 台   | 1    |
| 2747     | AB180701001 |  4   | iPhoneX，5.5寸    | 台   | 2    |

##### 3-1-3 盘点单身数据

| 帐套代码 | 单据编号    | 单序 | 名称           | 单位 | 当前库存 | 实际库存 | 盈亏数量 |
| -------- | ----------- | :--: | -------------- | ---- | -------- | -------- | -------- |
| 2747     | AE180701001 |  1   | 小米MIX，5.9寸 | 台   | 10       | 15       | 15       |
| 2747     | AE180701001 |  2   | iPhoneX，5.5寸 | 台   | 12       | 8        | -4       |

**注意：**

1、单身商品名称请关联商品基本表获取

2、盈亏数量 = 实际库存 - 当前库存

3、单据编号和单序j尽量能自动生成

#### 3-2 数据结构

##### 3-2-1 单头结构

表名 `s_tranh`

| 字段        | 说明     | 备注             |
| ----------- | -------- | ---------------- |
| corpNo_     | 帐套     | **手机号后四位** |
| tb_         | 单别     |                  |
| tbNo_       | 单号     |                  |
| tbDate_     | 单据日期 |                  |
| createUser_ | 建档人员 |                  |
| createDate_ | 建档日期 |                  |

##### 3-2-2 单身结构

表名 `s_tranb`

| 字段      | 说明     | 备注                                     |
| --------- | -------- | ---------------------------------------- |
| corpNo_   | 帐套     | **手机号后四位**                         |
| tbNo_     | 单号     | 与s_tranh表的tbNo_关联                   |
| it_       | 单序     |                                          |
| code_     | 商品编号 | 关联商品基本表 s_partinfo                |
| curstock_ | 当前库存 | 仅用于盘点单，进货单和销售单用不到该栏位 |
| newstock_ | 实际库存 | 仅用于盘点单，进货单和销售单用不到该栏位 |
| num_      | 商品数量 |                                          |

### 4、统计报表

#### 5-1 数据汇总

| 商品名称       | 入库数量 | 出库数量 | 盈亏数量 |
| -------------- | -------- | -------- | -------- |
| 小米MIX，5.9寸 | 200      | 100      | 100      |
| iPhoneX，5.5寸 | 100      | 20       | 80       |

#### 5-2 报表明细

| 商品名称       | 单据日期   | 单据编号    | 数量 | 单位 |
| -------------- | ---------- | ----------- | ---- | ---- |
| 小米MIX，5.9寸 | 2018-07-01 | AB180701001 | 100  | 台   |
| 小米MIX，5.9寸 | 2018-07-01 | AB180701002 | 100  | 台   |

数量合计 200

**说明**

1、点击汇总表的数据能跳转到明细表查看明细数据

2、明细表的数据等于汇总表的数据才能说明数据准确

3、统计报表需要包含汇总数据和明细数据两个页面

4、要能通过明细数据计算并且得到汇总页面的数据

5、能够查询指定日期和指定单据的数据明细

## 三、注意事项

1、商品信息、单据信息请根据帐套进行区分，即个人手机号后4位

2、增删改查不允许改动其他帐套的数据，即不可以改动他人的数据

3、进货管理和销售管理共用一张表结构，通过单别进行区分

4、每一张单据都是由单头关联单身进行组成，不允许出现单身无关联的单头信息

5、点击商品能够跳转到商品详细页面，查看商品数据