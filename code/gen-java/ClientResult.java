/**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.3)", date = "2019-03-04")
public class ClientResult implements org.apache.thrift.TBase<ClientResult, ClientResult._Fields>, java.io.Serializable, Cloneable, Comparable<ClientResult> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ClientResult");

  private static final org.apache.thrift.protocol.TField FILE_ORDER_FIELD_DESC = new org.apache.thrift.protocol.TField("fileOrder", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField TIME_FIELD_DESC = new org.apache.thrift.protocol.TField("time", org.apache.thrift.protocol.TType.I32, (short)2);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new ClientResultStandardSchemeFactory());
    schemes.put(TupleScheme.class, new ClientResultTupleSchemeFactory());
  }

  public String fileOrder; // required
  public int time; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    FILE_ORDER((short)1, "fileOrder"),
    TIME((short)2, "time");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // FILE_ORDER
          return FILE_ORDER;
        case 2: // TIME
          return TIME;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __TIME_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.FILE_ORDER, new org.apache.thrift.meta_data.FieldMetaData("fileOrder", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.TIME, new org.apache.thrift.meta_data.FieldMetaData("time", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ClientResult.class, metaDataMap);
  }

  public ClientResult() {
  }

  public ClientResult(
    String fileOrder,
    int time)
  {
    this();
    this.fileOrder = fileOrder;
    this.time = time;
    setTimeIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ClientResult(ClientResult other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetFileOrder()) {
      this.fileOrder = other.fileOrder;
    }
    this.time = other.time;
  }

  public ClientResult deepCopy() {
    return new ClientResult(this);
  }

  @Override
  public void clear() {
    this.fileOrder = null;
    setTimeIsSet(false);
    this.time = 0;
  }

  public String getFileOrder() {
    return this.fileOrder;
  }

  public ClientResult setFileOrder(String fileOrder) {
    this.fileOrder = fileOrder;
    return this;
  }

  public void unsetFileOrder() {
    this.fileOrder = null;
  }

  /** Returns true if field fileOrder is set (has been assigned a value) and false otherwise */
  public boolean isSetFileOrder() {
    return this.fileOrder != null;
  }

  public void setFileOrderIsSet(boolean value) {
    if (!value) {
      this.fileOrder = null;
    }
  }

  public int getTime() {
    return this.time;
  }

  public ClientResult setTime(int time) {
    this.time = time;
    setTimeIsSet(true);
    return this;
  }

  public void unsetTime() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __TIME_ISSET_ID);
  }

  /** Returns true if field time is set (has been assigned a value) and false otherwise */
  public boolean isSetTime() {
    return EncodingUtils.testBit(__isset_bitfield, __TIME_ISSET_ID);
  }

  public void setTimeIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __TIME_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case FILE_ORDER:
      if (value == null) {
        unsetFileOrder();
      } else {
        setFileOrder((String)value);
      }
      break;

    case TIME:
      if (value == null) {
        unsetTime();
      } else {
        setTime((Integer)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case FILE_ORDER:
      return getFileOrder();

    case TIME:
      return getTime();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case FILE_ORDER:
      return isSetFileOrder();
    case TIME:
      return isSetTime();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof ClientResult)
      return this.equals((ClientResult)that);
    return false;
  }

  public boolean equals(ClientResult that) {
    if (that == null)
      return false;

    boolean this_present_fileOrder = true && this.isSetFileOrder();
    boolean that_present_fileOrder = true && that.isSetFileOrder();
    if (this_present_fileOrder || that_present_fileOrder) {
      if (!(this_present_fileOrder && that_present_fileOrder))
        return false;
      if (!this.fileOrder.equals(that.fileOrder))
        return false;
    }

    boolean this_present_time = true;
    boolean that_present_time = true;
    if (this_present_time || that_present_time) {
      if (!(this_present_time && that_present_time))
        return false;
      if (this.time != that.time)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_fileOrder = true && (isSetFileOrder());
    list.add(present_fileOrder);
    if (present_fileOrder)
      list.add(fileOrder);

    boolean present_time = true;
    list.add(present_time);
    if (present_time)
      list.add(time);

    return list.hashCode();
  }

  @Override
  public int compareTo(ClientResult other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetFileOrder()).compareTo(other.isSetFileOrder());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetFileOrder()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.fileOrder, other.fileOrder);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetTime()).compareTo(other.isSetTime());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTime()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.time, other.time);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("ClientResult(");
    boolean first = true;

    sb.append("fileOrder:");
    if (this.fileOrder == null) {
      sb.append("null");
    } else {
      sb.append(this.fileOrder);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("time:");
    sb.append(this.time);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class ClientResultStandardSchemeFactory implements SchemeFactory {
    public ClientResultStandardScheme getScheme() {
      return new ClientResultStandardScheme();
    }
  }

  private static class ClientResultStandardScheme extends StandardScheme<ClientResult> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, ClientResult struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // FILE_ORDER
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.fileOrder = iprot.readString();
              struct.setFileOrderIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // TIME
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.time = iprot.readI32();
              struct.setTimeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, ClientResult struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.fileOrder != null) {
        oprot.writeFieldBegin(FILE_ORDER_FIELD_DESC);
        oprot.writeString(struct.fileOrder);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(TIME_FIELD_DESC);
      oprot.writeI32(struct.time);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class ClientResultTupleSchemeFactory implements SchemeFactory {
    public ClientResultTupleScheme getScheme() {
      return new ClientResultTupleScheme();
    }
  }

  private static class ClientResultTupleScheme extends TupleScheme<ClientResult> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, ClientResult struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetFileOrder()) {
        optionals.set(0);
      }
      if (struct.isSetTime()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetFileOrder()) {
        oprot.writeString(struct.fileOrder);
      }
      if (struct.isSetTime()) {
        oprot.writeI32(struct.time);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, ClientResult struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.fileOrder = iprot.readString();
        struct.setFileOrderIsSet(true);
      }
      if (incoming.get(1)) {
        struct.time = iprot.readI32();
        struct.setTimeIsSet(true);
      }
    }
  }

}

