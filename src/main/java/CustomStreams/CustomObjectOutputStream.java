package CustomStreams;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class CustomObjectOutputStream extends ObjectOutputStream {

    public CustomObjectOutputStream() throws IOException{
        super();
    }

    public CustomObjectOutputStream(OutputStream o) throws IOException{
        super(o);
    }

    public void writeStreamHeader() throws IOException{
        return;
    }
}
