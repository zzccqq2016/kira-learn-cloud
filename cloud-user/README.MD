获取授权码地址:http://127.0.0.1:8080/oauth/authorize?response_type=code&client_id=client

获取token:
    授权码模式:
        http://127.0.0.1:8080/oauth/token?grant_type=authorization_code&client_secret=123&code=iaXF_c&redirect_uri=http://www.baidu.com&client_id=client
        参数: grant_type:    授权类型
             client_secret: 密码
             client_id:     账号
             code:          /oauth/authorize 接口获取的授权码
             redirect_uri:  重定向地址
