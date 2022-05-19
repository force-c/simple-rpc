package simple.rpc.serialization.hessian2;

import com.caucho.hessian.io.AbstractHessianInput;
import com.caucho.hessian.io.AbstractHessianOutput;
import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.caucho.hessian.io.SerializerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author guochuang
 * @version 6.3
 * @date 2022/05/19 17:16
 */
public class Hessian2Helper {

	private static final ThreadLocal<SerializerFactory> SERIALIZER_FACTORY =
			ThreadLocal.withInitial(SerializerFactory::new);


	public static byte[] serialize(Object obj) {

		ByteArrayOutputStream os = new ByteArrayOutputStream();
		AbstractHessianOutput output = new Hessian2Output(os);
		output.setSerializerFactory(SERIALIZER_FACTORY.get());
		try {
			output.writeObject(obj);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				output.close();
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return os.toByteArray();
	}


	public static  <T> T deserialize(byte[] bytes) {

		ByteArrayInputStream is = new ByteArrayInputStream(bytes);
		AbstractHessianInput in = new Hessian2Input(is);
		in.setSerializerFactory(SERIALIZER_FACTORY.get());

		T value = null;
		try {
			value = (T) in.readObject();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				in.close();
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return value;
	}

}
