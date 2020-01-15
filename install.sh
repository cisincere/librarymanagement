echo '开始安装环境'
echo '请确保安装了jdk8 git maven并设置环境变量'
echo '请确保安装了docker docker-compose并设置好环境变量'
echo 'docker-compose可以使用pip安装，如果未安装请先安装上述环境'

# 确认环境信息准备就绪
read -r -p "是否准备安装？[Y/N]" envConfirm
case $envConfirm in
    [yY][eE][sS]|[yY])
        echo "YES 继续执行"
        ;;
    [nN][oO]|[nN])
        echo "No 终止执行"
        exit 1
        ;;
esac

echo '==================清理当前脚本启动的容器和产生的镜像(可选的)=============='
read -r -p "是否清理？[Y/N]" envConfirm
case $envConfirm in
    [yY][eE][sS]|[yY])
         docker stop sc-rabbitmq sc-redis sc-mysql
         docker rm sc-rabbitmq sc-redis sc-mysql
         docker image rm rabbitmq:alpine redis:alpine mysql:9.6-alpine
         docker stop sc-monitor-admin sc-authorization-server sc-authentication-server sc-organization sc-gateway-admin sc-gateway-web
         docker rm sc-monitor-admin sc-authorization-server sc-authentication-server sc-organization sc-gateway-admin sc-gateway-web
         docker image rm cike/admin cike/authorization-server:latest cike/authentication-server:latest cike/organization:latest cike/gateway-admin:latest cike/gateway-web:latest
        ;;
    [nN][oO]|[nN])
        echo "No 终止执行"
        exit 1
        ;;
esac

echo '==================安装认证公共包到本地maven仓库=================='
cd common && mvn install
echo '当前目录:' && pwd

#回到根目录
cd -

echo '==================安装认证客户端到本地maven仓库=================='
#安装认证客户端到本地maven仓库
cd auth/authentication-client && mvn install
echo '当前目录:' && pwd

#回到根目录
cd -
echo '==================docker-compose启动公共服务==================='
#去docker-compose目录
cd docker-compose
echo '==================显示环境变量: docker-compose/.env =========='
#显示环境变量
cat ./.env
echo ''

#按需要开启公共服务
echo '==================启动 mysql or redis or rabbitmq ========'
docker-compose -f docker-compose.yml up -d mysql
docker-compose -f docker-compose.yml up -d redis
docker-compose -f docker-compose.yml up -d rabbitmq

echo '当前目录:' && pwd

# 回到根目录
cd -
echo '==================构建镜像：配置中心，消息中心 ========'

# 回到根目录
cd -

# 构建镜像：消息中心
cd ./center/bus
mvn package && mvn docker:build
echo '当前目录:' && pwd

#回到根目录
cd -
echo '==================4.4.启动注册中心, 配置中心, 消息中心============'
#去docker-compose目录
cd docer-compose
#启动注册中心
docker-compose -f docker-compose.yml -f docker-compose.nacos.yml up -d nacos

#回到根目录
cd -

#构建镜像:网关服务
cd ./gateway/gateway-web
mvn package && mvn docker:build

#回到根目录
cd -

#构建镜像:网关管理服务
cd ./gateway/gateway-admin
mvn package && mvn docker:build

cd -
echo '你可以立即去部署网关服务的DB(脚本路径:./gateway/gateway-admin/src/main/db),然后回来继续...'
read -r -p "确认网关服务的DB部署好了吗? [Y/n] " gwDbConfirm
case $gwDbConfirm in
    [yY][eE][sS]|[yY])
		echo "Yes 继续执行"
		;;
    [nN][oO]|[nN])
		echo "No 终止执行"
		exit 1
       	;;
    *)
		echo "Invalid input... 终止执行"
		exit 1
		;;
esac

#回到根目录
cd -
#去docker-compose目录
cd docker-compose


#启动网关服务
docker-compose -f docker-compose.yml -f docker-compose.spring-gateway.yml up -d gateway-web

#启动网关管理服务
docker-compose -f docker-compose.yml -f docker-compose.spring-gateway.yml up -d gateway-admin

#回到根目录
cd -

echo '==================6.构建镜像并启动组织(organization)相关服务=================='
#构建镜像:组织服务
cd ./sysadmin/organization
mvn package && mvn docker:build

#确认初始化授权/认证服务的DB:./sysadmin/db
echo '你可以立即去部署组织服务的DB(脚本路径:./sysadmin/db),然后回来继续...'
read -r -p "确认部署组织服务的DB部署好了吗? [Y/n] " orgDbConfirm
case $orgDbConfirm in
    [yY][eE][sS]|[yY])
		echo "Yes 继续执行"
		;;
    [nN][oO]|[nN])
		echo "No 终止执行"
		exit 1
       	;;
    *)
		echo "Invalid input... 终止执行"
		exit 1
		;;
esac

#回到根目录
cd -

#去docker-compose目录
cd docker-compose

#启动组织服务
docker-compose -f docker-compose.yml -f docker-compose.auth.yml up -d organization



#回到根目录
cd -

echo '==================6.构建镜像并启动认证(auth)相关服务=================='
#构建镜像:认证服务
cd ./auth/authentication-server
mvn package && mvn docker:build

cd -
#构建镜像:授权服务
cd ./auth/authorization-server
mvn package && mvn docker:build
#确认初始化授权/认证服务的DB:./auth/db
echo '你可以立即去部署授权/认证服务的DB(脚本路径:./auth/db),然后回来继续...'
read -r -p "确认部署授权/认证服务的DB部署好了吗? [Y/n] " authDbConfirm
case $authDbConfirm in
    [yY][eE][sS]|[yY])
		echo "Yes 继续执行"
		;;
    [nN][oO]|[nN])
		echo "No 终止执行"
		exit 1
       	;;
    *)
		echo "Invalid input... 终止执行"
		exit 1
		;;
esac
cd -
cd docker-compose
#启动网关服务
docker-compose -f docker-compose.yml -f docker-compose.auth.yml up -d authorization-server

#启动网关管理服务
docker-compose -f docker-compose.yml -f docker-compose.auth.yml up -d authentication-server

#回到根目录
cd -
echo '==================8.构建镜像并启动监控(monitor)相关服务==============='
#构建镜像:监控服务
cd ./monitor/admin
mvn package && mvn docker:build

# 回到根目录
cd -
cd docker-compose
docker-compose -f docker-compose.yml -f docker-compose.monitor.yml up -d monitor-admin
#回到根目录
cd -

