5.27测试是否同步06
#  test-management-demo1

一个基于 Maven 构建、使用 JUnit 进行单元测试，并支持 Jenkins 持续集成测试的 Java 项目。该项目用于演示软件测试实验中的测试管理全过程：从缺陷注入、单元测试执行，到构建报告生成和缺陷提交管理。

---

###  项目简介

本项目包含以下主要功能：

- 使用 Apache Commons Digester 进行 XML 规则解析。
- 集成 JUnit 4 编写并运行单元测试。
- 使用 Maven 管理依赖与构建生命周期。
- 借助 Jenkins 实现自动化构建、测试与报告发布。
- 模拟缺陷注入与缺陷追踪流程，配合 GitHub Issues 进行缺陷管理。

---

###  项目结构

```plaintext
test-management-demo1/
├── .idea/                          # IDEA 项目配置
├── src/
│   ├── main/
│   │   └── java/
│   │       └── org/apache/commons/digester3/
│   │           └── Digester.java       # 业务逻辑类
│   └── test/
│       ├── java/
│       │   └── org/apache/commons/digester3/
│       │       ├── DigesterTest.java   # 单元测试类
│       │       ├── Student.java        # 测试用 Bean 类
│       │       └── Students.java       # 测试用 Bean 类
│       └── resources/
│           └── student.xml             # 测试数据文件
├── target/...
├── .gitignore                         # Git 忽略配置
├── pom.xml                            # Maven 构建配置文件
└── README.md                          # 项目说明文档

```

## -> 快速开始

### 克隆项目

```bash
git clone https://github.com/cainiaoxz/test-management-demo1.git
cd test-management-demo1
```

### 运行测试

```bash
mvn clean test
```

---

### 4. 依赖技术栈

| 工具/框架      | 版本         | 说明         |
| -------------- | ------------ | ------------ |
| Java           | 17           | 编译运行环境 |
| Maven          | 3.x          | 构建工具     |
| JUnit          | 4.13.2       | 单元测试框架 |
| Apache Commons | Digester 3.2 | XML解析      |
| Jenkins        | 自行部署     | 持续集成     |

---

###  Jenkins 构建说明

- **构建目标**：`clean test`
- **测试报告路径**：`target/surefire-reports/*.xml`
- **构建触发器**：通过 GitHub Webhook 自动触发构建

---

### 缺陷管理（实验部分）

| 编号    | 缺陷描述                             | 状态    | 提交方式     |
| ------- | ------------------------------------ | ------- | ------------ |
| BUG-001 | Jenkins 构建失败，缺失 surefire 插件 | ✅已解决 | GitHub Issue |
| BUG-002 | 注入错误路径导致测试失败             | ✅已解决 | GitHub Issue |

---

###  测试覆盖

- 测试类：`DigesterTest`
- 总测试数：6
- 构建成功时 Jenkins 显示测试覆盖率与执行情况。

---

###  输出结果

构建成功后，将自动生成以下内容：

- ✅ 测试报告（XML格式）
- ✅ Jenkins 构建记录和状态

---

###  作者说明

此项目为软件测试实验课程实验4，测试管理实验，用于模拟真实项目中的缺陷注入、单元测试编写、持续集成配置和缺陷管理流程，具备良好的实验报告展示价值。



