# Item11: Always override `hashCode`when you override `equals`

## Neden? 

`equal` olan iki objenin `hashCode`'ları da aynı değer olmalı. 
Eğer aynı olmazsa hashCode için olan *general contract*ı bozmuş oluyorsun. 
HashCode'un general contract'ı bozarsan yazdığın `class` `collection`larda düzgün çalışmayacaktır.

### General Contract for `hashCode`

1. When the hashCode method is invoked on an object repeatedly during an execution of an application, it must consistently return the same value, provided no information used in equals comparisons is modified. 
2. If two objects are equal according to the equals (Object) method,then calling hashCode on the two objects must produce the same integer result.
3. be aware that producing distinct results for unequal objects may improve the performance of hash tables

Kodunun düzgün çalışmasını engelleyen şey 2. madde.

Örnek: 

```java
       Map<PhoneNumber, String> m = new HashMap<>();
       m.put(new PhoneNumber(707, 867, 5309), "Jenny");
```

Yukarıdaki örnekte `PhoneNumber` classında tamamen doğru bir equals metodu yazıldığını varsayalım.

`m.get(new PhoneNumber(707, 867, 5309));` put edilen ve get edilen iki obje de `equal` olduğu için
`"Jenny"` değerine ulaşmayı bekleriz ama fakat ulaşamayız. 

Sebebi `HashMap`'lerin `hashCode`'u aynı olmayan iki `key` için `object equality`'e hiç bakmaması. (optimizasyon)

---

### En kötü hashCode override'ı (ASLA KULLANMA)

```java
    @Override public int hashCode() { return 42; }
```
HashTable'lar, tüm hashCode'lar aynı olduğunda, yapısı bozulup linkedlist'lere dönüşür 
ve lineer time'da çalışması gereken programlar quadratic time'larda çalışmaya başlar.

### İyi bir `hashCode` override'ı `equal` olmayan tüm instance'lar için farklı hashCode üretir.

Yalnızca `equals` method'unda eşitliğini kontrol ettiğin tüm field'ları hashCode hesaplamada kullanmalısın.

Örnek: bkz Effective Java 3rd Edition Page 73

```java
    // Typical hashCode method
    // 31 is selected because it is an odd prime
   @Override public int hashCode() {
       int result = Short.hashCode(areaCode);
       result = 31 * result + Short.hashCode(prefix);
       result = 31 * result + Short.hashCode(lineNum);
       return result;
}

```
Not: Performans kritik değilse hashCode üretmek `Objects.hash(...)` kullanabilirsin.

Not: HashCode oluşturma yöntemini client'lara açıklama böylece istemizce bir bağımlılık yaratmamış olursun

Not: `equals` override etmek için kullanılan AutoValue framework'u burada da kullanılabilir.

Not: IDE'lerde equals ve hashCode override etmek için kullanılabilir.