import hmac
import json
import time
import base64
import copy


class Jwt(object):
    def __init__(self):
        pass

    @staticmethod
    def encode(my_payload: dict, key, exp=300):
        header = {'alg': 'HS256', 'type': 'JWT'}
        header_json = json.dumps(header, sort_keys=True, separators=(',', ':'))
        header_bs = Jwt.b64encode(header_json.encode())
        # 生成payload
        payload = copy.deepcopy(my_payload)
        # 设置过期时间
        exp = time.time() + 300
        payload['exp'] = exp
        # 去除不需要的空格
        payload_json = json.dumps(payload, sort_keys=True, separators=(',', ':'))
        # 使用安全的base64加密
        payload_bs = Jwt.b64encode(payload_json.encode())
        str = header_bs + b'.' + payload_bs
        # 使用hmac 用本地网卡地址加密
        h = hmac.new(key.encode(), str, digestmod='SHA256')
        sign = h.digest()
        return header_bs + b'.' + payload_bs + b'.' + Jwt.b64encode(sign)

    @staticmethod
    def b64encode(j_s):
        # 使用安全的base64加密 去除占位符
        return base64.urlsafe_b64encode(j_s).replace(b'=', b'')

    @staticmethod
    def b64decode(b_s):
        # 将去除的占位符加回来
        rem = len(b_s) % 4
        if rem > 0:
            b_s += b'=' * (4 - rem)
        return base64.urlsafe_b64decode(b_s)

    @staticmethod
    def decode(token, key):
        """
           JWT 解密
        """
        header_bs, payload_bs, sign_bs = token.split(b'.')
        h = hmac.new(key.encode(), header_bs + b'.' + payload_bs, digestmod='SHA256')
        sign = Jwt.b64encode(h.digest())
        if sign_bs != sign:
            raise
        payload_json = Jwt.b64decode(payload_bs)
        print(payload_json)
        payload = json.loads(payload_json)
        if 'exp' in payload:
            if payload['exp'] < time.time():
                raise
        else:
            raise
        return payload


if __name__ == '__main__':
    import jwt

    s = jwt.encode({'username': 'lbb'}, b'bbl', algorithm='HS256')
    print(jwt.decode(s, b'bbl', algorithm='HS256'))
