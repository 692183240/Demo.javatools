# springboot-vue

#### 介绍
这是一个前后端分离的Demo

#### 软件架构
1. springboot 
2. swagger
3. mybatis
4. vue
5. vue-router
6. nginx


#### 启动教程

### 数据库导入
1. 打开navicat，创建数据库为 springbootdemo数据库
2. 运行springbootdemo.sql即可
### 后端启动
1. idea以maven工程导入springboot-demo目录
2. idea运行DemoApplication.java
### 前端启动
1. 请确保已安装node.js8.*以上，并安装yarn
1. yarn install (安装依赖)
2. yarn run bulid (生成dist文件夹)
### nginx配置
1. windows环境下打开nginx配置文件nginx.conf(其他环境配置文件路径有所差异)
2. 修改如下配置：
    #### nginx.conf 
        listen       8888;
        server_name  0.0.0.0;
        # 界面所在目录
		location / {
			root D:\*\*\dist; # 这里配置前端生成dist文件夹的路径
		}

		# 服务地址
		 location /api/{   # 这里的api是前端代码(axios.js)里配置的
            proxy_pass http://127.0.0.1:8090/; # 这里配置后端服务地址
        }

    

