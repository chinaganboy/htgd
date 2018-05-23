package htgd.com.radiocontrol.visualaudio.utils;

import android.util.Log;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DoubleConverter;
import com.thoughtworks.xstream.converters.basic.FloatConverter;
import com.thoughtworks.xstream.converters.basic.IntConverter;
import com.thoughtworks.xstream.converters.basic.LongConverter;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * xml解析工具类
 *
 * @author FireAnt（http://my.oschina.net/LittleDY）
 * @version 创建时间：2014年9月27日 下午2:04:19
 */

public class XmlUtils {

    private final static String TAG = XmlUtils.class.getSimpleName();

    /**
     * 将一个xml流转换为bean实体类
     *
     * @param type 类类型
     * @param is   输入流
     * @return 相应类类型的实例
     */
    @SuppressWarnings("unchecked")
    public static <T> T toBean(Class<T> type, InputStream is) {
        XStream xmStream = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-", "_")));

        // 设置可忽略为在javabean类中定义的界面属性
        xmStream.ignoreUnknownElements();
        xmStream.registerConverter(new MyIntCoverter());
        xmStream.registerConverter(new MyLongCoverter());
        xmStream.registerConverter(new MyFloatCoverter());
        xmStream.registerConverter(new MyDoubleCoverter());
        xmStream.processAnnotations(type);
        T obj = null;
        try {
            obj = (T) xmStream.fromXML(is);
        } catch (Exception e) {
            Log.e(TAG, "parse xml error: " + e.getMessage());
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                is = null;
            }
        }
        return obj;
    }

    /**
     * 将一个byte数组转换为bean实体类
     *
     * @param type  类类型
     * @param bytes byte数组
     * @param <T>   相应类类型的实例
     * @return 相应类类型的实例
     */
    public static <T> T toBean(Class<T> type, byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        return toBean(type, new ByteArrayInputStream(bytes));
    }

    private static class MyIntCoverter extends IntConverter {

        @Override
        public Object fromString(String str) {
            int value;
            try {
                value = (Integer) super.fromString(str);
            } catch (Exception e) {
                value = 0;
            }
            return value;
        }

        @Override
        public String toString(Object obj) {
            return super.toString(obj);
        }
    }

    private static class MyLongCoverter extends LongConverter {
        @Override
        public Object fromString(String str) {
            long value;
            try {
                value = (Long) super.fromString(str);
            } catch (Exception e) {
                value = 0;
            }
            return value;
        }

        @Override
        public String toString(Object obj) {
            return super.toString(obj);
        }
    }

    private static class MyFloatCoverter extends FloatConverter {
        @Override
        public Object fromString(String str) {
            float value;
            try {
                value = (Float) super.fromString(str);
            } catch (Exception e) {
                value = 0;
            }
            return value;
        }

        @Override
        public String toString(Object obj) {
            return super.toString(obj);
        }
    }

    private static class MyDoubleCoverter extends DoubleConverter {
        @Override
        public Object fromString(String str) {
            double value;
            try {
                value = (Double) super.fromString(str);
            } catch (Exception e) {
                value = 0;
            }
            return value;
        }

        @Override
        public String toString(Object obj) {
            return super.toString(obj);
        }
    }

    /**
     * javabean转string
     *
     * @param obj 类型
     * @return 序列化后的字符串值
     */
    public static String toXml(Object obj) {
        XStream xstream = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-", "_")));
        // 识别obj类中的注解
        xstream.processAnnotations(obj.getClass());
        /*
         // 以压缩的方式输出XML
         StringWriter sw = new StringWriter();
         xstream.marshal(obj, new CompactWriter(sw));
         return sw.toString();
         */

        // 以格式化的方式输出XML
        return xstream.toXML(obj);
    }

}
