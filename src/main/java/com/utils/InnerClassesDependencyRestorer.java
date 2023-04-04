package com.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class InnerClassesDependencyRestorer {
    private final ArrayList<InnerClassDependencyRecord> dependencyRecords;

    public InnerClassesDependencyRestorer() {
        this.dependencyRecords = new ArrayList<>();
    }

    public void add(InnerClassDependencyRecord record) {
        this.dependencyRecords.add(record);
    }

    public void clear() {
        this.dependencyRecords.clear();
    }

    public void restoreInnerClassesDependencies(ArrayList<Object> listToRestore) throws InvocationTargetException, IllegalAccessException {
        for (InnerClassDependencyRecord dependencyRecord : this.dependencyRecords) {
            FieldOptions innerClassFieldOption = dependencyRecord.getInnerClassFieldOption();
            Object objectWithInnerClass = dependencyRecord.getObjectWithInnerClass();
            int innerClassReferenceIndex = dependencyRecord.getInnerClassReferenceIndex();
            if ((innerClassReferenceIndex >= 0) && (innerClassReferenceIndex < listToRestore.size())) {
                Object referenceObject = listToRestore.get(innerClassReferenceIndex);
                innerClassFieldOption.getSet().invoke(objectWithInnerClass, referenceObject);
            }
        }
    }
}
