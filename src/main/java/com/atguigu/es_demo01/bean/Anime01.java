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

    private Integer time;

    private Integer characters;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Anime01 anime01 = (Anime01) o;

        if (animeName != null ? !animeName.equals(anime01.animeName) : anime01.animeName != null) return false;
        if (time != null ? !time.equals(anime01.time) : anime01.time != null) return false;
        return characters != null ? characters.equals(anime01.characters) : anime01.characters == null;
    }

    @Override
    public int hashCode() {
        int result = animeName != null ? animeName.hashCode() : 0;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (characters != null ? characters.hashCode() : 0);
        return result;
    }

    public String getAnimeName() {
        return animeName;
    }

    public void setAnimeName(String animeName) {
        this.animeName = animeName;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getCharacters() {
        return characters;
    }

    public void setCharacters(Integer characters) {
        this.characters = characters;
    }

    public Anime01(String animeName, Integer time, Integer characters) {
        this.animeName = animeName;
        this.time = time;
        this.characters = characters;
    }

    public Anime01() {
    }
}
