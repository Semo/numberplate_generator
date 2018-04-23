/*
 * Copyright (c) 2018.  semo
 */

import main.NumberplateClientCommands;
import org.assertj.core.api.Assertions;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.shell.ConfigurableCommandRegistry;
import org.springframework.shell.MethodTarget;
import org.springframework.shell.standard.StandardMethodTargetRegistrar;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.ReflectionUtils;

import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@DisplayName("Testing Spring-Shell numberplate commands")
@RunWith(SpringJUnit4ClassRunner.class)
class NumberplateClientCommandsTest {

    private StandardMethodTargetRegistrar registrar = new StandardMethodTargetRegistrar();
    private ConfigurableCommandRegistry registry = new ConfigurableCommandRegistry();
    private Map<String, MethodTarget> commands;

    @BeforeEach
    void setUp() {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(NumberplateClientCommands.class);
        registrar.setApplicationContext(context);
        registrar.register(registry);
    }

    @DisplayName("Should return the sum of two values")
    @Test
    void shouldAddTwoIntegers() {
        commands = registry.listCommands();

        MethodTarget methodTarget = commands.get("sum");

        assertThat(methodTarget, notNullValue());
        Assertions.assertThat(methodTarget.getGroup()).isEqualTo(
                "Numberplate Client Command");
        assertThat(methodTarget.getHelp(), Is.is("Add up to sum."));
        assertThat(methodTarget.getMethod(), is(
                ReflectionUtils.findMethod(NumberplateClientCommands.class, "sum", int.class,
                        int.class)));
        assertThat(methodTarget.getAvailability().isAvailable(), is(true));
        assertEquals(3, ReflectionUtils.invokeMethod(methodTarget.getMethod(),
                methodTarget.getBean(), 1, 2));
    }

    @DisplayName("should say \"hi\" to a given person's name.")
    @Test
    void shouldSayHi() {
        commands = registry.listCommands();
        MethodTarget methodTarget = commands.get("say-hi");

        assertThat(methodTarget, notNullValue());
        Assertions.assertThat(methodTarget.getGroup()).isEqualTo(
                "Numberplate Client Command");
        assertThat(methodTarget.getHelp(), Is.is("Saying hi to a given person's name."));
        assertThat(methodTarget.getMethod(), is(
                ReflectionUtils.findMethod(NumberplateClientCommands.class, "sayHi", String.class)));
        assertThat(methodTarget.getAvailability().isAvailable(), is(true));
        assertEquals("Hi, Nadine", ReflectionUtils.invokeMethod(methodTarget.getMethod(),
                methodTarget.getBean(), "Nadine"));
    }


}
