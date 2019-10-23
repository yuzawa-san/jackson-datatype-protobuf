package com.hubspot.jackson.datatype.protobuf.builtin.deserializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Message;
import com.google.protobuf.Message.Builder;
import com.hubspot.jackson.datatype.protobuf.ProtobufDeserializer;
import com.hubspot.jackson.datatype.protobuf.ProtobufJacksonConfig;

public class WrappedPrimitiveDeserializer<T extends Message, V extends Builder> extends ProtobufDeserializer<T, V> {

  public WrappedPrimitiveDeserializer(Class<T> wrapperType, ProtobufJacksonConfig config) {
    super(wrapperType, config);
  }

  @Override
  protected void populate(V builder, JsonParser parser, DeserializationContext context) throws IOException {
    FieldDescriptor field = builder.getDescriptorForType().findFieldByName("value");
    Object value = readValue(builder, field, null, parser, context);
    builder.setField(field, value);
  }
}
