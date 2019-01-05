import java.io.*;

/**
 * http://www.importnew.com/29878.html
 */
public class bestPrectice {

    /**
     * 1. 在Finally块中清理资源或者使用jdk7的Try-With-Resource语句
     */
    public void closeResourceInFinally() {
        FileInputStream fis = null;
        try {
            File file = new File("./tmp.txt");
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Java7 引入了try-with-resource语句，它会自动关闭实现了AutoCloseable接口的
     * 所有资源
     * https://stackify.com/specify-handle-exceptions-java/#tryWithResource
     */
    public void automaticallyCloseResource() {
        File file = new File("./tmp.txt");
        try (FileInputStream inputStream = new FileInputStream(file);) {
            // use the inputStream to read a file
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 2. 什么时候应该抛出异常：
     * Java异常处理原则之一：延迟捕获
     * 当异常发生时，应该考虑当前作用域是否有能力处理这一异常的能力，如果没有，则应该将异常向上抛出，交由更上层的作用域去处理
     *
     * 以下的例子中会抛出FileNotFoundException，由于方法可能会在不同的场景下被不同的代码调用，在这些场景中，对于“文件未找到”的情况
     * 的处理逻辑可能不同，这样的情况下，在readFile的方法内的作用域中处理不了这个异常，需要抛出，交由具备处理这个异常的能力的作用域来处理
     *
     *
     * https://www.zhihu.com/question/25530011
     */
    public String readFile(String fileName) throws FileNotFoundException,IOException {
        FileInputStream fis = new FileInputStream(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }


    /**
     * 3. 最先捕获特定的异常
     */
    public void catchMostSpecificExceptionFirst(){
        try {
            new Long("aaa");
        }catch (NumberFormatException e){
            e.printStackTrace();
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }
    }

    /**
     * 4. 不要捕获Throwable
     */
    public void doNotCatchThrowable(){
        try {
            // do something
        }catch (Throwable t){
            //don't do this
        }
    }

    /**
     * 5. 不要忽略异常
     */
    public void doNotIgnoreExceptions() {
        try {
            // do something
        } catch (NumberFormatException e) {
            // this will never happen
        }
    }

    /**
     * 6.不要
     */

}
