package com.hubspot.jackson.datatype.protobuf.builtin.deserializers;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Message;
import com.google.protobuf.Struct;
import com.hubspot.jackson.datatype.protobuf.ProtobufDeserializer;
import com.hubspot.jackson.datatype.protobuf.ProtobufJacksonConfig;

public class StructDeserializer extends ProtobufDeserializer<Struct, Struct.Builder> {
  private static final FieldDescriptor FIELDS_FIELD = Struct.getDescriptor().findFieldByName("fields");

  public StructDeserializer(ProtobufJacksonConfig config) {
    super(Struct.class, config);
  }

  @Override
  protected void populate(
          Struct.Builder builder,
          JsonParser parser,
          DeserializationContext context
  ) throws IOException {
    List<Message> entries = readMap(builder, FIELDS_FIELD, parser, context);
    for (Message entry : entries) {
      builder.addRepeatedField(FIELDS_FIELD, entry);
    }
  }
}
