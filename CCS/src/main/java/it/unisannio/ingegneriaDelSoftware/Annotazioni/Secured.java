package it.unisannio.ingegneriaDelSoftware.Annotazioni;

import javax.ws.rs.NameBinding;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

//Filters and interceptors can be name-bound.
//Name binding is a concept that allows to say to a JAX-RS runtime that a
//specific filter or interceptor will be executed only for a specific resource method
//When a filter or an interceptor is limited only to a specific resource method we say that it is name-bound.
// Filters and interceptors that do not have such a limitation are called global.
/**una annotazione name binding*/
@NameBinding
@Retention(RUNTIME)
@Target({TYPE, METHOD})
public @interface Secured {}