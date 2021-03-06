# DCL-210: Advanced Java SE Programming

These projects are created as part of the following training: DCL-210 "Advanced Java SE Programming"

Please follow the link for the complete training catalog: https://www.deepcloudlabs.com/resources

Kurulum Bilgisi:
========================================
Eğitimde lab çalışmaları için gerekli olan çalışma ortamının kurulumu için öncelikle aşağıdaki bağlantıda yer alan sıkıştırılmış dosyayı makinanıza indirmeniz gerekiyor: https://courseware.deepcloudlabs.com/software/DEVEL-stage-2022a-java.se.and.spring.zip

Sıkıştırılmış dosyayı **C:\\** dizinine açtıktan sonra dizin yapısı aşağıda gösterildiği şekilde olacaktır:

![Installation folder](DEVEL-stage.png?raw=true "C: drive after DEVEL-stage-2022a-java.se.and.spring.zip")

Diskinizdeki dizin yapısını yukarıdaki ile karşılaştırarak kontrol ediniz. **C:** sürücünüzün dolu dolması durumunda farklı bir sürücüye sıkıştırılmış dosyayı açabilirsiniz. Ancak bu durumda bir kaç konfigürasyon dosyasında değişiklik yapmanız gerekecektir. Lütfen, eğitim sırasında bu değişikliklerin neler olduğunu eğitmeninize sorunuz. 

Notes Day #1:
========================================
1. JIT Compiler -> Client VM (C1), Server VM (C2)

   since Java 7 -> Tiered Compiler 
    
    There are 5 Tier
                   3 Tier -> C1
                   4th Tier -> C2
                   
   -XX:+TieredCompilation
   -XX:-TieredCompilation
   
2. Garbage Collector
   G1GC (Garbage First) -> default Since Java E 9
3. Class Loader 
   Circle.java  -> Circle.class (binary -> byte-code)
   Lazy Loading 
   JDK -> Class Sharing -> Startup time 
JVM Ergonomics -> default values   
