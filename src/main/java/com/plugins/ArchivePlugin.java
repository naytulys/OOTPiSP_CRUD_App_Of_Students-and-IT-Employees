package com.plugins;

import com.annotations.LocalizedName;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@LocalizedName("Archive Plugin abstract class")
public abstract class ArchivePlugin {

    protected final String COMPRESS_FILE_NAME  = "OOTPiSP_CRUD_App_Of_Students_and_IT_Employees_CompressedFile";
    protected final byte[] ARCHIVE_PLUGIN_BUFFER = new byte[256 * 4];

    public abstract void compress(InputStream inputStream, OutputStream outputStream) throws IOException;

    public abstract void decompress(InputStream inputStream, OutputStream outputStream) throws IOException;
}
