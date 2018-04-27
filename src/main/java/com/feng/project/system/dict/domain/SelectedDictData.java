package com.feng.project.system.dict.domain;

public class SelectedDictData {
    /** 字典标签 */
    private String dictLabel;
    /** 字典键值 */
    private String dictValue;
    /** 是否已被选择 默认没选择 */
    private boolean flag = false;

    public String getDictLabel() {
        return dictLabel;
    }

    public String getDictValue() {
        return dictValue;
    }

    public boolean isFlag() {
        return flag;
    }
    public boolean getFlag() {
        return flag;
    }

    public void setDictLabel(String dictLabel) {
        this.dictLabel = dictLabel;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "SelectedDictData{" +
                "dictLabel='" + dictLabel + '\'' +
                ", dictValue='" + dictValue + '\'' +
                ", flag=" + flag +
                '}';
    }
}
