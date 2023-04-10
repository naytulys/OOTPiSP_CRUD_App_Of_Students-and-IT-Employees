package com.utils;

import com.plugins.ArchivePlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ArchivePluginLoader {

    public ArrayList<PluginDescription> loadPlugins(String pluginsFolderDirectory) throws IOException, ClassNotFoundException {
        ArrayList<PluginDescription> loadedPluginDescriptions = new ArrayList<>();
        File pluginsFolder = new File(pluginsFolderDirectory);
        File [] pluginsJarList = pluginsFolder.listFiles(file -> file.isFile() && file.getName().endsWith(".jar"));
        if (pluginsJarList != null) {
            for (File currentJarFile : pluginsJarList) {
                PluginProperties pluginProperties = new PluginProperties(currentJarFile);
                if (!pluginProperties.isEmpty()){
                    Class<? extends ArchivePlugin> archivePlugin = (Class<? extends ArchivePlugin>) pluginProperties.getPluginClass();
                    ArrayList<String> pluginExtensions = pluginProperties.getPluginExtensionsList();
                    if (archivePlugin != null && !pluginProperties.isEmpty()){
                        loadedPluginDescriptions.add(new PluginDescription(archivePlugin, pluginExtensions));
                    }
                }

            }
        }
        return loadedPluginDescriptions;
    }
}

