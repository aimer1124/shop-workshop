package macdao;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class HelloTest {

    @Test
    public void test_hello() throws Exception {
        assertThat(new Hello().hello(), is("hello, world"));
    }
}
