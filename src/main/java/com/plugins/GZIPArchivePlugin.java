package com.plugins;

import com.annotations.LocalizedName;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

@LocalizedName("GZIP archive")
public class GZIPArchivePlugin extends ArchivePlugin{
    @Override
    public void compress(InputStream inputStream, OutputStream outputStream) throws IOException {
        try (GZIPOutputStream gzipCompressOutputStream = new GZIPOutputStream(outputStream)){
            int readBytesAmount = inputStream.read(this.ARCHIVE_PLUGIN_BUFFER);
            while (readBytesAmount > 0){
                gzipCompressOutputStream.write(this.ARCHIVE_PLUGIN_BUFFER, 0, readBytesAmount);
                readBytesAmount = inputStream.read(this.ARCHIVE_PLUGIN_BUFFER);
            }
        }
    }

    @Override
    public void decompress(InputStream inputStream, OutputStream outputStream) throws IOException {
        try (GZIPInputStream gzipDecompressInputStream = new GZIPInputStream(inputStream)){
            int readBytesAmount = gzipDecompressInputStream.read(this.ARCHIVE_PLUGIN_BUFFER);
            while (readBytesAmount > 0) {
                outputStream.write(this.ARCHIVE_PLUGIN_BUFFER, 0, readBytesAmount);
                readBytesAmount = gzipDecompressInputStream.read(this.ARCHIVE_PLUGIN_BUFFER);
            }
        }
    }
}
