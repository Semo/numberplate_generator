package services;

import java.io.IOException;

import components.NumberPlateUtility;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public class NumberPlateMessageConverter extends AbstractHttpMessageConverter<Object> {

	private static final MediaType KRYO = new MediaType("application", "x-kryo");

	private static final ThreadLocal<Kryo> kryoThreadLocal = new ThreadLocal<Kryo>() {
		@Override
		protected Kryo initialValue() {
			Kryo kryo = new Kryo();
			kryo.register(NumberPlateUtility.class, 1);
			return kryo;
		}
	};

	public NumberPlateMessageConverter() {
		super(KRYO);
	}

	@Override
	protected boolean supports(Class<?> clazz) {
		return Object.class.isAssignableFrom(clazz);
	}

	@Override
	protected Object readInternal(Class<? extends Object> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		Input input = new Input(inputMessage.getBody());
		return kryoThreadLocal.get().readClassAndObject(input);
	}

	@Override
	protected void writeInternal(Object object, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		Output output = new Output(outputMessage.getBody());
		kryoThreadLocal.get().writeClassAndObject(output, object);
		output.flush();
	}

	@Override
	protected MediaType getDefaultContentType(Object object) {
		return KRYO;
	}

}
