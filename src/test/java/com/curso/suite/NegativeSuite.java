package com.curso.suite;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import com.curso.inter.NegativeInterface;
import com.curso.test.CPFCNPJTest;
import com.curso.test.NavigationCacheTest;
import com.curso.test.NavigationTabsTest;
import com.curso.test.WebElementsTest;

@RunWith(Categories.class)
@SuiteClasses({WebElementsTest.class,
				CPFCNPJTest.class,
				NavigationCacheTest.class,
				NavigationTabsTest.class})
@IncludeCategory( NegativeInterface.class)
public class NegativeSuite {

}
