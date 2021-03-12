package com.curso.suite;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.ExcludeCategory;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import com.curso.inter.NegativeInterface;
import com.curso.inter.PositiveInterface;
import com.curso.test.CPFCNPJTest;
import com.curso.test.NavigationCacheTest;
import com.curso.test.NavigationTabsTest;
import com.curso.test.WebElementsTest;

@RunWith(Categories.class)
@SuiteClasses({WebElementsTest.class,
				CPFCNPJTest.class,
				NavigationCacheTest.class,
				NavigationTabsTest.class})
@ExcludeCategory(NegativeInterface.class)
@IncludeCategory({PositiveInterface.class, NegativeInterface.class})
public class PositiveSuite {

}
