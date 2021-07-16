package com.example.breakingbad_codetest

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    NavigationTest::class,
    CharacterList_FragmentTest::class
)
class AllTestsSuite