package com.wd.yiyangyun.mvp.mine.view.cebianlan;


/**
 * 实现Comparable接口，使GoodMan具有可比性
 * Created by wk on 2016/6/14.
 */
public class GoodMan implements Comparable<GoodMan>{

    public String name;
    public String pinyin;

    public GoodMan(String name) {
        this.name = name;
        this.pinyin = PinyinUtils.getPingYin(name);
    }


    @Override
    public int compareTo(GoodMan another) {
        return this.pinyin.compareTo(another.pinyin);
    }
}