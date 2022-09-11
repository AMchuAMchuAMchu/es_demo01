package com.atguigu.es_demo01.bean;

import java.io.Serializable;

/**
 * Description==>TODO
 * BelongsProject==>es_demo01
 * BelongsPackage==>com.atguigu.es_demo01.bean
 * CreateTime==>2022-09-11 20:18:32
 * Version==>1.0
 * Author==>02雪乃赤瞳楪祈校条祭制作委员会 wyq_start
 */
public class Anime01 implements Serializable {

    private String animeName;

    private String time;

    private Integer characters;

    @Override
    public String toString() {
        return "Anime01{" +
                "animeName='" + animeName + '\'' +
                ", time='" + time + '\'' +
                ", characters=" + characters +
                '}';
    }

    public String getAnimeName() {
        return animeName;
    }

    public void setAnimeName(String animeName) {
        this.animeName = animeName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getCharacters() {
        return characters;
    }

    public void setCharacters(Integer characters) {
        this.characters = characters;
    }

    public Anime01(String animeName, String time, Integer characters) {
        this.animeName = animeName;
        this.time = time;
        this.characters = characters;
    }

    public Anime01() {
    }
}
