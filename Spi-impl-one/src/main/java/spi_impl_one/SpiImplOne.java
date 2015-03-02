package spi_impl_one;

import spi_base.PlugIn;

/**
 * @author stuart-hostler
 * @since 10/10/14.
 */
public class SpiImplOne implements PlugIn {
    private static String hello = "Hello, I'm number one.";

    public SpiImplOne() {
    }

    @Override
    public String helloWorld() {
        return hello;
    }
}
