package com.intemanagement.sdk.compression;

import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * @Author panyunfeng
 * @Date 2019-11-05
 */
public class ZLib {

    /**
     * 压缩
     *
     * @param text 待压缩数据
     * @param charset text 转字节码数组的编码
     * @return byte[] 压缩后的数据
     */
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
            throw new ZLibException(charset.toString(), e);
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
        return compress(data, Deflater.DEFAULT_COMPRESSION);
    }

    /**
     * 压缩
     *
     * @param data
     *            待压缩数据
     * @return byte[] 压缩后的数据
     */
    public static byte[] compress(byte[] data, int level) {
        Deflater compresser = new Deflater(level);
        try {
            compresser.reset();
            compresser.setInput(data);
            compresser.finish();
            ByteArrayOutputStream bos = new ByteArrayOutputStream(data.length);
            byte[] buf = new byte[1024];
            while (!compresser.finished()) {
                int i = compresser.deflate(buf);
                bos.write(buf, 0, i);
            }
            return bos.toByteArray();
        } catch (Exception e) {
            throw new ZLibException("compress failed.", e);
        } finally {
            compresser.end();
        }
    }

    /**
     * 解压缩
     *
     * @param data
     *            待压缩的数据
     * @return byte[] 解压缩后的数据
     */
    public static byte[] decompress(byte[] data) {
        Inflater decompresser = new Inflater();
        try {
            decompresser.reset();
            decompresser.setInput(data);
            ByteArrayOutputStream o = new ByteArrayOutputStream(data.length);
            byte[] buf = new byte[1024];
            while (!decompresser.finished()) {
                int i = decompresser.inflate(buf);
                o.write(buf, 0, i);
            }
            return o.toByteArray();
        } catch (Exception e) {
            throw new ZLibException("decompress failed.", e);
        } finally {
            decompresser.end();
        }
    }

    /**
     * @Author panyunfeng
     */
    public static final class ZLibException extends RuntimeException {
        public ZLibException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
