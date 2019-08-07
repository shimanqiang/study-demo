package anna.pdf;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private int age;

    //初始化日常
    private List<String> practicalLifeList = new ArrayList<>();
    //初始化感官
    private List<String> sensorialList = new ArrayList<>();
    //初始化语言
    private List<String> languageList = new ArrayList<>();
    //初始化数学
    private List<String> mathematicsList = new ArrayList<>();
    //初始化文化
    private List<String> cultureList = new ArrayList<>();
    //初始化观察记录
    private List<String> observationList = new ArrayList<>();

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getPracticalLifeList() {
        return practicalLifeList;
    }

    public void setPracticalLifeList(List<String> practicalLifeList) {
        this.practicalLifeList = practicalLifeList;
    }

    public List<String> getSensorialList() {
        return sensorialList;
    }

    public void setSensorialList(List<String> sensorialList) {
        this.sensorialList = sensorialList;
    }

    public List<String> getLanguageList() {
        return languageList;
    }

    public void setLanguageList(List<String> languageList) {
        this.languageList = languageList;
    }

    public List<String> getMathematicsList() {
        return mathematicsList;
    }

    public void setMathematicsList(List<String> mathematicsList) {
        this.mathematicsList = mathematicsList;
    }

    public List<String> getCultureList() {
        return cultureList;
    }

    public void setCultureList(List<String> cultureList) {
        this.cultureList = cultureList;
    }

    public List<String> getObservationList() {
        return observationList;
    }

    public void setObservationList(List<String> observationList) {
        this.observationList = observationList;
    }
}