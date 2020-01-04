package me.shimanqiang.spring;

/**
 * @author shimanqiang
 * @since 2019/9/6 11:31
 */
public class TestBean {
    private String name;
    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"age\":\"")
                .append(age).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
