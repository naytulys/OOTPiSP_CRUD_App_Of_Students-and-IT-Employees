package com.plugins;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZIPArchivePlugin extends ArchivePlugin {

    @Override
    public void compress(InputStream inputStream, OutputStream outputStream) throws IOException {
        try (ZipOutputStream zipCompressOutputStream = new ZipOutputStream(outputStream)){
            ZipEntry compressZipEntry = new ZipEntry(this.COMPRESS_FILE_NAME);
            zipCompressOutputStream.putNextEntry(compressZipEntry);
            int readBytesAmount = inputStream.read(this.ARCHIVE_PLUGIN_BUFFER);
            while (readBytesAmount > 0){
                zipCompressOutputStream.write(this.ARCHIVE_PLUGIN_BUFFER, 0, readBytesAmount);
                readBytesAmount = inputStream.read(this.ARCHIVE_PLUGIN_BUFFER);
            }
            zipCompressOutputStream.closeEntry();
        }
    }

    @Override
    public void decompress(InputStream inputStream, OutputStream outputStream) throws IOException {
        try (ZipInputStream zipDecompressInputStream = new ZipInputStream(inputStream)){
            ZipEntry decompressZipEntry = zipDecompressInputStream.getNextEntry();
            if (decompressZipEntry != null){
                int readBytesAmount = zipDecompressInputStream.read(this.ARCHIVE_PLUGIN_BUFFER);
                while (readBytesAmount > 0){
                    outputStream.write(this.ARCHIVE_PLUGIN_BUFFER, 0, readBytesAmount);
                    readBytesAmount = zipDecompressInputStream.read(this.ARCHIVE_PLUGIN_BUFFER);
                }
            }
        }
    }
}
