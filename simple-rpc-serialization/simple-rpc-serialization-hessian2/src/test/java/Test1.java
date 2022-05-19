import lombok.Data;
import lombok.experimental.Accessors;
import simple.rpc.serialization.hessian2.Hessian2Helper;

import java.io.Serializable;

/**
 * @author guochuang
 * @version 6.3
 * @date 2022/05/19 17:36
 */
public class Test1 {


	@Data
	@Accessors(chain = true)
	static class temp implements Serializable {

		private String name;

		private int age;

	}


	public static void main(String[] args) {

		temp emp = new temp().setAge(1).setName("郭闯");

		byte[] serialize = Hessian2Helper.serialize(emp);

		System.out.println(serialize.length);

		temp deserialize = Hessian2Helper.deserialize(serialize);

		System.out.println(deserialize.toString());

	}

}
