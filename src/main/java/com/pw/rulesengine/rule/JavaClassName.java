package com.pw.rulesengine.rule;

public interface JavaClassName {

    public String getConditionClassName();
    public void setConditionClassName(String conditionClassName);

    public String getPassClassName();
    public void setPassClassName(String passClassName);

    public String getFailClassName();
    public void setFailClassName(String failClassName);

    public String getAlwaysClassName();
    public void setAlwaysClassName(String alwaysClassName);

}
