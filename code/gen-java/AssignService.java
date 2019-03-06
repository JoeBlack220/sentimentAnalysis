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
@Generated(value = "Autogenerated by Thrift Compiler (0.9.3)", date = "2019-03-05")
public class AssignService {

  public interface Iface {

    public ClientResult assign(String folderAddress, int mode) throws org.apache.thrift.TException;

  }

  public interface AsyncIface {

    public void assign(String folderAddress, int mode, org.apache.thrift.async.AsyncMethodCallback resultHandler) throws org.apache.thrift.TException;

  }

  public static class Client extends org.apache.thrift.TServiceClient implements Iface {
    public static class Factory implements org.apache.thrift.TServiceClientFactory<Client> {
      public Factory() {}
      public Client getClient(org.apache.thrift.protocol.TProtocol prot) {
        return new Client(prot);
      }
      public Client getClient(org.apache.thrift.protocol.TProtocol iprot, org.apache.thrift.protocol.TProtocol oprot) {
        return new Client(iprot, oprot);
      }
    }

    public Client(org.apache.thrift.protocol.TProtocol prot)
    {
      super(prot, prot);
    }

    public Client(org.apache.thrift.protocol.TProtocol iprot, org.apache.thrift.protocol.TProtocol oprot) {
      super(iprot, oprot);
    }

    public ClientResult assign(String folderAddress, int mode) throws org.apache.thrift.TException
    {
      send_assign(folderAddress, mode);
      return recv_assign();
    }

    public void send_assign(String folderAddress, int mode) throws org.apache.thrift.TException
    {
      assign_args args = new assign_args();
      args.setFolderAddress(folderAddress);
      args.setMode(mode);
      sendBase("assign", args);
    }

    public ClientResult recv_assign() throws org.apache.thrift.TException
    {
      assign_result result = new assign_result();
      receiveBase(result, "assign");
      if (result.isSetSuccess()) {
        return result.success;
      }
      throw new org.apache.thrift.TApplicationException(org.apache.thrift.TApplicationException.MISSING_RESULT, "assign failed: unknown result");
    }

  }
  public static class AsyncClient extends org.apache.thrift.async.TAsyncClient implements AsyncIface {
    public static class Factory implements org.apache.thrift.async.TAsyncClientFactory<AsyncClient> {
      private org.apache.thrift.async.TAsyncClientManager clientManager;
      private org.apache.thrift.protocol.TProtocolFactory protocolFactory;
      public Factory(org.apache.thrift.async.TAsyncClientManager clientManager, org.apache.thrift.protocol.TProtocolFactory protocolFactory) {
        this.clientManager = clientManager;
        this.protocolFactory = protocolFactory;
      }
      public AsyncClient getAsyncClient(org.apache.thrift.transport.TNonblockingTransport transport) {
        return new AsyncClient(protocolFactory, clientManager, transport);
      }
    }

    public AsyncClient(org.apache.thrift.protocol.TProtocolFactory protocolFactory, org.apache.thrift.async.TAsyncClientManager clientManager, org.apache.thrift.transport.TNonblockingTransport transport) {
      super(protocolFactory, clientManager, transport);
    }

    public void assign(String folderAddress, int mode, org.apache.thrift.async.AsyncMethodCallback resultHandler) throws org.apache.thrift.TException {
      checkReady();
      assign_call method_call = new assign_call(folderAddress, mode, resultHandler, this, ___protocolFactory, ___transport);
      this.___currentMethod = method_call;
      ___manager.call(method_call);
    }

    public static class assign_call extends org.apache.thrift.async.TAsyncMethodCall {
      private String folderAddress;
      private int mode;
      public assign_call(String folderAddress, int mode, org.apache.thrift.async.AsyncMethodCallback resultHandler, org.apache.thrift.async.TAsyncClient client, org.apache.thrift.protocol.TProtocolFactory protocolFactory, org.apache.thrift.transport.TNonblockingTransport transport) throws org.apache.thrift.TException {
        super(client, protocolFactory, transport, resultHandler, false);
        this.folderAddress = folderAddress;
        this.mode = mode;
      }

      public void write_args(org.apache.thrift.protocol.TProtocol prot) throws org.apache.thrift.TException {
        prot.writeMessageBegin(new org.apache.thrift.protocol.TMessage("assign", org.apache.thrift.protocol.TMessageType.CALL, 0));
        assign_args args = new assign_args();
        args.setFolderAddress(folderAddress);
        args.setMode(mode);
        args.write(prot);
        prot.writeMessageEnd();
      }

      public ClientResult getResult() throws org.apache.thrift.TException {
        if (getState() != org.apache.thrift.async.TAsyncMethodCall.State.RESPONSE_READ) {
          throw new IllegalStateException("Method call not finished!");
        }
        org.apache.thrift.transport.TMemoryInputTransport memoryTransport = new org.apache.thrift.transport.TMemoryInputTransport(getFrameBuffer().array());
        org.apache.thrift.protocol.TProtocol prot = client.getProtocolFactory().getProtocol(memoryTransport);
        return (new Client(prot)).recv_assign();
      }
    }

  }

  public static class Processor<I extends Iface> extends org.apache.thrift.TBaseProcessor<I> implements org.apache.thrift.TProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(Processor.class.getName());
    public Processor(I iface) {
      super(iface, getProcessMap(new HashMap<String, org.apache.thrift.ProcessFunction<I, ? extends org.apache.thrift.TBase>>()));
    }

    protected Processor(I iface, Map<String,  org.apache.thrift.ProcessFunction<I, ? extends  org.apache.thrift.TBase>> processMap) {
      super(iface, getProcessMap(processMap));
    }

    private static <I extends Iface> Map<String,  org.apache.thrift.ProcessFunction<I, ? extends  org.apache.thrift.TBase>> getProcessMap(Map<String,  org.apache.thrift.ProcessFunction<I, ? extends  org.apache.thrift.TBase>> processMap) {
      processMap.put("assign", new assign());
      return processMap;
    }

    public static class assign<I extends Iface> extends org.apache.thrift.ProcessFunction<I, assign_args> {
      public assign() {
        super("assign");
      }

      public assign_args getEmptyArgsInstance() {
        return new assign_args();
      }

      protected boolean isOneway() {
        return false;
      }

      public assign_result getResult(I iface, assign_args args) throws org.apache.thrift.TException {
        assign_result result = new assign_result();
        result.success = iface.assign(args.folderAddress, args.mode);
        return result;
      }
    }

  }

  public static class AsyncProcessor<I extends AsyncIface> extends org.apache.thrift.TBaseAsyncProcessor<I> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncProcessor.class.getName());
    public AsyncProcessor(I iface) {
      super(iface, getProcessMap(new HashMap<String, org.apache.thrift.AsyncProcessFunction<I, ? extends org.apache.thrift.TBase, ?>>()));
    }

    protected AsyncProcessor(I iface, Map<String,  org.apache.thrift.AsyncProcessFunction<I, ? extends  org.apache.thrift.TBase, ?>> processMap) {
      super(iface, getProcessMap(processMap));
    }

    private static <I extends AsyncIface> Map<String,  org.apache.thrift.AsyncProcessFunction<I, ? extends  org.apache.thrift.TBase,?>> getProcessMap(Map<String,  org.apache.thrift.AsyncProcessFunction<I, ? extends  org.apache.thrift.TBase, ?>> processMap) {
      processMap.put("assign", new assign());
      return processMap;
    }

    public static class assign<I extends AsyncIface> extends org.apache.thrift.AsyncProcessFunction<I, assign_args, ClientResult> {
      public assign() {
        super("assign");
      }

      public assign_args getEmptyArgsInstance() {
        return new assign_args();
      }

      public AsyncMethodCallback<ClientResult> getResultHandler(final AsyncFrameBuffer fb, final int seqid) {
        final org.apache.thrift.AsyncProcessFunction fcall = this;
        return new AsyncMethodCallback<ClientResult>() { 
          public void onComplete(ClientResult o) {
            assign_result result = new assign_result();
            result.success = o;
            try {
              fcall.sendResponse(fb,result, org.apache.thrift.protocol.TMessageType.REPLY,seqid);
              return;
            } catch (Exception e) {
              LOGGER.error("Exception writing to internal frame buffer", e);
            }
            fb.close();
          }
          public void onError(Exception e) {
            byte msgType = org.apache.thrift.protocol.TMessageType.REPLY;
            org.apache.thrift.TBase msg;
            assign_result result = new assign_result();
            {
              msgType = org.apache.thrift.protocol.TMessageType.EXCEPTION;
              msg = (org.apache.thrift.TBase)new org.apache.thrift.TApplicationException(org.apache.thrift.TApplicationException.INTERNAL_ERROR, e.getMessage());
            }
            try {
              fcall.sendResponse(fb,msg,msgType,seqid);
              return;
            } catch (Exception ex) {
              LOGGER.error("Exception writing to internal frame buffer", ex);
            }
            fb.close();
          }
        };
      }

      protected boolean isOneway() {
        return false;
      }

      public void start(I iface, assign_args args, org.apache.thrift.async.AsyncMethodCallback<ClientResult> resultHandler) throws TException {
        iface.assign(args.folderAddress, args.mode,resultHandler);
      }
    }

  }

  public static class assign_args implements org.apache.thrift.TBase<assign_args, assign_args._Fields>, java.io.Serializable, Cloneable, Comparable<assign_args>   {
    private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("assign_args");

    private static final org.apache.thrift.protocol.TField FOLDER_ADDRESS_FIELD_DESC = new org.apache.thrift.protocol.TField("folderAddress", org.apache.thrift.protocol.TType.STRING, (short)1);
    private static final org.apache.thrift.protocol.TField MODE_FIELD_DESC = new org.apache.thrift.protocol.TField("mode", org.apache.thrift.protocol.TType.I32, (short)2);

    private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
    static {
      schemes.put(StandardScheme.class, new assign_argsStandardSchemeFactory());
      schemes.put(TupleScheme.class, new assign_argsTupleSchemeFactory());
    }

    public String folderAddress; // required
    public int mode; // required

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements org.apache.thrift.TFieldIdEnum {
      FOLDER_ADDRESS((short)1, "folderAddress"),
      MODE((short)2, "mode");

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
          case 1: // FOLDER_ADDRESS
            return FOLDER_ADDRESS;
          case 2: // MODE
            return MODE;
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
    private static final int __MODE_ISSET_ID = 0;
    private byte __isset_bitfield = 0;
    public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
    static {
      Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
      tmpMap.put(_Fields.FOLDER_ADDRESS, new org.apache.thrift.meta_data.FieldMetaData("folderAddress", org.apache.thrift.TFieldRequirementType.DEFAULT, 
          new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
      tmpMap.put(_Fields.MODE, new org.apache.thrift.meta_data.FieldMetaData("mode", org.apache.thrift.TFieldRequirementType.DEFAULT, 
          new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(assign_args.class, metaDataMap);
    }

    public assign_args() {
    }

    public assign_args(
      String folderAddress,
      int mode)
    {
      this();
      this.folderAddress = folderAddress;
      this.mode = mode;
      setModeIsSet(true);
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public assign_args(assign_args other) {
      __isset_bitfield = other.__isset_bitfield;
      if (other.isSetFolderAddress()) {
        this.folderAddress = other.folderAddress;
      }
      this.mode = other.mode;
    }

    public assign_args deepCopy() {
      return new assign_args(this);
    }

    @Override
    public void clear() {
      this.folderAddress = null;
      setModeIsSet(false);
      this.mode = 0;
    }

    public String getFolderAddress() {
      return this.folderAddress;
    }

    public assign_args setFolderAddress(String folderAddress) {
      this.folderAddress = folderAddress;
      return this;
    }

    public void unsetFolderAddress() {
      this.folderAddress = null;
    }

    /** Returns true if field folderAddress is set (has been assigned a value) and false otherwise */
    public boolean isSetFolderAddress() {
      return this.folderAddress != null;
    }

    public void setFolderAddressIsSet(boolean value) {
      if (!value) {
        this.folderAddress = null;
      }
    }

    public int getMode() {
      return this.mode;
    }

    public assign_args setMode(int mode) {
      this.mode = mode;
      setModeIsSet(true);
      return this;
    }

    public void unsetMode() {
      __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __MODE_ISSET_ID);
    }

    /** Returns true if field mode is set (has been assigned a value) and false otherwise */
    public boolean isSetMode() {
      return EncodingUtils.testBit(__isset_bitfield, __MODE_ISSET_ID);
    }

    public void setModeIsSet(boolean value) {
      __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __MODE_ISSET_ID, value);
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case FOLDER_ADDRESS:
        if (value == null) {
          unsetFolderAddress();
        } else {
          setFolderAddress((String)value);
        }
        break;

      case MODE:
        if (value == null) {
          unsetMode();
        } else {
          setMode((Integer)value);
        }
        break;

      }
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case FOLDER_ADDRESS:
        return getFolderAddress();

      case MODE:
        return getMode();

      }
      throw new IllegalStateException();
    }

    /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      if (field == null) {
        throw new IllegalArgumentException();
      }

      switch (field) {
      case FOLDER_ADDRESS:
        return isSetFolderAddress();
      case MODE:
        return isSetMode();
      }
      throw new IllegalStateException();
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof assign_args)
        return this.equals((assign_args)that);
      return false;
    }

    public boolean equals(assign_args that) {
      if (that == null)
        return false;

      boolean this_present_folderAddress = true && this.isSetFolderAddress();
      boolean that_present_folderAddress = true && that.isSetFolderAddress();
      if (this_present_folderAddress || that_present_folderAddress) {
        if (!(this_present_folderAddress && that_present_folderAddress))
          return false;
        if (!this.folderAddress.equals(that.folderAddress))
          return false;
      }

      boolean this_present_mode = true;
      boolean that_present_mode = true;
      if (this_present_mode || that_present_mode) {
        if (!(this_present_mode && that_present_mode))
          return false;
        if (this.mode != that.mode)
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      List<Object> list = new ArrayList<Object>();

      boolean present_folderAddress = true && (isSetFolderAddress());
      list.add(present_folderAddress);
      if (present_folderAddress)
        list.add(folderAddress);

      boolean present_mode = true;
      list.add(present_mode);
      if (present_mode)
        list.add(mode);

      return list.hashCode();
    }

    @Override
    public int compareTo(assign_args other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;

      lastComparison = Boolean.valueOf(isSetFolderAddress()).compareTo(other.isSetFolderAddress());
      if (lastComparison != 0) {
        return lastComparison;
      }
      if (isSetFolderAddress()) {
        lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.folderAddress, other.folderAddress);
        if (lastComparison != 0) {
          return lastComparison;
        }
      }
      lastComparison = Boolean.valueOf(isSetMode()).compareTo(other.isSetMode());
      if (lastComparison != 0) {
        return lastComparison;
      }
      if (isSetMode()) {
        lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.mode, other.mode);
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
      StringBuilder sb = new StringBuilder("assign_args(");
      boolean first = true;

      sb.append("folderAddress:");
      if (this.folderAddress == null) {
        sb.append("null");
      } else {
        sb.append(this.folderAddress);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("mode:");
      sb.append(this.mode);
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

    private static class assign_argsStandardSchemeFactory implements SchemeFactory {
      public assign_argsStandardScheme getScheme() {
        return new assign_argsStandardScheme();
      }
    }

    private static class assign_argsStandardScheme extends StandardScheme<assign_args> {

      public void read(org.apache.thrift.protocol.TProtocol iprot, assign_args struct) throws org.apache.thrift.TException {
        org.apache.thrift.protocol.TField schemeField;
        iprot.readStructBegin();
        while (true)
        {
          schemeField = iprot.readFieldBegin();
          if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
            break;
          }
          switch (schemeField.id) {
            case 1: // FOLDER_ADDRESS
              if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
                struct.folderAddress = iprot.readString();
                struct.setFolderAddressIsSet(true);
              } else { 
                org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
              }
              break;
            case 2: // MODE
              if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
                struct.mode = iprot.readI32();
                struct.setModeIsSet(true);
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

      public void write(org.apache.thrift.protocol.TProtocol oprot, assign_args struct) throws org.apache.thrift.TException {
        struct.validate();

        oprot.writeStructBegin(STRUCT_DESC);
        if (struct.folderAddress != null) {
          oprot.writeFieldBegin(FOLDER_ADDRESS_FIELD_DESC);
          oprot.writeString(struct.folderAddress);
          oprot.writeFieldEnd();
        }
        oprot.writeFieldBegin(MODE_FIELD_DESC);
        oprot.writeI32(struct.mode);
        oprot.writeFieldEnd();
        oprot.writeFieldStop();
        oprot.writeStructEnd();
      }

    }

    private static class assign_argsTupleSchemeFactory implements SchemeFactory {
      public assign_argsTupleScheme getScheme() {
        return new assign_argsTupleScheme();
      }
    }

    private static class assign_argsTupleScheme extends TupleScheme<assign_args> {

      @Override
      public void write(org.apache.thrift.protocol.TProtocol prot, assign_args struct) throws org.apache.thrift.TException {
        TTupleProtocol oprot = (TTupleProtocol) prot;
        BitSet optionals = new BitSet();
        if (struct.isSetFolderAddress()) {
          optionals.set(0);
        }
        if (struct.isSetMode()) {
          optionals.set(1);
        }
        oprot.writeBitSet(optionals, 2);
        if (struct.isSetFolderAddress()) {
          oprot.writeString(struct.folderAddress);
        }
        if (struct.isSetMode()) {
          oprot.writeI32(struct.mode);
        }
      }

      @Override
      public void read(org.apache.thrift.protocol.TProtocol prot, assign_args struct) throws org.apache.thrift.TException {
        TTupleProtocol iprot = (TTupleProtocol) prot;
        BitSet incoming = iprot.readBitSet(2);
        if (incoming.get(0)) {
          struct.folderAddress = iprot.readString();
          struct.setFolderAddressIsSet(true);
        }
        if (incoming.get(1)) {
          struct.mode = iprot.readI32();
          struct.setModeIsSet(true);
        }
      }
    }

  }

  public static class assign_result implements org.apache.thrift.TBase<assign_result, assign_result._Fields>, java.io.Serializable, Cloneable, Comparable<assign_result>   {
    private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("assign_result");

    private static final org.apache.thrift.protocol.TField SUCCESS_FIELD_DESC = new org.apache.thrift.protocol.TField("success", org.apache.thrift.protocol.TType.STRUCT, (short)0);

    private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
    static {
      schemes.put(StandardScheme.class, new assign_resultStandardSchemeFactory());
      schemes.put(TupleScheme.class, new assign_resultTupleSchemeFactory());
    }

    public ClientResult success; // required

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements org.apache.thrift.TFieldIdEnum {
      SUCCESS((short)0, "success");

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
          case 0: // SUCCESS
            return SUCCESS;
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
    public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
    static {
      Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
      tmpMap.put(_Fields.SUCCESS, new org.apache.thrift.meta_data.FieldMetaData("success", org.apache.thrift.TFieldRequirementType.DEFAULT, 
          new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, ClientResult.class)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(assign_result.class, metaDataMap);
    }

    public assign_result() {
    }

    public assign_result(
      ClientResult success)
    {
      this();
      this.success = success;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public assign_result(assign_result other) {
      if (other.isSetSuccess()) {
        this.success = new ClientResult(other.success);
      }
    }

    public assign_result deepCopy() {
      return new assign_result(this);
    }

    @Override
    public void clear() {
      this.success = null;
    }

    public ClientResult getSuccess() {
      return this.success;
    }

    public assign_result setSuccess(ClientResult success) {
      this.success = success;
      return this;
    }

    public void unsetSuccess() {
      this.success = null;
    }

    /** Returns true if field success is set (has been assigned a value) and false otherwise */
    public boolean isSetSuccess() {
      return this.success != null;
    }

    public void setSuccessIsSet(boolean value) {
      if (!value) {
        this.success = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case SUCCESS:
        if (value == null) {
          unsetSuccess();
        } else {
          setSuccess((ClientResult)value);
        }
        break;

      }
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case SUCCESS:
        return getSuccess();

      }
      throw new IllegalStateException();
    }

    /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      if (field == null) {
        throw new IllegalArgumentException();
      }

      switch (field) {
      case SUCCESS:
        return isSetSuccess();
      }
      throw new IllegalStateException();
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof assign_result)
        return this.equals((assign_result)that);
      return false;
    }

    public boolean equals(assign_result that) {
      if (that == null)
        return false;

      boolean this_present_success = true && this.isSetSuccess();
      boolean that_present_success = true && that.isSetSuccess();
      if (this_present_success || that_present_success) {
        if (!(this_present_success && that_present_success))
          return false;
        if (!this.success.equals(that.success))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      List<Object> list = new ArrayList<Object>();

      boolean present_success = true && (isSetSuccess());
      list.add(present_success);
      if (present_success)
        list.add(success);

      return list.hashCode();
    }

    @Override
    public int compareTo(assign_result other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;

      lastComparison = Boolean.valueOf(isSetSuccess()).compareTo(other.isSetSuccess());
      if (lastComparison != 0) {
        return lastComparison;
      }
      if (isSetSuccess()) {
        lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.success, other.success);
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
      StringBuilder sb = new StringBuilder("assign_result(");
      boolean first = true;

      sb.append("success:");
      if (this.success == null) {
        sb.append("null");
      } else {
        sb.append(this.success);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws org.apache.thrift.TException {
      // check for required fields
      // check for sub-struct validity
      if (success != null) {
        success.validate();
      }
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
        read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
      } catch (org.apache.thrift.TException te) {
        throw new java.io.IOException(te);
      }
    }

    private static class assign_resultStandardSchemeFactory implements SchemeFactory {
      public assign_resultStandardScheme getScheme() {
        return new assign_resultStandardScheme();
      }
    }

    private static class assign_resultStandardScheme extends StandardScheme<assign_result> {

      public void read(org.apache.thrift.protocol.TProtocol iprot, assign_result struct) throws org.apache.thrift.TException {
        org.apache.thrift.protocol.TField schemeField;
        iprot.readStructBegin();
        while (true)
        {
          schemeField = iprot.readFieldBegin();
          if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
            break;
          }
          switch (schemeField.id) {
            case 0: // SUCCESS
              if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
                struct.success = new ClientResult();
                struct.success.read(iprot);
                struct.setSuccessIsSet(true);
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

      public void write(org.apache.thrift.protocol.TProtocol oprot, assign_result struct) throws org.apache.thrift.TException {
        struct.validate();

        oprot.writeStructBegin(STRUCT_DESC);
        if (struct.success != null) {
          oprot.writeFieldBegin(SUCCESS_FIELD_DESC);
          struct.success.write(oprot);
          oprot.writeFieldEnd();
        }
        oprot.writeFieldStop();
        oprot.writeStructEnd();
      }

    }

    private static class assign_resultTupleSchemeFactory implements SchemeFactory {
      public assign_resultTupleScheme getScheme() {
        return new assign_resultTupleScheme();
      }
    }

    private static class assign_resultTupleScheme extends TupleScheme<assign_result> {

      @Override
      public void write(org.apache.thrift.protocol.TProtocol prot, assign_result struct) throws org.apache.thrift.TException {
        TTupleProtocol oprot = (TTupleProtocol) prot;
        BitSet optionals = new BitSet();
        if (struct.isSetSuccess()) {
          optionals.set(0);
        }
        oprot.writeBitSet(optionals, 1);
        if (struct.isSetSuccess()) {
          struct.success.write(oprot);
        }
      }

      @Override
      public void read(org.apache.thrift.protocol.TProtocol prot, assign_result struct) throws org.apache.thrift.TException {
        TTupleProtocol iprot = (TTupleProtocol) prot;
        BitSet incoming = iprot.readBitSet(1);
        if (incoming.get(0)) {
          struct.success = new ClientResult();
          struct.success.read(iprot);
          struct.setSuccessIsSet(true);
        }
      }
    }

  }

}
