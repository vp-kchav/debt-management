package com.mcm.demo.model;

import java.util.HashMap;
import java.util.Map;

public class ApplicationConstants {

    public static final String DEBT_FIELD = "debts";
    public static final String CLIENT_CONTEXT = "client_context";
    public static final String DATE_FORMAT = "yyyyMMdd";
    public static final String DISPLAY_DATE_FORMAT = "MM/dd/yyyy";
    public static final String FILE_SUPPORT_SEPARATION = ",";
    public static final String OUTPUT_FILE_NAME = "/consumer.xml";

    public static Map<String, FileType> FILE_TYPE_PROCESSOR_MAP = new HashMap<>();
    static {
        FILE_TYPE_PROCESSOR_MAP.put("xml", FileType.XML);
        FILE_TYPE_PROCESSOR_MAP.put("json", FileType.JSON);
    }

}
