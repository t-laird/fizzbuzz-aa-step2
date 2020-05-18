package Utilities

import Constants.FizzBuzzResults
import Exeptions.FizzBuzzException
import spock.lang.Specification
import spock.lang.Unroll

class FizzBuzzUtilTest extends Specification {

    def "Validates Range Inputs - Valid Case"() {
        given:
        def start = 1
        def end = 100

        when:
        FizzBuzzUtil.validateInputs(start, end)

        then:
        noExceptionThrown()
    }

    def "Validates Range Inputs - Invalid Case"() {
        given:
        def start = 5
        def end = 1

        when:
        FizzBuzzUtil.validateInputs(start, end)

        then:
        def exc = thrown(FizzBuzzException)
        exc.message == "Invalid Range"
    }

    @Unroll
    def "createRange - #start - #end"() {
        when:
        def res = FizzBuzzUtil.createRange(start, end)

        then:
        res == expectation

        where:
        start   |   end   |   expectation
        1       |   3     |   [1, 2, 3]
        8       |   14    |   [8, 9, 10, 11, 12, 13, 14]
    }

    @Unroll
    def "getFizzBuzzStringForNum - #num"() {
        when:
        def fbString = FizzBuzzUtil.getFizzBuzzStringForNum(num)

        then:
        fbString == expectation

        where:
        num   |  expectation
        3     |  FizzBuzzResults.LUCKY
        5     |  FizzBuzzResults.BUZZ
        15    |  FizzBuzzResults.FIZZBUZZ
        23    |  FizzBuzzResults.LUCKY
        6     |  FizzBuzzResults.FIZZ
        10    |  FizzBuzzResults.BUZZ
        30    |  FizzBuzzResults.LUCKY
        37    |  FizzBuzzResults.LUCKY
        41    |  "41"
    }

    @Unroll
    def "isLucky - #num"() {
        when:
        def isLucky = FizzBuzzUtil.isLucky(num)

        then:
        isLucky == expectation

        where:
        num   |  expectation
        3     |  true
        4     |  false
        13    |  true
        39    |  true
        1003  |  true
    }
}
