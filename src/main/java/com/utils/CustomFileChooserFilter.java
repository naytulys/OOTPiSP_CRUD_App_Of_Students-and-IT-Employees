package com.utils;

import com.serializers.SerializerDescription;

import java.io.File;
import java.util.ArrayList;

public class CustomFileChooserFilter extends javax.swing.filechooser.FileFilter {

    private final SerializerDescription serializerDescription;
    private final ArrayList<PluginDescription> pluginDescriptions;

    public CustomFileChooserFilter(SerializerDescription serializerDescription, ArrayList<PluginDescription> pluginDescriptions){
        this.serializerDescription = serializerDescription;
        this.pluginDescriptions = pluginDescriptions;
    }

    @Override
    public boolean accept(File file) {
        boolean isAcceptableFile = file.isDirectory();
        String archiveExtension = null;
        String fileName = file.getName();
        for (PluginDescription currentPluginDescription : this.pluginDescriptions){
            for (String compareArchiveExtension : currentPluginDescription.getArchiveExtension()) {
                if (fileName.endsWith(compareArchiveExtension)){
                    archiveExtension = compareArchiveExtension;
                }
            }
        }
        if (archiveExtension != null){
            fileName = fileName.substring(0, fileName.length() - archiveExtension.length());
        }
        for (String compareSerializeExtension : this.serializerDescription.getExtensionsToSerialize()){
            isAcceptableFile = fileName.endsWith(compareSerializeExtension) || isAcceptableFile;
        }
        return isAcceptableFile;
    }

    public String getDescription() {
        StringBuilder filterDescription = new StringBuilder();
        filterDescription.append(this.serializerDescription.toString());
        for (String serializeExtension : this.serializerDescription.getExtensionsToSerialize()){
            filterDescription.append(
                    String.format(" (*%s)", serializeExtension)
            );
        }
        for (PluginDescription currentPluginDescription : this.pluginDescriptions){
            for (String archiveExtension : currentPluginDescription.getArchiveExtension()) {
                filterDescription.append(
                        String.format(" (*%s)", archiveExtension)
                );
            }
        }
        return filterDescription.toString();
    }
}
