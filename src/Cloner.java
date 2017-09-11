import java.io.*;

public class Cloner {
	
	private Cloner(){
		
	}
	
	static public Object deepCopy(Object object) throws Exception{
		
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		
		try{
			
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(bos);
			oos.writeObject(object);
			oos.flush();
			
			ByteArrayInputStream bin = new ByteArrayInputStream(bos.toByteArray());
			ois = new ObjectInputStream(bin);
			
			return ois.readObject();
					
		}catch(Exception e){
			System.out.println("Exception during cloning: " + e);
		}
		finally{
			oos.close();
			ois.close();
		}
		return null;
	}
}
