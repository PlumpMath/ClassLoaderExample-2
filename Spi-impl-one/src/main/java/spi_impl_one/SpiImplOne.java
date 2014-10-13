package spi_impl_one;

import spi_base.BaseClass;

/**
 * @author stuart-hostler
 * @since 10/10/14.
 */
public class SpiImplOne implements BaseClass{
    private static String hello = "Hello, I'm number one.";

    public SpiImplOne() {
    }

    @Override
    public String sayHello() {
        return hello;
    }
}
