package decorator;
import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

//只需要 补充完成 LowerCaseStringReader 类，且仅需提交 LowerCaseStringReader 类即可

class LowerCaseStringReader extends FilterReader {

    Reader in;

    protected LowerCaseStringReader(Reader in) {
        super(in);
        this.in = in;
    }

    @Override
    public int read() throws IOException {
        int c = in.read();
        if (c > 0) {
            return Character.toLowerCase((char) c);
        }
        return c;
    }
}

public class IODec {
    public static void main(String[] args) throws IOException {
        LowerCaseStringReader r = new LowerCaseStringReader(new StringReader("Design Pattern"));
        int c;
        while ((c = r.read()) > 0) {
            System.out.print((char) c);
        }
    }
}
