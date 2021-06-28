package com.odc.gid.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

public class GetStackTraceUtil {
    public static String getStacktraceString(Exception e){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String sStackTrace = sw.toString(); // stack trace as a string
        return(sStackTrace);
    }
}
