package org.apache.jdo.exectck;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.CharBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import javax.jdo.JDOException;

public class Utilities {

    Utilities() {
    }
    
    public static final String DATE_FORMAT_NOW = "yyyyMMdd-HHmmss";

    /*
     * Return the current date/time as a String.
     */
    public static String now() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(cal.getTime());
    }

    /*
     * From an array of URLs, create a classpath String suitable for use
     *   as a command line argument
     */
    public static String urls2ClasspathString(ArrayList<URL> urls) {
        StringBuffer cp = new StringBuffer();

        for (URL url: urls) {
            cp.append(url.getPath());
            cp.append(File.pathSeparator);
        }
        return cp.toString();
    }

    public static void printClasspath(ClassLoader loader) {

        //Get the URLs
        URL[] urls = ((URLClassLoader) loader).getURLs();

        System.out.println(urls.length + " URL(s) for loader: ");
        for (int i = 0; i < urls.length; i++) {
            System.out.println("    " + urls[i].getFile());
        }

//        //Get the Context Classloader
//        loader = Thread.currentThread().getContextClassLoader();
//
//        //Get the URLs
//        urls = ((URLClassLoader)loader).getURLs();
//
//        System.out.println(urls.length + "URLs for loader: ");
//        for(int i=0; i< urls.length; i++)
//        {
//            System.out.println("    " + urls[i].getFile());
//        }

    }

    public void printClasspath() {

        //Get the System Classloader
        ClassLoader loader = ClassLoader.getSystemClassLoader();

        //Get the URLs
        URL[] urls = ((URLClassLoader) loader).getURLs();

        for (int i = 0; i < urls.length; i++) {
            System.out.println("    " + urls[i].getFile());
        }

        //Get the System Classloader
        loader = Thread.currentThread().getContextClassLoader();

        //Get the URLs
        urls = ((URLClassLoader) loader).getURLs();

        for (int i = 0; i < urls.length; i++) {
            System.out.println("    " + urls[i].getFile());
        }

    }

//    public static String convertStreamToString(InputStream is)
//            throws IOException {
//        /*
//         * To convert the InputStream to String we use the
//         * Reader.read(char[] buffer) method. We iterate until the
//         * Reader return -1 which means there's no more data to
//         * read. We use the StringWriter class to produce the string.
//         */
//        if (is != null) {
//            Writer writer = new StringWriter();
//
//            char[] buffer = new char[1024];
//            try {
//                Reader reader = new BufferedReader(
//                        new InputStreamReader(is, "UTF-8"));
//                int n;
//                while ((n = reader.read(buffer)) != -1) {
//                    writer.write(buffer, 0, n);
//                }
//            } finally {
//                is.close();
//            }
//            return writer.toString();
//        } else {
//            return "";
//        }
//    }
    InvocationResult invokeTest(List command) {
        InvocationResult result = new InvocationResult();
        try {
            ProcessBuilder builder = new ProcessBuilder(command);
            Map<String, String> env = builder.environment();
            Process proc = builder.start();
            InputStream stdout = proc.getInputStream();
            InputStream stderr = proc.getErrorStream();
            CharBuffer outBuffer = CharBuffer.allocate(1000000);
            CharBuffer errBuffer = CharBuffer.allocate(1000000);
            Thread outputThread = createReaderThread(stdout, outBuffer);
            Thread errorThread = createReaderThread(stderr, errBuffer);
            int exitValue = proc.waitFor();
            result.setExitValue(exitValue);
            errorThread.join(10000); // wait ten seconds to get stderr after process terminates
            outputThread.join(10000); // wait ten seconds to get stdout after process terminates
            result.setErrorString(errBuffer.toString());
            result.setOutputString(outBuffer.toString());
            // wait until the Enhancer command finishes
        } catch (InterruptedException ex) {
            throw new RuntimeException("InterruptedException", ex);
        } catch (IOException ex) {
            throw new RuntimeException("IOException", ex);
        } catch (JDOException jdoex) {
            jdoex.printStackTrace();
            Throwable[] throwables = jdoex.getNestedExceptions();
            System.out.println("Exception throwables of size: " + throwables.length);
            for (Throwable throwable: throwables) {
                throwable.printStackTrace();
            }
        }
        return result;
    }

    private Thread createReaderThread(final InputStream input, final CharBuffer output) {
        final Reader reader = new InputStreamReader(input);
        Thread thread = new Thread(
                new Runnable() {
                    public void run() {
                        int count = 0;
                        int outputBytesRead = 0;
                        try {
                            while (-1 != (outputBytesRead = reader.read(output))) {
                                count += outputBytesRead;
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            output.flip();
                        }
                    }
                });
        thread.start();
        return thread;
    }


    class InvocationResult {
        private int exitValue;
        private String errorString;
        private String outputString;

        int getExitValue() {
            return exitValue;
        }

        private void setExitValue(int exitValue) {
            this.exitValue = exitValue;
        }

        private void setErrorString(String errorString) {
            this.errorString = errorString;
        }

        String getErrorString() {
            return errorString;
        }

        private void setOutputString(String outputString) {
            this.outputString = outputString;
        }

        String getOutputString() {
            return outputString;
        }

    }
}
