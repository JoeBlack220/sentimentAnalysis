mport org.apache.thrift.TException;
import java.util.*;

public class MapHandler implements Map.Iface
{
	Map<String,String> storage =new HashMap<String,String>();        
	
	@Override
        public boolean accept() throws TException {
		System.out.println("I got accept()");
		return true;
		}

        @Override
        public MapResult mapping(String fileUri) throws TException {
		storage.put(key,value);		
		System.out.println("I got put\n");
                return true;
        }
        
        @Override
        public String get(String key) throws TException {
		System.out.printf("I got get  -> key: %s value: %s\n", key, storage.get(key));
                return storage.get(key);
        }
}

