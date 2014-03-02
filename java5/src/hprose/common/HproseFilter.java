/**********************************************************\
|                                                          |
|                          hprose                          |
|                                                          |
| Official WebSite: http://www.hprose.com/                 |
|                   http://www.hprose.net/                 |
|                   http://www.hprose.org/                 |
|                                                          |
\**********************************************************/
/**********************************************************\
 *                                                        *
 * HproseFilter.java                                      *
 *                                                        *
 * hprose filter interface for Java.                      *
 *                                                        *
 * LastModified: Mar 1, 2014                              *
 * Author: Ma Bingyao <andot@hprose.com>                  *
 *                                                        *
\**********************************************************/
package hprose.common;

import java.nio.ByteBuffer;

public interface HproseFilter {
    ByteBuffer inputFilter(ByteBuffer istream);
    ByteBuffer outputFilter(ByteBuffer ostream);
}