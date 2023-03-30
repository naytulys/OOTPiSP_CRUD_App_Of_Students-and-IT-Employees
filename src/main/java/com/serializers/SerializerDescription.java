package com.serializers;

import com.annotations.LocalizedName;

import java.util.ArrayList;

public class SerializerDescription {
    private final Class<? extends Serializer> serializer;
    private final ArrayList<String> extensionsToSerialize;

    public SerializerDescription(Class<? extends Serializer> serializer, ArrayList<String> extensionsToSerialize) {
        this.serializer = serializer;
        this.extensionsToSerialize = extensionsToSerialize;
    }

    public Class<? extends Serializer> getSerializer() {
        return serializer;
    }

    public ArrayList<String> getExtensionsToSerialize() {
        return extensionsToSerialize;
    }

    private String getLocalName() {
        String localName;
        if (serializer.isAnnotationPresent(LocalizedName.class)) {
            localName = serializer.getAnnotation(LocalizedName.class).value();
        } else {
            localName = serializer.getSimpleName();
        }
        return localName;
    }

    @Override
    public String toString() {
        return getLocalName();
    }
}
