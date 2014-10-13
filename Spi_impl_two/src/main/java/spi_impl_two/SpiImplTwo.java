package spi_impl_two;

import spi_base.BaseClass;

/**
 * @author stuart-hostler
 * @since 10/10/14.
 */
public class SpiImplTwo implements BaseClass{
    private static String hello = "Hello, I'm number two.";

    @Override
    public String sayHello() {
        return hello;
    }
}
