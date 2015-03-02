package spi_impl_two;

import spi_base.PlugIn;

/**
 * @author stuart-hostler
 * @since 10/10/14.
 */
public class SpiImplTwo implements PlugIn {
    private static String hello = "Hello, I'm number two.";

    @Override
    public String helloWorld() {
        return hello;
    }
}
