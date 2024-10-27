FROM swr.cn-north-4.myhuaweicloud.com/ddn-k8s/docker.io/node:20.11-alpine3.19


RUN sed -i 's/dl-cdn.alpinelinux.org/mirrors.tuna.tsinghua.edu.cn/g' /etc/apk/repositories 
RUN apk update \
    && apk add git  

WORKDIR /app
RUN git clone https://gitcode.com/continew/continew-admin-ui.git 
WORKDIR /app/continew-admin-ui
RUN npm config set registry https://registry.npmmirror.com \
    && npm install \
    && npm -v
    # && chown -R $(whoami) /usr/local/lib/node_modules/npm
EXPOSE 5173
ENTRYPOINT ["npm","run","dev"]

# 容器对外暴露的端口号(这个3000 必须是当前node项目的端口)

