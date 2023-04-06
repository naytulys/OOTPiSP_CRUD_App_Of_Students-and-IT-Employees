package com.utils;

import com.serializers.SerializerDescription;

import java.io.File;

public class CustomFileChooserFilter extends javax.swing.filechooser.FileFilter {

    SerializerDescription serializerDescription;

    public CustomFileChooserFilter(SerializerDescription serializerDescription){
        this.serializerDescription = serializerDescription;
    }

    @Override
    public boolean accept(File file) {
        boolean isAcceptableFile = file.isDirectory();
        for (String compareExtension : this.serializerDescription.getExtensionsToSerialize()){
            isAcceptableFile = file.getName().endsWith(compareExtension) || isAcceptableFile;
        }
        return isAcceptableFile;
    }

    public String getDescription() {
        StringBuilder filterDescription = new StringBuilder();
        filterDescription.append(this.serializerDescription.toString());
        for (String filterExtension : this.serializerDescription.getExtensionsToSerialize()){
            filterDescription.append(
                    String.format(" (*%s)", filterExtension)
            );
        }
        return filterDescription.toString();
    }
}
