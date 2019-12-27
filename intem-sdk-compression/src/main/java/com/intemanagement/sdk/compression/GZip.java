package com.intemanagement.sdk.compression;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @Author panyunfeng04
 * @Date 2019-11-05
 */
public class GZip {

    public static byte[] compress(String text, Charset charset) {
        if (text == null) {
            throw new NullPointerException("text is null.");
        }

        if (charset == null) {
            charset = Charset.defaultCharset();
        }

        try {
            return compress(text.getBytes(charset));
        } catch (Exception e) {
            throw new ZLib.ZLibException(charset.toString(), e);
        }
    }

    /**
     * 压缩
     *
     * @param data
     *            待压缩数据
     * @return byte[] 压缩后的数据
     */
    public static byte[] compress(byte[] data) {
        if (data == null || data.length == 0) {
            return null;
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip;
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(data);
            gzip.close();
        } catch ( Exception e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }

    public static byte[] uncompress(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        try {
            GZIPInputStream ungzip = new GZIPInputStream(in);
            byte[] buffer = new byte[256];
            int n;
            while ((n = ungzip.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }

}
