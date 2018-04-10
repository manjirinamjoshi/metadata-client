package com.pac.msm.client;

import java.io.Reader;
import java.security.InvalidParameterException;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * An Utility class is used to serialize/deserialize between Java object and
 * JSON string. Implemented with Jackson ObjectMapper.
 *
 * @author Eric Jin
 */
public class JsonUtil {
    private static JsonUtil thisInstance = new JsonUtil();

    public static final String JSON_VALUE_TRUE = "true";
    public static final String JSON_VALUE_FALSE = "false";
    private static final String SERIALIZE_ERROR_MSG = "An error happened while converting java object to json ";
    private static final String DESERIALIZE_ERROR_MSG = "An error happened while converting json to java object ";

    private ObjectMapper om;

    public static JsonUtil getInstance() {
        return thisInstance;
    }

    /**
     * Serialize java object to JSON string.
     *
     * @param object
     * @return
     * @throws JsonConversionException
     */
    public String convertToJSON(Object object)
            throws JsonProcessingException {

        if (object == null)
            throw new InvalidParameterException("Parameter object is null!");
        try {
            return om.writeValueAsString(object);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(SERIALIZE_ERROR_MSG
                    + object.getClass().getName(), ex);
        }
    }

    /**
     * Convert a JSON string to certain type of Java object.
     *
     * @param <T>  Class type
     * @param json a JSON string to be deserialized
     * @param type the Java object type
     * @return a Java object
     * @throws JsonConversionException
     */
    public <T> T parseJSONString(String json, Class<T> type) {

        if (json == null)
            throw new InvalidParameterException("Parameter json is null!");
        if (type == null)
            throw new InvalidParameterException("Parameter type is null!");
        try {
            return om.readValue(json, type);
        } catch (Exception ex) {
            throw new RuntimeException(DESERIALIZE_ERROR_MSG +
                    "type=" + type.getTypeName() +
                            "value=" + json +
                            ", Exception=" + ex.getMessage()
                            + ", json=" + json, ex);
        }
    }

    /**
     *
     * @param sourceFile
     * @param type
     * @param <T>
     * @return
     * @throws JsonConversionException
     */
    public <T> T parseJson(Reader sourceFile, Class<T> type) {
        if (sourceFile == null)
            throw new InvalidParameterException("Parameter sourceFile is null!");
        if (type == null)
            throw new InvalidParameterException("Parameter type is null!");
        try {
            return om.readValue(sourceFile, type);
        } catch (Exception ex) {
            throw new RuntimeException(DESERIALIZE_ERROR_MSG
                    + type.getName() + ", Exception=" + ex.getMessage()
                    , ex);
        }
    }

    private JsonFactory strictedJsonFactory;
    private JsonFactory flexibleJsonFactory;

    public JsonFactory getFlexibleJsonFactory() {
        return flexibleJsonFactory;
    }

    public JsonFactory getStrictedJsonFactory() {
        return strictedJsonFactory;
    }

    private JsonUtil() {
        om = new ObjectMapper();
        om.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        om.configure(MapperFeature.AUTO_DETECT_IS_GETTERS, false).configure(
                MapperFeature.AUTO_DETECT_FIELDS, false);
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        om.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        om.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        om.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        om.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

        strictedJsonFactory = new JsonFactory();
        flexibleJsonFactory = new JsonFactory();
        flexibleJsonFactory.configure(
                com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES,
                true);
        flexibleJsonFactory.configure(
                com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_SINGLE_QUOTES,
                true);
    }
    
    /**
     * Convenience method for doing two-step conversion from given value, into instance of given value type, if (but only if!) conversion is needed. If given value is already of requested type, value is returned as is.
     * 
     * @param p_object
     * @param p_type
     * @return
     * @throws JsonConversionException
     */
    public <T> T convertValue(Object p_object, Class<T> p_type) 
	{
		return om.convertValue(p_object, p_type);
	}
}