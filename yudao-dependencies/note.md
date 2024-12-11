#### 使用小结

1. 在 yudao-dependencies 的 pom.xml 中，使用 dependencyManagement 定义依赖的版本
2. 在项目根目录的 pom.xml 中，使用 dependencyManagement 引入 yudao-dependencies
3. 在需要使用到该依赖的 Maven Module 的 pom.xml 中，使用 dependency 引入该依赖，无需填写版本号