package com.simoncat.framework.serializer.api;

import java.io.InputStream;
import java.io.OutputStream;

import javax.annotation.Nonnull;

/**
 * The API provided for high performance serialization and deserialization Services
 * 
 * @author Simon LUO (printscroll@163.com)
 *
 */
public interface SimoncatSerializer {

	/**
	 * <p>
	 * Serialize target object into a byte array.
	 * </p>
	 * 
	 * <p>
	 * Please use this API as following example:
	 * </p>
	 * 
	 * <pre>
	 * &#064;Autowired
	 * private SimoncatSerializer serializer;
	 * 
	 * public yourMethod(YourClass yourObject) {
	 *   ...
	 *   byte[] byteArrays = serializer.serialize(yourObject);
	 *   ...
	 * }
	 * </pre>
	 * 
	 * <p>
	 * Caution: This API can only work with {@link SimoncatSerializer#deserialize(byte[], Class)}
	 * </p>
	 * 
	 * <pre>
	 * &#064;Autowired
	 * private SimoncatSerializer serializer;
	 * 
	 * public yourMethod(YourClass yourObject) {
	 *   ...
	 *   byte[] byteArrays = serializer.serialize(yourObject);
	 *   ...
	 *   YourClass deserializedObject = serializer.deserialize(byteArrays, YourClass.class);
	 *   ...
	 * }
	 * </pre>
	 * 
	 * @param o
	 *            The target object which will be serialized.
	 * @return Byte Arrays
	 */
	byte[] serialize(@Nonnull Object o);

	/**
	 * <p>
	 * Serialize target object along with its class information into a byte array.
	 * </p>
	 * 
	 * <p>
	 * Please use this API as following example:
	 * </p>
	 * 
	 * <pre>
	 * &#064;Autowired
	 * private SimoncatSerializer serializer;
	 * 
	 * public yourMethod(YourClass yourObject) {
	 *   ...
	 *   byte[] byteArrays = serializer.serializeWithClass(yourObject);
	 *   ...
	 * }
	 * </pre>
	 * 
	 * <p>
	 * Caution: This API can only work with {@link SimoncatSerializer#deserializeWithClass(byte[])}
	 * </p>
	 * 
	 * <pre>
	 * &#064;Autowired
	 * private SimoncatSerializer serializer;
	 * 
	 * public yourMethod(YourClass yourObject) {
	 *   ...
	 *   byte[] byteArrays = serializer.serializeWithClass(yourObject);
	 *   ...
	 *   // Better to have type validations by yourselves.
	 *   YourClass deserializedObject = (YourClass)serializer.deserializeWithClass(byteArrays);
	 *   ...
	 * }
	 * </pre>
	 * 
	 * @param o
	 *            The target object which will be serialized.
	 * @return Byte Arrays
	 */
	byte[] serializeWithClass(@Nonnull Object o);

	/**
	 * <p>
	 * Serialize target object into an output stream.
	 * </p>
	 * 
	 * <p>
	 * Please use this API as following example:
	 * </p>
	 * 
	 * <pre>
	 * &#064;Autowired
	 * private SimoncatSerializer serializer;
	 * 
	 * public yourMethod(YourClass yourObject) {
	 *   ...
	 *   // This is just a sample, you can create {@link OutputStream} by yourselves.
	 *   OutputStream os = new FileOutputStream("your_file_path");
	 *   serializer.serializeIntoStream(yourObject, os);
	 *   ...
	 * }
	 * </pre>
	 * 
	 * <p>
	 * Caution: This API can only work with {@link SimoncatSerializer#deserialize(InputStream, Class)}
	 * </p>
	 * 
	 * <pre>
	 * &#064;Autowired
	 * private SimoncatSerializer serializer;
	 * 
	 * public yourMethod(YourClass yourObject) {
	 *   ...
	 *   // This is just a sample, you can create {@link OutputStream} by yourselves.
	 *   OutputStream os = new FileOutputStream("your_file_path");
	 *   serializer.serializeIntoStream(yourObject, os);
	 *   ...
	 *   // This is just a sample, you can create {@link InputStream} by yourselves.
	 *   InputStream is = new FileInputStream("your_file_path");
	 *   YourClass deserializedObject = serializer.deserialize(is, YourClass.class);
	 *   ...
	 * }
	 * </pre>
	 * 
	 * @param o
	 *            The target object which will be serialized.
	 * @param os
	 *            The OutputStream which will hold the serialized data.
	 */
	void serializeIntoStream(@Nonnull Object o, @Nonnull OutputStream os);

	/**
	 * <p>
	 * Serialize target object along with its class information into an output stream.
	 * </p>
	 * 
	 * <p>
	 * Please use this API as following example:
	 * </p>
	 * 
	 * <pre>
	 * &#064;Autowired
	 * private SimoncatSerializer serializer;
	 * 
	 * public yourMethod(YourClass yourObject) {
	 *   ...
	 *   // This is just a sample, you can create {@link OutputStream} by yourselves.
	 *   OutputStream os = new FileOutputStream("your_file_path");
	 *   serializer.serializeWithClassIntoStream(yourObject, os);
	 *   ...
	 * }
	 * </pre>
	 * 
	 * <p>
	 * Caution: This API can only work with {@link SimoncatSerializer#deserializeWithClass(InputStream)}
	 * </p>
	 * 
	 * <pre>
	 * &#064;Autowired
	 * private SimoncatSerializer serializer;
	 * 
	 * public yourMethod(YourClass yourObject) {
	 *   ...
	 *   // This is just a sample, you can create {@link OutputStream} by yourselves.
	 *   OutputStream os = new FileOutputStream("your_file_path");
	 *   serializer.serializeWithClassIntoStream(yourObject, os);
	 *   ...
	 *   // This is just a sample, you can create {@link InputStream} by yourselves.
	 *   InputStream is = new FileInputStream("your_file_path");
	 *   // Better to have type validations by yourselves.
	 *   YourClass deserializedObject = (YourClass)serializer.deserializeWithClass(is);
	 *   ...
	 * }
	 * </pre>
	 * 
	 * @param o
	 *            The target object which will be serialized.
	 * @param os
	 *            The OutputStream which will hold the serialized data.
	 */
	void serializeWithClassIntoStream(@Nonnull Object o, @Nonnull OutputStream os);

	/**
	 * <p>
	 * Deserialize an object of given type from a byte array.
	 * </p>
	 * 
	 * <p>
	 * Caution: This API can only work with {@link SimoncatSerializer#serialize(Object)}
	 * </p>
	 * 
	 * <p>
	 * Please use this API as following example:
	 * </p>
	 * 
	 * <pre>
	 * &#064;Autowired
	 * private SimoncatSerializer serializer;
	 * 
	 * public yourMethod(YourClass yourObject) {
	 *   ...
	 *   // This is only a sample for test, in order to prepare the byte array.
	 *   byte[] byteArrays = serializer.serialize(yourObject);
	 *   ...
	 *   YourClass deserializedObject = serializer.deserialize(byteArrays, YourClass.class);
	 *   ...
	 * }
	 * </pre>
	 * 
	 * @param bytes
	 *            The byte array which we are going to deserialize.
	 * @param clazz
	 *            The target object class.
	 * @return The Object with specific class type.
	 */
	<T> T deserialize(@Nonnull byte[] bytes, @Nonnull Class<T> clazz);

	/**
	 * <p>
	 * Deserialize an object and its class information from a byte array.
	 * </p>
	 * 
	 * <p>
	 * Caution: This API can only work with {@link SimoncatSerializer#serializeWithClass(Object)}
	 * </p>
	 * 
	 * <p>
	 * Please use this API as following example:
	 * </p>
	 * 
	 * <pre>
	 * &#064;Autowired
	 * private SimoncatSerializer serializer;
	 * 
	 * public yourMethod(YourClass yourObject) {
	 *   ...
	 *   byte[] byteArrays = serializer.serializeWithClass(yourObject);
	 *   ...
	 *   // Better to have type validations by yourselves.
	 *   YourClass deserializedObject = (YourClass)serializer.deserializeWithClass(byteArrays);
	 *   ...
	 * }
	 * </pre>
	 * 
	 * @param bytes
	 *            The byte array which we are going to deserialize.
	 * @return
	 */
	Object deserializeWithClass(@Nonnull byte[] bytes);

	/**
	 * <p>
	 * Deserialize an object of given type from an input stream.
	 * </p>
	 * 
	 * <p>
	 * Caution: This API can only work with {@link SimoncatSerializer#serialize(OutputStream, Class)}
	 * </p>
	 * 
	 * <p>
	 * Please use this API as following example:
	 * </p>
	 * 
	 * <pre>
	 * &#064;Autowired
	 * private SimoncatSerializer serializer;
	 * 
	 * public yourMethod(YourClass yourObject) {
	 *   ...
	 *   // This is just a sample, you can create {@link OutputStream} by yourselves.
	 *   OutputStream os = new FileOutputStream("your_file_path");
	 *   serializer.serializeIntoStream(yourObject, os);
	 *   ...
	 *   // This is just a sample, you can create {@link InputStream} by yourselves.
	 *   InputStream is = new FileInputStream("your_file_path");
	 *   YourClass deserializedObject = serializer.deserialize(is, YourClass.class);
	 *   ...
	 * }
	 * </pre>
	 * 
	 * @param is
	 *            The InputStream which will hold the deserialized data.
	 * @param clazz
	 *            The specific target class.
	 * @return
	 */
	<T> T deserialize(@Nonnull InputStream is, @Nonnull Class<T> clazz);

	/**
	 * <p>
	 * Deserialize an object and its class information from an input stream.
	 * </p>
	 * 
	 * <p>
	 * Caution: This API can only work with {@link SimoncatSerializer#serializeWithClass(OutputStream)}
	 * </p>
	 * 
	 * <p>
	 * Please use this API as following example:
	 * </p>
	 * 
	 * <pre>
	 * &#064;Autowired
	 * private SimoncatSerializer serializer;
	 * 
	 * public yourMethod(YourClass yourObject) {
	 *   ...
	 *   // This is just a sample, you can create {@link OutputStream} by yourselves.
	 *   OutputStream os = new FileOutputStream("your_file_path");
	 *   serializer.serializeWithClassIntoStream(yourObject, os);
	 *   ...
	 *   // This is just a sample, you can create {@link InputStream} by yourselves.
	 *   InputStream is = new FileInputStream("your_file_path");
	 *   // Better to have type validations by yourselves.
	 *   YourClass deserializedObject = (YourClass)serializer.deserializeWithClass(is);
	 *   ...
	 * }
	 * </pre>
	 * 
	 * @param is
	 *            The InputStream which will hold the deserialized data.
	 * @return
	 */
	Object deserializeWithClass(@Nonnull InputStream is);
}
