package com.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class PluginProperties {

    private final String CONFIGURE_FILENAME = "plugin.config";
    private final String EXTENSIONS_SEPARATOR = ".";
    private final Properties  pluginProperties;
    private final File pluginFile;

    PluginProperties(File pluginFile) throws IOException {
        this.pluginFile = pluginFile;
        this.pluginProperties = getPluginProperties(pluginFile);
    }

    public boolean isEmpty() {
        return this.pluginProperties == null;
    }


    public Class<?> getPluginClass() throws MalformedURLException, ClassNotFoundException {
        String pluginClassName = getClassNameProperty();
        if (pluginClassName == null){
            return null;
        }
        URL jarURL = this.pluginFile.toURI().toURL();
        URLClassLoader classLoader = new URLClassLoader(new URL[]{jarURL});
        return classLoader.loadClass(pluginClassName);
    }

    public ArrayList<String> getPluginExtensionsList(){
        String extensions = this.getExtensionProperty();
        ArrayList<String> extensionsList = new ArrayList<>();
        if (extensions == null){
            return extensionsList;
        }
        while (!extensions.isEmpty()){
            int separatorIndex = extensions.indexOf(EXTENSIONS_SEPARATOR, 1);
            separatorIndex = separatorIndex == -1 ? extensions.length() : separatorIndex;
            extensionsList.add(extensions.substring(0, separatorIndex));
            extensions = extensions.substring(separatorIndex);
        }
        return extensionsList;
    }

    private String getExtensionProperty(){
        return this.pluginProperties.getProperty("extension");
    }

    private String getClassNameProperty(){
        return this.pluginProperties.getProperty("class");
    }

    private Properties getPluginProperties(File pluginFile) throws IOException {
        Properties result = null;
        JarFile jarFile = new JarFile(pluginFile);
        Enumeration<JarEntry> enumerationEntries = jarFile.entries();
        while (enumerationEntries.hasMoreElements()){
            JarEntry currentJarEntry = enumerationEntries.nextElement();
            if (currentJarEntry.getName().equals(CONFIGURE_FILENAME)){
                try (InputStream in = jarFile.getInputStream(currentJarEntry)) {
                    result = new Properties();
                    result.load(in);
                }
            }
        }
        return result;
    }
}
