package com.utils;

public class InnerClassDependencyRecord {
    private Object objectWithInnerClass;
    private FieldOptions innerClassFieldOption;
    private int innerClassReferenceIndex;

    public InnerClassDependencyRecord(Object objectWithInnerClass, FieldOptions innerClassFieldOption, int innerClassReferenceIndex) {
        this.objectWithInnerClass = objectWithInnerClass;
        this.innerClassFieldOption = innerClassFieldOption;
        this.innerClassReferenceIndex = innerClassReferenceIndex;
    }

    public Object getObjectWithInnerClass() {
        return objectWithInnerClass;
    }

    public FieldOptions getInnerClassFieldOption() {
        return innerClassFieldOption;
    }

    public int getInnerClassReferenceIndex() {
        return innerClassReferenceIndex;
    }

    public void setObjectWithInnerClass(Object objectWithInnerClass) {
        this.objectWithInnerClass = objectWithInnerClass;
    }

    public void setInnerClassFieldOption(FieldOptions innerClassFieldOption) {
        this.innerClassFieldOption = innerClassFieldOption;
    }

    public void setInnerClassReferenceIndex(int innerClassReferenceIndex) {
        this.innerClassReferenceIndex = innerClassReferenceIndex;
    }
}
