package com.utils;

import com.serializers.SerializerDescription;
import javafx.collections.ObservableList;

import java.io.File;
import java.util.ArrayList;

public class SerializeFileDescription {

    private String fileAbsolutePath;
    private String serializeFilePath;
    private String archiveExtension = null;
    private String serializeFileExtension = null;

    public SerializeFileDescription(File fileToDescription, ObservableList<SerializerDescription> serializerList, ArrayList<PluginDescription> pluginsList) {
        this.fileAbsolutePath = fileToDescription.getAbsolutePath();
        for (PluginDescription currentPluginDescription : pluginsList){
            for (String compareArchiveExtension : currentPluginDescription.getArchiveExtension()) {
                if (fileAbsolutePath.endsWith(compareArchiveExtension)){
                    this.archiveExtension = compareArchiveExtension;
                }
            }
        }
        this.serializeFilePath = this.archiveExtension == null ? this.fileAbsolutePath :
                this.fileAbsolutePath.substring(0, this.fileAbsolutePath.length() - this.archiveExtension.length());
        for (SerializerDescription currentSerializeDescription : serializerList){
            for (String compareSerializeExtension : currentSerializeDescription.getExtensionsToSerialize()) {
                if (serializeFilePath.endsWith(compareSerializeExtension)){
                    this.serializeFileExtension = compareSerializeExtension;
                }
            }
        }
    }

    public String getFileAbsolutePath() {
        return fileAbsolutePath;
    }

    public String getSerializeFilePath() {
        return serializeFilePath;
    }

    public String getArchiveExtension() {
        return archiveExtension;
    }

    public String getSerializeFileExtension() {
        return serializeFileExtension;
    }

    public void setFileAbsolutePath(String fileAbsolutePath) {
        this.fileAbsolutePath = fileAbsolutePath;
    }

    public void setSerializeFilePath(String serializeFilePath) {
        this.serializeFilePath = serializeFilePath;
    }

    public void setArchiveExtension(String archiveExtension) {
        this.archiveExtension = archiveExtension;
    }

    public void setSerializeFileExtension(String serializeFileExtension) {
        this.serializeFileExtension = serializeFileExtension;
    }
}
