package spi_base;

import java.io.File;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

/**
 * @author stuart-hostler
 * @since 10/10/14.
 */
public class SpiMain {

    private static final String PROPERTIES_FILE_NAME = "spiimpl.properties";
    private static final String CLASS_NAME_PROPERTY = "implName";

    /**
     *  Using a ClassLoader to discover/create implementations of an interface at runtime.
     *
     *  SpiImplOne and SpiImplTwo implement the BaseClass interface.  They should be compiled into separate jars...
     *  containing only the class file and the spiimpl.properties [which contains only the name of the class to load]
     *
     * @param args [0] directory to search for jars. This should not be on the class path, or the call to getResources
     *             will return duplicates (one for the local class loader, and one for it's parents)
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{

        String dynamicLoadDir = ((args != null) && (args.length > 0)) ? args[0] : "../dynamicLoadJars";

        while(true) {
            System.out.println("............");
            Thread.sleep(5000);
            //get all the jar files
            List<URL> jars = getAllTheFilesInADirectory(dynamicLoadDir);

            //URLClassLoader loader = new URLClassLoader(jars.toArray(new URL[jars.size()]), null); //do not search parent class loader.
            URLClassLoader loader = new URLClassLoader(jars.toArray(new URL[jars.size()]));
            Enumeration<URL> en = loader.getResources(PROPERTIES_FILE_NAME);

            //make a list of these classes
            List<PlugIn> plugIns = new ArrayList<PlugIn>();

            while (en.hasMoreElements()) {
                URL url = en.nextElement();

                JarURLConnection urlcon = (JarURLConnection) (url.openConnection());
                //JarFile jar = urlcon.getJarFile();

                //get the properties file
                Properties p = new Properties();
                p.load(url.openStream());

                Class tempClass = loader.loadClass(p.getProperty(CLASS_NAME_PROPERTY));
                plugIns.add((PlugIn) tempClass.newInstance());

            }

            //call the routines
            for (PlugIn bc : plugIns) {
                System.out.println(bc.helloWorld());
            }

        }
    }

    private static List<URL> getAllTheFilesInADirectory(String directoryPath) throws MalformedURLException {
        List<URL> urls = new ArrayList<URL>();


        File[] files = new File(directoryPath).listFiles();
        //If this pathname does not denote a directory, then listFiles() returns null.
        if(files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    urls.add(file.toURI().toURL());
                }
            }
        }
        return urls;
    }
}


