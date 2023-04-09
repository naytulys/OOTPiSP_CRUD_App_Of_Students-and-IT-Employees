package com.utils;

import com.annotations.LocalizedName;
import com.plugins.ArchivePlugin;

import java.util.ArrayList;

public class PluginDescription {
    private final Class<? extends ArchivePlugin> archivePlugin;
    private final ArrayList<String> archiveExtension;

    public PluginDescription(Class<? extends ArchivePlugin> archivePlugin, ArrayList<String> archiveExtension) {
        this.archivePlugin = archivePlugin;
        this.archiveExtension = archiveExtension;
    }

    public Class<? extends ArchivePlugin> getArchivePlugin() {
        return archivePlugin;
    }

    public ArrayList<String> getArchiveExtension() {
        return archiveExtension;
    }

    private String getLocalName() {
        String localName;
        if (archivePlugin.isAnnotationPresent(LocalizedName.class)) {
            localName = archivePlugin.getAnnotation(LocalizedName.class).value();
        } else {
            localName = archivePlugin.getSimpleName();
        }
        return localName;
    }

    @Override
    public String toString() {
        return getLocalName();
    }
}
