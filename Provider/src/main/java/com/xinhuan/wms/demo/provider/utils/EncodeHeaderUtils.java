package com.xinhuan.wms.demo.provider.utils;

public class EncodeHeaderUtils {

    /**
     * Header头中包含了中文 需要转换
     * @param headInfo
     * @return
     */
    public static String encodeHeadInfo( String headInfo ) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0, length = headInfo.length(); i < length; i++) {
            char c = headInfo.charAt(i);
            if (c <= '\u001f' || c >= '\u007f') {
                stringBuffer.append( String.format ("\\u%04x", (int)c) );
            } else {
                stringBuffer.append(c);
            }
        }
        return stringBuffer.toString();
    }

}
