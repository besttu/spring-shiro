# -*- coding: UTF-8 -*-
import codecs
import re;
import requests;
import random;
import json;
import sys;
import demjson;

USER_AGENTS = [
    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; AcooBrowser; .NET CLR 1.1.4322; .NET CLR 2.0.50727)",
    "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0; Acoo Browser; SLCC1; .NET CLR 2.0.50727; Media Center PC 5.0; .NET CLR 3.0.04506)",
    "Mozilla/4.0 (compatible; MSIE 7.0; AOL 9.5; AOLBuild 4337.35; Windows NT 5.1; .NET CLR 1.1.4322; .NET CLR 2.0.50727)",
    "Mozilla/5.0 (Windows; U; MSIE 9.0; Windows NT 9.0; en-US)",
    "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Win64; x64; Trident/5.0; .NET CLR 3.5.30729; .NET CLR 3.0.30729; .NET CLR 2.0.50727; Media Center PC 6.0)",
    "Mozilla/5.0 (compatible; MSIE 8.0; Windows NT 6.0; Trident/4.0; WOW64; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; .NET CLR 1.0.3705; .NET CLR 1.1.4322)",
    "Mozilla/4.0 (compatible; MSIE 7.0b; Windows NT 5.2; .NET CLR 1.1.4322; .NET CLR 2.0.50727; InfoPath.2; .NET CLR 3.0.04506.30)",
    "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN) AppleWebKit/523.15 (KHTML, like Gecko, Safari/419.3) Arora/0.3 (Change: 287 c9dfb30)",
    "Mozilla/5.0 (X11; U; Linux; en-US) AppleWebKit/527+ (KHTML, like Gecko, Safari/419.3) Arora/0.6",
    "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.8.1.2pre) Gecko/20070215 K-Ninja/2.1.1",
    "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9) Gecko/20080705 Firefox/3.0 Kapiko/3.0",
    "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.56 Safari/535.11",
    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_3) AppleWebKit/535.20 (KHTML, like Gecko) Chrome/19.0.1036.7 Safari/535.20",
    "Opera/9.80 (Macintosh; Intel Mac OS X 10.6.8; U; fr) Presto/2.9.168 Version/11.52", ]


def process_request():
    return random.choice(USER_AGENTS)


def get_header():
    return {'User-Agent': process_request()};


header = {
    'User-Agent': process_request(),
}

url = "http://search.kuwo.cn/r.s?client=kt&all=word&pn=page&rn=size&uid=221260053&ver=kwplayer_ar_99.99.99.99&vipver=1&ft=music&cluster=0&strategy=2012&encoding=utf8&rformat=json&vermerge=1&mobi=1";
url1 = "http://api.ly93.cc/kw.php?id=new_id&ext=new_ext&rate=new_rate"


class music(object):
    def __init__(self, song_name, name, id, urls):
        self.name = name;
        self.song_name = song_name;
        self.id = id;
        self.urls = urls;


class ku_wo(object):
    def get_url(self, url, word, page="0", size="3"):
        url = url.replace("word", word)
        url = url.replace("page", page)
        url = url.replace("size", size)
        return url;

    def get_html(self, url, count=1, MAX_COUNT=5):
        if (count >= MAX_COUNT):
            # print("连接次数大于或者等于5")
            return None;
        try:
            response = requests.get(url, headers=get_header())
            if (response.status_code == 200):
                return response.text
            else:
                count = count + 1
            return self.get_html(url, count)

        except Exception as e:
            # print("connection error :" + str(e.args))
            count = count + 1
            return self.get_html(url, count)

    def match_html(self, html, regular):
        group = re.findall(regular, html);
        for g in group:
            yield g

    def get_url1(self, url, id, ext, size):
        url = url.replace("new_id", id)
        url = url.replace("new_ext", ext)
        url = url.replace("new_size", size)
        return url;

    def get_final_url(self, i):
        l = self.get_url1(url1, i, "mp3", "320")
        h = self.get_html(l)
        j = json.loads(h)
        u = j['url']
        t = requests.get(u).content;
        # python3 为 s = re.compile(r'url=(http.*)\\r').findall(str(t))
        s = re.findall(r'url=(http.*)\r', str(t))
        a = []
        a.append(s[0])
        l = self.get_url1(url1, i, "ape", "1000")
        h = self.get_html(l)
        j = json.loads(h)
        u = j['url']
        t = requests.get(u).content;
        # python3 为 s = re.compile(r'url=(http.*)\\r').findall(str(t))
        s = re.compile(r'url=(http.*)\r').findall(str(t))
        a.append(s[0])
        l = self.get_url1(url1, i, "flac", "2000")
        h = self.get_html(l)
        j = json.loads(h)
        u = j['url']
        t = requests.get(u).content;
        # python3 为 s = re.compile(r'url=(http.*)\\r').findall(str(t))
        s = re.compile(r'url=(http.*)\r').findall(str(t))
        a.append(s[0])
        aa = set(s[0])
        return list(set(a))

    def get_list(self, u, songname, name):
        list = []
        for index, i in enumerate(u):
            d = {}
            try:
                urls = self.get_final_url(str(i))
                d["urls"] = urls
                d["songname"] = songname[index]
                d["name"] = name[index]
                list.append(d)
            except Exception as e:
                # print(e.args)
                e.args
        return list;

    def self_main(self, word):
        t_url = self.get_url(url, word)
        html = self.get_html(t_url)
        if (html):
            j = json.loads(html)
            l = j['abslist']
            songname = []
            name = []
            u = []
            for l in j['abslist']:
                songname.append(l['SONGNAME'])
                name.append(l['ARTIST'])
                g = l['MUSICRID']
                s = re.findall("MUSIC_(.*)", g)
                u.append(s[0])
        l = self.get_list(u, songname, name)
        return l;


def get_list(word="我很"):
    charset = ""
    try:
        w = word.decode("gbk")
        charset = "gbk"
    except Exception as e:
        w = word.decode("utf-8")
        charset = "utf-8"
    kw = ku_wo();
    l = kw.self_main(w)
    print(json.dumps(l, ensure_ascii=False).encode("utf-8"));

get_list(sys.argv[1])