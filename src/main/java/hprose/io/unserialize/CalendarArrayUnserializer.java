/**********************************************************\
|                                                          |
|                          hprose                          |
|                                                          |
| Official WebSite: http://www.hprose.com/                 |
|                   http://www.hprose.org/                 |
|                                                          |
\**********************************************************/
/**********************************************************\
 *                                                        *
 * CalendarArrayUnserializer.java                         *
 *                                                        *
 * Calendar array unserializer class for Java.            *
 *                                                        *
 * LastModified: Apr 17, 2016                             *
 * Author: Ma Bingyao <andot@hprose.com>                  *
 *                                                        *
\**********************************************************/

package hprose.io.unserialize;

import static hprose.io.HproseTags.TagList;
import static hprose.io.HproseTags.TagNull;
import static hprose.io.HproseTags.TagOpenbrace;
import static hprose.io.HproseTags.TagRef;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import java.util.Calendar;

final class CalendarArrayUnserializer implements Unserializer {

    public final static CalendarArrayUnserializer instance = new CalendarArrayUnserializer();

    final static Calendar[] read(Reader reader, ByteBuffer buffer) throws IOException {
        int tag = buffer.get();
        switch (tag) {
            case TagNull: return null;
            case TagList: {
                int count = ValueReader.readInt(buffer, TagOpenbrace);
                Calendar[] a = new Calendar[count];
                reader.refer.set(a);
                for (int i = 0; i < count; ++i) {
                    a[i] = CalendarUnserializer.read(reader, buffer);
                }
                buffer.get();
                return a;
            }
            case TagRef: return (Calendar[])reader.readRef(buffer);
            default: throw ValueReader.castError(reader.tagToString(tag), Array.class);
        }
    }

    final static Calendar[] read(Reader reader, InputStream stream) throws IOException {
        int tag = stream.read();
        switch (tag) {
            case TagNull: return null;
            case TagList: {
                int count = ValueReader.readInt(stream, TagOpenbrace);
                Calendar[] a = new Calendar[count];
                reader.refer.set(a);
                for (int i = 0; i < count; ++i) {
                    a[i] = CalendarUnserializer.read(reader, stream);
                }
                stream.read();
                return a;
            }
            case TagRef: return (Calendar[])reader.readRef(stream);
            default: throw ValueReader.castError(reader.tagToString(tag), Array.class);
        }
    }

    public final Object read(Reader reader, ByteBuffer buffer, Class<?> cls, Type type) throws IOException {
        return read(reader, buffer);
    }

    public final Object read(Reader reader, InputStream stream, Class<?> cls, Type type) throws IOException {
        return read(reader, stream);
    }

}
