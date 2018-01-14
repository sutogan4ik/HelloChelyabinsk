package ru.brucha.hellochelyabinsk.backend.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by prog on 14.01.2018.
 */
@Scope
@Retention(RUNTIME)
public @interface FragmentScope {
}
