package com.nhnacademy.store99.front.common.decoder;

import com.fasterxml.jackson.databind.type.TypeFactory;
import com.nhnacademy.store99.front.common.response.CommonResponse;
import feign.FeignException;
import feign.Response;
import feign.codec.Decoder;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.core.ResolvableType;

public class CommonResponseDecoder extends ResponseEntityDecoder {

    public CommonResponseDecoder(Decoder decoder) {
        super(decoder);
    }

    @Override
    public Object decode(final Response response, Type type) throws IOException, FeignException {
        Class<?> returnType = TypeFactory.rawClass(type);
        ResolvableType forClassWithGenerics = ResolvableType.forClassWithGenerics(CommonResponse.class, returnType);

        try {
            return ((CommonResponse<?>) super.decode(response, forClassWithGenerics.getType())).getResult();
        } catch (Exception e) {
            return super.decode(response, type);
        }

//        if (isParameterizeCommonResponse(type)) {
//            type = ((ParameterizedType) type).getActualTypeArguments()[0];
//            Object decodedObject = super.decode(response, type);
//
//            if (decodedObject instanceof CommonResponse) {
//                return ((CommonResponse<?>) decodedObject).getResult();
//            }
//        }
//
//        return super.decode(response, type);
    }

    private boolean isParameterizeCommonResponse(Type type) {
        if (type instanceof ParameterizedType) {
            return isCommonResponse(((ParameterizedType) type).getRawType());
        }
        return false;
    }

    private boolean isCommonResponse(Type type) {
        if (type instanceof Class) {
            Class<?> c = (Class<?>) type;
            return CommonResponse.class.isAssignableFrom(c);
        }
        return false;
    }
}