import kotlin.reflect.KClass
import kotlin.reflect.full.declaredMemberExtensionProperties
import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.primaryConstructor

/*
Reflection
 - 객체의 Type 을 알아보는 방법
 */

fun main() {
    // Class Type
    val a1:KClass<String> = String::class
    val a2:Class<String> = String::class.java

    println("String Kotlin 에서의 Type : $a1")
    println("String Java 에서의 Type : $a2")

    /*
    변수에 담겨있는 객체를 갖고 Type 을 확인하고자 하는 경우에는
    부모 Class 형으로 Generic 을 변환하는 out Keyword 를 입력해야 합니다.
     */
    val str1:String = "안녕하세요."
    val a3:KClass<out String> = str1::class
    val a4:Class<out String> = str1::class.java

    println("안녕하세요. Kotlin 에서의 Type : $a3")
    println("안녕하세요. Java 에서의 Type : $a4")

    val a5:KClass<*> = str1::class
    val a6:Class<*> = str1::class.java
    println("안녕하세요. Kotlin 에서의 Type : $a5")
    println("안녕하세요. Java 에서의 Type : $a6")

    val test1:TestClass = TestClass()
    val a7:KClass<*> = test1::class
    val a8:Class<*> = test1::class.java
    println("test1 코틀린에서의 타입 : $a7")
    println("test1 자바에서의 타입 : $a8")

    // 클래스 정보 분석
    println("추상 클래스 인가 : ${test1::class.isAbstract}")
    println("Companion 클래스 인가 : ${test1::class.isCompanion}")
    println("Data 클래스 인가 : ${test1::class.isData}")
    println("final 클래스 인가 : ${test1::class.isFinal}")
    println("open 클래스 인가 : ${test1::class.isOpen}")
    println("중첩 클래스 인가 : ${test1::class.isInner}")
    println("Sealed 클래스 인가 : ${test1::class.isSealed}")

    // 생성자 정보
    val constructors = test1::class.constructors

    println(constructors)

    for(con in constructors){
        println("constructor : $con")

        for (param in con.parameters){
            println("parameter index : ${param.index}")
            println("parameter type : ${param.type}")
            println("parameter name : ${param.name}")
        }
    }

    println("----- 주생성자 -----")

    // 주생성자 정보
    val primaryCon = test1::class.primaryConstructor
    println(primaryCon) // 출력값 : fun <init>(): TestClass
    if (primaryCon != null) {
        println(primaryCon)

        for (param in primaryCon.parameters) { // for 문 조건식 구조 : in 뒤에 있는 것들을 순서 대로 in 앞에 대입하겠습니다.
            println("parameter index : ${param.index}")
            println("parameter index : ${param.type}")
            println("parameter index : ${param.name}")
        }
    }

    println("----- Property -----")

    // Property : Class 내부 Property 정보 접근 가능
    val properties = test1::class.declaredMemberProperties
    for (prop in properties) {
        println(prop.name)
    }

    println("----- Method -----")

    // Method 정보 : Class 내부 Method 정보 접근 가능
    val method = test1::class.declaredMemberFunctions
    for (met in method) {
        println(met.name)
    }
}

class TestClass(){

    val number1:Int = 100
    var number2:Int = 200

    constructor(a1:Int) : this(){

    }

    constructor(a1:Int, a2:Int) : this(){

    }

    fun testMethod1(){

    }
}









