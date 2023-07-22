#! /bin/shell

#======================================================================
# 项目停服shell脚本
# 通过项目名称查找到PID
# 然后kill -15 pid
#======================================================================

VERSION="1.0.0-SNAPSHOT"
# 项目名称
APPLICATION="restful-generator"

# 项目启动jar包名称
APPLICATION_JAR="${APPLICATION}-${VERSION}.jar"

PID=$(pgrep "${APPLICATION_JAR}")
if [[ -z "$PID" ]]
then
    echo ${APPLICATION} is already stopped
else
    echo kill  "${PID}"
    kill -15 "${PID}"
    echo ${APPLICATION} stopped successfully
fi