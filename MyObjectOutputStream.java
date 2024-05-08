import java.io.*;
/**
 * MyObjectOutputStream
 */
class MyObjectOutputStream  extends ObjectOutputStream{

    public MyObjectOutputStream(OutputStream out) throws IOException {
        super(out);
    }

    public void writeStreamHeader() throws IOException{
        return;
    }
}

    
